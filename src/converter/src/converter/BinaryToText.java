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

    public static String decimalToBinary(int number) {
        StringBuilder binaire = new StringBuilder();
        if (number == 0) return "00000000";
        while (number > 0) {
            binaire.insert(0, number % 2);
            number /= 2;
        }
        while (binaire.length() < 8) {
            binaire.insert(0, '0');
        }
        return binaire.toString();
    }

    public static String Converter(String binaire) {
        if (!binaire.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }
        if (binaire.length() % 8 != 0) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire doit être un multiple de 8 bits.");
        }

        StringBuilder texte = new StringBuilder();
        for (int i = 0; i < binaire.length(); i += 8) {
            String octet = binaire.substring(i, i + 8);
            int codeAscii = binaryToDecimal(octet);
            texte.append((char) codeAscii);
        }
        return texte.toString();
    }

    public static String textToBinary(String texte) {
        StringBuilder binaire = new StringBuilder();
        for (char c : texte.toCharArray()) {
            binaire.append(decimalToBinary((int) c));
        }
        return binaire.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.print("\nEntrez une chaîne binaire : ");
            String binaire = scanner.nextLine().replaceAll("\\s+", "");

            String texte = "";

            try {
                texte = Converter(binaire);
                System.out.println("Texte correspondant : " + texte);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            boolean choixValide = false;
            while (!choixValide) {
                System.out.println("\nSouhaitez-vous :\n1. Revenir au binaire depuis le texte\n2. Faire une nouvelle conversion\n3. Quitter");
                String choix = scanner.nextLine();

                switch (choix) {
                    case "1":
                        System.out.println("Binaire reconverti : " + textToBinary(texte));
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
        scanner.close();
    }
}

