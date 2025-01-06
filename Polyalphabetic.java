import java.util.Scanner;

public class Polyalphabetic {
   
    // Function to encrypt the plaintext
    public static String encrypt(String plaintext, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();
        int keyIndex = 0;

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char keyChar = key.charAt(keyIndex % keyLength);
                int shift = Character.isUpperCase(keyChar) ? keyChar - 'A' : keyChar - 'a';
                char encryptedChar = (char) ((c - base + shift) % 26 + base);
                encryptedText.append(encryptedChar);
                keyIndex++;
            } else {
                encryptedText.append(c); // Keep non-alphabet characters as is
            }
        }
        return encryptedText.toString();
    }

    // Function to decrypt the ciphertext
    public static String decrypt(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();
        int keyIndex = 0;

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char keyChar = key.charAt(keyIndex % keyLength);
                int shift = Character.isUpperCase(keyChar) ? keyChar - 'A' : keyChar - 'a';
                char decryptedChar = (char) ((c - base - shift + 26) % 26 + base);
                decryptedText.append(decryptedChar);
                keyIndex++;
            } else {
                decryptedText.append(c); // Keep non-alphabet characters as is
            }
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}