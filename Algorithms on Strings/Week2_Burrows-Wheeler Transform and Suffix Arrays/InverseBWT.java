import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Task. Reconstruct a string from its Burrows–Wheeler transform.

Input Format. A string Transform with a single “$” sign.

Output Format. The string Text such that BWT(Text) = Transform. (There exists a unique such string.)

########## Sample 1 ###########
Input:
AC$A

Output:
ACA$

########## Sample 2 ############
Input:
AGGGAA$

Output:
GAGAGA$
 */

public class InverseBWT {
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

    //organizes the columns
    public void sortColumn(char c, HashMap<Character, Integer> m, ArrayList<charNode> arr) {
        if ( !(m.containsKey(c) ) ) {
            m.put(c, 1);
        }
        int pos = m.get(c);
        m.replace(c, pos + 1);
        arr.add(new charNode(c, pos));
    }


    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();

        // write your code here
        char[] textArray = bwt.toCharArray();
        int n = textArray.length;
        ArrayList<charNode> firstCol = new ArrayList<>();
        ArrayList<charNode> lastCol = new ArrayList<>(n);
        HashMap<Character, Integer> lastColMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char lastChar = textArray[i];
            sortColumn(lastChar, lastColMap, lastCol);
        }
        firstCol = new ArrayList<>(lastCol);
        firstCol.sort(new Comparator<charNode>() {
            @Override
            public int compare(charNode o1, charNode o2) {
                return o1.compareTo(o2);
            }
        });
        charNode endNode = firstCol.get(0);
        charNode startNode = lastCol.get(0);
        result.append(endNode.value);
        //while (endNode.value != startNode.value) {
        for (int i = 0; i < n - 1; i++) {
            result.append(startNode.value);
            int index = firstCol.indexOf(startNode);
            startNode = lastCol.get(index);

        }
        result.reverse();
        //System.out.println(result);
        return result.toString();
    }

    public class charNode {
        char value;
        int count;

        public charNode(char c, int count) {
            this.value = c;
            this.count = count;
        }

        //compares charNodes
        public int compareTo(charNode obj) {
            if (this.value < obj.value) {
                return -1;
            }
            else if (this.value > obj.value) {
                return 1;
            }
            else if (this.count < obj.count) {
                return  -1;
            }
            return 1;
        }

    }

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
        //new InverseBWT().inverseBWT(args[0]);
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
