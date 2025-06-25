package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class DecimalToBinary {

    public static String toBinary(BigInteger n) {
        String binaire = n.toString(2); // conversion directe en binaire

        while (binaire.length() % 8 != 0) {
            binaire = "0" + binaire; // padding par blocs de 8 bits
        }

        return binaire;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez un mot ou un nombre à convertir en binaire :");
        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Erreur : l'entrée ne peut pas être vide.");
            sc.close();
            return;
        }

        StringBuilder result = new StringBuilder();

        if (input.matches("\\d+")) {
            try {
                BigInteger number = new BigInteger(input);
                result.append(toBinary(number));
                System.out.println("Nombre original : " + number);
                System.out.println("Binaire : " + result);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : le nombre est trop grand.");
            }
        } else if (input.matches("[a-zA-Z]+")) {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                result.append(toBinary(BigInteger.valueOf((int) c)));
            }
            System.out.println("Texte original : " + input);
            System.out.println("Binaire : " + result);
        } else {
            System.out.println("Erreur : l'entrée doit contenir uniquement des lettres ou uniquement des chiffres.");
        }

        sc.close();
    }
}

