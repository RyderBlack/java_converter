
package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class BinaryToHexadecimal {

    public static String convertToHex(String binaryString) {
        if (!binaryString.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }


        if (binaryString.length() % 4 != 0) {
            binaryString = "0".repeat((4 - binaryString.length() % 4) % 4) + binaryString;
        }

        return new BigInteger(binaryString, 2).toString(16).toUpperCase();
    }

    public static String hexToBinary(String hexString) {
        hexString = hexString.startsWith("0x") ? hexString.substring(2) : hexString;

        if (!hexString.matches("[0-9A-Fa-f]+")) {
            throw new IllegalArgumentException("Erreur : chaîne hexadécimale invalide");
        }

        String binary = new BigInteger(hexString, 16).toString(2);

        if (binary.length() % 4 != 0) {
            binary = "0".repeat((4 - binary.length() % 4) % 4) + binary;
        }

        return binary;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.print("\nEntrez une chaîne binaire (ou 'q' pour quitter) : ");
            String entree = scanner.nextLine().trim().replaceAll("\\s+", "");
            
            if (entree.equalsIgnoreCase("q")) {
                isRunning = false;
                continue;
            }

            try {
                String hex = convertToHex(entree);
                System.out.println("Valeur hexadécimale : 0x" + hex);
                
                System.out.print("Reconvertir en binaire ? (o/n) : ");
                if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                    String my_binary = hexToBinary(hex);
                    System.out.println("Valeur binaire : " + my_binary);
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}
