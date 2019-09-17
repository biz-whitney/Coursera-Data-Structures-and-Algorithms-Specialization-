import java.io.*;
import java.util.*;
/*

Task. The first step of the HeapSort algorithm is to create a heap from the array you want to sort. 
    By the way, did you know that algorithms based on Heaps are widely used for external sort, when 
    you need to sort huge files that don’t fit into memory of a computer? Your task is to implement 
    this first step and convert a given array of integers into a heap. You will do that by applying 
    a certain number of swaps to the array. Swap is an operation which exchanges elements 𝑎𝑖 and 𝑎𝑗 
    of the array 𝑎 for some 𝑖 and 𝑗. You will need to convert the array into a heap using only 𝑂(𝑛) 
    swaps, as was described in the lectures. Note that you will need to use a min-heap instead of a 
    max-heap in this problem.

Input Format. The first line of the input contains single integer 𝑛. The next line contains 𝑛 
    space-separated integers 𝑎𝑖.

Output Format. The first line of the output should contain single integer 𝑚 — the total number of 
    swaps. 𝑚 must satisfy conditions 0 ≤ 𝑚 ≤ 4𝑛. The next 𝑚 lines should contain the swap operations 
    used to convert the array 𝑎 into a heap. Each swap is described by a pair of integers 𝑖,𝑗 — the 
    0-based indices of the elements to be swapped. After applying all the swaps in the specified 
    order the array must become a heap, that is, for each 𝑖 where 0 ≤ 𝑖 ≤ 𝑛 − 1 the following 
    conditions must be true: 

        1. If2𝑖+1≤𝑛−1,then𝑎𝑖 <𝑎2𝑖+1. 
        2. If2𝑖+2≤𝑛−1,then𝑎𝑖 <𝑎2𝑖+2.

    Note that all the elements of the input array are distinct. Note that any sequence of swaps t
    hat has length at most 4𝑛 and after which your initial array becomes a correct heap will be 
    graded as correct.

####### Sample 1 ########
Input:
5
5 4 3 2 1

Output:
3 
1 4
0 1
1 3

####### Sample 2 ########
Input:
5
1 2 3 4 5

Output:
0

*/

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }


    //Gets the parent of the node
    private int Parent(int i) {
        return i/2;
    }

    //Gets the left child of a node
    private int LeftChild(int i) {
        return 2* i;
    }

    //Gets the right child of a node
    private int RightChild(int i) {
        return 2 * i + 1;
    }

    //Shifts node up
    private void shiftUp(int i, int size) {
        while (i > 1 && data[Parent(i) - 1]  < data[i - 1]) {
            int temp = data[i - 1];
            data[i - 1] = data[Parent(i) - 1];
            data[Parent(i) - 1] = temp;
            i = Parent(i);
        }
    }

    //Shifts a node down in the heap and stores the swaps
    private void shiftDown(int i, int size) {
        int minIndex = i - 1;
        int left = LeftChild(i) -1;
        if (left < size && data[left] < data[minIndex] ) {
            minIndex = left;
        }
        int right = RightChild(i) - 1;
        if (right < size && data[right] < data[minIndex]) {
            minIndex = right;
        }
        if ( i - 1 != minIndex) {
            swaps.add(new Swap(Math.min(minIndex, i - 1), Math.max(minIndex, i - 1)));
            int temp = data[i -1];
            data[i -1] = data[minIndex];
            data[minIndex] = temp;
            shiftDown(minIndex + 1,size);
        }
    }

    //Swaps two nodes in the heap
    private void swap(int i, int j) {
        int temp = data[i-1];
        data[i -1] = data[j];
        data[j] = temp;
    }

    //second method to shift down a node
    private void shiftDown2(int i, int size) {
        int minIndex = i - 1;
        int left = LeftChild(i) -1;
        if (left < size && data[left] < data[minIndex] ) {
            minIndex = left;
        }
        int right = RightChild(i) - 1;
        if (right < size && data[right] < data[minIndex]) {
            minIndex = right;
        }
        if ( i - 1 != minIndex) {
            int temp = data[i -1];
            data[i -1] = data[minIndex];
            data[minIndex] = temp;
            shiftDown2(minIndex + 1,size);
        }
    }

    //Tp start building the heap
    private void buildMinheap(int size) {
        for (int i = (size + 1)/ 2; i > 0; i --) {
            shiftDown(i, size);
        }
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
        int size = data.length;
        buildMinheap(data.length);
        for(int i = data.length ; i>=2 ; i-- ) {
            swap(1, i - 1);
            size -=1;
            shiftDown2(1, size);
        }
        Collections.reverse(Arrays.asList(data));
    }


    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
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
