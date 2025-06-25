package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class BinaryToNumber {

    public static BigInteger Converter(String binaire) {
        if (!binaire.matches("[01]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire ne doit contenir que des 0 et des 1.");
        }

        if (binaire.length() % 8 != 0) {
            throw new IllegalArgumentException("Erreur : la chaîne binaire doit être un multiple de 8 bits.");
        }

        return new BigInteger(binaire, 2);
    }

    public static String toBinary(BigInteger number) {
        String binaire = number.toString(2);
        while (binaire.length() % 8 != 0) {
            binaire = "0" + binaire;
        }
        return binaire;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.print("\nEntrez un nombre binaire : ");
            String binaire = scanner.nextLine().replaceAll("\\s+", "");

            BigInteger resultat;

            try {
                resultat = Converter(binaire);
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
                        System.out.println("Binaire reconverti : " + toBinary(resultat));
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
