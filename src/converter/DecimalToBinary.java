package converter;

import java.util.Scanner;

public class DecimalToBinary {

    public static String toBinary(int n) {
        String my_binary = "";
        if (n == 0) return "00000000";
        while (n > 0) {
            my_binary = (n % 2) + my_binary;
            n = n / 2;
        }
        while (my_binary.length() < 8) {
            my_binary = "0" + my_binary;
        }
        return my_binary;
    }

    public static int stringToDecimal(String input) {
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException("Erreur : caractère non numérique détecté.");
            }
            result = result * 10 + (c - '0');
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nEntrez un mot ou un nombre à convertir en binaire :");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Erreur : l'entrée ne peut pas être vide.");
                continue;
            }

            StringBuilder result = new StringBuilder();

            if (input.matches("\\d+")) {
                int number = stringToDecimal(input);
                result.append(toBinary(number));
                System.out.println("Nombre original : " + number);
                System.out.println("Binaire : " + result);

            } else if (input.matches("[a-zA-Z]+")) {
                for (int i = 0; i < input.length(); i++) {
                    char c = input.charAt(i);
                    result.append(toBinary((int) c));
                }
                System.out.println("Texte original : " + input);
                System.out.println("Binaire : " + result);

            } else {
                System.out.println("Erreur : l'entrée doit contenir uniquement des lettres ou uniquement des chiffres.");
                continue;
            }

        }

    }
}
