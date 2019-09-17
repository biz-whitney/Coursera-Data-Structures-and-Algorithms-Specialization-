import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*

Task. Given 𝑛 points on a plane, connect them with segments of minimum total length such that there is a path
    between any two points. Recall that the length of a segment with endpoints (𝑥1,𝑦1) and (𝑥2,𝑦2) is equal
    to √︀(𝑥1 − 𝑥2)2 + (𝑦1 − 𝑦2)2.

Input Format. The first line contains the number 𝑛 of points. Each of the following 𝑛 lines defines a point (𝑥𝑖, 𝑦𝑖).

Output Format. Output the minimum total length of segments. The absolute value of the difference between the answer
    of your program and the optimal value should be at most 10−6. To ensure this, output your answer with at least
    seven digits after the decimal point (otherwise your answer, while being computed correctly, can turn out to be
    wrong because of rounding issues).

######## Sample 1 ########
Input:
4
0 0
0 1
1 0
1 1

Output:
3.000

######## Sample 2 ########
Input:
5
0 0
0 2
1 1
3 0
3 2

Output:
7.0644

 */

public class ConnectingPoints {
    int index;
    double dist;
    int x;
    int y;

    public ConnectingPoints(int i, double d, int x, int y) {
        index = i;
        dist = d;
        this.x = x;
        this.y = y;

    }

    public int compareTo(ConnectingPoints obj) {
        if (this.dist < obj.dist) {
            return -1;
        }
        else if (this.dist > obj.dist) {
            return 1;
        }
        return 0;
    }

    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here
        double[] cost = new double[x.length];
        ConnectingPoints[] track = new ConnectingPoints[x.length];
        for (int i = 0; i < x.length; i ++) {
            cost[i] = Double.MAX_VALUE;

        }
        PriorityQueue<ConnectingPoints> pq = new PriorityQueue<>(x.length, new Comparator<ConnectingPoints>() {
            @Override
            public int compare(ConnectingPoints o1, ConnectingPoints o2) {
                return o1.compareTo(o2);
            }
        });
        cost[0] = 0;
        ConnectingPoints point = new ConnectingPoints(0, 0, x[0], y[0]);
        track[0] = point;
        pq.add(point);
        for (int i = 1; i < x.length; i++) {
            ConnectingPoints ppp = new ConnectingPoints(i, cost[i], x[i], y[i]);
            track[i] = ppp;
            pq.add(ppp);
        }

        while (!(pq.isEmpty())) {
            ConnectingPoints vert = pq.poll();
            for (int i = 0; i < x.length; i++) {
                ConnectingPoints newPoint = new ConnectingPoints(i, cost[i], x[i], y[i]);
                double nDist = calDistance(vert, newPoint);
                if ( pq.contains(track[i]) && vert.index != i && cost[i] > nDist) {
                    cost[i] = nDist;
                    pq.remove(newPoint);
                    ConnectingPoints addPoint = new ConnectingPoints(i, cost[i], x[i], y[i]);
                    track[i] = addPoint;
                    pq.add(addPoint);
                }
            }
        }
        result = Arrays.stream(cost).sum();
        return result;
    }

    public static double calDistance(ConnectingPoints vert, ConnectingPoints newPoint) {
        double result = Math.pow(vert.x - newPoint.x, 2) + Math.pow(vert.y - newPoint.y, 2);
        return Math.abs(Math.sqrt(result));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}

