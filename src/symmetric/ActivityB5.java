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
        System.out.println(" The raw CT is: " + rawCT);

        byte[] ct = CryptoTools.hexToBytes(rawCT);
        byte[] key = "FACEBOOK".getBytes();
        byte[] compKey = ~key;  // Get bitwise compliment of the key
        // Decrypt with the bitwise compliment first and then with the regular key

        Key secret = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] pt = cipher.doFinal(ct);
        System.out.println("BK = " + new String(pt) + "<");


    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
