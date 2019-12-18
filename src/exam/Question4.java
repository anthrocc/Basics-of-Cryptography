package exam;

import java.math.BigInteger;
import java.util.Random;

import util.CryptoTools;

public class Question4 {
    public static void main(String[] args) {
    	// Need to find g
    	
    	BigInteger p = new BigInteger("436916316386107509272284723552448214887"); // Prime p
        BigInteger aX = new BigInteger("100650611865305818152559094999245937373"); // Alice's DH private key

        BigInteger aY = new BigInteger("6506318399664528040589769449793996118"); // Alice's public DH key
        
        // Check if gcd(aX, p-1) = 1 (Euler's)
        BigInteger x = aX.gcd(p.subtract(BigInteger.ONE));
        System.out.println("gcd(aX, p-1) = " + x);
        
        BigInteger u = aX.modInverse(p.subtract(BigInteger.ONE));
        
        BigInteger g = aY.modPow(u, p);
        
        System.out.println("g = " + g);
        
        // Check if g is correct 
        BigInteger check = g.modPow(aX, p); // Alice's public DH key
        System.out.println("Original: " + aY);
        System.out.println("Reconstruct: " + check);
    }
}
