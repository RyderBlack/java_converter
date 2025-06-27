package converter;

import java.math.BigInteger;

public class HexToBinary {
    public static String Converter(String hex) {
        hex = hex.startsWith("0x") ? hex.substring(2) : hex;
        
        if (!hex.matches("[0-9A-Fa-f]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne hexadécimale n'est pas valide.");
        }

        String binary = new BigInteger(hex, 16).toString(2);
        
        if (binary.length() % 4 != 0) {
            binary = "0".repeat((4 - binary.length() % 4) % 4) + binary;
        }
        
        return binary;
    }
}
