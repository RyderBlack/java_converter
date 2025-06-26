package converter;

import java.util.Scanner;

public class DecimalToBinary {

    public static String toBinary(int n) {
        String binaire = "";
        if (n == 0) return "00000000";
        while (n > 0) {
            binaire = (n % 2) + binaire;
            n = n / 2;
        }
        while (binaire.length() < 8) {
            binaire = "0" + binaire;
        }
        return binaire;
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

    public static String binaryToText(String binary) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteStr = binary.substring(i, i + 8);
            int charCode = binaryToDecimal(byteStr);
            text.append((char) charCode);
        }
        return text.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\nEntrez un mot ou un nombre à convertir en binaire :");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Erreur : l'entrée ne peut pas être vide.");
                continue;
            }

            StringBuilder result = new StringBuilder();
            String originalInput = input;

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

            boolean choixValide = false;
            while (!choixValide) {
                System.out.println("\nSouhaitez-vous :\n1. Revenir à l'original à partir du binaire\n2. Faire une nouvelle conversion\n3. Quitter");
                String choix = sc.nextLine();

                switch (choix) {
                    case "1":
                        if (originalInput.matches("\\d+")) {
                            System.out.println("Décimal : " + binaryToDecimal(result.toString()));
                        } else if (originalInput.matches("[a-zA-Z]+")) {
                            System.out.println("Texte : " + binaryToText(result.toString()));
                        }
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
        sc.close();
    }
}
