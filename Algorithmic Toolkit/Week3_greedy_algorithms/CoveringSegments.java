import java.util.*;

/*
Task. Given a set of 𝑛 segments {[𝑎0,𝑏0],[𝑎1,𝑏1],...,[𝑎𝑛−1,𝑏𝑛−1]} with integer coordinates
    on a line, find the minimum number 𝑚 of points such that each segment contains at least
    one point. That is, find a set of integers 𝑋 of the minimum size such that for any segment
    [𝑎𝑖,𝑏𝑖] there is a point 𝑥 ∈ 𝑋 such that𝑎𝑖 ≤𝑥≤𝑏𝑖.
Input Format. The first line of the input contains the number 𝑛 of segments. Each of the
    following 𝑛 lines contains two integers 𝑎𝑖 and 𝑏𝑖 (separated by a space) defining the
    coordinates of endpoints of the 𝑖-th segment.
Output Format. Output the minimum number 𝑚 of points on the first line and the integer
    coordinates of 𝑚 points (separated by spaces) on the second line. You can output the
    points in any order. If there are many such sets of points, you can output any set.
    (It is not difficult to see that there always exist a set of points of the minimum size
    such that all the coordinates of the points are integers.)

###### Sample 1 #######
Input:
3
1 3
2 5
3 6

Output :
1
3

###### Sample 2 #######
Input:
4
4 7
1 3
2 5
5 6

Output :
2
3 6
 */

public class CoveringSegments {
    private static int[] optimalPoints(Segment[] segments) {
        Arrays.sort(segments, Segment::compareTo);
        int counter = 0;
        int[] temp = new int[2 * segments.length];
        for (int i = 0; i < segments.length;) {
            int pt = segments[i].end;
            temp[counter] = pt;
            counter ++;
            while (i < segments.length) {
                if (segments[i].start <= pt) {
                    i ++;
                } else {
                    break;
                }
            }
        }
        int[] points = new int[counter];
        for (int i = 0; i < points.length; i++) {
            points[i] = temp[i];
        }
        return points;
    }

    private static class Segment implements Comparator<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        //to compare FractionalKnapsack objects based on value/weight (ratio)
        public int compareTo(Segment obj) {
            if (this.end == obj.end) {
                return 0;
            }
            else if (this.end < obj.end) {
                return -1;
            }
            return 1;
        }

        @Override
        public int compare(Segment o1, Segment o2) {
            return o1.compareTo(o2);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }

        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
