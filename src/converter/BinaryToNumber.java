package converter;

import java.util.Scanner;

public class BinaryToNumber {

    public static String binaryToDecimal(String binaire) {
        if (!binaire.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }

        if (binaire.length() % 8 != 0) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire doit être un multiple de 8 bits.");
        }

        String result = "0";

        for (int i = 0; i < binaire.length(); i++) {
            char bit = binaire.charAt(i);
            result = multiplyByTwo(result);
            if (bit == '1') {
                result = addOne(result);
            }
        }

        return result;
    }

    public static String multiplyByTwo(String number) {
        StringBuilder result = new StringBuilder();
        int carry = 0;

        for (int i = number.length() - 1; i >= 0; i--) {
            int n = (number.charAt(i) - '0') * 2 + carry;
            result.insert(0, n % 10);
            carry = n / 10;
        }

        if (carry > 0) {
            result.insert(0, carry);
        }

        return result.toString();
    }

    public static String addOne(String number) {
        StringBuilder result = new StringBuilder();
        int carry = 1;

        for (int i = number.length() - 1; i >= 0; i--) {
            int n = (number.charAt(i) - '0') + carry;
            result.insert(0, n % 10);
            carry = n / 10;
        }

        if (carry > 0) {
            result.insert(0, carry);
        }

        return result.toString();
    }

    public static String decimalToBinary(String number) {
        if (number.equals("0")) return "00000000";

        StringBuilder result = new StringBuilder();
        String n = number;

        while (!n.equals("0")) {
            int remainder = modByTwo(n);
            result.insert(0, remainder);
            n = divideByTwo(n);
        }

        while (result.length() % 8 != 0) {
            result.insert(0, '0');
        }

        return result.toString();
    }

    public static int modByTwo(String number) {
        return (number.charAt(number.length() - 1) - '0') % 2;
    }

    public static String divideByTwo(String number) {
        StringBuilder result = new StringBuilder();
        int carry = 0;

        for (int i = 0; i < number.length(); i++) {
            int current = carry * 10 + (number.charAt(i) - '0');
            result.append(current / 2);
            carry = current % 2;
        }

        // Supprimer les zéros en tête
        int index = 0;
        while (index < result.length() - 1 && result.charAt(index) == '0') {
            index++;
        }

        return result.substring(index);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.print("\nEntrez un nombre binaire : ");
            String binaire = scanner.nextLine().replaceAll("\\s+", "");

            String resultat;

            try {
                resultat = binaryToDecimal(binaire);
                System.out.println("Valeur décimale : " + resultat);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            boolean choixValide = false;
            while (!choixValide) {
                System.out.println("\nSouhaitez-vous :\n1. Revenir au binaire depuis le nombre\n2. Faire une nouvelle conversion\n3. Quitter");
                String choix = scanner.nextLine();

                switch (choix) {
                    case "1":
                        System.out.println("Binaire reconverti : " + decimalToBinary(resultat));
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
