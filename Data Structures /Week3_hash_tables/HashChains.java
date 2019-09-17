import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.LinkedList;

/*

Task. In this task your goal is to implement a hash table with lists chaining. Your program should 
    support the following kinds of queries:

        ∙ add string — insert string into the table. If there is already such string in the hash 
        table, then just ignore the query.
        ∙ del string — remove string from the table. If there is no such string in the hash table, 
        then just ignore the query.
        ∙ find string — output “yes" or “no" (without quotes) depending on whether the table contains 
        string or not.
        ∙ check 𝑖 — output the content of the 𝑖-th list in the table. Use spaces to separate the 
        elements of the list. If 𝑖-th list is empty, output a blank line.

    When inserting a new string into a hash chain, you must insert it in the beginning of the chain.

Input Format. There is a single integer 𝑚 in the first line — the number of buckets you should have. 
    The next line contains the number of queries 𝑁. It’s followed by 𝑁 lines, each of them contains 
    one query in the format described above.

Output Format. Print the result of each of the find and check queries, one result per line, in the 
    same order as these queries are given in the input.

####### Sample 1 ########
Input:
5
12
add world 
add HellO 
check 4 find World 
find world 
del world 
check 4 
del HellO 
add luck 
add GooD 
check 2 
del good

Output:
HellO world 
no
yes
HellO
GooD luck

*/

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    // for HashTable function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    //For the hashtable
    private LinkedList[] HashTable;


    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        //out.flush();
    }

    //To add element into the Hashtable
    private void addElement (String str) {
        int code = hashFunc(str);
        LinkedList lst = HashTable[code];
        if (!lst.contains(str)) {
            lst.add(0, str);
        }
    }

    //Finds if the element is in the Hashtable
    private boolean findElement (String str) {
        int code = hashFunc(str);
        LinkedList lst = HashTable[code];
        if (lst.contains(str)) {
            return true;
        } else {
            return false;
        }
    }

    //Check the elements in a particular table
    private void checkElements (int index) {
        LinkedList lst = HashTable[index];
        for (int i = 0; i < lst.size(); i++) {
            out.print(lst.get(i) + " ");
            //out.print(" ");*/
        }
    }

    //Delets an element from the hashtable
    private void delElement(String str) {
        int code = hashFunc(str);
        LinkedList lst = HashTable[code];
        if (lst.contains(str)) {
            lst.remove(str);
        }

    }

    private void processQuery(Query query) {
        switch (query.type) {
            case "add":
                addElement(query.s);
                break;
            case "del":
                delElement(query.s);
                break;
            case "find":
                writeSearchResult(findElement(query.s));
                break;
            case "check":
                checkElements(query.ind);
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                //out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        int queryCount = in.nextInt();
        HashTable = new LinkedList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            LinkedList<String> lst = new LinkedList<>();
            HashTable[i] = lst;
        }
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

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