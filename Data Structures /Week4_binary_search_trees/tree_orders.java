import java.util.*;
import java.io.*;

/*
Task. You are given a rooted binary tree. Build and output its in-order, pre-order and post-order 
	traversals. 
	
Input Format. The first line contains the number of vertices 𝑛. The vertices of the tree are numbered
	from 0 to 𝑛−1. Vertex 0 is the root.

	The next 𝑛 lines contain information about vertices 0, 1, ..., 𝑛 − 1 in order. Each of these lines 
	contains three integers 𝑘𝑒𝑦𝑖, 𝑙𝑒𝑓𝑡𝑖 and 𝑟𝑖𝑔h𝑡𝑖 — 𝑘𝑒𝑦𝑖 is the key of the 𝑖-th vertex, 𝑙𝑒𝑓𝑡𝑖 is the index 
	of the left child of the 𝑖-th vertex, and 𝑟𝑖𝑔h𝑡𝑖 is the index of the right child of the 𝑖-th vertex. 
	If 𝑖 doesn’t have left or right child (or both), the corresponding 𝑙𝑒𝑓𝑡𝑖 or 𝑟𝑖𝑔h𝑡𝑖 (or both) will be 
	equal to −1.
	
Output Format. Print three lines. The first line should contain the keys of the vertices in the 
	in-order traversal of the tree. The second line should contain the keys of the vertices in the 
	pre-order traversal of the tree. The third line should contain the keys of the vertices in the 
	post-order traversal of the tree.

####### Sample 1 ########
Input:
5
4 1 2 
2 3 4
5 -1 -1 
1 -1 -1 
3 -1 -1

Output:
 1 2 3 4 5 
 4 2 1 3 5 
 1 3 2 5 4

####### Sample 2 ########
Input:
10
072
10 -1 -1 
20 -1 6 
30 8 9 
40 3 -1 
50 -1 -1 
60 1 -1 
70 5 4 
80 -1 -1 
90 -1 -1

Output:
50 70 80 30 90 40 0 20 10 60 
0 70 50 40 30 80 90 20 60 10 
50 80 90 30 40 70 10 60 20 0

*/

public class tree_orders {
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

	public class TreeOrders {
		int n;
		int[] key, left, right;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
            inOrderHelper(result, 0);
			return result;
		}

		//inOrder Traveral helper method
		private void inOrderHelper(ArrayList<Integer> result, int k) {
			if (left[k] == -1 && right[k] != -1) {
				result.add(key[k]);
				inOrderHelper(result, right[k]);
				return ;
			}
			else if (left[k] != -1 && right[k] == -1) {
				inOrderHelper(result, left[k]);
				result.add(key[k]);
			}
			else if (left[k] == -1) {
				result.add(key[k]);
				return ;
			}
			else if ( right[k] == -1) {
				return ;
			}else {
				inOrderHelper(result, left[k]);
				result.add(key[k]);
				inOrderHelper(result, right[k]);
			}
		}

		List<Integer> preOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			preOrderHelper(result, 0);
			return result;
		}

		//preOrder Traveral helper method
		private void preOrderHelper(ArrayList<Integer> result, int k) {
			result.add(key[k]);
			if (left[k] == -1 && right[k] != -1) {
				preOrderHelper(result, right[k]);
				return ;
			}else if (left[k] != -1 && right[k] == -1) {
				preOrderHelper(result, left[k]);
				return ;
			}
			else if ( left[k] == -1 && right[k] == -1) {
				return ;
			}else {
				preOrderHelper(result, left[k]);
				preOrderHelper(result, right[k]);
			}
		}




		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
            postOrderHelper(result, 0);
			return result;
		}

		//preOrder Traveral helper method
		private void postOrderHelper(ArrayList<Integer> result, int k) {
			if (left[k] == -1 && right[k] != -1) {
				postOrderHelper(result, right[k]);
				result.add(key[k]);
				return ;
			}else if (left[k] != -1 && right[k] == -1) {
				postOrderHelper(result, left[k]);
				result.add(key[k]);
				return ;
			}

			else if ( left[k] == -1 && right[k] == -1) {
				result.add(key[k]);
				return ;
			} else {
				postOrderHelper(result, left[k]);
				postOrderHelper(result, right[k]);
				result.add(key[k]);
			}
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
