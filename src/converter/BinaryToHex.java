package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class BinaryToHex {

    public static String Converter(String binaire) {
        if (!binaire.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }

        if (binaire.length() % 4 != 0) {
            // Pad with leading zeros to make length a multiple of 4
            binaire = "0".repeat((4 - binaire.length() % 4) % 4) + binaire;
        }

        // Convert binary string to hexadecimal
        String hex = new BigInteger(binaire, 2).toString(16).toUpperCase();
        
        return hex;
    }

    public static String hexToBinary(String hex) {
        // Remove any leading "0x" if present
        hex = hex.startsWith("0x") ? hex.substring(2) : hex;
        
        // Convert hex to binary
        String binary = new BigInteger(hex, 16).toString(2);
        
        // Pad with leading zeros to make length a multiple of 4
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

            boolean choixValide = false;
            while (!choixValide) {
                System.out.println("\nSouhaitez-vous :\n1. Revenir au binaire depuis l'hexadécimal\n2. Faire une nouvelle conversion\n3. Quitter");
                String choix = scanner.nextLine();

                switch (choix) {
                    case "1":
                        System.out.println("Binaire reconverti : " + hexToBinary(hex));
                        choixValide = true;
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
