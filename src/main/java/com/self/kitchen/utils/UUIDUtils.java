package com.self.kitchen.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        String u = UUIDUtils.getUUID();
        System.out.println(u);
        System.out.println(u.substring(0,11));
    }
}
