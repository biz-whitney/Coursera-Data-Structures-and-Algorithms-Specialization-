import java.util.Scanner;

/*
Task : apply dynamic programming for solving the Money Change Problem for denominations 1, 3, and 4

Input Format. Integer money.

Output Format. The minimum number of coins with denominations 1, 3, 4 that changes money. Constraints.
    1 ≤ money ≤ 103.

######### Sample 1 ########
Input:
34

Output:
9

######### Sample 2 ########
Input:
2

Output:
2

 */

public class ChangeDP {
    private static int getChange(int m) {
        //write your code here
        int[] coins = new int[m + 1];
        int[] temp = new int[]{0,1,2,1,1};
        if (m < 5) {
            return temp[m];
        }
        for (int i  = 0; i < temp.length; i++) {
            coins[i] = temp[i];
        }
        int onept = 4;
        int thrept = 2;
        int fourpt = 1;
        for (int i = 5; i < m + 1; i++) {
            int one = coins[onept] + 1;
            int three = coins[thrept] + 1;
            int four = coins[fourpt] + 1;
            coins[i] = Math.min(Math.min(one, three), four);
            onept++;
            thrept++;
            fourpt++;
        }
        return coins[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

