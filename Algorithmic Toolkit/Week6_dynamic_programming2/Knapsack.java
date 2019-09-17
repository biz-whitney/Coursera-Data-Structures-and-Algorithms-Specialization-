import java.util.*;
/*
Task. Given 𝑛 gold bars, find the maximum weight of gold that fits into a bag of capacity 𝑊 .

Input Format. The first line of the input contains the capacity 𝑊 of a knapsack and the number
    𝑛 of bars of gold. The next line contains 𝑛 integers 𝑤0 , 𝑤1 , . . . , 𝑤𝑛−1 defining the
    weights of the bars of gold. Constraints. 1 ≤ 𝑊 ≤ 104; 1 ≤ 𝑛 ≤ 300; 0 ≤ 𝑤0,...,𝑤𝑛−1 ≤ 105.

Output Format. Output the maximum weight of gold that fits into a knapsack of capacity 𝑊 .

######### Sample 1 ########
Input:
 10 3
 1 4 8

Output:
9

 */

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int row = w.length + 1;
        int col = W + 1;
        int[][] matrix = new int[row][col];
        for (int i = 0; i < col; i++) {
            matrix[0][i] = 0;
        }
        for (int i = 0; i < row; i++) {
            matrix[i][0] = 0;
        }

        for (int ww = 1; ww < row; ww ++) {
            for (int i = 1; i < col; i ++) {
                int wi = w[ww- 1];
                if(wi <= i) {
                    matrix[ww][i] = Math.max(matrix[ww - 1][i], matrix[ww -1][i - wi] + wi);
                }
                else  {
                    matrix[ww][i] = matrix[ww - 1][i] ;
                }
            }
        }
        return matrix[row - 1][col - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }


        System.out.println(optimalWeight(W, w));
    }
}

