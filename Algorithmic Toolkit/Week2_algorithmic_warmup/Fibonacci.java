import java.util.Scanner;
/*
Task. Given an integer 𝑛, find the 𝑛th Fibonacci number 𝐹𝑛. 
Input Format. The input consists of a single integer 𝑛. 
Output Format. Output 𝐹𝑛.

###### Sample 1 #######
Input:
10

Output :
55
*/

public class Fibonacci {
  private static long calc_fib(int n) {
    long prev = 0;
    long curr = 1;
    if (n == 0){
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    for (int i = 0; i < n - 2; i ++) {
      long temp = curr;
      curr += prev;
      prev = temp;
    }

    return prev + curr;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
