package converter;

import java.util.Scanner;

public class BinaryToNumber {

    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        for (int i = 0; i < binary.length(); i++) {
            char bit = binary.charAt(binary.length() - 1 - i);
            if (bit == '1') {
                decimal += (1 << i);
            } else if (bit != '0') {
                throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
            }
        }
        return decimal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez un nombre binaire : ");
        String binary = scanner.nextLine().replaceAll("\\s+", "");

        try {
            int decimal = binaryToDecimal(binary);
            System.out.println("Décimal : " + decimal);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}

