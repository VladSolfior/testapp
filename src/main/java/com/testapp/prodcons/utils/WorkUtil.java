package com.testapp.prodcons.utils;


public class WorkUtil {

    private WorkUtil() {
    }

    public static boolean endReached(int value) {
        return value >= 100 || value <= 0;
    }

}
