package resourcePrograms;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class B2Q2 {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] pt1 = "12345678".getBytes();
        byte[] pt2 = "92345678".getBytes();

        byte[] ky = new byte[8];
        Arrays.fill(ky, (byte)1);

        Cipher engine = Cipher.getInstance("DES/ECB/NoPadding");
        Key myKey = new SecretKeySpec(ky, "DES");
        engine.init(Cipher.ENCRYPT_MODE, myKey);
        byte[] ct1 = engine.doFinal(pt1);
        byte[] ct2 = engine.doFinal(pt1);
        System.out.println(CryptoTools.bytesToHex(ct1));
        System.out.println(CryptoTools.bytesToHex(ct2));


    }

}
