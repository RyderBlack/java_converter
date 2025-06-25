package converter;
import java.util.Scanner;

public class BinaryToText {

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
            int codeAscii = Integer.parseInt(octet, 2);
            texte.append((char) codeAscii);
        }

        return texte.toString();
    }

    public static String textToBinary(String texte) {
        StringBuilder binaire = new StringBuilder();
        for (char c : texte.toCharArray()) {
            String bin = Integer.toBinaryString((int) c);
            while (bin.length() < 8) {
                bin = "0" + bin;
            }
            binaire.append(bin);
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
