package symmetric;

import util.CryptoTools;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class ActivityB1 {
    public static void main(String[]args) throws Exception {
        // Key, IV, and CipherText declaration
        byte[] key = CryptoTools.hexToBytes("9F0DCEDB322F3C6873F9256E01376BA4");
        byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
        byte[] ct = CryptoTools.hexToBytes("F38ADBA8A7B4CC613578355032205D50");


        // New Key and instance of a cipher
        Key secret = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        AlgorithmParameterSpec aps = new IvParameterSpec(iv);

        // Initialize the new cipher and decrypt
        cipher.init(Cipher.DECRYPT_MODE, secret, aps);
        byte[] temp = cipher.doFinal(ct);

        // Convert to string
        String pt = new String(temp, StandardCharsets.UTF_8);

        System.out.println("PT = " + pt);
    }

}
