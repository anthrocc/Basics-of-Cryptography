package symmetric;

import util.CryptoTools;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class ActivityB3
{

    public static void main(String[] args) throws Exception
    {
        byte[] key = "CSE@YORK".getBytes();
        byte[] iv = CryptoTools.hexToBytes("0123456701234567");
        byte[] ct1 = CryptoTools.hexToBytes("4E51297B424F90D8");
        byte[] ct2 = CryptoTools.hexToBytes("B2ACD6ADF010DDC4");  // Idk how he got this

        Key secret = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        IvParameterSpec ips = new IvParameterSpec(ct1);
        cipher.init(Cipher.DECRYPT_MODE, secret, ips);
        byte[] pt = cipher.doFinal(ct2);

        System.out.println("PT = " + new String(pt));


    }

}