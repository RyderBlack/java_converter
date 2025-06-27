
package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class BinaryToHexadecimal {

    public static String Converter(String binaire) {
        if (!binaire.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }

        if (binaire.length() % 4 != 0) {
            binaire = "0".repeat((4 - binaire.length() % 4) % 4) + binaire;
        }

        String hex = new BigInteger(binaire, 2).toString(16).toUpperCase();

        return hex;
    }

    public static String hexToBinary(String hex) {
        hex = hex.startsWith("0x") ? hex.substring(2) : hex;

        String binary = new BigInteger(hex, 16).toString(2);

        if (binary.length() % 4 != 0) {
            binary = "0".repeat((4 - binary.length() % 4) % 4) + binary;
        }

        return binary;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.print("\nEntrez une chaîne binaire : ");
            String binaire = scanner.nextLine().replaceAll("\\s+", "");

            String hex = "";

            try {
                hex = Converter(binaire);
                System.out.println("Valeur hexadécimale : " + hex);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

        }

    }
}
