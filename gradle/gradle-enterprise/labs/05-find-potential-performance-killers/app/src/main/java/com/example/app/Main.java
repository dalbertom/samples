package com.example.app;

import com.example.lib.StringUtils;

public class Main {

    public static void main(String... args) {
        System.out.println("Padded args:");
        for (String arg : args) {
            System.out.println(StringUtils.leftPad(arg, 20));
        }
    }

}
