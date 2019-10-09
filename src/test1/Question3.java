package test1;

import util.CryptoTools;

public class Question3 {

    public static void main(String[] args) throws Exception {

        byte[] cairoPT = CryptoTools.hexToBytes("436169726f");
        byte[] ct = CryptoTools.hexToBytes("264139130B");
        byte[] msg = CryptoTools.hexToBytes("5061726973");
        byte[] key = CryptoTools.xor(msg, ct);
        System.out.println("Key = "+ CryptoTools.bytesToHex(key));
        byte[] newCT = CryptoTools.xor(cairoPT, key);
        System.out.println("newCt = "+ CryptoTools.bytesToHex(newCT));

    }
}
