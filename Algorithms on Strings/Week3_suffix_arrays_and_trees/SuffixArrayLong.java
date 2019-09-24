import java.util.*;
import java.io.*;
import java.util.zip.CheckedInputStream;

/*
Task. Construct the suffix array of a string.

Input Format. A string Text ending with a “$” symbol.

Output Format. SuffixArray(Text), that is, the list of starting positions of sorted suffixes
separated by spaces.

########## Sample 1 #########
Input:
AAA$

Output:
3 2 1 0

########## Sample 2 #########
Input:
GAC$

Output:
3 1 2 0

########## Sample 3 #########
Input:
GAGAGAGA$

Output:
8 7 5 3 1 6 4 2 0

*/

public class SuffixArrayLong {
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

    public class Suffix implements Comparable {
        String suffix;
        int start;

        Suffix(String suffix, int start) {
            this.suffix = suffix;
            this.start = start;
        }

        @Override
        public int compareTo(Object o) {
            Suffix other = (Suffix) o;
            return suffix.compareTo(other.suffix);
        }
    }

    //used to calculate the index of an array for each char
    int letterToIndex (char letter)
    {
        switch (letter)
        {
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
            default: assert (false); return 0;
        }
    }

    //sorts the characters using count sort
    public int[] SortCharacters(String S) {
        int[] order = new int[S.length()];
        int[] count = new int[]{0, 0, 0, 0, 0};
        for( int i = 0; i < S.length(); i++) {
            count[letterToIndex(S.charAt(i))] += 1;
        }
        for (int i = 1; i < count.length; i ++) {
            count[i] += count[i -1];
        }
        for (int i = S.length() -1; i >= 0; i--) {
            int c = letterToIndex(S.charAt(i));
            count[c] -= 1;
            order[count[c]] = i;
        }
        return order;
    }

    //computes the char classes for each char
    public int[] ComputeCharClasses(String S, int[] order) {
        int[] Class = new int[S.length()];
        Class[order[0]] = 0;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(order[i]) != S.charAt(order[i - 1]) ) {
                Class[order[i]] = Class[order[i -1]] + 1;
            } else {
                Class[order[i]] = Class[order[i - 1]];
            }
        }
        return Class;
    }

    //sorts the array based on cyclic shift of size L
    public int[] SortDoubled(String S, int L, int[] order, int[] Class) {
        int[] count = new int[S.length()];
        int[] newOrder = new int[S.length()];
        for (int i = 0; i < S.length(); i++) {
            count[Class[i]] += 1;
        }
        for (int j = 1; j < S.length(); j++) {
            count[j] += count[j - 1];
        }
        for (int i = S.length() - 1; i >= 0; i--) {
            int start = (order[i] - L + S.length()) % S.length();
            int cl = Class[start];
            count[cl] -= 1;
            newOrder[count[cl]] = start;
        }
        return newOrder;
    }

    //updates the char classes after shifting the characters by L
    public int[] UpdateClasses(int[] newOrder, int[] Class, int L) {
        int n = newOrder.length;
        int[] newClass = new int[n];
        newClass[newOrder[0]] = 0;
        for (int i = 1; i < n ; i++) {
            int cur = newOrder[i];
            int prev = newOrder[i - 1];
            int mid = (cur + L) % n;
            int midPrev =  (prev + L) % n;
            if (Class[cur] != Class[prev] || Class[mid] != Class[midPrev]) {
                newClass[cur] = newClass[prev] + 1;
            } else {
                newClass[cur]= newClass[prev];
            }
        }
        return newClass;
    }


    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public int[] computeSuffixArray(String text) {
        // write your code here

        int[] order = SortCharacters(text);
        int[] Class = ComputeCharClasses(text, order);
        int L = 1;
        while (L < text.length()) {
            order = SortDoubled(text, L, order, Class);
            Class = UpdateClasses(order, Class, L);
            L = L* 2;
        }
        return order;
    }


    static public void main(String[] args) throws IOException {
        new SuffixArrayLong().run();

    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
    }
}
