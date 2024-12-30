import java.util.Scanner;

public class AutoKeyCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase();

        System.out.print("Enter key: ");
        String key = scanner.nextLine().toUpperCase();

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }

    public static String encrypt(String plaintext, String key) {
        StringBuilder result = new StringBuilder();
        String newKey = key + plaintext;
        newKey = newKey.substring(0, plaintext.length());

        for (int i = 0; i < plaintext.length(); i++) {
            int charPosition = ALPHABET.indexOf(plaintext.charAt(i));
            int keyPosition = ALPHABET.indexOf(newKey.charAt(i));
            int encryptedCharPosition = (charPosition + keyPosition) % ALPHABET.length();
            result.append(ALPHABET.charAt(encryptedCharPosition));
        }

        return result.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder result = new StringBuilder();
        String currentKey = key;

        for (int i = 0; i < ciphertext.length(); i++) {
            int charPosition = ALPHABET.indexOf(ciphertext.charAt(i));
            int keyPosition = ALPHABET.indexOf(currentKey.charAt(i));
            int decryptedCharPosition = (charPosition - keyPosition + ALPHABET.length()) % ALPHABET.length();
            char decryptedChar = ALPHABET.charAt(decryptedCharPosition);
            result.append(decryptedChar);
            currentKey += decryptedChar;
        }

        return result.toString();
    }
}
