package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class HexToBinary {
    public static String convertToBinary(String hexString) {
        // Supprimer le préfixe 0x si présent
        hexString = hexString.startsWith("0x") ? hexString.substring(2) : hexString;
        
        if (!hexString.matches("[0-9A-Fa-f]+")) {
            throw new IllegalArgumentException("Erreur : chaîne hexadécimale invalide");
        }

        String binary = new BigInteger(hexString, 16).toString(2);
        
        // Ajouter des zéros non significatifs pour obtenir une longueur multiple de 4
        if (binary.length() % 4 != 0) {
            binary = "0".repeat((4 - binary.length() % 4) % 4) + binary;
        }
        
        return binary;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.print("\nEntrez une chaîne hexadécimale (ou 'q' pour quitter) : ");
            String entree = scanner.nextLine().trim();
            
            if (entree.equalsIgnoreCase("q")) {
                continuer = false;
                continue;
            }

            try {
                String binaire = convertToBinary(entree);
                System.out.println("Binaire : " + binaire);
                
                System.out.print("Reconvertir en hexadécimal ? (o/n) : ");
                if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                    String hex = new BigInteger(binaire, 2).toString(16).toUpperCase();
                    System.out.println("Hexadécimal : 0x" + hex);
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}
