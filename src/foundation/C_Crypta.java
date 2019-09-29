package foundation;

import util.CryptoTools;

public class C_Crypta {
    public static void main(String[]args) throws Exception {
        byte ct[] = CryptoTools.fileToBytes("data/MSG2.ct");
        int freq[] = new int[26];
        int tempI;
        String tempS;

        for(int i = 0; i < ct.length; i++) {

            switch(ct[i])
            {
                case 'A':
                    freq[0]++;
                    break;
                case 'B':
                    freq[1]++;
                    break;
                case 'C':
                    freq[2]++;
                    break;
                case 'D':
                    freq[3]++;
                    break;
                case 'E':
                    freq[4]++;
                    break;
                case 'F':
                    freq[5]++;
                    break;
                case 'G':
                    freq[6]++;
                    break;
                case 'H':
                    freq[7]++;
                    break;
                case 'I':
                    freq[8]++;
                    break;
                case 'J':
                    freq[9]++;
                    break;
                case 'K':
                    freq[10]++;
                    break;
                case 'L':
                    freq[11]++;
                    break;
                case 'M':
                    freq[12]++;
                    break;
                case 'N':
                    freq[13]++;
                    break;
                case 'O':
                    freq[14]++;
                    break;
                case 'P':
                    freq[15]++;
                    break;
                case 'Q':
                    freq[16]++;
                    break;
                case 'R':
                    freq[17]++;
                    break;
                case 'S':
                    freq[18]++;
                    break;
                case 'T':
                    freq[19]++;
                    break;
                case 'U':
                    freq[20]++;
                    break;
                case 'V':
                    freq[21]++;
                    break;
                case 'W':
                    freq[22]++;
                    break;
                case 'X':
                    freq[23]++;
                    break;
                case 'Y':
                    freq[24]++;
                    break;
                case 'Z':
                    freq[25]++;
                    break;
            }
        }

        for(int j = 0; j < freq.length; j++) {
            tempI = j + 65;
            tempS = Character.toString ((char) tempI);
            System.out.println(tempS + ":" + freq[j]);
        }

        // The key is 22 based on this result
        // A is the most frequent at 70 occurrences which is E
        // This is a shift of 22
    }
}
