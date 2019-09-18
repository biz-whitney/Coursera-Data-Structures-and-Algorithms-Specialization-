import java.io.*;
import java.util.*;
/*
Task. Implement TrieMatching algorithm.

Input Format. The first line of the input contains a string Text, the second line contains an integer 𝑛,
    each of the following 𝑛 lines contains a pattern from Patterns = {𝑝1, . . . , 𝑝𝑛}.

Output Format. All starting positions in Text where a string from Patterns appears as a substring in
    increasing order (assuming that Text is a 0-based array of symbols).


####### Sample 1 #####
Input:
AAA
1
AA

Output:
0 1

####### Sample 1 #####
Input:
AATCGGGTTCAATCGGGGT
2
ATCG
GGGT

Output:
1 4 11 15



 */

class node {
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];

	node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
	}
}

public class TrieMatching implements Runnable {
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return node.NA;
		}
	}

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();
		char[] textArray = text.toCharArray();

		// write your code here
		String[] pat = new String[n];
		for (int i = 0; i < n; i++) {
			pat[i] = patterns.get(i);
		}
		List<Map<Character, Integer>> trie = buildTrie(pat);
		for (int i = 0; i < textArray.length; i++) {
			prefixTrieMatching(textArray, i, result, trie);
		}

		return result;
	}

	//Checks if there is a pattern starting from a prefix
	public void prefixTrieMatching(char[] text, int i, List<Integer> result,
								   List<Map<Character, Integer>> trie) {
		int position= i;
		char symbol = text[i];
		Map<Character, Integer> currNode = trie.get(0);
		while (true) {
			if (currNode.isEmpty() )  {
				result.add(i);
				return;
			}
			else if (currNode.containsKey(symbol)) {
				int index = currNode.get(symbol);
				currNode = trie.get(index);
				position ++;
				if (position == text.length) {
					if (currNode.isEmpty() )  {
						result.add(i);
					}
					return;
				}
				symbol = text[position];
			}
			else {
				return;
			}
		}
	}


	//build the trie data structure
	List<Map<Character, Integer>> buildTrie(String[] patterns) {
		List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
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

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatching ()).start ();
	}
}
