import java.util.Scanner;
/*
Purpose: Compute the sum of two single digit numbers.
Input: Two single digit numbers. 
Output: The sum of these num- bers.

Sample 1:
Input:
9 7

Output:
16
*/


class APlusB {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int b = s.nextInt();
        System.out.println(a + b);
    }
}