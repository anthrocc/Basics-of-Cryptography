package misc;

import util.CryptoTools;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class PictureEncryption {
    public static void main(String[]args) throws Exception {

        PictureEncryption pic = new PictureEncryption();

        // Key, IV, and CipherText declaration
        byte[] key = CryptoTools.hexToBytes("9F0DCEDB322F3C6873F9256E01376BA4");
        byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
        byte[] pt = pic.extractBytes("data/misc/l8h3fe9rrvy31.jpg");

        // New Key and instance of a cipher
        Key secret = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        AlgorithmParameterSpec aps = new IvParameterSpec(iv);

        // Initialize the new cipher and decrypt
        cipher.init(Cipher.ENCRYPT_MODE, secret, aps);
        byte[] temp = cipher.doFinal(pt);

    }

    public byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }

}
