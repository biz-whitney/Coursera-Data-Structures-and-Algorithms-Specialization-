# python3

import sys
import threading

# Task. You are given a description of a rooted tree. Your task is to compute and output its height.
# 	Recall that the height of a (rooted) tree is the maximum depth of a node, or the maximum 
# 	distance from a leaf to the root. You are given an arbitrary tree, not necessarily a binary 
# 	tree.

# Input Format. The first line contains the number of nodes 𝑛. The second line contains 𝑛 integer 
# 	numbers from −1 to 𝑛 − 1 — parents of nodes. If the 𝑖-th one of them (0 ≤ 𝑖 ≤ 𝑛 − 1) is −1, 
# 	node 𝑖 is the root, otherwise it’s 0-based index of the parent of 𝑖-th node. It is guaranteed 
# 	that there is exactly one root. It is guaranteed that the input represents a tree.

# Output Format. Output the height of the tree.

# ####### Sample 1 ########
# Input:
# 5
# 4 -1 4 1 1

# Output:
# 3

####### Sample 2 ########
Input:
 5
-1 0 4 0 3

Output:
4

def compute_height(n, parents):
    # Replace this code with a faster implementation
    max_height = 0
    for vertex in range(n):
        height = 0
        current = vertex
        while current != -1:
            height += 1
            current = parents[current]
        max_height = max(max_height, height)
    return max_height


def main():
    n = int(input())
    parents = list(map(int, input().split()))
    print(compute_height(n, parents))


# In Python, the default limit on recursion depth is rather low,
# so raise it here for this problem. Note that to take advantage
# of bigger stack, we have to launch the computation in a new thread.
sys.setrecursionlimit(10**7)  # max depth of recursion
threading.stack_size(2**27)   # new thread will get stack of such size
threading.Thread(target=main).start()
