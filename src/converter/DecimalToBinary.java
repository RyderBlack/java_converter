package converter;

import java.util.Scanner;

public class DecimalToBinary {

    public static String toBinary(int n) {
        String binaire = "";

        while (n > 0) {
            binaire = (n % 2) + binaire;
            n = n / 2;
        }

        while (binaire.length() < 8) {
            binaire = "0" + binaire;
        }

        return binaire;
    }

    public static int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
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
                int number = Integer.parseInt(input);
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

