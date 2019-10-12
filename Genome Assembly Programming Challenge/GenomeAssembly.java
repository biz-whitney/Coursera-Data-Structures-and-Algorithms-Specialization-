import java.util.*;
import java.lang.*;

/*
Task. Given a list of error-free reads, perform the task of Genome Assembly and return the circular genome
from which they came.

Dataset. Each of 1 618 lines of the input contains a single read, that is, a string over {A, C, G, T}. The
reads are given to you in alphabetical order because their true order is hidden from you. Each read
is 100 nucleotides long and contains no sequencing errors. Note that you are not given the 100-mer
composition of the genome, i.e., some 100-mers may be missing.

Output. Output the assembled genome on a single line.
 */


class GenomeAssembly {
    Map<String, ArrayList<Neighbor>> adj = new HashMap<String, ArrayList<Neighbor>>();
    ArrayList<String> nodes = new ArrayList<>();
    ArrayList<Integer> order;
    boolean[] visted;
    String Result;
    int kMer;


    // Finds the overlap of prefixes and subfixes
    public int overLap(String a, String b, int min_length) {
        int start = 0;
        while (true) {
            String subfix = a.substring(start);
            boolean  prefixBool= b.startsWith(subfix);
            if (a.length() - start <= min_length) {
                return 0;
            }
            if (prefixBool) {
                return a.length() - start;
            }
            start ++;
        }
    }


    //Constructs the direct graph of substrings
    public void constructGraph() {
        for (int i = 0; i < nodes.size(); i++) {
            String a = nodes.get(i);
            adj.put(a, new ArrayList<>());
            for (int j = 0; j < nodes.size(); j++) {
                String b = nodes.get(j);
                int overLayIndex = overLap(a, b, kMer);
                if (i != j &&  overLayIndex != 0) {
                    String substr = a.substring(a.length() - overLayIndex);
                    Neighbor neig = new Neighbor(j, overLayIndex, substr);
                    adj.get(a).add(neig);
                }
            }
        }
        sortNeighbors();
        HamiltonPath();
        findSequence();
        System.out.println(Result);

    }


    // Reconsttructs the sequence after the finding the Hamilton path
    public void findSequence() {
        int start = order.indexOf(0);
        int index = order.get( start);
        String currSeq = nodes.get(index);
        Result = currSeq;
        int endlap = 0;
        for (int i = 1; i < nodes.size(); i++) {
            index = order.get((i + start)% nodes.size());
            String nextSeq = nodes.get(index);
            int lap =overLap(currSeq, nextSeq, kMer);

            if (i == nodes.size() - 1) {
                String startSeq = nodes.get(order.get(start));
                endlap = overLap(nextSeq, startSeq, kMer);
                Result = Result + nextSeq.substring(lap, endlap);
            }
            else {
                Result = Result + nextSeq.substring(lap);

            }
            currSeq = nextSeq;
        }
        String endSeq = nodes.get(index);
        int endLap = overLap(endSeq.substring(endlap), Result, kMer);
        Result = Result.substring(endLap);
    }



    // Finds the Hamilton path for a directed graph of substrings
    public void HamiltonPath() {
        for (int  i = 0; i < nodes.size(); i++) {
            order = new ArrayList<>();
            visted = new boolean[nodes.size()];
            Arrays.fill(visted, false);
            if (searchPath(i)) {
                break;
            }
        }
    }


    // Helper method to find the Hamilton path
    boolean searchPath(int n) {
        if (visted[n]) {
            if (order.size() == nodes.size()) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (!(visted[n])) {
            visted[n] = true;
            order.add(n);
            ArrayList<Neighbor> neighsList = adj.get(nodes.get(n));
            for (int i = 0; i < neighsList.size(); i++) {
                searchPath(neighsList.get(i).neighborID);
            }
        }
        return false;
    }


    // A node class with for substrings and weights
    private class Neighbor {
        int neighborID;
        int wieght;
        String match;

        private Neighbor(int n, int w, String m) {
            neighborID = n;
            wieght = w;
            match = m;
        }

        public int compareTo(Neighbor obj) {
            if (this.wieght < obj.wieght) {
                return 1;
            }
            else if (this.wieght > obj.wieght) {
                return -1;
            }
            return 0;
        }
    }

    // Sort the adj values based on the weight of the edges
    public void sortNeighbors() {
        for (int i = 0; i < nodes.size(); i++){
            ArrayList<Neighbor> arr = adj.get(nodes.get(i));
            arr.sort(new Comparator<Neighbor>() {
                @Override
                public int compare(Neighbor o1, Neighbor o2) {
                    return o1.compareTo(o2);
                }
            });
        }
    }



    public static void main(String[] args) {
        GenomeAssembly GA = new GenomeAssembly();
        Scanner scanner = new Scanner(System.in);
        int n = 1618;
        for (int i = 0; i < n; i ++){
            GA.nodes.add(scanner.nextLine());
        }
        GA.constructGraph();
    }
}
