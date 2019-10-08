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

public class B1Q3 {
    public static void main(String args[]) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        byte[] keyBytes = "DO NOT TELL EVE!".getBytes();//  (expressed as an English string)
        byte[] ivBytes  = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
        byte[] ct = CryptoTools.hexToBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");

        Key key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        Cipher engine = Cipher.getInstance("AES/CBC/PKCS5Padding");

        engine.init(Cipher.DECRYPT_MODE, key, iv);

        byte[] pt = engine.doFinal(ct);
        System.out.println(new String(pt));
    }
}
