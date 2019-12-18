package exam;

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

public class Question2 {
    public static void main(String[] args) throws Exception {
        BigInteger p = new BigInteger("133717718270293296200188525502739327388353629179621058494112854251193809058703");  //prime modulus
        BigInteger g = new BigInteger("88653877561129476419831247959360597000469401190192885546499619313456391487");   //primitive root
        BigInteger aX = new BigInteger("40946114960323376780632697114159865026104715183899616608412774756574256842942");  //Alice's DH private
        BigInteger bY = new BigInteger("66084206540781068627271629211666577778562915982852519047450133068281295962922"); //Bob's DH public
        byte[] ct = CryptoTools.hexToBytes("46ECC6540D13291E1C6D97277597423F40EE73C6FE179DEBDB40B6CB7A0C973E"); //The received DES/ECB/PKCS5Padding ciphertext 0x
        BigInteger cipherT = new BigInteger(ct);


        BigInteger aY = g.modPow(aX, p); // Alice's public DH key

        byte[] ky = (bY.modPow(aX, p)).toByteArray();        
        
        byte[] key = CryptoTools.hexToBytes(CryptoTools.getMD5(ky));
        
        Key secret = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] pt = cipher.doFinal(ct);
        System.out.println("PT = " + new String(pt));
    }
}
