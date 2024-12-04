package org.baibei.binarybot.Objects;

import java.util.HashMap;

public class Convertor {

    public static long convertDecimalToBinary(int decimal) {
        long binary = 0;

        for (int i = 0; decimal > 0; i++) {
            binary += (long) ((decimal % 2) * Math.pow(10, i));
            decimal /= 2;
        }

        return binary;
    }

    public static long convertBinaryToDecimal(long binary) {
        long decimal = 0;

        for (int i = 0; binary > 0; i++) {
            decimal += (long) ((binary % 10) * Math.pow(2, i));
            binary /= 10;
        }

        return decimal;
    }

    public static String convertTo(long source, int baseFrom, int baseTo) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; source != 0; i++) {
            result.append((source % baseTo) * Math.pow(baseFrom, i));
            source /= baseFrom;
        }

        return result.toString();
    }

    public static String convertTo(String source, int baseFrom, int baseTo) {
        HashMap<Character, Integer> symbols = new HashMap<>();
        HashMap<Integer, Character> ints = new HashMap<>();

        for (int i = 0; i <= 9; i++) {
            symbols.put((char) (i + '0'), i);
            ints.put(i, (char) (i + '0'));
        }
        for (int i = 0; i < 26; i++) {
            symbols.put((char) ('A' + i), (10 + i));
            ints.put((10 + i), (char) ('A' + i));
        }
        for (int i = 0; i < 26; i++) {
            symbols.put((char) ('a' + i), (10 + 26 + i));
            ints.put((10 + 26 + i), (char) ('a' + i));
        }

        int intSource = 0;
        for (int i = 0; i < source.length(); i++) {
            intSource += (int) (symbols.get(source.charAt(i))
                                * Math.pow(baseFrom, (source.length() - i - 1)));
        }

        StringBuilder result = new StringBuilder();
        while (intSource != 0) {
            result.append(ints.get(intSource % baseTo));
            intSource /= baseTo;
        }

        return result.reverse().toString();
    }

    public static String convertStringTo(String[] source, int baseFrom, int baseTo) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < source.length; i++) {
            if (!source[i].isEmpty()) {
                try {
                    char c = (char) (Integer.parseInt(convertTo(source[i], baseFrom, baseTo)));
                    result.append(c);
                    if (i < result.length() - 1) {
                        result.append(' ');
                    }
                } catch (Exception e) {
                    return "❌";
                }
            }
        }

        return result.toString();
    }

    public static String convertStringToBinary(String binary) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < binary.length(); i++) {
            builder.append(convertDecimalToBinary(binary.charAt(i)));
            if (i < binary.length() - 1) {
                builder.append(' ');
            }
        }

        return builder.toString();
    }

    public static String convertStringToBinary(String[] words) {
        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            builder.append(convertStringToBinary(word));
            if (!word.equals(words[words.length - 1])) {
                  builder.append(' ');
                builder.append(convertDecimalToBinary(' '));
                builder.append(' ');
            }
        }

        return builder.toString();
    }

    public static String convertStringToDecimal(String binary) {
        StringBuilder builder = new StringBuilder();
        String[] words = binary.split(" ");

        return getString(words, builder, 2, 10);
    }

    public static String convertStringToDecimal(String[] words) {
        StringBuilder builder = new StringBuilder();

        return getString(words, builder, 10, 2);
    }

    private static String getString(String[] words, StringBuilder builder, int baseFrom, int baseTo) {
        for (String word : words) {
            if (!word.isEmpty()) {
                try {
                    char c = (char) (convertBinaryToDecimal(Long.parseLong(word)));
                    builder.append(c);
                } catch (Exception e) {
                    return "❌";
                }
            }
        }

        return builder.toString();
    }
}
