package com.example.transfer.helper;

import com.example.transfer.R;

import java.util.HashMap;
import java.util.Map;

public class StatusLogoProvider {
    private static final Map<String, Integer> bankLogoMap = new HashMap<>();

    static {
        bankLogoMap.put("succes", R.drawable.logosucces);
        bankLogoMap.put("cancel", R.drawable.logocancel);
        bankLogoMap.put("wait", R.drawable.logowait);
    }

    public static Integer getLogoResource(String status) {
        return bankLogoMap.get(status);
    }
}
