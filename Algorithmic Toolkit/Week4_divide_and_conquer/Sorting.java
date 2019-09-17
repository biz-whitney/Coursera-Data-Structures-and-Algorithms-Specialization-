import java.io.*;
import java.util.*;

/*

Task. To force the given implementation of the quick sort algorithm to efficiently process
    sequences with few unique elements, your goal is replace a 2-way partition with a 3-way
    partition. That is, your new partition procedure should partition the array into three
    parts: < 𝑥 part, = 𝑥 part, and > 𝑥 part.
Input Format. The first line of the input contains an integer 𝑛. The next line contains
    a sequence of 𝑛 integers 𝑎0, 𝑎1, . . . , 𝑎𝑛−1.

Output Format. Output this sequence sorted in non-decreasing order.

######### Sample 1 ########
Input:
5
2 3 9 2 2

Output:
2 2 2 3 9

 */

public class Sorting {
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
      //write your code here
        int x = a[l];
        int j = l;
        int count = 0;
        int n = l + 1;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] < x) {
                j++;
                int t = a[i];
                a[i] = a[n];
                a[n] = a[n - count];
                a[j] = t;
                n ++;
            }
            else if (a[i] == x) {
                count += 1;
                if (i == n) {
                   n ++;
                }
                else if(n == 1){
                    int t = a[i];
                    a[i] = a[n];
                    a[n] = t;
                    n ++;
                }else {
                    int temp = a[i];
                    a[i] = a[n];
                    a[n] = temp;
                    n ++;
                }
            }
        }

        int t = a[l];
        a[l] = a[j];
        a[j] = t;


        int m1 = j;
        int m2 = j + count;
        int[] m = {m1, m2};
        return m;
        }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        //int k = 2;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int[] m = partition3(a, l, r);
        //int m = partition2(a, l, r);
        //randomizedQuickSort(a, l, m - 1);
        //randomizedQuickSort(a, m + 1, r);
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    private static void QuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int m = partition2(a, l, r);
        randomizedQuickSort(a, l, m - 1);
        randomizedQuickSort(a, m + 1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }


        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
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

