package converter;

import java.util.Scanner;

public class HexToBinary {
    
    public static String toBinary(String hex) {
        // First convert hex to decimal
        int decimal = 0;
        hex = hex.toUpperCase();
        
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            int digit;
            
            if (c >= '0' && c <= '9') {
                digit = c - '0';
            } else if (c >= 'A' && c <= 'F') {
                digit = 10 + (c - 'A');
            } else {
                throw new IllegalArgumentException("Erreur : nombre hexadécimal invalide.");
            }
            
            decimal = decimal * 16 + digit;
        }
        
        // Then convert decimal to binary
        if (decimal == 0) return "00000000";
        
        StringBuilder binary = new StringBuilder();
        while (decimal > 0) {
            binary.insert(0, decimal % 2);
            decimal = decimal / 2;
        }
        
        // Pad with leading zeros to make it a byte
        while (binary.length() < 8) {
            binary.insert(0, "0");
        }
        
        return binary.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\nEntrez un nombre hexadécimal à convertir en binaire :");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Erreur : l'entrée ne peut pas être vide.");
                continue;
            }

            try {
                String result = toBinary(input);
                System.out.println("Hexadécimal : " + input);
                System.out.println("Binaire : " + result);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
}
