package rehearsalTest1;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class Q4_AES_PKCS5 {
    public static void main(String[]args) throws Exception {
        // Key, IV, and CipherText declaration
        byte[] key = CryptoTools.hexToBytes("444F204E4F542054454C4C2045564521");
        byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
        byte[] ct = CryptoTools.hexToBytes("FB0692B011F74F8BF77EDE2630852C1700C204407EDF2222D965F74A8BCEB236");

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
