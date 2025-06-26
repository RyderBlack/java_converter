package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class BinaryToOctal {

    public static String Converter(String binaire) {
        if (!binaire.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }

        // Pad with leading zeros to make length a multiple of 3
        while (binaire.length() % 3 != 0) {
            binaire = "0" + binaire;
        }

        // Convert binary string to octal
        return new BigInteger(binaire, 2).toString(8);
    }

    public static String octalToBinary(String octal) {
        // Validate octal input
        if (!octal.matches("[0-7]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne octale ne doit contenir que des chiffres de 0 à 7.");
        }

        // Convert octal to binary
        String binary = new BigInteger(octal, 8).toString(2);
        
        // Pad with leading zeros to make length a multiple of 3
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

            boolean choixValide = false;
            while (!choixValide) {
                System.out.println("\nSouhaitez-vous :\n1. Revenir au binaire depuis l'octal\n2. Faire une nouvelle conversion\n3. Quitter");
                String choix = scanner.nextLine();

                switch (choix) {
                    case "1":
                        try {
                            System.out.println("Binaire reconverti : " + octalToBinary(octal));
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "2":
                        choixValide = true;
                        break;

                    case "3":
                        continuer = false;
                        choixValide = true;
                        break;

                    default:
                        System.out.println("Choix invalide. Veuillez entrer 1, 2 ou 3.");
                }
            }
        }
        System.out.println("Au revoir !");
        scanner.close();
    }
}
