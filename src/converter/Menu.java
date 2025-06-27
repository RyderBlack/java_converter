package converter;

import java.util.Scanner;
import static converter.TerminalUI.*;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            clearScreen();
            printHeader("CONVERTISSEUR UNIVERSEEL");
            
            printMenuItem(1, "Texte ou nombre → Binaire", "Convertit du texte ou un nombre en binaire");
            printMenuItem(2, "Binaire → Texte", "Convertit du binaire en texte (avec option d'encryptage César)");
            printMenuItem(3, "Binaire → Nombre décimal", "Convertit un nombre binaire en décimal");
            printMenuItem(4, "Binaire → Hexadécimal", "Convertit un nombre binaire en hexadécimal");
            printMenuItem(5, "Binaire → Octal", "Convertit un nombre binaire en octal");
            printMenuItem(6, "Quitter", "Fermer l'application");
            
            printSeparator();
            System.out.print(getColoredPrompt("Votre choix"));
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    System.out.print("\n" + getColoredPrompt("Appuyez sur Entrée pour continuer..."));
                    scanner.nextLine();
                    System.out.print(getColoredPrompt("Entrez un mot ou un nombre"));
                    String input1 = scanner.nextLine().trim();
                    StringBuilder result = new StringBuilder();

                    if (input1.matches("\\d+")) {
                        try {
                            int number = DecimalToBinary.stringToDecimal(input1);
                            String binary = DecimalToBinary.toBinary(number);
                            printResult("Résultat Binaire", binary);

                            System.out.print(getColoredPrompt("Voulez-vous convertir ce binaire en décimal ? (o/n)"));
                            if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                                int decimalValue = BinaryToNumber.binaryToDecimal(binary);
                                printResult("Valeur Décimale", String.valueOf(decimalValue));
                            }
                        } catch (NumberFormatException e) {
                            printError("Nombre invalide. Veuillez entrer un nombre valide.");
                        }

                    } else if (input1.matches("[a-zA-Z ]+")) {
                        try {
                            for (int i = 0; i < input1.length(); i++) {
                                result.append(DecimalToBinary.toBinary((int) input1.charAt(i)));
                            }
                            String binaryText = result.toString();
                            printResult("Résultat Binaire", binaryText);

                            System.out.print(getColoredPrompt("Voulez-vous reconvertir ce binaire en texte ? (o/n)"));
                            if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                                String textResult = BinaryToText.convertToText(binaryText);
                                printResult("Texte Décodé", textResult);
                            }
                        } catch (Exception e) {
                            printError("Erreur lors de la conversion du texte: " + e.getMessage());
                        }

                    } else {
                        System.out.println("Entrée invalide (lettres ou chiffres uniquement).");
                    }
                    break;
                }

                case "2": {
                    System.out.print("Entrez une chaîne binaire : ");
                    String my_binary = scanner.nextLine().replaceAll("\\s+", "");
                    try {
                        String text = BinaryToText.convertToText(my_binary);
                        printResult("Texte Décodé", text);

                        System.out.print(getColoredPrompt("Voulez-vous l'encrypter avec le chiffre de César ? (o/n)"));
                        if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                            try {
                                System.out.print(getColoredPrompt("Entrez le décalage (nombre entier) : "));
                                String shiftStr = scanner.nextLine().trim();
                                int shift = Integer.parseInt(shiftStr);
                                String encrypted = CesarEncryptor.cesarEncrypt(text, shift);
                                printResult("Texte Chiffré (César)", encrypted);

                                System.out.print(getColoredPrompt("Voulez-vous déchiffrer ce texte ? (o/n)"));
                                if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                                    String decrypted = CesarEncryptor.cesarEncrypt(encrypted, 26 - (shift % 26));
                                    printResult("Texte Déchiffré", decrypted);
                                }
                            } catch (NumberFormatException e) {
                                printError("Décalage invalide. Veuillez entrer un nombre entier valide.");
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
                        int decimalValue = BinaryToNumber.binaryToDecimal(binaryNumber);
                        printResult("Valeur Décimale", String.valueOf(decimalValue));
                        
                        System.out.print(getColoredPrompt("Voulez-vous convertir ce nombre en binaire ? (o/n)"));
                        if (scanner.nextLine().trim().equalsIgnoreCase("o")) {
                            try {
                                String binary = DecimalToBinary.toBinary(decimalValue);
                                printResult("Binaire Converti", binary);
                            } catch (NumberFormatException e) {
                                printError("Erreur lors de la conversion en binaire: " + e.getMessage());
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        printError("Erreur: " + e.getMessage());
                    }
                    break;
                }

                case "4": {
                    System.out.print("Entrez une chaîne binaire : ");
                    String my_binary = scanner.nextLine().replaceAll("\\s+", "");
                    try {
                        String hex = BinaryToHexadecimal.convertToHex(my_binary);
                        printResult("Valeur Hexadécimale", hex);
                        System.out.print(getColoredPrompt("Voulez-vous reconvertir cet hexadécimal en binaire ? (o/n)"));
                        String convertBackHex = scanner.nextLine();
                        if (convertBackHex.equalsIgnoreCase("o")) {
                            try {
                                String binaryFromHex = HexToBinary.convertToBinary(hex);
                                printResult("Binaire Converti", binaryFromHex);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Erreur lors de la conversion : " + e.getMessage());
                            }
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                    break;
                }

                case "5": {
                    System.out.print("Entrez une chaîne binaire : ");
                    String my_binary = scanner.nextLine().replaceAll("\\s+", "");
                    try {
                        String octal = BinaryToOctal.convertToOctal(my_binary);
                        printResult("Valeur Octale", octal);
                        System.out.print(getColoredPrompt("Voulez-vous reconvertir cet octal en binaire ? (o/n)"));
                        String convertBackOctal = scanner.nextLine();
                        if (convertBackOctal.equalsIgnoreCase("o")) {
                            try {
                                String binaryFromOctal = OctalToBinary.convertToBinary(octal);
                                printResult("Binaire Converti", binaryFromOctal);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Erreur lors de la conversion : " + e.getMessage());
                            }
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                    break;
                }

                case "6":
                    isRunning = false;
                    clearScreen();
                    printHeader("AU REVOIR !");
                    printInfo("Merci d'avoir utilisé le convertisseur universel.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 6.");
            }
        }

        scanner.close();
    }
}


