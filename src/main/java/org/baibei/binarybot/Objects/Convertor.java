package org.baibei.binarybot.Objects;

public class Convertor {

    public static long convertDecimalToBinary(int decimal) {
        long binary = 0;

        for (int i = 0; decimal > 0; i++)
        {
            binary += (long) ((decimal % 2) * Math.pow(10, i));
            decimal = decimal / 2;
        }

        return binary;
    }

    public static long convertBinaryToDecimal(long binary) {
        long remainder, decimal = 0, i = 0;

        while (binary != 0) {
            remainder = binary % 10;
            binary /= 10;
            decimal += (long) (remainder * Math.pow(2, i));
            ++i;
        }

        return decimal;
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

        return getString(words, builder);
    }

    public static String convertStringToDecimal(String[] words) {
        StringBuilder builder = new StringBuilder();

        return getString(words, builder);
    }

    private static String getString(String[] words, StringBuilder builder) {
        for (String word : words) {
            if (!word.isEmpty()) {
                char c = (char) (convertBinaryToDecimal(Long.parseLong(word)));
                builder.append(c);
            }
        }

        return builder.toString();
    }
}
