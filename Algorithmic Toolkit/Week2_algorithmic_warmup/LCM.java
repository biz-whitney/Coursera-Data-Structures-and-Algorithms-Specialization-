import java.util.*;
/*
Task. Given two integers 𝑎 and 𝑏, find their least common multiple.
Input Format. The two integers 𝑎 and 𝑏 are given in the same line separated by space.
Output Format. Output the least common multiple of 𝑎 and 𝑏.

###### Sample 1 #######
Input:
6 8

Output :
24

###### Sample 2 #######
Input:
761457 614573

Output :
467970912861

 */

public class LCM {
  private static long lcm_naive(int a, int b) {
    long lowest = (long) gcd_naive(a, b);
    if (lowest == 1) {
      return (long) a * b;
    }else {
      return (long) ((a / lowest) * (b / lowest) * lowest);
    }
  }


  private static int gcd_naive(int a, int b) {
    if (b == 0) {
      return a;
    }else {
      return gcd_naive(b, a % b);
    }
  }
  

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm_naive(a, b));
  }
}
