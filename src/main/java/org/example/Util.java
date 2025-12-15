package org.example;

public class Util {
    /**
     * Converts a string to title case.
     * @param str the string to convert
     * @return the title-cased string, or null if input is null, or empty string if input is empty
     */
    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String[] words = str.split(" ");
        StringBuilder titleCase = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                String word = words[i].toLowerCase();
                titleCase.append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1));
            }

            if (i < words.length - 1) {
                titleCase.append(" ");
            }
        }

        return titleCase.toString();
    }
}
