import java.util.ArrayList;
import java.util.Scanner;

/*
Task. Given an undirected graph and two distinct vertices 𝑢 and 𝑣, check if there is a path between 𝑢 and 𝑣.

Input Format. An undirected graph with 𝑛 vertices and 𝑚 edges. The next line contains two vertices 𝑢
    and 𝑣 of the graph.

Output Format. Output 1 if there is a path between 𝑢 and 𝑣 and 0 otherwise.

######## Sample 1 ########
Input:
4 4
1 2
3 2
4 3
1 4
1 4

Output:
1

Input:
4 2
1 2
3 2
1 4

Output:
0

 */

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        //write your code here
        int[] visited = new int[adj.length];
        return explore(adj, visited, x, y);
    }

    private static int explore(ArrayList<Integer>[] adj, int[] visited, int x, int y) {
        if (x == y) {
            return 1;
        }
        visited[x] = 1;
        for (int i = 0; i < adj[x].size(); i++) {
            if (visited[adj[x].get(i)] == 0) {
                if (explore(adj, visited, adj[x].get(i), y) == 1) {
                    return 1;
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
        System.out.println(reach(adj, x, y));
    }
}

