import java.util.Scanner;

/*
Task. The goal in this problem is to find the minimum number of coins needed to change the input value (an integer) into coins with denominations 1, 5, and 10.
Input Format. The input consists of a single integer 𝑚.
Output Format. Output the minimum number of coins with denominations 1, 5, 10 that changes 𝑚.

###### Sample 1 #######
Input:
28

Output :
6

###### Sample 2 #######
Input:
2

Output :
2

 */

public class Change {
    private static int getChange(int m) {
        int num = 0;
        while (m >= 10) {
        	num += 1;
        	m = m - 10;
        }
        while (m >= 5) {
        	num += 1;
        	m = m - 5;
        }
        while (m >= 1) {
        	num += 1;
        	m = m - 1;
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

