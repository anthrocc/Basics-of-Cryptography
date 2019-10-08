package rehearsalTest1;

import util.CryptoTools;

public class Q1_V_Crypta {

    public static void main(String[] args) throws Exception {

        byte[] ct = CryptoTools.fileToBytes("data/RehearsalTest1/Q1.ct");
        byte[] pt = new byte[ct.length];

        //int[] frq = CryptoTools.getFrequencies(ct);
        int curMatch = 0, maxMatch = 0, curShift = 1, maxShift = 1;
//        for (; curShift <= 20; curShift++) {
//            for (int i = 0; i < ct.length - curShift; i++) {
//                if (ct[i] == ct[i + curShift]) {
//                    curMatch++;
//                }
//            }
//            if (curMatch > maxMatch) {
//                maxMatch = curMatch;
//                maxShift = curShift;
//            }
//
//            curMatch = 0;
//        }

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

        for(int m = 0 ; m < ct.length; m ++){
            pt[m] = (byte)((ct[m] - 'A' - (key[m%maxShift] -'A')) % 26);
            if(pt[m] < 0){
                pt[m] += 26;
            }
            pt[m] += 'A';
        }

        CryptoTools.bytesToFile(pt, "data/RehearsalTest1/Q1.pt");
        System.out.println("CipherText decoded and saved under data/RehearsalTest1/Q1.pt");
    }

}