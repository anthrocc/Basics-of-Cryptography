package stopics;

import util.CryptoTools;

import java.math.BigInteger;

public class ActivityE8 {
    public static void main(String[] args) {
        // Answer: 0x 00C15A519D8BB2050044D9E7F9803CCF66

        // Alice sends aY = g^ax(mod p)
        // Bob sends bY = g^bx (mod p)
        // Compute shared session key by doing received ^ bX (mod p)

        BigInteger p = new BigInteger("341769842231234673709819975074677605139"); // Prime p
        BigInteger g = new BigInteger("37186859139075205179672162892481226795"); // Primitive root g
        BigInteger aX = new BigInteger("83986164647417479907629397738411168307"); // Alice's DH private key
        BigInteger bX = new BigInteger("140479748264028247931575653178988397140"); // Bob's DH private key

        BigInteger aY = g.modPow(aX, p); // Alice's public DH key
        BigInteger bY = g.modPow(bX, p); // Bob's public DH key

        byte[] kS = (aY.modPow(bX, p)).toByteArray();
        byte[] kS2 = (bY.modPow(aX, p)).toByteArray();

        System.out.println("Ks1: " + CryptoTools.bytesToHex(kS));
        System.out.println("Ks2: " + CryptoTools.bytesToHex(kS2));
        System.out.println("Expected result: 00C15A519D8BB2050044D9E7F9803CCF66");
    }
}
