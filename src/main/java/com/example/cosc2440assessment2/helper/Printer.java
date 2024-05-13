/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.helper;

public class Printer {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void error(String body) {
        System.out.println(RED + body + RESET);
    }

    public static void result(String body) {
        System.out.println(GREEN + body + RESET);
    }

    public static void hint(String body) {
        System.out.println(BLUE + body + RESET);
    }
}
