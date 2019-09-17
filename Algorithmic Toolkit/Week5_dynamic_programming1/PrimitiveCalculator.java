import java.util.*;

/*
Task. Given an integer 𝑛, compute the minimum number of operations needed to obtain the number
    𝑛 starting from the number 1.
Input Format. The input consists of a single integer 1 ≤ 𝑛 ≤ 106.
Output Format. In the first line, output the minimum number 𝑘 of operations needed
    to get 𝑛 from 1. In the second line output a sequence of intermediate numbers.
    That is, the second line should contain positiveintegers𝑎0,𝑎2,...,𝑎𝑘−1 suchthat𝑎0 =1,𝑎
    𝑘−1 =𝑛andforall0≤𝑖<𝑘−1,𝑎𝑖+1 isequalto either 𝑎𝑖 + 1, 2𝑎𝑖, or 3𝑎𝑖. If there are many such s
    equences, output any one of them.

######### Sample 1 ########
Input:
1

Output:
0
1

######### Sample 2 ########
Input:
5

Output:
3
1 2 4 5

######### Sample 3 ########s
Input:
96234

Output:
14
1 3 9 10 11 22 66 198 594 1782 5346 16038 16039 32078 96234

 */

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        List<Integer> table = new ArrayList<>();
        if (n == 1) {
            sequence.add(1);
            return sequence;
        }
        table.add(0);
        table.add(0);
        table.add(1);
        table.add(1);
        int pt1 = 3;
        int pt2 = 2;
        int pt3 = 2;
        if (n < 4) {
            sequence.add(table.get(n));
            return sequence;
        }
        for (int i = 4; i < n + 1; i ++) {
            int add1 = pt1 + 1;
            int mul2 = pt2 * 2;
            int mul3 = pt3 * 3;
            if ( add1 == i && mul2 == i && mul3 == i) {
                if (table.get(pt2) <= table.get(pt1)) {
                    if (table.get(pt2) <= table.get(pt3)) {
                        table.add(table.get(pt2) + 1);
                    }
                    if (table.get(pt2) > table.get(pt3)) {
                        table.add(table.get(pt3) + 1);
                    }
                }
                else if (table.get(pt1) < table.get(pt2)) {
                    if (table.get(pt1) <= table.get(pt3)) {
                        table.add(table.get(pt1) + 1);
                    }
                    if (table.get(pt1) > table.get(pt3)) {
                        table.add(table.get(pt3) + 1);
                    }
                }
                pt1++;
                pt2++;
                pt3++;
            }
            else if (add1 == i && mul2 == i) {
                if (table.get(pt2) <= table.get(pt1)) {
                    table.add(table.get(pt2) + 1);
                }
                if (table.get(pt2) > table.get(pt1)) {
                    table.add(table.get(pt1) + 1);
                }
                pt1 ++;
                pt2 ++;

            }
            else if (add1 == i && mul3 == i) {
                if (table.get(pt1) < table.get(pt3)) {
                    table.add(table.get(pt1) + 1);
                }
                if (table.get(pt1) >= table.get(pt3)) {
                    table.add(table.get(pt3) + 1);
                }
                pt1++;
                pt3++;
            }
            else {
                table.add(table.get(pt1) + 1);
                pt1++;
            }

        }
        sequence.add(n);
        int max = table.get(n);
        int nn = n;


        for(int i = n - 1; i > 0; i--) {
            int value = table.get(i);
            if (max > value) {
                if ( nn % i == 0) {
                    nn = i;
                    sequence.add(nn);
                    max = value;
                }
                else if (nn - 1 == i) {
                    nn = i;
                    sequence.add(nn);
                    max = value;
                }

            }

        }


        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //int n = Integer.parseInt(args[0]);
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

