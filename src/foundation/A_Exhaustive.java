package foundation;

import util.CryptoTools;
import java.math.*;

public class A_Exhaustive {
    public static void main(String[]args) throws Exception {
        int a = 0, b = 0, keyA = 0, keyB = 0, multA = 0, temp;
        double max = 0.0, dotProduct = 0.0;
        BigInteger tempA, alphabetSize;
        byte[] ct = CryptoTools.fileToBytes("data/ACTIVITY1.ct");
        byte[] pt = new byte[ct.length];
        byte[] test = new byte[ct.length];
        int[] freq;
        int[] inverse = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};

        alphabetSize = new BigInteger("26");

        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < inverse.length; j++) {
                for(int k = 0; k < ct.length; k++) {
                    a = inverse[j];
                    b = i;

                    temp = ((ct[k] - 'A' - b) * a) % 26;
                    if (temp < 0)
                        temp += 26;
                    test[k] = (byte) (temp + 'A');

                }

                freq = CryptoTools.getFrequencies(test);
                for(int n = 0; n < 26; n++) {
//                    dotProd[n] =  (double) (freq[n]) * CryptoTools.ENGLISH[n];
                    dotProduct += (double) (freq[n]) * CryptoTools.ENGLISH[n];
                    //System.out.println(dotProduct);
                }
                if(dotProduct > max) {
                    max = dotProduct;
                    multA = a;
                    keyB = b;
                }
                System.out.println("Alpha: " + a + " | Beta: " + b + " | Dot Product: " + dotProduct);
                System.out.println("-------------------------------------------");
                dotProduct = 0.0;


            }
        }

        // Get the multiplicative inverse of a
        tempA = BigInteger.valueOf(multA);
        tempA = tempA.modInverse(alphabetSize);
        keyA = tempA.intValue();
        System.out.println("Max: " + max + " | Alpha: " + keyA + " | Beta: " + keyB);

        // Decode the ciphertext
        for (int w = 0; w < ct.length; w++) {
            temp = ((ct[w] - 'A' - keyB) * multA) % 26;
            if (temp < 0)
                temp += 26;
            pt[w] = (byte) (temp + 'A');
        }
        CryptoTools.bytesToFile(pt, "data/ACTIVITY1.pt");
    }
}
