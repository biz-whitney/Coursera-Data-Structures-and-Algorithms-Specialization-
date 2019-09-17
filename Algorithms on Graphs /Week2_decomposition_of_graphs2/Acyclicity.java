import java.util.ArrayList;
import java.util.Scanner;

/*

Task. Check whether a given directed graph with 𝑛 vertices and 𝑚 edges contains a cycle.

Input Format. A graph is given in the standard format.

Output Format. Output 1 if the graph contains a cycle and 0 otherwise.

######## Sample 1 ########
Input:
4 4
1 2
4 1
2 3
3 1

Output:
1

######## Sample 2 ########
Input:
5 7
1 2
2 3
1 3
3 4
1 4
2 5
3 5

Output:
0
 */

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
        int[] visited = new int[adj.length];
        int[] stack = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            if (visited[i] == 0) {
                if ((explorer(adj, visited, i, stack)) == 1 ) {
                    return 1;
                }
            }

        }
        return 0;
    }


    private static int explorer(ArrayList<Integer>[] adj,int[] visited, int x, int[] stack) {
        visited[x] = 1;
        stack[x] = 1;
        for (int i : adj[x]) {
            if (visited[i] == 0 && (explorer(adj, visited, i, stack)) == 1) {
                return 1;
            }else if (stack[i] == 1)  {
                return 1;
            }

        }
        stack[x] = 0;
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
        }
        System.out.println(acyclic(adj));

    }
}

