import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PlayfairCipher {

    private static char[][] keyMatrix = new char[5][5];
    private static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the key from the user
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        // Get the plaintext from the user
        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        createKeyMatrix(key);
        String ciphertext = encrypt(plaintext);

        System.out.println("\nKey Matrix:");
        printKeyMatrix();
        System.out.println("\nCiphertext: " + ciphertext);

        scanner.close();
    }

    private static void createKeyMatrix(String key) {
        StringBuilder keyString = new StringBuilder();
        Set<Character> usedChars = new HashSet<>();

        // Add key characters to the keyString
        for (char c : key.toUpperCase().toCharArray()) {
            if (!usedChars.contains(c) && c != 'J') {
                keyString.append(c);
                usedChars.add(c);
            }
        }

        // Add remaining characters from the alphabet
        for (char c : ALPHABET.toCharArray()) {
            if (!usedChars.contains(c)) {
                keyString.append(c);
                usedChars.add(c);
            }
        }

        // Fill the key matrix
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                keyMatrix[i][j] = keyString.charAt(index++);
            }
        }
    }

    private static String encrypt(String plaintext) {
        plaintext = preparePlaintext(plaintext);
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = plaintext.charAt(i + 1);
            ciphertext.append(encryptPair(a, b));
        }

        return ciphertext.toString();
    }

    private static String preparePlaintext(String plaintext) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder preparedText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char current = plaintext.charAt(i);
            preparedText.append(current);

            // If the next character is the same, insert 'X'
            if (i + 1 < plaintext.length() && plaintext.charAt(i + 1) == current) {
                preparedText.append('X');
            }
        }

        // If the length is odd, add 'X' at the end
        if (preparedText.length() % 2 != 0) {
            preparedText.append('X');
        }

        return preparedText.toString();
    }

    private static String encryptPair(char a, char b) {
        int[] posA = findPosition(a);
        int[] posB = findPosition(b);

        if (posA[0] == posB[0]) { // Same row
            return "" + keyMatrix[posA[0]][(posA[1] + 1) % 5] + keyMatrix[posB[0]][(posB[1] + 1) % 5];
        } else if (posA[1] == posB[1]) { // Same column
            return "" + keyMatrix[(posA[0] + 1) % 5][posA[1]] + keyMatrix[(posB[0] + 1) % 5][posB[1]];
        } else { // Rectangle rule
            return "" + keyMatrix[posA[0]][posB[1]] + keyMatrix[posB[0]][posA[1]];
        }
    }

    private static int[] findPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private static void printKeyMatrix() {
        for (char[] row : keyMatrix) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
