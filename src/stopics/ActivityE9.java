package stopics;

import java.math.BigInteger;
import java.util.Random;

public class ActivityE9 {
    public static void main(String[] args) {
        // We need to split it into 5 shares such that:
        //  a) Each of them is at least 80-bit strong,
        //  b) All of them are needed to reconstitute the secret, and
        //  c) The entropy of knowing any 4 of them is the same as the entropy of knowing none of them.

        BigInteger secret = new BigInteger("291639075201575653178417");
        BigInteger s1 = BigInteger.probablePrime(80, new Random());
        BigInteger s2 = BigInteger.probablePrime(80, new Random());
        BigInteger s3 = BigInteger.probablePrime(80, new Random());
        BigInteger s4 = BigInteger.probablePrime(80, new Random());
        BigInteger s5 = secret.xor(s1).xor(s2).xor(s3).xor(s4);

        System.out.println(s1.xor(s2).xor(s3).xor(s4).xor(s5));

        // Generate n - 1 randoms of the specified bit-length
        // Calculate the nth split by taking secret xor s1 xor s2 xor sn-1
        // Reconstitute by xor-ing all the shares together


    }
}
