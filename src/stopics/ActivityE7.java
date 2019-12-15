package stopics;

import java.math.BigInteger;
import java.util.Random;

public class ActivityE7 {
    public static void main(String[] args) {

        BigInteger p = BigInteger.probablePrime(129, new Random()); // Generate a random prime BigInteger
        BigInteger g = new BigInteger("37186859139075205179672162892481226795"); // Primitive root g


        // Ally
        BigInteger aX = new BigInteger("83986164647417479907629397738411168307"); // Ally's DH private key
        BigInteger aY = g.modPow(aX, p); // Ally's public DH key

        // Bill
        BigInteger bX = new BigInteger("140479748264028247931575653178988397140"); // Bill's DH private key
        BigInteger bY = g.modPow(bX, p); // Bill's public DH key

        // Charlie
        BigInteger cX = aX.add(bX).divide(BigInteger.TWO); // Charlie's DH private key
        BigInteger cY = g.modPow(cX, p);

        // First round of exchanges
        BigInteger zCA = cY.modPow(aX, p);  // Ally's
        BigInteger zAB = aY.modPow(bX, p);  // Bill's
        BigInteger zBC = bY.modPow(cX, p);  // Charlie's

        // Second round of exchanges
        BigInteger kBCA = zBC.modPow(aX, p);    // Ally's
        BigInteger kCAB = zCA.modPow(bX, p);    // Bill's
        BigInteger kABC = zAB.modPow(cX, p);    // Charlie's

        System.out.println("Ally's: " + kBCA);
        System.out.println("Bill's: " + kCAB);
        System.out.println("Charlie's: " + kABC);

        // https://crypto.stackexchange.com/questions/1025/can-one-generalize-the-diffie-hellman-key-exchange-to-three-or-more-parties


    }
}
