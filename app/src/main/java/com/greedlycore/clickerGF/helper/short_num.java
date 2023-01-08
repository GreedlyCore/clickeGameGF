package com.greedlycore.clickerGF.helper;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class short_num {
//https://stackoverflow.com/questions/4753251/how-to-go-about-formatting-1200-to-1-2k-in-java
    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {


        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "B");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    public static String to_text(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        //
        if (value == Long.MIN_VALUE) return to_text(Long.MIN_VALUE + 1);
        // отриц числа
        if (value < 0) return "-" + to_text(-value);
        //
        if (value < 1000) return Long.toString(value); //deal with easy case

//      Map.Entry<K, V> floorEntry(K obj): возвращает элемент с наибольшим ключом k, который меньше или равен ключу obj (k <=obj). Если такого ключа нет, то возвращается null.
        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        //10d = 10 double
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        //? = if statement
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;

    }
}
