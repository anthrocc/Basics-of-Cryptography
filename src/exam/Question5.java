package exam;

import java.math.BigInteger;

public class Question5 {
	public static void main(String[] args) {

        final BigInteger prime = new BigInteger("311");

        final SecretShare[] shares = Question5.generate();

        SecretShare[] sharesToViewSecret = new SecretShare[] {shares[0],shares[1]};
        BigInteger result = Question5.combine(sharesToViewSecret, prime);
        
        System.out.println("The secret is: " + result);
	}
	
	public static SecretShare[] generate()
    {

        final SecretShare[] shares = new SecretShare[5];
        
        shares[0] = new SecretShare(1, new BigInteger("217"));
        shares[1] = new SecretShare(2, new BigInteger("237"));
        shares[2] = new SecretShare(3, new BigInteger("277"));
        shares[3] = new SecretShare(4, new BigInteger("6"));
        shares[4] = new SecretShare(5, new BigInteger("106"));


        return shares;
    }
	
	public static BigInteger combine(final SecretShare[] shares, final BigInteger prime)
    {
        BigInteger accum = BigInteger.ZERO;

        for(int formula = 0; formula < shares.length; formula++)
        {
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for(int count = 0; count < shares.length; count++)
            {
                if(formula == count)
                    continue; // If not the same value

                int startposition = shares[formula].getNumber();
                int nextposition = shares[count].getNumber();

                numerator = numerator.multiply(BigInteger.valueOf(nextposition).negate()).mod(prime); // (numerator * -nextposition) % prime;
                denominator = denominator.multiply(BigInteger.valueOf(startposition - nextposition)).mod(prime); // (denominator * (startposition - nextposition)) % prime;
            }
            BigInteger value = shares[formula].getShare();
            BigInteger tmp = value.multiply(numerator) . multiply(modInverse(denominator, prime));
            accum = prime.add(accum).add(tmp) . mod(prime); //  (prime + accum + (value * numerator * modInverse(denominator))) % prime;
        }

        return accum;
    }
	
    private static BigInteger[] gcdD(BigInteger a, BigInteger b)
    { 
        if (b.compareTo(BigInteger.ZERO) == 0)
            return new BigInteger[] {a, BigInteger.ONE, BigInteger.ZERO}; 
        else
        { 
            BigInteger n = a.divide(b);
            BigInteger c = a.mod(b);
            BigInteger[] r = gcdD(b, c); 
            return new BigInteger[] {r[0], r[2], r[1].subtract(r[2].multiply(n))};
        }
    }
	
    private static BigInteger modInverse(BigInteger k, BigInteger prime)
    { 
        k = k.mod(prime);
        BigInteger r = (k.compareTo(BigInteger.ZERO) == -1) ? (gcdD(prime, k.negate())[2]).negate() : gcdD(prime,k)[2];
        return prime.add(r).mod(prime);
    }
    
   
}
