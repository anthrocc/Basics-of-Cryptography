package hash;

public class ActivityD5 {
    /*
    With x people in a room, compute the probability S that at least two of them have the same birthday as follows (ignore leap years):
        a)  Do the calculation exactly by subtracting 1 from the probability of all birthdays being different.
        b)  Use the approximation: 1 - x = e-x (valid for small x) to express S as:
            1 - e-x2/(2N)
            where N=365 is the number of days in the year.
        c)  Show that S becomes ≥ 50% when x >= 1.177*sqrt(N)
        d)  Pick a value for x and verify the correctness of the above analysis experimentally. Hint: Monte Carlo.
     */
    public static void main(String[] args) {

        System.out.println(birthdayA(10));
        System.out.println(birthdayB(50, 365));

    }

    public static double birthdayA(double k) {
        double temp = k * - 1;
        temp = temp / 365;
        double x = 1 - Math.exp(temp);

        return x;
    }

    public static double birthdayB(double k, double n) {
        double temp = Math.pow(k, 2);
        System.out.println("1: " + temp);
        temp = k * - 1;
        System.out.println("2: " + temp);
        temp = temp / (2 * n);
        System.out.println("3: " + temp);

        double x = 1 - Math.exp(temp);

        return x;
    }

    //public static double birthdayC()
}
