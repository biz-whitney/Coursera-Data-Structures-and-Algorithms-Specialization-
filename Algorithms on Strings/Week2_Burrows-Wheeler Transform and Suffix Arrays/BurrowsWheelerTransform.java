import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Task. Construct the Burrows–Wheeler transform of a string.

Input Format. A string Text ending with a “$” symbol.

Output Format. BWT(Text).

########## Sample 1 ###########
Input:
AA$

Output:
AA$

########## Sample 2 ############
Input:
ACACACAC$

Output:
CCCC$AAAA

########## Sample 3 ############
Input:
AGACATA$

Output:
ATG$CAAA

 */

public class BurrowsWheelerTransform {
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

    String BWT(String text) {
        StringBuilder result = new StringBuilder();

        // write your code here
        char[] textArray = text.toCharArray();
        int n = textArray.length;
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i ++) {
            for ( int j = 0; j < n; j++) {
                matrix[i][j] = textArray[(i + j) % n];
            }
        }
        Arrays.sort(matrix, new Comparator<char[]>() {
            @Override
            public int compare(char[] o1, char[] o2) {
                for (int i = 0; i < o1.length; i++) {
                    if (o1[i] < o2[i]) {
                        return -1;
                    }
                    if (o1[i] > o2[i]) {
                        return 1;
                    }
                }
                return 0;
            }
        });
        for (int i = 0; i < n; i ++) {
            result.append(matrix[i][n - 1]);
        }
        return result.toString();
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
        //new BurrowsWheelerTransform().BWT(args[0]);
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
