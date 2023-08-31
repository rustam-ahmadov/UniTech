package com.rustam.unitech.service.external;

import com.rustam.unitech.dto.request.CurrencyRateRequest;
import com.rustam.unitech.dto.response.CurrencyRateResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ExternalCurrencyRatesApi {

    //Just for example
    private static Map<String, Double> rates = new HashMap<>();
    private static LocalDateTime updatedTime;

    static {
        rates.put("GBP", 2.1630);
        rates.put("EUR", 1.8561);
        rates.put("USD", 1.7);
        rates.put("TRY", 0.0636);
        rates.put("UAH", 0.0460);
        rates.put("RUB", 0.0176);
        updatedTime = LocalDateTime.now();
    }

    @Scheduled(fixedDelay = 1000 * 60)
    private void updateRates() {
        rates.forEach(
                (key, value) -> {
                    double bound = 0.001;
                    if (key.equals("GBP") || key.equals("EUR") || key.equals("USD"))
                        bound = 0.01;
                    value = round(new Random().nextDouble(value - bound, value + bound));
                    rates.put(key, value);
                }
        );
        updatedTime = LocalDateTime.now();
    }

    public LocalDateTime getUpdatedTime(){
        return updatedTime;
    }



    public Map<String, Double> getRates() {
        return rates;
    }
    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
