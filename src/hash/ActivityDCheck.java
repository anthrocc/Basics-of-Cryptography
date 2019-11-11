package hash;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;

/*
    Compute the MAC of the message shown in this document using MD5 for the hash, AES no padding for the cipher, and
    the secret key shown in the same document. Express the MAC as a hex string and write it below.

    NOTE: If the digest size = cipher block size then there is only one block and no mode of operation is needed.
    Otherwise, the problem must specify if ECB or CBC should be used, and in the latter case, should give us the
    IV to be used.
 */


public class ActivityDCheck {
    public static void main(String[] args) throws Exception {

        byte[] key = "glassmillioncove".getBytes();
        byte[] ct = "No one can make you feel inferior without your consent.".getBytes();

        // Compute the hash
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(ct);

        System.out.println("Original MD5 Hash: " + CryptoTools.bytesToHex(hash));

        // New Key and instance of a cipher
        Key secret = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");

        // Initialize the new cipher and decrypt
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] mac = cipher.doFinal(hash);

        String hex = CryptoTools.bytesToHex(mac);
        System.out.print("Mac result: ");
        System.out.println(hex);

        // Debug for testing to ensure it worked
//        cipher.init(Cipher.DECRYPT_MODE, secret);
//        byte[] back = cipher.doFinal(mac);
//        System.out.println("Decrypted Mac: " + CryptoTools.bytesToHex(back));

    }
}
