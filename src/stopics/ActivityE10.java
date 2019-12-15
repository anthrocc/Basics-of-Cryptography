package stopics;

public class ActivityE10 {
    public static void main(String[] args) {
        // A secret m = 44 is to be shared amongst 5 parties such that:
        //  a) The share of each party is at least two-digit,
        //  b) Any 3 of them can reconstitute the secret, and
        //  c) The entropy of knowing any 2 of them is the same as the entropy of knowing none of them.

        int secret = 44;
        int shares = 5;
        int k = 3;
        int mod = 101;  // Random modulus p > secret, p > n

        for(int i = 0; i < 5; i++)
            System.out.println("(" + (i+1) + ", " + formula(i+1, secret, mod) + ")");
    }

    // Generate the shares
    public static int formula(int x, int M, int mod) {
        int y = (int) (M + (28 * x) + 19 * (Math.pow(x, 2)));
        int answer = ((y % mod) + mod) % mod;
        return answer;
    }
}
