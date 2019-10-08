package resourcePrograms;



import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.BitSet;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class B1Q1
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
        for(int i=0;i<3;i++)
        {
            int rand=(int) (Math.random()*63);
            ct2=encrypt(pt,key);
            bitset.flip(rand);
            pt=bitset.toByteArray();
            ct1=encrypt(pt,key);
            difference=CryptoTools.xor(ct1, ct2);
            out =new String(difference,"UTF-8");
            System.out.println(CryptoTools.bytesToBin(difference));//Gives wierd output :|
        }


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