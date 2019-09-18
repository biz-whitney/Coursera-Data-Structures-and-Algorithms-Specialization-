import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Task. Construct a trie from a collection of patterns.

Input Format. An integer 𝑛 and a collection of strings Patterns = {𝑝1, . . . , 𝑝𝑛}
	(each string is given on a separate line).

Output Format. The adjacency list corresponding to Trie(Patterns), in the following
	format. If Trie(Patterns) has 𝑛 nodes, first label the root with 0 and then label
	the remaining nodes with the integers 1 through 𝑛−1 in any order you like. Each
	edge of the adjacency list of Trie(Patterns) will be encoded by a triple: the first
	two members of the triple must be the integers 𝑖, 𝑗 labeling the initial and terminal
	nodes of the edge, respectively; the third member of the triple must be the symbol 𝑐
	labeling the edge; output each such triple in the format u->v:c (with no spaces) on
	a separate line.


######## Sample 1 ######
Input:
1
ATA

Output:
0->1:A
2->3:A
1->2:T

######## Sample 2 ######
Input:
3
AT
AG
AC

Output:
0->1:A
1->4:C
1->3:G
1->2:T
 */

public class Trie {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();

        // write your code here
        Map<Character, Integer> firstNode = new HashMap<>();
        trie.add(firstNode);
        int index  = 1;
        for (int i = 0; i < patterns.length; i++) {
            Map<Character, Integer> curNode = trie.get(0);
            char[] pat = patterns[i].toCharArray();
            for (int j = 0; j < pat.length; j++ ) {
                char curSymbol = pat[j];
                if (curNode.containsKey(curSymbol)) {
                    int num = curNode.get(curSymbol);
                    curNode = trie.get(num);
                } else {
                    curNode.put(curSymbol, index);
                    Map<Character, Integer> newNode = new HashMap<>();
                    trie.add(index, newNode);
                    curNode = newNode;
                    index += 1;
                }

            }

        }
        return trie;
    }

    static public void main(String[] args) throws IOException {
        //new Trie().run();

        int n = Integer.parseInt(args[0]);
        String[] patterns = new String[n];
        for (int i = 0; i < n; i++) {
            patterns[i] = args[i + 1];
        }
        new Trie().rudDebugger(patterns);

    }

    public void print(List<Map<Character, Integer>> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
            }
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }

    public void rudDebugger(String[] patterns) {
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }
}
