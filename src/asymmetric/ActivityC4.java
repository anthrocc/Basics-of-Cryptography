package asymmetric;

import java.math.BigInteger;

public class ActivityC4 {
    // Returns modulo inverse of a with respect to m using extended Euclid Algorithm. Refer below post for details:
    static BigInteger inv(BigInteger a, BigInteger m)
    {
        BigInteger m0 = m;
        BigInteger t;
        BigInteger q;
        BigInteger x0 = BigInteger.ZERO;
        BigInteger x1 = BigInteger.ONE;


        if (m.equals(BigInteger.ONE))
            return BigInteger.ZERO;

        // Apply extended Euclid Algorithm
        while (a.compareTo(BigInteger.ONE) == 1)
        {
            // q is quotient
            q = a.divide(m);

            t = m;

            // m is remainder now, process same as euclid's algo
            m = a.mod(m);

            a = t;

            t = x0;

            x0 = x1.subtract(q.multiply(x0));

            x1 = t;
        }

        // Make x1 positive
        if (x1.compareTo(BigInteger.ZERO) == -1)
            x1 = x1.add(m0);

        return x1;
    }

    // k is size of num[] and rem[].
    // Returns the smallest number
    // x such that:
    // x % num[0] = rem[0],
    // x % num[1] = rem[1],
    // ..................
    // x % num[k-2] = rem[k-1]
    // Assumption: Numbers in num[] are pairwise
    // coprime (gcd for every pair is 1)
    static BigInteger findMinX(BigInteger num[], BigInteger rem[], int k)
    {
        // Compute product of all numbers
        BigInteger prod = BigInteger.ONE;
        for (int i = 0; i < k; i++)
            prod = prod.multiply(num[i]);

        // Initialize result
        BigInteger result = BigInteger.ZERO;

        // Apply above formula
        for (int i = 0; i < k; i++)
        {
            BigInteger pp = prod.divide(num[i]);
            result = result.add(rem[i].multiply(inv(pp, num[i])).multiply(pp));

        }

        return result.mod(prod);
    }

    // Driver method
    public static void main(String args[])
    {
        // Find the smallest integer x that satisfies the following two properties:
        // 1) x mod 1055827021987 = 365944767426
        // 2) x mod 973491987203  = 698856040412

        BigInteger aNum = new BigInteger("1055827021987");
        BigInteger aRem = new BigInteger("365944767426");
        BigInteger bNum = new BigInteger("973491987203");
        BigInteger bRem = new BigInteger("698856040412");
        BigInteger num[] = {aNum, bNum};
        BigInteger rem[] = {aRem, bRem};
        int k = num.length;
        BigInteger x = findMinX(num, rem, k);
        System.out.println("x is " + x);
        System.out.println("------------ CHECK ------------");
        System.out.println("x mod 1055827021987 = 365944767426: " + x.mod(aNum));
        System.out.println("x mod 973491987203  = 698856040412: " + x.mod(bNum));
    }
}
