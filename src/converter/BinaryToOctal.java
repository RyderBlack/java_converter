
package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class BinaryToOctal {

    public static String Converter(String binaire) {
        if (!binaire.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }

        while (binaire.length() % 3 != 0) {
            binaire = "0" + binaire;
        }

        return new BigInteger(binaire, 2).toString(8);
    }

    public static String octalToBinary(String octal) {
        if (!octal.matches("[0-7]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne octale ne doit contenir que des chiffres de 0 à 7.");
        }

        String binary = new BigInteger(octal, 8).toString(2);

        while (binary.length() % 3 != 0) {
            binary = "0" + binary;
        }

        return binary;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.print("\nEntrez une chaîne binaire : ");
            String binaire = scanner.nextLine().replaceAll("\\s+", "");

            String octal = "";

            try {
                octal = Converter(binaire);
                System.out.println("Valeur octale : " + octal);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

        }

    }
}
