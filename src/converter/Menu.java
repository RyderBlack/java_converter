package converter;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Texte ou nombre → Binaire");
            System.out.println("2. Binaire → Texte (avec option d'encryptage César)");
            System.out.println("3. Binaire → Nombre décimal");
            System.out.println("4. Texte → Binaire");
            System.out.println("5. Quitter");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1": {
                    System.out.print("Entrez un mot ou un nombre : ");
                    String input1 = scanner.nextLine().trim();
                    StringBuilder result = new StringBuilder();

                    if (input1.matches("\\d+")) {
                        int number = DecimalToBinary.stringToDecimal(input1);
                        String binary = DecimalToBinary.toBinary(number);
                        System.out.println("Binaire : " + binary);

                        System.out.print("Voulez-vous convertir ce binaire en décimal ? (o/n) : ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                            int reverseDecimal = BinaryToNumber.binaryToDecimal(binary);
                            System.out.println("Décimal : " + reverseDecimal);
                        }

                    } else if (input1.matches("[a-zA-Z]+")) {
                        for (int i = 0; i < input1.length(); i++) {
                            result.append(DecimalToBinary.toBinary((int) input1.charAt(i)));
                        }
                        String binaireTexte = result.toString();
                        System.out.println("Binaire : " + binaireTexte);

                        System.out.print("Voulez-vous reconvertir ce binaire en texte ? (o/n) : ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                            String texte = BinaryToText.Converter(binaireTexte);
                            System.out.println("Texte : " + texte);
                        }

                    } else {
                        System.out.println("Entrée invalide (lettres ou chiffres uniquement).");
                    }
                    break;
                }

                case "2": {
                    System.out.print("Entrez une chaîne binaire : ");
                    String binaryText = scanner.nextLine().replaceAll("\\s+", "");
                    try {
                        String texte = BinaryToText.Converter(binaryText);
                        System.out.println("Texte : " + texte);

                        System.out.print("Voulez-vous l’encrypter avec César ? (o/n) : ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                            System.out.print("Entrez le décalage (nombre entier) : ");
                            String shiftStr = scanner.nextLine().trim();
                            int shift = DecimalToBinary.stringToDecimal(shiftStr);
                            String encrypted = CesarEncryptor.cesarEncrypt(texte, shift);
                            System.out.println("Texte chiffré : " + encrypted);

                            System.out.print("Voulez-vous déchiffrer ce texte ? (o/n) : ");
                            if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                                String decrypted = CesarEncryptor.cesarEncrypt(encrypted, 26 - (shift % 26));
                                System.out.println("Texte déchiffré : " + decrypted);
                            }
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                    break;
                }

                case "3": {
                    System.out.print("Entrez une chaîne binaire : ");
                    String binaryNumber = scanner.nextLine().replaceAll("\\s+", "");
                    try {
                        int decimal = BinaryToNumber.binaryToDecimal(binaryNumber);
                        System.out.println("Décimal : " + decimal);

                        System.out.print("Voulez-vous reconvertir ce nombre en binaire ? (o/n) : ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                            String binaire = DecimalToBinary.toBinary(decimal);
                            System.out.println("Binaire : " + binaire);
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                    break;
                }

                case "4": {
                    System.out.print("Entrez un texte : ");
                    String texte = scanner.nextLine();
                    StringBuilder binaire = new StringBuilder();
                    for (char c : texte.toCharArray()) {
                        binaire.append(DecimalToBinary.toBinary((int) c));
                    }
                    System.out.println("Binaire : " + binaire);

                    System.out.print("Voulez-vous reconvertir ce binaire en texte ? (o/n) : ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                        String reverseTexte = BinaryToText.Converter(binaire.toString());
                        System.out.println("Texte : " + reverseTexte);
                    }
                    break;
                }

                case "5":
                    continuer = false;
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 5.");
            }
        }

        scanner.close();
    }
}


