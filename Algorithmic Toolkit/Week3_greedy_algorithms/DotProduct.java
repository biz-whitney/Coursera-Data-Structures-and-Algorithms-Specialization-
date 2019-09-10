import java.util.*;
import java.lang.*;

/*

Task. Given two sequences 𝑎1,𝑎2,...,𝑎𝑛 (𝑎𝑖 is the profit per click of the 𝑖-th ad) and
        𝑏1,𝑏2,...,𝑏𝑛 (𝑏𝑖 is the average number of clicks per day of the 𝑖-th slot), we
        need to partition them into 𝑛 pairs (𝑎𝑖,𝑏𝑗) such that the sum of their products
        is maximized.
Input Format. The first line contains an integer 𝑛, the second one contains a sequence
        of integers 𝑎1,𝑎2,...,𝑎𝑛, the third one contains a sequence of integers 𝑏1,𝑏2,...,𝑏𝑛.
Output Format. Output the maximum value of ∑︀ 𝑎𝑖𝑐𝑖, where 𝑐1, 𝑐2, . . . , 𝑐𝑛 is a permutation
        of 𝑏1,𝑏2,...,𝑏𝑛.


###### Sample 1 #######
Input:
1
23
39

Output :
879

###### Sample 2 #######
Input:
 3
1 3 -5
-2 4 1

Output :
23
 */


public class DotProduct {
    private static long maxDotProduct(int[] a, int[] b) {
        //write your code here
        Arrays.sort(a);
        Arrays.sort(b);
        Collections.reverse(Arrays.asList(a));
        Collections.reverse(Arrays.asList(b));
        long result = 0;
        for (int i = 0; i < a.length; i++) {
            result += Long.valueOf(a[i]) * Long.valueOf(b[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

