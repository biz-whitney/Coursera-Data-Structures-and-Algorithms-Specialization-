import java.util.*;

/*

Task. Given an undirected graph with 𝑛 vertices and 𝑚 edges, check whether it is bipartite.

Input Format. A graph is given in the standard format.

Output Format. Output 1 if the graph is bipartite and 0 otherwise.

######## Sample 1 ########
Input:
4 4
1 2
4 1
2 3
3 1

Output:
0

######## Sample 2 ########
Input:
5 4
5 2
4 2
3 4
1 4


Output:
1

 */

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
        String[] colors = new String[adj.length];
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < adj.length; i++) {
            colors[i] = "nil";
        }
        colors[0] = "black";
        que.add(0);
        while (!(que.isEmpty())) {
            int u = que.poll();
            for (int neigh : adj[u]) {
                if (colors[u].equals(colors[neigh]) ) {
                    return 0;
                }
                else if (colors[neigh].equals("nil") ) {
                    que.add(neigh);
                    if (colors[u].equals("black") ){
                        colors[neigh] = "white";
                    } else {
                        colors[neigh] = "black";
                    }
                }

            }
        }
        return 1;
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
        System.out.println(bipartite(adj));

    }
}

