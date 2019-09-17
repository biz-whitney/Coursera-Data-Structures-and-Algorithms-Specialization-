import java.util.*;

/*

Task. Given an directed graph with positive edge weights and with 𝑛 vertices and 𝑚 edges as well as two vertices
    𝑢 and 𝑣, compute the weight of a shortest path between 𝑢 and 𝑣 (that is, the minimum total weight of a path
    from 𝑢 to 𝑣).
Input Format. A graph is given in the standard format. The next line contains two vertices 𝑢 and 𝑣.

Output Format. Output the minimum weight of a path from 𝑢 to 𝑣, or −1 if there is no path.

######## Sample 1 ########
Input:
4 4
1 2 1
4 1 2
2 3 2
1 3 5
1 3

Output:
3

######## Sample 2 ########
Input:
5 9
1 2 4
1 3 2
2 3 2
3 2 1
2 4 2
3 5 4
5 4 1
2 5 3
3 4 4
1 5

Output:
6

 */

public class Dijkstra {
    int index;
    int dist;

    public Dijkstra(int l, int d) {
        index = l;
        dist = d;
    }

    public int compareTo(Dijkstra obj) {
        if (this.dist > obj.dist) {
            return -1;
        }
        else if (this.dist < obj.dist) {
            return 1;
        }
        return 0;
    }


    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int[] dist = new int[adj.length];
        for (int i = 0; i < adj.length; i ++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        PriorityQueue<Dijkstra> pq = new PriorityQueue<>(adj.length, new Comparator<Dijkstra>() {
            @Override
            public int compare(Dijkstra o1, Dijkstra o2) {
                return o1.compareTo(o2);
            }
        });
        Dijkstra point = new Dijkstra(s, 0);
        pq.add(point);
        while (!(pq.isEmpty())) {
            Dijkstra vert = pq.poll();
            for (int i = 0; i < adj[vert.index].size(); i++) {
                int neigh = adj[vert.index].get(i);
                if (dist[neigh] > (dist[vert.index] + cost[vert.index].get(i))) {
                    Dijkstra oldPoint = new Dijkstra(neigh, dist[neigh]);
                    dist[neigh] = dist[vert.index] + cost[vert.index].get(i);
                    Dijkstra newPoint = new Dijkstra(neigh, dist[neigh]);
                    pq.remove(oldPoint);
                    pq.add(newPoint);
                }
            }
        }
        int d = dist[t];
        if (d != Integer.MAX_VALUE) {
            return d;
        }
        return -1;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));

    }
}

