package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class OctalToBinary {
    public static String convertToBinary(String octalString) {
        // Supprimer le 0 initial si présent
        octalString = octalString.startsWith("0") && octalString.length() > 1 ? 
                    octalString.substring(1) : octalString;
        
        if (!octalString.matches("[0-7]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne octale ne doit contenir que des chiffres de 0 à 7");
        }

        String binary = new BigInteger(octalString, 8).toString(2);
        
        // Ajouter des zéros non significatifs pour obtenir une longueur multiple de 3
        if (binary.length() % 3 != 0) {
            binary = "0".repeat((3 - binary.length() % 3) % 3) + binary;
        }
        
        return binary;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.print("\nEntrez une chaîne octale (ou 'q' pour quitter) : ");
            String entree = scanner.nextLine().trim();
            
            if (entree.equalsIgnoreCase("q")) {
                continuer = false;
                continue;
            }

            try {
                String binaire = convertToBinary(entree);
                System.out.println("Binaire : " + binaire);
                
                System.out.print("Reconvertir en octal ? (o/n) : ");
                if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                    String octal = new BigInteger(binaire, 2).toString(8);
                    System.out.println("Octal : 0" + octal);
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}
