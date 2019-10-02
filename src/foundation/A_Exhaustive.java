package foundation;

import util.CryptoTools;
import java.math.*;

public class A_Exhaustive {
    public static void main(String[]args) throws Exception {
        int a, b, temp;
        BigInteger tempA, alphabetSize;

        alphabetSize = new BigInteger("26");

        byte ct[] = CryptoTools.fileToBytes("data/MSG3.ct");
        byte test[] = new byte[ct.length];
        byte freq[] = new byte[26];

        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 26; j++) {
                for(int k = 0; k < ct.length; k++) {
                    // Get the multiplicative inverse
                    tempA = BigInteger.valueOf(i);
                    tempA = tempA.modInverse(alphabetSize);
                    a = tempA.intValue();

                    b = j;

                    test[k] = (byte) ((ct[k] - b) * a);

                    // Compute frequencies here
                    // Store in freq[]
                }
            }
        }
    }
}
