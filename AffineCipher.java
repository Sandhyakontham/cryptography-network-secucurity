import java.util.Scanner;

public class AffineCipher {

    // Function to find modular inverse
    public static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return -1; // No modular inverse
    }

    // Encrypt function
    public static String encrypt(String plaintext) {
        int a = 3, b = 12, m = 26;
        StringBuilder ciphertext = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                ciphertext.append((char) (((a * (ch - 'a') + b) % m) + 'a'));
            } else if (Character.isUpperCase(ch)) {
                ciphertext.append((char) (((a * (ch - 'A') + b) % m) + 'A'));
            } else {
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }

    // Decrypt function
    public static String decrypt(String ciphertext) {
        int a = 3, b = 12, m = 26;
        int a_inverse = modInverse(a, m);
        StringBuilder plaintext = new StringBuilder();

        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                plaintext.append((char) ((a_inverse * ((ch - 'a' - b + m) % m) % m) + 'a'));
            } else if (Character.isUpperCase(ch)) {
                plaintext.append((char) ((a_inverse * ((ch - 'A' - b + m) % m) % m) + 'A'));
            } else {
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        String ciphertext = encrypt(plaintext);
        System.out.println("Encrypted text: " + ciphertext);

        String decryptedText = decrypt(ciphertext);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}
