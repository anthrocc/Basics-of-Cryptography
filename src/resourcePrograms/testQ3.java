package resourcePrograms;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.BitSet;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class testQ3
{

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchPaddingException, Exception
    {
        byte[] pt="Facebook".getBytes();
        byte[] key="universe".getBytes();
        byte[] ct2;
        byte[] ct1;
        byte[] difference;

        BitSet bitset = BitSet.valueOf(pt);
        String out;

        int rand=30;
        ct2=encrypt(pt,key);
        bitset.flip(rand);
        pt=bitset.toByteArray();
        ct1=encrypt(pt,key);
        difference=CryptoTools.xor(ct1, ct2);


        System.out.println(CryptoTools.bytesToBin(ct2));//Gives wierd output :|
        System.out.println(CryptoTools.bytesToBin(ct1));

        System.out.println(CryptoTools.bytesToBin(difference));
        int sum=0;
        for(int i=0;i<difference.length;i++)
        {
            sum=+difference[i];

        }
        System.out.println(sum);
    }

    public static byte[] encrypt (byte[] pt,byte[] key) throws UnsupportedEncodingException, Exception, NoSuchPaddingException
    {

        Cipher engine=Cipher.getInstance("DES/ECB/NoPadding");
        Key myKey=new SecretKeySpec(key,"DES");
        engine.init(Cipher.ENCRYPT_MODE, myKey);
        byte[] ct=engine.doFinal(pt);
        return ct;
    }

}
