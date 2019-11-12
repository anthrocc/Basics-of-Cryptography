package hash;

/*
    We need to compute the HMAC of the message:
    m = "Mainly cloudy with 40 percent chance of showers"
    using SHA1 and the symmetric key:
    K = "This is an ultra-secret key"

    The HMAC algorithm can be found in this (https://en.wikipedia.org/wiki/HMAC) Wikipedia article. Implement the
    algorithm as described in the article in the following two cases:

    a) The padding constants are:
        byte[] opad = bytes valued 0x5c
        byte[] ipad = bytes valued 0x36
    b) The padding constants are reversed:
        byte[] opad = bytes valued 0x36
        byte[] ipad = bytes valued 0x5c
 */

import util.CryptoTools;

import java.security.MessageDigest;

public class ActivityD3 {

    // hash - hash function to be used
    private String hashFunc = "SHA-1";

    // blockSize - the block size of the underlying hash function (e.g. 64 bytes for SHA-1)
    private int blockSize = 64;

    // outputSize - the output size of the underlying hash function (e.g. 20 bytes for SHA-1)
    private int outputSize = 20;

    public static void main(String[] args) throws Exception {

        ActivityD3 ob = new ActivityD3();

        /* ------ Inputs ------ */
        // key - array of bytes
        byte[] key = "This is an ultra-secret key".getBytes();

        // message - array of bytes to be hashed
        byte[] message = "Mainly cloudy with 40 percent chance of showers".getBytes();

        // create the hash
        MessageDigest md = MessageDigest.getInstance(ob.hashFunc);
        byte[] hash = md.digest(message);

        // Keys longer than blockSize are shortened by hashing them
        if (key.length > ob.blockSize) {
            byte[] shortKey = md.digest(key); //Key becomes outputSize bytes long
            key = shortKey;
        }

        // Keys shorter than blockSize are padded to blockSize by padding with zeros on the right
        if (key.length < ob.blockSize) {
            byte[] longKey = new byte[ob.blockSize];
            for (int i = 0; i < longKey.length; i++) {
                if (i < key.length)
                    longKey[i] = key[i];
                else {
                    longKey[i] = 0;
                }
            }
            key = longKey;
        }

        byte[] regHMACHash = ob.regularHashFunction(message, key);
        byte[] revHMACHash = ob.reverseHashFunction(message, key);
        System.out.println("Regular Padding Hash: 0x " + CryptoTools.bytesToHex(regHMACHash));
        System.out.println("Reverse Padding Hash: 0x " + CryptoTools.bytesToHex(revHMACHash));

    }

    public byte[] regularHashFunction(byte[] message, byte[] key) throws Exception {
        byte[] opad = new byte[key.length];
        byte[] ipad = new byte[key.length];
        // o_key_pad ← key xor [0x5c * blockSize]   Outer padded key
        byte[] tempOuterHex = CryptoTools.hexToBytes("5c");
        // i_key_pad ← key xor [0x36 * blockSize]   Inner padded key
        byte[] tempInnerHex = CryptoTools.hexToBytes("36");

        for (int j = 0; j < key.length; j++) {
            opad[j] = (byte) (key[j] ^ tempOuterHex[0]);
            ipad[j] = (byte) (key[j] ^ tempInnerHex[0]);
        }

        byte[] hashAndInner = new byte[ipad.length + message.length];

        // Counter variable for second half of array
        int c = 0;

        for (int i = 0; i < hashAndInner.length; i++) {
            if (i < ipad.length)
                hashAndInner[i] = ipad[i];
            else {
                hashAndInner[i] = message[c];
                c++;
            }
        }

        MessageDigest md = MessageDigest.getInstance(hashFunc);
        byte[] innerHash = md.digest(hashAndInner);

        byte[] outerAndHash = new byte[opad.length + innerHash.length];

        // Reset counter variable
        c = 0;

        for (int i = 0; i < outerAndHash.length; i++) {
            if (i < opad.length)
                outerAndHash[i] = opad[i];
            else {
                outerAndHash[i] = message[c];
                c++;
            }
        }

        byte[] finalHash = md.digest(outerAndHash);

        return finalHash;
    }

    public byte[] reverseHashFunction(byte[] message, byte[] key) throws Exception {
        byte[] opad = new byte[key.length];
        byte[] ipad = new byte[key.length];
        // o_key_pad ← key xor [0x5c * blockSize]   Outer padded key
        byte[] tempOuterHex = CryptoTools.hexToBytes("36");
        // i_key_pad ← key xor [0x36 * blockSize]   Inner padded key
        byte[] tempInnerHex = CryptoTools.hexToBytes("5c");

        for (int j = 0; j < key.length; j++) {
            opad[j] = (byte) (key[j] ^ tempOuterHex[0]);
            ipad[j] = (byte) (key[j] ^ tempInnerHex[0]);
        }

        byte[] hashAndInner = new byte[ipad.length + message.length];

        // Counter variable for second half of array
        int c = 0;

        for (int i = 0; i < hashAndInner.length; i++) {
            if (i < ipad.length)
                hashAndInner[i] = ipad[i];
            else {
                hashAndInner[i] = message[c];
                c++;
            }
        }

        MessageDigest md = MessageDigest.getInstance(hashFunc);
        byte[] innerHash = md.digest(hashAndInner);

        byte[] outerAndHash = new byte[opad.length + innerHash.length];

        // Reset counter variable
        c = 0;

        for (int i = 0; i < outerAndHash.length; i++) {
            if (i < opad.length)
                outerAndHash[i] = opad[i];
            else {
                outerAndHash[i] = message[c];
                c++;
            }
        }

        byte[] finalHash = md.digest(outerAndHash);

        return finalHash;
    }
}
