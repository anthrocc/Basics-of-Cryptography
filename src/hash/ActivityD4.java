package hash;

/*
    A password psw was hashed using the code fragment:
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(psw.getBytes());
        System.out.println(CryptoTools.bytesToHex(digest));
    and it produced the hash: 0x 5ae9b7f211e23aac3df5f2b8f3b8eada.

    a) Show (by presenting a logical argument) that it is impossible to determine the password.
    b) Show that it is nevertheless possible to find some pre-image of the hash.
    c) Estimate the time needed to find such a pre-image.
    d) Find such a pre-image. You can use an online password cracking tool.

 */

import util.CryptoTools;

import java.security.MessageDigest;

public class ActivityD4 {
    public static void main(String[] args) throws Exception {
       /* ------------ a) ------------
       * It is impossible because hash functions provide one-way-ness.
       * Also because MD5 includes padding (salt) to the original plaintext password meaning it is now even more
       * difficult to find the original password because we don't know what is password and what is salt.
       * */

        /* ------------ b) ------------
         * It is possible due to a preimage attack in which we try and find a value that equals the hash.
         * This is a POSSIBLE preimage of the hash and not THE preimage because of the pigeonhole principle. We can
         * also use a collision attack to find two messages that generate the same hash.
         * */

        /* ------------ c) ------------
         * O(2^n) is the worst-case complexity.
         *
         * So if the hash is 2^32, there are 2^32 possibilities therefore if it takes 1key/microsec/core and
         * cluster 1M/sec it takes 2.15ms for a cluster and 1hour for a core.
         *
         * 10^22 years in total with 128 as the hash size for MD5
         * */

        /* ------------ d) ------------
         * A possible password (preimage) is "crypto"
         * We can confirm this as follows
         * */

        String psw = "crypto";
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(psw.getBytes());
        System.out.println("Generated hash: " + CryptoTools.bytesToHex(digest));
        System.out.println("Original hash: 5ae9b7f211e23aac3df5f2b8f3b8eada");
    }
}
