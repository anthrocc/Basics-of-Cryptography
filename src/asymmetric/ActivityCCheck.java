package asymmetric;

import util.CryptoTools;
import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;

public class ActivityCCheck {
    public static void main(String[] args) throws Exception {
        // Modulus
        BigInteger n = new BigInteger("5707076047102887158196927549751497905253605262905791228671171047704361671463299120679046523934593067560420095288685820143766560209957798046641002725066237");

        // Public Key
        BigInteger e = new BigInteger("101");

        // Private Key
        BigInteger d = new BigInteger("3898893537129695187283049514186666885767314486539599948300106953382187676544130789955595589524528947942964304421661547211267343735144138026005658409113705");

        // CipherText
        BigInteger ct = new BigInteger("4474264296024164227377178953965075079591234126791976724400163834322222243602772333748724375108854556963985421083092488985703644223951526554574362632287264");


        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
        PrivateKey privKey = keyFactory.generatePrivate(privSpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, privKey);

        byte[] pt = cipher.doFinal(ct.toByteArray());
        CryptoTools.bytesToFile(pt, "data/ActivityC/ActivityCCheckPT.pt");
        System.out.println("PT: " + new String(pt).trim());
    }
}
