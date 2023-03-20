package com.gora;


/**
 * Class for logging data
 */
public class Logger {
    /**
     * function responsible for logging data
     * @param c - color of the text
     * @param message - message to be logged
     */
    public static void log(Color c, String message){
        System.out.println(c + message + Color.RESET);
    }

    /**
     * enum representing colors
     */
    enum Color{
        CYAN("\u001B[36m"),
        WHITE( "\u001B[37m"),
        GREEN("\u001B[32m"),
        BLUE( "\u001B[34m"),
        RESET("\u001B[0m");

        private final String colorName;

        Color(String s) {
            this.colorName = s;
        }
        @Override
        public String toString() {
            return colorName;
        }
    }
}
