package hash;

import util.CryptoTools;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class ActivityD1 {
    public static void main(String[] args) throws Exception {
        // Modulus
        BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");

        // Public key
        BigInteger e = new BigInteger("74327");

        // Private Key
        BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");

        BigInteger pt = new BigInteger("Meet me at 5 pm tomorrow".getBytes());

        MessageDigest digester = MessageDigest.getInstance("SHA-512");

        /* ------------ MANUALLY ------------ */
        // Encrypt the plaintext with the private key manually (sign it)
        BigInteger signature = pt.modPow(d, n);

        // Hash the signature with SHA2-512
        byte[] hashedSig = digester.digest(signature.toByteArray());


        /* ------------ JCE ------------ */
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
//        PrivateKey privKey = keyFactory.generatePrivate(privSpec);
//
//        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
//        cipher.init(Cipher.ENCRYPT_MODE, privKey);
//        byte[] signature = cipher.doFinal(pt.toByteArray());

        // Hash the signature with SHA2-512
//        byte[] hashedSig = digester.digest(signature);

        System.out.println("Hashed Signature: " + new String(hashedSig));
    }
}
