package symmetric;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;

public class ActivityB5 {
    public static void main(String[]args) throws Exception {
        String rawCT = readFile("data/MSGActivityB5.ct", StandardCharsets.US_ASCII);
        byte[] key = "FACEBOOK".getBytes();
        byte[] compKey = complementByte(key);
        byte[] ct = CryptoTools.hexToBytes(rawCT);

        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        Key secret = new SecretKeySpec(key, "DES");
        Key compSecret = new SecretKeySpec(compKey, "DES");
        cipher.init(Cipher.DECRYPT_MODE, compSecret);
        byte[] ct1 = cipher.doFinal(ct);

        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] ct2 = cipher.doFinal(ct1);

        System.out.println(new String(ct2));
    }

    // Method to get the complement of a byte
    public static byte[] complementByte(byte[] temp) {
        byte[] ct = new byte[temp.length];
        for (int i = 0; i < temp.length; i++) {
            ct[i] = (byte) (~temp[i]);
        }
        return ct;
    }

    // Method to parse a file into a string
    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
