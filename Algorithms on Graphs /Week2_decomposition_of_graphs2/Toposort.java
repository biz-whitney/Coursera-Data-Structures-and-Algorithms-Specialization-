import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/*

Task. Compute a topological ordering of a given directed acyclic graph (DAG) with 𝑛 vertices and 𝑚 edges.

Input Format. A graph is given in the standard format.

Output Format. Output any topological ordering of its vertices. (Many DAGs have more than just one topological
    ordering. You may output any of them.)

######## Sample 1 ########
Input:
4 3
1 2
4 1
3 1

Output:
4 3 1 2

######## Sample 2 ########
Input:
5 7
2 1
3 2
3 1
4 3
4 1
5 2
5 3

Output:
5 4 3 2 1

 */

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        //write your code here
        for (int i = 0; i < adj.length; i++) {
            for( int neigh : adj[i]) {
                used[neigh] += 1;
            }
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < adj.length; i++) {
            if (used[i] == 0) {
                stack.add(i);
            }
        }
        while (!(stack.empty()) ) {
            int value = stack.pop();
            order.add(value);
            for (int neigh : adj[value]) {
                used[neigh] -= 1;
                if (used[neigh] == 0) {
                    stack.add(neigh);
                }

            }

        }
        return order;
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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

