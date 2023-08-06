package com.xiyuan.orange.Utils;


import org.junit.Test;

import java.util.Random;

public class CommonUtils {
    public static String getRandomString(int length) {
        String dictionary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random r = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(dictionary.charAt(r.nextInt(35)));
        }
        return result.toString();
    }
}
