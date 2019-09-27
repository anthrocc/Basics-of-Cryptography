package foundation;

import util.CryptoTools;

public class C_Exhaustive {
    public static void main(String[]args) throws Exception {
        byte ct[] = CryptoTools.fileToBytes("data/MSG2.ct");
        byte pt[] = new byte[ct.length];
        byte tmpAr[] = new byte[ct.length];
        int freq[];
        int maxInd = 0;
        double shift[] = new double[26];
        double max = 0;
        double sum = 0;

        for(int j = 0; j < 26; j++) {
            for (int i = 0; i < ct.length; i++) {
                int tmp = (ct[i] - 'A' - j) % 26;
                if (tmp < 0)
                    tmp += 26;
                tmpAr[i] = (byte) (tmp + 'A');
            }
            freq = CryptoTools.getFrequencies(tmpAr);
            for(int n = 0; n < 26; n++) {
                shift[j] +=  (double) (freq[n]) * CryptoTools.ENGLISH[n];
            }
            System.out.println("Shift: " + j + " | Dot Product: " + shift[j]);
        }

        for(int m = 0; m < shift.length; m++) {
            if(shift[m] > max) {
                max = shift[m];
                maxInd = m;
            }
        }

        System.out.println("Max Shift: " + maxInd + " | Max Value: " + max);

        for (int i = 0; i < ct.length; i++) {
            int tmp = (ct[i] - 'A' - maxInd) % 26;
            if (tmp < 0)
                tmp += 26;
            pt[i] = (byte) (tmp + 'A');
        }

        CryptoTools.bytesToFile(pt, "data/MSG2.pt");
    }
}
