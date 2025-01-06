import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HillCipher {
    static float[][] decrypt = new float[3][1];
    static float[][] a = new float[3][3];
    static float[][] b = new float[3][3];
    static float[][] mes = new float[3][1];
    static float[][] res = new float[3][1];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        getkeymes();

        // Encrypt the message
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 3; k++) {
                    res[i][j] += a[i][k] * mes[k][j];
                }
                res[i][j] %= 26;
            }
        }

        System.out.print("\nEncrypted string is: ");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) (res[i][0] + 97));
        }

        // Decrypt the message
        inverse();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 3; k++) {
                    decrypt[i][j] += b[i][k] * res[k][j];
                }
                decrypt[i][j] %= 26;
            }
        }

        System.out.print("\nDecrypted string is: ");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) (decrypt[i][0] + 97));
        }
        System.out.println();
    }

    public static void getkeymes() throws IOException {
        System.out.println("Enter 3x3 matrix for key (It should be invertible): ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = sc.nextFloat();
            }
        }

        System.out.print("\nEnter a 3-letter string: ");
        String msg = br.readLine();
        for (int i = 0; i < 3; i++) {
            mes[i][0] = msg.charAt(i) - 97;
        }
    }

    public static void inverse() {
        float p, q;
        float[][] c = new float[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(a[i], 0, c[i], 0, 3);
        }

        // Initialize the identity matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b[i][j] = (i == j) ? 1 : 0;
            }
        }

        // Perform Gaussian elimination
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {
                if (i != k) {
                    p = c[i][k];
                    q = c[k][k];
                    for (int j = 0; j < 3; j++) {
                        c[i][j] = c[i][j] * q - p * c[k][j];
                        b[i][j] = b[i][j] * q - p * b[k][j];
                    }
                }
            }
        }

        // Normalize rows
        for (int i = 0; i < 3; i++) {
            float divisor = c[i][i];
            for (int j = 0; j < 3; j++) {
                b[i][j] /= divisor;
            }
        }

        System.out.println("\nInverse Matrix is:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
    }
}
