import java.util.*;

/*
Task. Given two integers 𝑎 and 𝑏, find their greatest common divisor.
Input Format. The two integers 𝑎, 𝑏 are given in the same line separated by space.
Output Format. Output GCD(𝑎, 𝑏).

####### Sample 1 #######
Input:
18 35

Output:
1

####### Sample 2 #######
Input:
28851538 1183019

Output:
17657

*/

public class GCD {
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

    System.out.println(gcd_naive(a, b));
  }
}
