import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
Task. Given an undirected graph with 𝑛 vertices and 𝑚 edges and two vertices 𝑢 and 𝑣, compute the length of a shortest
    path between 𝑢 and 𝑣 (that is, the minimum number of edges in a path from 𝑢 to 𝑣).

Input Format. A graph is given in the standard format. The next line contains two vertices 𝑢 and 𝑣.

Output Format. Output the minimum number of edges in a path from 𝑢 to 𝑣, or −1 if there is no path.

######## Sample 1 ########
Input:
4 3
1 2
4 1
2 3
3 1
2 4

Output:
2

######## Sample 2 ########
Input:
5 4
5 2
1 3
3 4
1 4
3 5

Output:
-1

 */

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        //write your code here
        int[] dist = new int[adj.length];
        int[] prev = new int[adj.length];
        Queue<Integer> que = new LinkedList<>();
        int myNil = Integer.MIN_VALUE;
        for (int i = 0; i < adj.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = myNil;
        }
        dist[s] = 0;
        que.add(s);
        while (!(que.isEmpty())) {
            int u = que.poll();
            for (int neigh : adj[u]) {
                if (neigh == t) {
                    return dist[u] +1;
                }
                if (dist[neigh] == Integer.MAX_VALUE) {
                    que.add(neigh);
                    dist[neigh] = dist[u] + 1;
                    prev[neigh] = u;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

