package asymmetric;

import util.CryptoTools;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class ActivityC3 {
    public static void main(String[] args) throws Exception {
        BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
        BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
        BigInteger e = new BigInteger("65537");
        BigInteger ct = new BigInteger("1817487313698347891034157970684926175211834109573277793102901854482611726141560963120214926234448852417078321539316776648109260519063106558303669880226359");

        // Need n, e, d, ct
        // Missing n, p
        // n = pq
        // phi = (p-1)(q-1)

        // Finding p using the rearranged phi equation
        // p = [phi/(q-1)] + 1
        BigInteger p1 = q.subtract(BigInteger.ONE);
        BigInteger p2 = phi.divide(p1);
        BigInteger p = p2.add(BigInteger.ONE);

        // Compute d = inverse of e mod phi
        BigInteger d = e.modInverse(phi);

        // Compute n = pq
        BigInteger n = p.multiply(q);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e);
        RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
        PublicKey pubKey = keyFactory.generatePublic(pubSpec);
        PrivateKey privKey = keyFactory.generatePrivate(privSpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, privKey);

        byte[] pt = cipher.doFinal(ct.toByteArray());
        CryptoTools.bytesToFile(pt, "data/ActivityC/ActivityC3PT.pt");
        System.out.println("PT: " + new String(pt).trim());
    }
}
