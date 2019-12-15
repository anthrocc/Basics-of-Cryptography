package stopics;

import util.CryptoTools;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class ActivityECheck {
    public static void main(String[] args) throws Exception {
        BigInteger p = new BigInteger("1426978031065901624399459");  //prime modulus
        BigInteger g = new BigInteger("142983226354603241203899");   //primitive root
        BigInteger aX = new BigInteger("344797553541992795444525");  //Alice's DH private
        BigInteger bY = new BigInteger("177643156304028399392544"); //Bob's DH public
        byte[] ct = CryptoTools.hexToBytes("47433B7BCE3FCE15"); //The received DES/ECB/PKCS5Padding ciphertext 0x
        BigInteger cipherT = new BigInteger(ct);


        BigInteger aY = g.modPow(aX, p); // Alice's public DH key

        byte[] ky = (bY.modPow(aX, p)).toByteArray();
        System.out.println(ky.length * 8);

        byte[] arr = new byte[8];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = ky[i];
        }

        Key secret = new SecretKeySpec(arr, "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] pt = cipher.doFinal(ct);
        System.out.println("PT = " + new String(pt));
    }
}
