package com.example.demo.utils;

public class StringUtils {
    public static boolean isBlankString(String string) {
        return string == null || string.isBlank();
    }

    public static Double formataStringRealParaDouble(String string) {
        String formatted = string.replace("R$ ", "")
                .replace(".", "")
                .replace(",", ".");
        return Double.parseDouble(formatted);
    }
}
