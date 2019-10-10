package test1;

import util.CryptoTools;

public class Question1 {

    public static void main(String[] args) throws Exception {

        byte[] ct = CryptoTools.fileToBytes("data/Test1/question1.ct");
        byte[] pt = new byte[ct.length];
        int key1 = 49;
        char[] key2 = "MIRACULOUS".toCharArray();

        int curMatch = 0, maxMatch = 0, curShift = 1, maxShift = 1;

        while (curShift <= 20) {
            for (int i = 0; i < ct.length - curShift; i++) {
                if (ct[i] == ct[i + curShift]) {
                    curMatch++;
                }
            }
            if (curMatch > maxMatch) {
                maxMatch = curMatch;
                maxShift = curShift;
            }

            curMatch = 0;
            curShift++;
        }

        System.out.println("Key Size = " + maxShift);
        char[] key = new char[maxShift];

        for (int j = 0; j < maxShift; j++) {
            int[] freq = new int[26];

            for (int k = j; k < ct.length; k += maxShift) {

                freq[ct[k] - 'A']++;
            }

            int lar = 0;
            int lari = 0;
            for (int i = 0; i < freq.length; i++) {

                if (freq[i] > lar) {
                    lar = freq[i];
                    lari = i;
                }
            }
            int shift = ('A' + lari) -'E';

            if(shift < 0){
                shift += 26;
            }
            shift += 'A';

            key[j] = (char)shift;
        }
        System.out.println("The key is most likely: " + new String(key));

        key = key2;

        System.out.println("Decrypting with key: " + new String(key));

        for(int m = 0 ; m < ct.length; m ++){
            pt[m] = (byte)((ct[m] - 'A' - (key[m%maxShift] -'A')) % 26);
            if(pt[m] < 0){
                pt[m] += 26;
            }
            pt[m] += 'A';
        }

        byte[] finalPT = new byte[pt.length];

        for (int i = 0; i < ct.length; i++)
        {
            int tmp = (pt[i] - 'A' - key1) % key1;
            if (tmp < 0) tmp+= key1;
            finalPT[i] = (byte) (tmp + 'A');
        }

        CryptoTools.bytesToFile(finalPT, "data/Test1/question1.pt");
        System.out.println("CipherText decoded and saved under data/Test1/question1.pt");
    }

}