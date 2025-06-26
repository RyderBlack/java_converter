package converter;
import java.util.Scanner;

public class BinaryToText {
    
    private static final int ALPHABET_SIZE = 26;
    private static final char FIRST_LOWERCASE = 'a';
    private static final char FIRST_UPPERCASE = 'A';

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

    public static String caesarEncrypt(String text, int shift) {
        shift = shift % ALPHABET_SIZE;
        if (shift < 0) {
            shift += ALPHABET_SIZE; // Handle negative shifts
        }

        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? FIRST_LOWERCASE : FIRST_UPPERCASE;
                int originalPosition = c - base;
                int newPosition = (originalPosition + shift) % ALPHABET_SIZE;
                result.append((char) (base + newPosition));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static String caesarDecrypt(String text, int shift) {
        return caesarEncrypt(text, ALPHABET_SIZE - (shift % ALPHABET_SIZE));
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
                
                // Ask if user wants to encrypt the text
                System.out.print("Voulez-vous chiffrer ce texte avec le chiffre de César ? (o/n) ");
                if (scanner.nextLine().equalsIgnoreCase("o")) {
                    System.out.print("Entrez le décalage pour le chiffrement : ");
                    int shift = Integer.parseInt(scanner.nextLine());
                    String encrypted = caesarEncrypt(texte, shift);
                    System.out.println("Texte chiffré : " + encrypted);
                    
                    System.out.print("Voulez-vous déchiffrer le texte ? (o/n) ");
                    if (scanner.nextLine().equalsIgnoreCase("o")) {
                        String decrypted = caesarDecrypt(encrypted, shift);
                        System.out.println("Texte déchiffré : " + decrypted);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : le décalage doit être un nombre entier.");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            boolean choixValide = false;
            while (!choixValide) {
                System.out.println("\nSouhaitez-vous :\n1. Revenir au binaire depuis le texte\n2. Chiffrer le texte avec César\n3. Faire une nouvelle conversion\n4. Quitter");
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
                        System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 4.");
                }
            }
        }

        System.out.println("Au revoir !");
        scanner.close();
    }
}
