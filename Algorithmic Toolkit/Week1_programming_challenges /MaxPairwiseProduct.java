import java.util.*;
import java.io.*;
import java.lang.Object.*;

/*
Purpose :Find the maximum product of two distinct num- bers in a sequence of non-negative integers.
Input: A sequence of non-negative integers
Output: The Maximun value that can be obtained by multiplying tow different elements from the sequence 

Input Format = The first line contains an integer n. 
               The next line contains n non-negative integers a1,...,an (separated by spaces).

Output Format = The maximum pairwise product.

Sample 1.
Input
3
1 2 3

Output:
6

Sample 2.
Input:
10
7 5 14 2 8 8 10 1 2 3

Output:
140 
*/
public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(int[] numbers) {
        int n = numbers.length;
        int larg = 0;
        int sec_larg = 0;
        for(int i = 0; i < n; i ++) {
            int value = numbers[i];
            if (value > larg) {
                sec_larg = larg;
                larg = value;
            } else {
                if (value > sec_larg) {
                    sec_larg = value;
                }
            }
        }
        larg = Long.parseLong(larg);
        sec_larg = Long.parseLong(sec_larg);
        return larg * sec_larg;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                    InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}