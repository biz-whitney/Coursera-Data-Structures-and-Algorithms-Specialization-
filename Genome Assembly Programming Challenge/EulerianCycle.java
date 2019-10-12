import java.lang.*;
import java.util.*;

/*
Task. Given a directed graph, find an Eulerian cycle in the
graph or report that none exists.

Output. The Eulerian path if it exits.
 */

public class EulerianCycle {
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    int n; //number of vertices
    int m; // number of edges
    int[] in; // number of edges coming in to a vertex
    int[] out; // number of edges going out from a vertex
    int pathExists = 0;
    List<Integer> path = new LinkedList<>();


    // Checks if the Eulerian path exists
    public void checkPathExists() {
        if ( (Arrays.equals(in, out) &&
                !(Arrays.asList(in).contains(0)) &&
                    !(Arrays.asList(out).contains(0)) ) ) {
            pathExists = 1;
        }
    }


    // finds the Eulerian path
    public void findPath() {
        depthSearch(0);
        path.remove(0); // removes duplicate of first vertex
        for (int i =0 ; i < m; i++) {
            System.out.print(path.get(i) + " ");
        }
    }


    // depth first search to help find the Eulerian path
    public void depthSearch(int at) {
        while (out[at] != 0) {
            out[at] -= 1;
            int nextEdge = adj.get(at).get(out[at]);
            depthSearch(nextEdge);
        }
        path.add(0, at + 1);
    }



    public static void main(String[] argv) {
        EulerianCycle EC = new EulerianCycle();

        Scanner scanner = new Scanner(System.in);
        EC.n = scanner.nextInt();
        EC.m = scanner.nextInt();
        EC.in = new int[EC.n];
        EC.out = new int[EC.n];
        Arrays.fill(EC.in, 0);
        Arrays.fill(EC.out, 0);
        for (int i = 0; i < EC.n; i++) {
            ArrayList<Integer> neighs = new ArrayList<>();
            EC.adj.add(neighs);
        }
        for (int i = 0; i < EC.m; i++) {
            int startVex = scanner.nextInt() - 1;
            int neighVex = scanner.nextInt() -1;
            EC.adj.get(startVex).add(neighVex);
            EC.out[startVex] += 1;
            EC.in[neighVex] += 1;
        }
        EC.checkPathExists();
        System.out.println(EC.pathExists);
        if (EC.pathExists == 0) {
            return;
        }
        EC.findPath();
    }
}
