package symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_NoPadding_ECB
{

	public static void main(String[] args) throws Exception
	{
		byte[] pt = "Hello World. My ".getBytes();
		byte[] ky = CryptoTools.hexToBytes("34567abcdef03211");
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] ct = cipher.doFinal(pt);
		
		System.out.println("CT = " + CryptoTools.bytesToHex(ct));
		
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] bk = cipher.doFinal(ct);
		System.out.println("BK = " + new String(bk) + "<");
		
//
	}

}
