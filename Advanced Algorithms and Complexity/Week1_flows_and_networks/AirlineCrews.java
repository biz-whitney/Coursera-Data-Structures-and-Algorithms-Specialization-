import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.*;

public class AirlineCrews {
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new AirlineCrews().solve();
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        boolean[][] bipartiteGraph = readData();
        int[] matching = findMatching(bipartiteGraph);
        writeResponse(matching);
        out.close();
    }

    boolean[][] readData() throws IOException {
        int numLeft = in.nextInt();
        int numRight = in.nextInt();
        boolean[][] adjMatrix = new boolean[numLeft][numRight];
        for (int i = 0; i < numLeft; ++i)
            for (int j = 0; j < numRight; ++j)
                adjMatrix[i][j] = (in.nextInt() == 1);
        return adjMatrix;
    }

    private int[] findMatching(boolean[][] bipartiteGraph) {
        // Replace this code with an algorithm that finds the maximum
        // matching correctly in all cases.
        int n = bipartiteGraph.length;
        int m = bipartiteGraph[0].length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        FlowGraphTwo graph = constructGraph(bipartiteGraph, n, m);
        int from = 0;
        int to = n + m + 1;
        while (true) {
            int[] edgeIndices = new int[graph.size()];
            int[] prev = BFS2(graph, from, to, edgeIndices);
            if (prev[graph.size() - 1] == Integer.MIN_VALUE){
                return result;
            }
            ReconstructPath(graph, from, to, prev, edgeIndices, result);
        }
    }


    //constructs the path between a flight and a crew member
    private void ReconstructPath(FlowGraphTwo graph, int S, int u, int[] prev, int[] eIndex, int[] r) {
        ArrayList<Integer> result = new ArrayList<>();
        while (u != S) {
            result.add(eIndex[u]);
            Edge e = graph.getEdge(eIndex[u]);
            if(e.flight != -1 && e.crew != -1 && e.to > e.from) {
                r[e.flight] = e.crew;
            }
            u = prev[u];
        }
        for (int i = 0; i < result.size(); i++) {
            graph.addFlow(result.get(i), 1);
        }
    }


    //bearth first search
    private int[] BFS2(FlowGraphTwo g, int from, int to, int[] eIndices) {
        int[] dist = new int[g.size()];
        int[] prev = new int[g.size()];
        PriorityQueue<Integer> que = new PriorityQueue<>();
        int myNil = Integer.MIN_VALUE;
        Arrays.fill(dist, Integer.MAX_VALUE );
        Arrays.fill(prev, myNil);
        dist[from] = 0;
        que.add(from);
        while( !(que.isEmpty())) {
            int u = que.poll();
            for (int neigh : g.getIds(u)) {
                Edge e = g.getEdge(neigh);
                if (dist[e.to] == Integer.MAX_VALUE && e.capacity != e.flow) {
                    if (e.to == to) {
                        prev[e.to] = u;
                        eIndices[e.to] = neigh;
                        return prev;
                    }
                    que.add(e.to);
                    dist[e.to] = dist[u] + 1;
                    prev[e.to] = u;
                    eIndices[e.to] = neigh;
                }
            }
        }
        return prev;
    }


    // Constructs a graph for the matching of crew members to flights
    public static FlowGraphTwo constructGraph(boolean[][] bi, int n, int m) {
        FlowGraphTwo graph = new FlowGraphTwo(n + m + 2);

        //edges from s to the flights
        for (int i = 0; i < bi.length; i++) {
            graph.addEdge(0, i + 1, 1, -1, -1);
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                boolean val = bi[i][j];
                if (val) {
                    graph.addEdge(i + 1, n + 1 + j, 1, i, j);
                }
            }
        }
        //edges from crew to target
        for (int j = 0; j < m; j++) {
            graph.addEdge(n + 1 + j, n + m + 1, 1, -1, -1);
        }
        return graph;
    }


    static class Edge {
        int from, to, capacity, flow, flight, crew;

        public Edge(int from, int to, int capacity, int f, int c) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
            this.flight = f;
            this.crew = c;
        }
    }

    /* This class implements a bit unusual scheme to store the graph edges, in order
     * to retrieve the backward edge for a given edge quickly. */
    static class FlowGraphTwo {
        /* List of all - forward and backward - edges */
        private List<AirlineCrews.Edge> edges;

        /* These adjacency lists store only indices of edges from the edges list */
        private List<Integer>[] graph;

        public FlowGraphTwo(int n) {
            this.graph = (ArrayList<Integer>[])new ArrayList[n];
            for (int i = 0; i < n; ++i)
                this.graph[i] = new ArrayList<>();
            this.edges = new ArrayList<>();
        }

        public void addEdge(int from, int to, int capacity, int flight, int crew) {
            /* Note that we first append a forward edge and then a backward edge,
             * so all forward edges are stored at even indices (starting from 0),
             * whereas backward edges are stored at odd indices. */
            AirlineCrews.Edge forwardEdge = new AirlineCrews.Edge(from, to, capacity, flight, crew);
            AirlineCrews.Edge backwardEdge = new AirlineCrews.Edge(to, from, 0, flight, crew);
            graph[from].add(edges.size());
            edges.add(forwardEdge);
            graph[to].add(edges.size());
            edges.add(backwardEdge);
        }

        public int size() {
            return graph.length;
        }

        public List<Integer> getIds(int from) {
            return graph[from];
        }

        public AirlineCrews.Edge getEdge(int id) {
            return edges.get(id);
        }

        public void addFlow(int id, int flow) {
            /* To get a backward edge for a true forward edge (i.e id is even), we should get id + 1
             * due to the described above scheme. On the other hand, when we have to get a "backward"
             * edge for a backward edge (i.e. get a forward edge for backward - id is odd), id - 1
             * should be taken.
             *
             * It turns out that id ^ 1 works for both cases. Think this through! */
            edges.get(id).flow += flow;
            edges.get(id ^ 1).flow -= flow;
        }
    }

    private void writeResponse(int[] matching) {
        for (int i = 0; i < matching.length; ++i) {
            if (i > 0) {
                out.print(" ");
            }
            if (matching[i] == -1) {
                out.print("-1");
            } else {
                out.print(matching[i] + 1);
            }
        }
        out.println();    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
