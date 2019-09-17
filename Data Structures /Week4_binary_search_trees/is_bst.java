import java.util.*;
import java.io.*;

/*
Task. You are given a binary tree with integers as its keys. You need to test whether it is a correct 
    binary search tree. The definition of the binary search tree is the following: for any node of 
    the tree, if its key is 𝑥, then for any node in its left subtree its key must be strictly less 
    than 𝑥, and for any node in its right subtree its key must be strictly greater than 𝑥. In other 
    words, smaller elements are to the left, and bigger elements are to the right. You need to check 
    whether the given binary tree structure satisfies this condition. You are guaranteed that the 
    input contains a valid binary tree. That is, it is a tree, and each node has at most two children.

Input Format. The first line contains the number of vertices 𝑛. The vertices of the tree are numbered 
    from 0 to 𝑛−1. Vertex 0 is the root.
    
    The next 𝑛 lines contain information about vertices 0, 1, ..., 𝑛 − 1 in order. Each of these 
    lines contains three integers 𝑘𝑒𝑦𝑖, 𝑙𝑒𝑓𝑡𝑖 and 𝑟𝑖𝑔h𝑡𝑖 — 𝑘𝑒𝑦𝑖 is the key of the 𝑖-th vertex, 𝑙𝑒𝑓𝑡𝑖 is 
    the index of the left child of the 𝑖-th vertex, and 𝑟𝑖𝑔h𝑡𝑖 is the index of the right child of 
    the 𝑖-th vertex. If 𝑖 doesn’t have left or right child (or both), the corresponding 𝑙𝑒𝑓𝑡𝑖 or 
    𝑟𝑖𝑔h𝑡𝑖 (or both) will be equal to −1.

Output Format. If the given binary tree is a correct binary search tree (see the definition in the 
    problem description), output one word “CORRECT” (without quotes). Otherwise, output one word 
    “INCOR- RECT” (without quotes).

####### Sample 1 ########
Input:
3
2 1 2
1 -1 -1 
3 -1 -1

Output:
correct

####### Sample 2 ########
Input:
3
1 1 2
2 -1 -1 
3 -1 -1

Output:
incorrect

####### Sample 3 ########
Input:
5
1 -1 1
2 -1 2
3 -1 3
4 -1 4
5 -1 -1

Output:
correct

*/

public class is_bst {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;
            long max;
            long min;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
            if (tree.length == 0) {
                return true;
            }
            Node root = tree[0];
            root.min = Long.MIN_VALUE;
            root.max = Long.MAX_VALUE;
            int bool;
            bool = helper(root, 0);
            if (bool == 0) {
                return true;
            }
            return false;
        }


        //helper method for isBinarySearchTree
        private int helper(Node root, int bool) {
            if (root.left == -1 && root.right != -1) {
                Node rightNode = tree[root.right];
                rightNode.min = root.key;
                rightNode.max = root.max;
                if (root.key >= rightNode.key || root.key >= root.max || root.key <= root.min) {
                    bool ++;
                }
                bool += helper(rightNode, bool);
                return bool;
            }
            else if (root.left != -1 && root.right == -1) {
                Node leftNode = tree[root.left];
                leftNode.min = root.min;
                leftNode.max = root.key;
                if (root.key <= leftNode.key || root.key >= root.max || root.key <= root.min) {
                    bool ++;
                }
                bool += helper(leftNode, bool);
                return bool;

            }
            else if (root.left == -1 && root.right == -1) {
                if (root.key > root.max || root.key < root.min) {
                    bool++;
                    return bool;
                }
                return bool;
            }
            else {
                Node rightNode = tree[root.right];
                Node leftNode = tree[root.left];
                rightNode.min = root.key;
                rightNode.max = root.max;
                leftNode.min = root.min;
                leftNode.max = root.key;
                if ( root.key >= rightNode.key || root.key <= leftNode.key ||root.key >= root.max || root.key <= root.min) {
                    bool ++;
                }
                bool += helper(leftNode, bool);
                bool += helper(rightNode, bool);
            }
            return bool;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
