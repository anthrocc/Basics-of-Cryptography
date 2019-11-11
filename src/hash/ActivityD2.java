package hash;

import java.math.BigInteger;

/*
    Alice and Bob have the following RSA parameters:

    nA = 171024704183616109700818066925197841516671277
    eA = 1571

    pB = 98763457697834568934613
    qB = 8495789457893457345793
    eB = 87697

    Alice wants to send a message m to Bob. To ensure sender integrity, she signs the message iteself using her private key and obtains the signature s. (Side note: one typically signs the hash of the message, not the message iteself, but since her message is pretty short, this works too.) And to keep the contents of the message confidential, she encrypts both m and s using Bob's public key and sends the two ciphertexts m' and s' to Bob. He receives:
    m' = 418726553997094258577980055061305150940547956
    s' = 749142649641548101520133634736865752883277237

    Help him determine the message that Alice sent and assure him of its origin integrity.
 */

public class ActivityD2 {
    public static void main(String[] args) throws Exception{

        /*  Alice sends message m to Bob, Bob receives ct (m') encrypted with eB
         *  She also sends sig (s') which is the signature of the message
         */

        /* ------------ Alice RSA Stuff ------------ */
        // Modulus
        BigInteger nA = new BigInteger("171024704183616109700818066925197841516671277");

        // Encryption exponent (Alice's public key)
        BigInteger eA = new BigInteger("1571");

        /* ------------ Bob RSA Stuff ------------ */
        // p and q used to calculate n and phi
        BigInteger pB = new BigInteger("98763457697834568934613");
        BigInteger qB = new BigInteger("8495789457893457345793");

        // Encryption exponent (Bob's public key)
        BigInteger eB = new BigInteger("87697");

        /* ------------ Bob Receives ------------ */
        // Encrypted message
        BigInteger ct = new BigInteger("418726553997094258577980055061305150940547956");

        // Encrypted signature
        BigInteger sig = new BigInteger("749142649641548101520133634736865752883277237");

        /* ------------ Calculating Bob's n, phi, d ------------ */
        // Calculate Bob's n (modulus)
        BigInteger nB = pB.multiply(qB);

        // Calculate Bob's phi
        BigInteger pTemp = pB.subtract(BigInteger.ONE);
        BigInteger qTemp = qB.subtract(BigInteger.ONE);
        BigInteger phiB = pTemp.multiply(qTemp);

        // Calculate Bob's d (secret key for decryption)
        BigInteger dB = eB.modInverse(phiB);

        /* ------------ Decrypting Message ------------ */
        BigInteger pt = ct.modPow(dB, nB);
        System.out.println("m: " + pt);

        /* ------------ Test Signature ------------ */
        BigInteger sB = sig.modPow(dB, nB);
        //System.out.println("s: " + sB);

        BigInteger sA = sB.modPow(eA, nA);
        System.out.println("m (from s): " + sA);
    }
}
