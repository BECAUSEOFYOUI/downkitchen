package com.self.kitchen.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatTimeUtils {
    public static int getTime(Date date){


        SimpleDateFormat sf = new SimpleDateFormat("HH");

        int d = Integer.valueOf(sf.format(date));
        return d;
    }

    public static void main(String[] args) {

        System.out.println(FormatTimeUtils.getTime(new Date()));
    }
}
