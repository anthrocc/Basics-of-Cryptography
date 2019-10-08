package resourcePrograms;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class B3Q1 {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException
    {
        byte[] KEY="CSE@YORK".getBytes();
        byte[] IV=CryptoTools.hexToBytes("0123456701234567");
        byte[] pt1=CryptoTools.hexToBytes("4E51297B424F90D8");
        byte[] pt2=CryptoTools.hexToBytes("B2ACD6ADF010DDC4");

        Cipher engine = Cipher.getInstance("DES/CBC/PKCS5Padding");
        Key myKey = new SecretKeySpec(KEY, "DES");

        IvParameterSpec iv = new IvParameterSpec(pt1);
        engine.init(Cipher.DECRYPT_MODE, myKey,iv);
        byte[] ct1 = engine.doFinal(pt2);



        System.out.println(new String(ct1));

    }
}
