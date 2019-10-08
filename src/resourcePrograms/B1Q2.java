package resourcePrograms;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import util.CryptoTools;

public class B1Q2
{
    public static void main(String[] args) throws Exception
    {
        byte[] IV = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
        byte[] CT = CryptoTools.hexToBytes("F38ADBA8A7B4CC613578355032205D50");
        byte[] ky = CryptoTools.hexToBytes("9F0DCEDB322F3C6873F9256E01376BA4");


        IvParameterSpec aps = new IvParameterSpec(IV);
        Cipher engine = Cipher.getInstance("AES/CBC/PKCS5Padding");
        Key myKey = new SecretKeySpec(ky, "AES");




        engine.init(Cipher.DECRYPT_MODE, myKey, aps);
        byte[] back = engine.doFinal(CT);

        System.out.println(new String(back));
        // This fragment is used as a debugging tool to
        // verify that we can recover the plaintext by
        // decrypting the ciphertext.
		/*
		engine.init(Cipher.DECRYPT_MODE, myKey);
		byte[] back = engine.doFinal(ct);
		System.out.println(new String(back));
		*/
    }
}