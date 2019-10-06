package symmetric;

import util.CryptoTools;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ActivityB4 {
    public static void main(String[]args) throws Exception {
        byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
        byte[] ct1= CryptoTools.hexToBytes("437DBAB5607137A5");
        byte[] ct2= CryptoTools.hexToBytes("CFC1031114634087");
        byte[] key = CryptoTools.hexToBytes("6B79466F724D4F50");

        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        Key secret = new SecretKeySpec(key, "DES");
        cipher.init(Cipher.DECRYPT_MODE, secret);

        byte[] pt1 = cipher.doFinal(ct1);
        byte[] pt2 = cipher.doFinal(ct2);

        byte[] me1 = CryptoTools.xor(pt1, inverse(iv));
        byte[] me2 = CryptoTools.xor(pt2, inverse(ct1));
        System.out.println(new String(me1) + new String(me2));
    }

    public static byte[] inverse(byte[] iv)
    {
        for(int i = 0; i < iv.length; i++)
        {
            iv[i] = (byte) ~iv[i];
        }
        return iv;
    }
}
