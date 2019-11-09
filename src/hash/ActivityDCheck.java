package hash;

import util.CryptoTools;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

public class ActivityDCheck {
    public static void main(String[] args) throws Exception {

        byte[] key = "glassmillioncove".getBytes();
        byte[] ct = "No one can make you feel inferior without your consent.".getBytes();

        //Key secret = new SecretKeySpec(key, "AES");

        Key secret = new SecretKeySpec(key, "AES");

        // Creating a Mac object
        Mac mac = Mac.getInstance("HmacMD5");

        // Initializing the Mac object
        mac.init(secret);


        // Computing the Mac
        byte[] bytes = ct;
        byte[] macResult = mac.doFinal(bytes);

        String hex = CryptoTools.bytesToHex(macResult);
        System.out.print("Mac result: ");
        System.out.println(hex);
    }
}
