import java.util.*;
/*

Task. Given an integer 𝑛, find the last digit of the 𝑛th Fibonacci number 𝐹𝑛 (that is, 𝐹𝑛 mod 10).
Input Format. The input consists of a single integer 𝑛.
Output Format. Output the last digit of 𝐹𝑛.

###### Sample 1 #######
Input:
3

Output :
2


###### Sample 2 #######
Input:
331

Output :
2

###### Sample 3 #######
Input:
327305


Output :
5
*/

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;
        int previous = 0;
        int current  = 1;
        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous % 10;
            previous = current % 10;
            current = tmp_previous + current;
            current = current % 10;
        }

        return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitNaive(n);
        System.out.println(c);
    }
}

