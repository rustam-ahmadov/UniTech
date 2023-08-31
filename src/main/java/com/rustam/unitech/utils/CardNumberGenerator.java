package com.rustam.unitech.utils;

import org.springframework.stereotype.Component;

import java.util.Random;


public class CardNumberGenerator {
    //Just for example using leocard first 8 digits
    private static final String PREFIX = "40985844";

    public static String generate() {
        Random rand = new Random();
        StringBuilder card = new StringBuilder(PREFIX);

        for (int i = 0; i < 8; i++)
            card.append(rand.nextInt(10));

        return card.toString();
    }
}
