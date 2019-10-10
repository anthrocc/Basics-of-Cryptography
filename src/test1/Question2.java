package test1;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class Question2 {
    public static void main(String[]args) throws Exception {
        // Key, IV, and CipherText declaration
        byte[] desKey = "Geologic".getBytes(); // DES
        byte[] aesKey = CryptoTools.hexToBytes("6D79796F726B756E6976657273697479"); // AES
        byte[] iv = CryptoTools.hexToBytes("30396F6968677771637667686A6B3635");
        byte[] ct = CryptoTools.hexToBytes("4519D15E452E82E3F93ECFDCA2373791");

        Key secret = new SecretKeySpec(aesKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        AlgorithmParameterSpec aps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secret, aps);
        byte[] temp = cipher.doFinal(ct);

        Key secret2 = new SecretKeySpec(desKey, "DES");
        Cipher cipher2 = Cipher.getInstance("DES/ECB/NoPadding");
        cipher2.init(Cipher.DECRYPT_MODE, secret2);
        byte[] pt = cipher2.doFinal(temp);


        // Convert to string
        String strPT = new String(pt, StandardCharsets.UTF_8);

        System.out.println("PT = " + strPT);
    }
}
