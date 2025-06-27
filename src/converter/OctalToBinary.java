package converter;

import java.math.BigInteger;

public class OctalToBinary {
    public static String Converter(String octal) {
        octal = octal.startsWith("0") && octal.length() > 1 ? octal.substring(1) : octal;
        
        if (!octal.matches("[0-7]+")) {
            throw new IllegalArgumentException("Erreur : la chaîne octale ne doit contenir que des chiffres de 0 à 7.");
        }

        String binary = new BigInteger(octal, 8).toString(2);
        
        if (binary.length() % 3 != 0) {
            binary = "0".repeat((3 - binary.length() % 3) % 3) + binary;
        }
        
        return binary;
    }
}
