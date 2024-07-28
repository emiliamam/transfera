package com.example.transfer.helper;


import com.example.transfer.R;

import java.util.HashMap;
import java.util.Map;

public class BankLogoProvider {
    private static final Map<String, Integer> bankLogoMap = new HashMap<>();

    static {
        bankLogoMap.put("Райфайзен", R.drawable.logoraif);
        bankLogoMap.put("Сбербанк", R.drawable.logosber);
        bankLogoMap.put("Тинькофф", R.drawable.logotbank);
        bankLogoMap.put("Уралсиб", R.drawable.logouralsib);
    }

    public static Integer getLogoResource(String bank) {
        return bankLogoMap.get(bank);
    }
}

