package symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_PKCS5Padding_ECB
{

	public static void main(String[] args) throws Exception
	{
		byte[] pt = "EECS3481".getBytes();
		byte[] ky = CryptoTools.hexToBytes("34567abcdef03211");
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] ct = cipher.doFinal(pt);
		
		System.out.println("CT = " + CryptoTools.bytesToHex(ct));
		
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] bk = cipher.doFinal(ct);
		System.out.println("BK = " + new String(bk) + "<");
		

	}

}
