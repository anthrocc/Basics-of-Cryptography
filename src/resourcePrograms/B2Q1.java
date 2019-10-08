package resourcePrograms;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class B2Q1 {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] pt1 = "12345678".getBytes();
        byte[] pt2 = complebyte(pt1);
        byte[] ky = "helloYou".getBytes();
        byte[]cky=complebyte(ky);
        Cipher engine = Cipher.getInstance("DES/ECB/NoPadding");
        Key myKey = new SecretKeySpec(ky, "DES");
        engine.init(Cipher.ENCRYPT_MODE, myKey);
        byte[] ct1 = engine.doFinal(pt1);
        Key CKey = new SecretKeySpec(cky, "DES");
        engine.init(Cipher.ENCRYPT_MODE, CKey);
        byte[] ct2 = engine.doFinal(pt2);
        System.out.println(CryptoTools.bytesToBin(ct1));
        System.out.println(CryptoTools.bytesToBin(complebyte(ct1)));
        System.out.println(CryptoTools.bytesToBin(ct2));

    }

    public static byte[] complebyte(byte[] pt)
    {
        byte[] ct=new byte[pt.length];
        for(int i=0;i<pt.length;i++)
        {
            ct[i]= (byte) ~pt[i];
        }
        return ct;

    }
}