
package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class BinaryToOctal {

    public static String convertToOctal(String binaryString) {
        if (!binaryString.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }

        // Ajouter des zéros non significatifs pour obtenir une longueur multiple de 3
        while (binaryString.length() % 3 != 0) {
            binaryString = "0" + binaryString;
        }

        return new BigInteger(binaryString, 2).toString(8);
    }

    public static String octalToBinary(String octalString) {
        if (!octalString.matches("[0-7]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne octale ne doit contenir que des chiffres de 0 à 7.");
        }

        String binary = new BigInteger(octalString, 8).toString(2);

        // Ajouter des zéros non significatifs pour obtenir une longueur multiple de 3
        while (binary.length() % 3 != 0) {
            binary = "0" + binary;
        }

        return binary;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.print("\nEntrez une chaîne binaire (ou 'q' pour quitter) : ");
            String entree = scanner.nextLine().replaceAll("\\s+", "");
            
            if (entree.equalsIgnoreCase("q")) {
                continuer = false;
                continue;
            }

            try {
                String octal = convertToOctal(entree);
                System.out.println("Valeur octale : " + octal);
                
                System.out.print("Reconvertir en binaire ? (o/n) : ");
                if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                    String binaire = octalToBinary(octal);
                    System.out.println("Valeur binaire : " + binaire);
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}
