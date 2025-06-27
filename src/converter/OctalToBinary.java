package converter;

import java.util.Scanner;

public class OctalToBinary {
    
    public static String toBinary(String octal) {
        // First convert octal to decimal
        int decimal = 0;
        for (int i = 0; i < octal.length(); i++) {
            char c = octal.charAt(i);
            if (c < '0' || c > '7') {
                throw new IllegalArgumentException("Erreur : nombre octal invalide.");
            }
            int digit = c - '0';
            decimal = decimal * 8 + digit;
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
            System.out.println("\nEntrez un nombre octal à convertir en binaire :");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Erreur : l'entrée ne peut pas être vide.");
                continue;
            }

            try {
                String result = toBinary(input);
                System.out.println("Octal : " + input);
                System.out.println("Binaire : " + result);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
}
