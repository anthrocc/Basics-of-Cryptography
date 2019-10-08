package rehearsalTest1;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class Q3_SAE_Operation {
    public static void main(String[]args) throws Exception {
        byte[] iv = CryptoTools.hexToBytes("496E566563746F72");
//        byte[] ct1 = CryptoTools.hexToBytes("7AA38A029E773CBBC188A9FC");
//        byte[] ct2 = CryptoTools.hexToBytes("EADAE99DA560B784C99AFEF2");
        byte[] ct1 = CryptoTools.hexToBytes("7AA38A029E773CBB");
        byte[] ct2 = CryptoTools.hexToBytes("C188A9FCEADAE99D");
        byte[] ct3 = CryptoTools.hexToBytes("A560B784C99AFEF2");
        byte[] key = CryptoTools.hexToBytes("4F75725269676874");

        Key secret = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        AlgorithmParameterSpec aps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secret, aps);

        byte[] pt1 = cipher.doFinal(ct1);
        byte[] pt2 = cipher.doFinal(ct2);
        byte[] pt3 = cipher.doFinal(ct3);

        byte[] me1 = CryptoTools.xor(pt1, inverse(iv));
        byte[] me2 = CryptoTools.xor(pt2, inverse(ct1));
        byte[] me3 = CryptoTools.xor(pt3, inverse(ct2));
        System.out.println(new String(me1) + new String(me2) + new String(me3));
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
