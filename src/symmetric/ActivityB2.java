package symmetric;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class ActivityB2 {
    public static void main(String[]args) throws Exception {
        // Key, IV, and CipherText declaration
        byte[] key = "DO NOT TELL EVE!".getBytes();
        byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
        byte[] ct = CryptoTools.hexToBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");

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
