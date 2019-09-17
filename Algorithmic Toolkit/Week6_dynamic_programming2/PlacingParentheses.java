import java.util.Scanner;
/*
Task. Find the maximum value of an arithmetic expression by specifying the order of applying
    its arithmetic operations using additional parentheses.

Input Format. The only line of the input contains a string 𝑠 of length 2𝑛 + 1 for some 𝑛,
    with symbols 𝑠0,𝑠1,...,𝑠2𝑛. Each symbol at an even position of 𝑠 is a digit (that is, an
    integer from 0 to 9) while each symbol at an odd position is one of three operations
    from {+,-,*}.

Output Format. Output the maximum possible value of the given arithmetic expression among different
    orders of applying arithmetic operations.

######### Sample 1 ########
Input:
1+5

Output:
6

######### Sample 2 ########
Input:
5-8+7*4-8+9

Output:
200

 */

public class  PlacingParentheses {
    private static long getMaximValue(String exp) {
        //write your code here
        char[] charexp = exp.toCharArray();
        int numsiz = (exp.length() + 1)/ 2;
        long[][] maxArray = new long[numsiz][numsiz];
        long[][] minArray = new long[numsiz][numsiz];
        for (int i = 0; i < numsiz; i++) {
            minArray[i][i] = Character.getNumericValue(charexp[2*i]);
            maxArray[i][i] = Character.getNumericValue(charexp[2*i]);
        }

        for (int s = 0; s < numsiz - 1; s++) {
            for (int i = 0; i < numsiz - s -1; i++) {
                int j = i + s +1;
                long max = Long.MAX_VALUE;
                long min = Long.MIN_VALUE;
                for (int k = i; k < j; k++) {
                    long a = eval(minArray[i][k],minArray[k + 1][j],charexp[2 * k + 1]);
                    long b = eval(minArray[i][k],maxArray[k + 1][j],charexp[2 * k + 1]);
                    long c = eval(maxArray[i][k],maxArray[k + 1][j],charexp[2 * k + 1]);
                    long d = eval(maxArray[i][k],maxArray[k + 1][j],charexp[2 * k + 1]);
                    long min2 = Math.min(Math.min(Math.min(a,b), c), d);
                    long max2 = Math.max(Math.max(Math.max(a,b), c), d);
                    if (k != i) {
                        min = Math.min(min, min2);
                        max = Math.max(max, max2);

                    }else {
                        min = min2;
                        max = max2;
                    }


                }
                minArray[i][j] = min;
                maxArray[i][j] = max;
            }
        }
        return maxArray[0][numsiz - 1];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        //String exp = args[0];
        System.out.println(getMaximValue(exp));
    }
}

