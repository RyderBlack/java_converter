package converter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Convertir binaire vers nombre (BinaryToNumber)");
            System.out.println("2. Convertir binaire vers texte (BinaryToText)");
            System.out.println("3. Convertir texte ou nombre vers binaire (DecimalToBinary)");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    BinaryToNumber.main(new String[0]);
                    break;

                case "2":
                    BinaryToText.main(new String[0]);
                    break;

                case "3":
                    DecimalToBinary.main(new String[0]);
                    break;

                case "4":
                    continuer = false;
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez entrer 1, 2, 3 ou 4.");
            }
        }

        scanner.close();
    }
}
