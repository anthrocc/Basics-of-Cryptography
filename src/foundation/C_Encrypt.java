package foundation;

import util.CryptoTools;

public class C_Encrypt {
    public static void main(String[]args) throws Exception {
        int key = 19;

        byte raw[] = CryptoTools.fileToBytes("data/MSG1.pt");  // Read plaintext into array of bytes
        byte pt[] = CryptoTools.clean(raw);  // Remove all characters that are not A-Z, capitalize all
        CryptoTools.bytesToFile(pt, "data/MSG1.clean");  // Writes the cleaned array to file
        byte ct[] = new byte[pt.length];

        // Encrypt with Caesar Cipher
        for(int i = 0; i < pt.length; i++) {
            ct[i] = (byte) ((pt[i] - 'A' + key) % 26 + 'A');  // Cast as byte
        }

        CryptoTools.bytesToFile(ct, "data/MSG1.ct");  // Writes the ciphertext array to file
        System.out.println("MD5 hash of the ciphertext is: " + CryptoTools.getMD5(ct));  // Calculate and print MD5 hash of ct
        System.out.println("Index of Confidence for the plaintext is: " + CryptoTools.getIC(pt));  // Calculate and print IC of pt
        System.out.println("Index of Confidence for the ciphertext is: " + CryptoTools.getIC(ct));  // Calculate and print IC of pt

        //----------------------------------------------Decrypt
        byte[] bk = new byte[ct.length];
        for (int i = 0; i < ct.length; i++)
        {
            int tmp = (ct[i] - 'A' - key) % 26;
            if (tmp < 0) tmp+= 26;
            bk[i] = (byte) (tmp + 'A');
        }
        System.out.println("BK = " + new String(bk));
    }
}
