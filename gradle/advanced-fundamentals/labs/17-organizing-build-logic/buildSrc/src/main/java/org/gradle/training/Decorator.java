package org.gradle.training;

public class Decorator {
    public static String decorate(String input, String decoration) {
        return decoration + input + decoration;
    }
}