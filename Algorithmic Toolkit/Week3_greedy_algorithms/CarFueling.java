import java.util.*;
import java.io.*;

/*
Input Format. The first line contains an integer 𝑑. The second line contains an integer 𝑚.
        The third line specifies an integer 𝑛. Finally, the last line contains integers
        stop1, stop2, . . . , stop𝑛.
Input Format. Assuming that the distance between the cities is 𝑑 miles, a car can travel
        at most 𝑚 miles on a full tank, and there are gas stations at distances stop1 ,
        stop2 , . . . , stop𝑛 along the way, output the minimum number of refills needed.
        Assume that the car starts with a full tank. If it is not possible to reach the
        destination, output −1.

###### Sample 1 #######
Input:
950
400
4
200 375 550 750

Output :
2

###### Sample 2 #######
Input:
10
3
4 1259

Output :
-1
 */


public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        int counts = 0;
        int len = stops.length;
        int travel = tank;
        for (int i = 0; i < stops.length; i++) {
            while (i < len && travel >= stops[i]) {
                i ++;
            }
            //refuel here
            if (i < len) {
                if ( (stops[i] - stops[i -1]) <= tank) {
                    travel = tank + stops[i - 1];
                    counts ++;
                }else { //can't get from station a to b
                    counts = -1;
                    break;
                }
            } else {
                if (tank >= dist) {
                    counts = 0;
                    break;
                }
                if (travel >= dist) {
                    break;
                }
                if (tank + stops[i - 1] >= dist) {
                    counts ++;
                    break;
                }
                counts = -1;
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
