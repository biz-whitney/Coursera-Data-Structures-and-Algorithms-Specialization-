import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Task. Find all occurrences of a pattern in a string.

Input Format. Strings 𝑃 𝑎𝑡𝑡𝑒𝑟𝑛 and 𝐺𝑒𝑛𝑜𝑚𝑒.

Output Format. All starting positions in 𝐺𝑒𝑛𝑜𝑚𝑒 where 𝑃 𝑎𝑡𝑡𝑒𝑟𝑛 appears as a
    substring (using 0-based indexing as usual).

######### Sample 1 ########
Input:
TACG
GT

Out:


######### Sample 2 ########
Input:
ATA
ATATA

Out:
0 2

######### Sample 3 ########
Input:
ATAT
GATATATGCATATACTT

Out:
1 3 9
 */


public class KnuthMorrisPratt {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    // Find all the occurrences of the pattern in the text and return
    // a list of all positions in the text (starting from 0) where
    // the pattern starts in the text.
    public List<Integer> findPattern(String pattern, String text) {
        // Implement this function yourself

        ArrayList<Integer> result = new ArrayList<Integer>();
        String string = pattern + "$" + text;
        char[] charArray = string.toCharArray();
        int[] prefixArray = new int[string.length()];
        ComputePrefixFunction(charArray, prefixArray);
        for (int i = pattern.length() + 1; i < string.length(); i++) {
            if (prefixArray[i] == pattern.length()){
                result.add(i - 2*pattern.length());
            }
        }

        return result;
    }

    //prefix function to calculate the longest prefix
    public void ComputePrefixFunction(char[] charArray, int[] prefixArray) {
        prefixArray[0] = 0;
        int border = 0;
        for (int i = 1; i < charArray.length; i ++) {
            while(border > 0 && charArray[i] != charArray[border]) {
                border = prefixArray[border -1];
            }
            if (charArray[i] == charArray[border]) {
                border += 1;
            }
            else {
                border = 0;
            }
            prefixArray[i] = border;
        }
    }

    static public void main(String[] args) throws IOException {
        new KnuthMorrisPratt().run();
    }

    public void print(List<Integer> x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String pattern = scanner.next();
        String text = scanner.next();
        List<Integer> positions = findPattern(pattern, text);
        print(positions);
    }
}
