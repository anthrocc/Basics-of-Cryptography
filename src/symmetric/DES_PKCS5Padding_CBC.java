package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_PKCS5Padding_CBC
{

    public static void main(String[] args) throws Exception
    {
        byte[] pt = "EECS3481 Applied Cryptography".getBytes();
        byte[] ky = CryptoTools.hexToBytes("34567abcdef03211");
        byte[] iv = "01234567".getBytes();

        Key secret = new SecretKeySpec(ky, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        AlgorithmParameterSpec aps = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secret, aps);
        byte[] ct = cipher.doFinal(pt);

        System.out.println("CT = " + CryptoTools.bytesToHex(ct));

        cipher.init(Cipher.DECRYPT_MODE, secret, aps);
        byte[] bk = cipher.doFinal(ct);
        System.out.println("BK = " + new String(bk) + "<");


    }

}