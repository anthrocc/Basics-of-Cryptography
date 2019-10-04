package foundation;

import util.CryptoTools;

public class V_Crypta {
    public static void main(String[]args) throws Exception {

        // Key should be "ELABORATE"
        // Size 9

        double expecIC = 0.067; // Index of coincidence for English
        double totalIC = 0;
        double alphabetSize = 26;
        double textLength;
        double keySize1;
        double keySize2;
        double keySize3;
        double keySize4;
        double keySizeF;
        double keySize;

        byte ct[] = CryptoTools.fileToBytes("data/MSG4.ct");

        textLength = ct.length;
        totalIC = CryptoTools.getIC(ct);


        keySize = ((expecIC - 1/alphabetSize) * textLength) / ((textLength - 1) * totalIC - textLength * 1/alphabetSize + expecIC);

        keySize1 = (expecIC - 1/alphabetSize) * textLength;  // GOOD
        keySize2 = (textLength - 1) * totalIC;
        keySize3 = (textLength * 1/alphabetSize) + expecIC; // GOOD
        keySize4 = keySize2 - keySize3;
        keySizeF = keySize1 / keySize4;



        //keySize = ((expecIC - 1/alphabetSize) * 1000) / ((1000 - 1) * 0.045 - 1000 * 1/alphabetSize + expecIC);

        //System.out.println("KeySize1 " + keySize1);
        //System.out.println("KeySize2 " + keySize2);
        //System.out.println("KeySize3 " + keySize3);
        //System.out.println("KeySize4 " + keySize4);
        System.out.println("KeySizeF " + keySizeF);
        System.out.println("KeySize " + keySize);


    }
}
