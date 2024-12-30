public class CaesarCipherj {

    public static void encrypt(char[] text, int shift) {
        for (int i = 0; i < text.length; i++) {
            if (text[i] >= 'a' && text[i] <= 'z') {
                text[i] = (char) ((text[i] - 'a' + shift) % 26 + 'a');
            } else if (text[i] >= 'A' && text[i] <= 'Z') {
                text[i] = (char) ((text[i] - 'A' + shift) % 26 + 'A');
            }
        }
    }

    public static void decrypt(char[] text, int shift) {
        encrypt(text, 26 - shift);
    }

    public static void main(String[] args) {
        char[] text = "HelloWorld".toCharArray();
        int shift = 3;
        
        encrypt(text, shift);
        System.out.println("Encrypted: " + new String(text));
        
        decrypt(text, shift);
        System.out.println("Decrypted: " + new String(text));
    }
}
