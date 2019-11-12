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

/*
    Your RSA keys are (all expressed as big integers):

    n =
      945874683351289829816050197767812346183848578056570056860845622609107886220137
      220709264916908438536900712481301344278323249667285825328323632215422317870682
      037630270674000828353944598575250177072847684118190067762114937353265007829546
      21660256501187035611332577696332459049538105669711385995976912007767106063
    e = 74327
    d = 7289370196881601766768920490284861650464951706793000236386405648425161747775298
      3441046583933853592091262678338882236956093668440986552405421520173544428836766
      3419319185756836904299985444024205035318170370675348574916529512369448767695219
      8090537385200990850805837963871485320168470788328336240930212290450023
    Use them to sign the message:
    "Meet me at 5 pm tomorrow"
    using 512-bit SHA2. After wards, show that if you send the message (in clear plaintext) and the signature to Alice
    then she can indeed verify that it came from you and that its content is intact.

 */

public class ActivityD1 {
    public static void main(String[] args) throws Exception {
        // Modulus
        BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");

        // Public key
        BigInteger e = new BigInteger("74327");

        // Private Key
        BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");

        BigInteger pt = new BigInteger("Meet me at 5 pm tomorrow".getBytes());

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hash = md.digest(pt.toByteArray());

        BigInteger sig = new BigInteger(hash).modPow(d, n);

        System.out.println("Hashed Signature: " + CryptoTools.bytesToHex(sig.toByteArray()));

        BigInteger decrypt = sig.modPow(e, n);
        System.out.println("Decrypted Signature: " + CryptoTools.bytesToHex(decrypt.toByteArray()));
        System.out.println("Original Hash: " + CryptoTools.bytesToHex(hash));
        System.out.println("Same: " + CryptoTools.bytesToHex(decrypt.toByteArray()).equals(CryptoTools.bytesToHex(hash)));
    }
}
