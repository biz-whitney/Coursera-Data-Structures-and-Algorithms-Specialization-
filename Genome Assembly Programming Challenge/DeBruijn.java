import java.util.*;
import java.lang.*;

/*
Assembles the genome from an input of 5396 k-mer composition.
 */

public class DeBruijn {
    ArrayList<String> kmerList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    int[] in;
    int[] out;
    List<Integer> path = new LinkedList<>();
    int kMer;
    int inputSize = 5396;
    StringBuilder result = new StringBuilder();


    // Construct the De Bruijn Graph
    public void constructGraph(String seq) {
        String prefix = seq.substring(0, kMer);
        String substr = seq.substring(1);
        if (!(kmerList.contains(prefix))) {
            addStringToGraph(prefix);
        }
        if ( !(kmerList.contains(substr))) {
            addStringToGraph(substr);
        }
        int startVex = kmerList.indexOf(prefix);
        int neighVex = kmerList.indexOf(substr);
        adj.get(startVex).add(neighVex);
        out[startVex] += 1;
        in[neighVex] += 1;
    }


    // helper method to construct the De Bruijn Graph
    private void addStringToGraph(String str) {
        kmerList.add(str);
        int index = kmerList.indexOf(str);
        ArrayList<Integer> neighs = new ArrayList<>();
        adj.add(index, neighs);
    }


    // Assemble the sequence
    private void findSequence() {
        result.append(kmerList.get(path.get(0)));
        for (int i = 1; i < path.size(); i++) {
            String strToAdd = kmerList.get(path.get(i));
            result.append(strToAdd.substring(kMer - 1));
        }

        int resultLength = result.length();
        result.delete(resultLength - kMer, resultLength);
        System.out.println(result);
    }


    // finds the Eulerian path
    public void findPath() {
        depthSearch(0);
    }


    // depth first search to help find the Eulerian path
    public void depthSearch(int at) {
        while (out[at] != 0) {
            out[at] -= 1;
            int nextEdge = adj.get(at).get(out[at]);
            depthSearch(nextEdge);
        }
        path.add(0, at);
    }


    public static void main(String[] argv) {
        DeBruijn DB = new DeBruijn();
        DB.in = new int[DB.inputSize];
        DB.out = new int[DB.inputSize];
        Arrays.fill(DB.in, 0);
        Arrays.fill(DB.out, 0);
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < DB.inputSize; i++) {
            String inputStr = scanner.nextLine();
            DB.kMer = inputStr.length() - 1;
            DB.constructGraph(inputStr);
        }
        DB.findPath();
        DB.findSequence();
    }
}
