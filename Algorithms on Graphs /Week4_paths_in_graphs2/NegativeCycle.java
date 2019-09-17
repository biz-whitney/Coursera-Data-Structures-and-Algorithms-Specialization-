import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

/*

Task. Given an directed graph with possibly negative edge weights and with 𝑛 vertices and 𝑚 edges, check whether
    it contains a cycle of negative weight.

Input Format. A graph is given in the standard format.

Output 1 if the graph contains a cycle of negative weight and 0 otherwise.

######## Sample 1 ########
Input:
4 4
1 2 -5
4 1 2
2 3 2
3 1 1

Output:
1


 */

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
        int[] dist = new int[adj.length];
        for(int i = 0; i < adj.length; i ++) {
            dist[i] = Integer.MAX_VALUE/2;
        }
        dist[0] = 0;
        for(int count = 0; count < adj.length; count ++) {
            for (int i = 0; i < adj.length; i++) {
                for (int j = 0; j < adj[i].size(); j++) {
                    int neigh = adj[i].get(j);
                    if (dist[neigh] > (dist[i] + cost[i].get(j))) {
                        dist[neigh] = dist[i] + cost[i].get(j);
                        if (count == adj.length - 1) {
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

