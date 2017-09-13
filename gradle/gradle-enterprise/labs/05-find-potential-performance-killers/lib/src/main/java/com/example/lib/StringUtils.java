package com.example.lib;

import com.google.common.base.Strings;

public class StringUtils {

    public static String leftPad(String src, int num) {
        return Strings.padStart(src, num, ' ');
    }

}
