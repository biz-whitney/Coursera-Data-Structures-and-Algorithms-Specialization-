import java.util.Scanner;
import java.util.*;
import java.lang.Math;


/*
Task. The goal of this code problem is to implement an algorithm for the fractional knapsack problem.
Input Format. The first line of the input contains the number 𝑛 of items and the capacity 𝑊 of a knapsack.
      The next 𝑛 lines define the values and weights of the items. The 𝑖-th line contains integers 𝑣𝑖 and 𝑤𝑖—the
      value and the weight of 𝑖-th item, respectively.
Output Format. Output the maximal value of fractions of items that fit into the knapsack. The absolute value of
       the difference between the answer of your program and the optimal value should be at most 10−3. To ensure
       this, output your answer with at least four digits after the decimal point (otherwise your answer, while
       being computed correctly, can turn out to be wrong because of rounding issues).


###### Sample 1 #######
Input:
3 50
60 20
100 50
120 30

Output :
180.0000

###### Sample 2 #######
Input:
 1 10
 500 30

Output :
166.666


 */

public class FractionalKnapsack implements Comparator<FractionalKnapsack> {
    int weight;
    int value;
    double ratio;

    private FractionalKnapsack (int weight1, int value1, double ratio1) {
        weight = weight1;
        value = value1;
        ratio = ratio1;

    }

    //method to get the value/weight for the
    private double getRatio() {
        return ratio;
    }

    //to compare FractionalKnapsack objects based on value/weight (ratio)
    public int compareTo(FractionalKnapsack obj) {
        if (this.getRatio() == obj.getRatio()) {
            return 0;
        }
        else if (this.getRatio() < obj.getRatio()) {
            return 1;
        }
        return -1;
    }

    //implements comparator so to compare FrationalKnasack objects
    @Override
    public int compare(FractionalKnapsack o1, FractionalKnapsack o2) {
        return o1.compareTo(o2);
    }

    //method to actually run the greedy algorithm
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        FractionalKnapsack[] vw = new FractionalKnapsack[values.length];
        for (int i = 0; i < values.length; i ++) {
            int val = values[i];
            int weig = weights[i];
            double ratio = Double.valueOf(values[i])/Double.valueOf(weights[i]);
            FractionalKnapsack obj = new FractionalKnapsack(weig, val, ratio);
            vw[i] =  obj;
        }
        if (vw.length > 1) {
            Arrays.sort(vw, FractionalKnapsack::compareTo);
        }
        for (int i = 0; i < values.length; i++) {
            if (capacity == 0) {
                return value;
            }
            int a = Math.min(vw[i].weight, capacity);
            value = value + a * vw[i].ratio;
            capacity -= a;
        }
        return value;
    }


    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

}
