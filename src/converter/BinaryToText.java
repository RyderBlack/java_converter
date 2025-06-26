package converter;

import java.util.Scanner;

public class BinaryToText {

    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        for (int i = 0; i < binary.length(); i++) {
            char bit = binary.charAt(binary.length() - 1 - i);
            if (bit == '1') {
                decimal += (1 << i);
            }
        }
        return decimal;
    }

    public static String Converter(String binaire) {
        if (!binaire.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }
        if (binaire.length() % 8 != 0) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire doit être un multiple de 8 bits.");
        }

        StringBuilder texte = new StringBuilder();
        for (int i = 0; i < binaire.length(); i += 8) {
            String octet = binaire.substring(i, i + 8);
            int codeAscii = binaryToDecimal(octet);
            texte.append((char) codeAscii);
        }
        return texte.toString();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.print("\nEntrez une chaîne binaire : ");
            String binaire = scanner.nextLine().replaceAll("\\s+", "");

            String text = "";

            try {
                text = Converter(binaire);
                System.out.println("Texte correspondant : " + text);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}

