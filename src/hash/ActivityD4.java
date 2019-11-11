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

public class ActivityD4 {
}
