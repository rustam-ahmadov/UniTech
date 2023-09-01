package com.rustam.unitech.service;

import com.rustam.unitech.dto.request.CurrencyRateRequest;
import com.rustam.unitech.dto.response.CurrencyRateResponse;
import com.rustam.unitech.service.external.ExternalCurrencyRatesApi;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyRatesService {
    private final ExternalCurrencyRatesApi externalApi;
    private static Map<String, Double> rates = new HashMap<>();
    private static LocalDateTime ratesRequestedTime;

    public CurrencyRatesService(ExternalCurrencyRatesApi externalApi) {
        this.externalApi = externalApi;
        rates = externalApi.getRates();
        ratesRequestedTime = externalApi.getUpdatedTime();
    }


    public CurrencyRateResponse get(CurrencyRateRequest request) {
        if (!isRatesUpToDate())
            updateRates();
        return getRatePair(request);
    }


    //region private methods
    private void updateRates() {
        rates = externalApi.getRates();
        ratesRequestedTime = externalApi.getUpdatedTime();
    }

    private boolean isRatesUpToDate() {
        //just for demo
        LocalDateTime updatedTime = externalApi.getUpdatedTime();
        int updDay = updatedTime.getDayOfYear();
        int updHour = updatedTime.getHour();
        int updMin = updatedTime.getMinute();


        int reqDay = ratesRequestedTime.getDayOfYear();
        int reqHour = ratesRequestedTime.getHour();
        int reqMin = ratesRequestedTime.getMinute();

        boolean isUptoDate = true;
        //at least one day earlier
        if (reqDay < updDay)
            isUptoDate = false;

        //same day but at least one hour ago
        if (reqHour < updHour)
            isUptoDate = false;

        //same day and hour but at least one min ago
        if (reqMin < updMin)
            isUptoDate = false;

        return isUptoDate;
    }

    private double getRateToAZN(String source, double amount) {
        return rates.get(source) * amount;
    }

    private double getRateSourceToTarget(String source, String target, double amount) {
        //example: azn to usd
        if (source.equalsIgnoreCase("AZN"))
            return amount / rates.get(target);

        //example: usd to eur
        return (rates.get(source) * amount) / rates.get(target);
    }

    private CurrencyRateResponse getRatePair(CurrencyRateRequest currencyReteRequest) {
        final double amount = currencyReteRequest.getAmount();
        final String source = currencyReteRequest.getSource().toUpperCase();
        final String target = currencyReteRequest.getTarget().map(String::toUpperCase).orElse("AZN");

        double convertedAmount = 0.0;

        if (target.equals("AZN"))
            convertedAmount = getRateToAZN(source, amount);
        else
            convertedAmount = getRateSourceToTarget(source, target, amount);

        return new CurrencyRateResponse(round(convertedAmount), target);
    }

    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    //endregion
}
