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

    public static String convertToText(String binaryString) {
        if (!binaryString.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }
        if (binaryString.length() % 8 != 0) {
            throw new IllegalArgumentException("Erreur : la longueur de la chaîne binaire doit être un multiple de 8 bits.");
        }

        StringBuilder textBuilder = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i += 8) {
            String byteStr = binaryString.substring(i, i + 8);
            int asciiCode = binaryToDecimal(byteStr);
            textBuilder.append((char) asciiCode);
        }
        return textBuilder.toString();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.print("\nEntrez une chaîne binaire (ou 'q' pour quitter) : ");
            String entree = scanner.nextLine().trim().replaceAll("\\s+", "");
            
            if (entree.equalsIgnoreCase("q")) {
                continuer = false;
                continue;
            }

            try {
                String texte = convertToText(entree);
                System.out.println("Texte décodé : " + texte);
                
                System.out.print("Reconvertir en binaire ? (o/n) : ");
                if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                    // Convertir le texte en binaire (8 bits par caractère)
                    StringBuilder binaire = new StringBuilder();
                    for (char c : texte.toCharArray()) {
                        binaire.append(String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(' ', '0'));
                    }
                    System.out.println("Binaire : " + binaire.toString());
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}

