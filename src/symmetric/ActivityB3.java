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
        //byte[] pt = "EECS3481 Applied Cryptography".getBytes();
        String knownCt = "4E51297B424F90D8B2ACD6ADF010DDC4"; // length = 32
        String unKnownCt = "????????????????"; // length = 16
        byte[] ct = CryptoTools.hexToBytes(knownCt + unKnownCt);
        //byte[] ct = CryptoTools.hexToBytes("4E51297B424F90D8B2ACD6ADF010DDC4");
        byte[] key = "CSE@YORK".getBytes();
        byte[] iv = CryptoTools.hexToBytes("0123456701234567");

        Key secret = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        AlgorithmParameterSpec aps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secret, aps);
        byte[] pt = cipher.doFinal(ct);

        System.out.println("PT = " + new String(pt) + "<");


    }

}