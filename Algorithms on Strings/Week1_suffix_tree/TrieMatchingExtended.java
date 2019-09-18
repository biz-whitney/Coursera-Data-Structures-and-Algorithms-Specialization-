import java.io.*;
import java.util.*;
import java.util.stream.*;
/*
Task. Extend TrieMatching algorithm so that it handles correctly cases when one of the patterns is a
	prefix of another one.

Input Format. The first line of the input contains a string Text, the second line contains an integer
	𝑛, each of the following 𝑛 lines contains a pattern from Patterns = {𝑝1, . . . , 𝑝𝑛}.

Output Format. All starting positions in Text where a string from Patterns appears as a substring in
	increasing order (assuming that Text is a 0-based array of symbols). If more than one pattern
	appears starting at position 𝑖, output 𝑖 once.


####### Sample 1 #####
Input:
AAA
1
AA

Output:
0 1

####### Sample 1 #####
Input:
AAA
1
AA

Output:
0 1

####### Sample 2 #####
Input:
ACATA
3
AT
A
AG

Output:
0 2 4

 */

class TrieNode
{
	char value;
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];
	public boolean patternEnd;

	TrieNode()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
		patternEnd = false;
	}
}

public class TrieMatchingExtended implements Runnable {
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return TrieNode.NA;
		}
	}

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();
		// write your code here
		String[] pat = new String[n];
		char[] textArray = text.toCharArray();
		for (int i = 0; i < n; i++) {
			pat[i] = patterns.get(i);
		}
		List<TrieNode> trie = new ArrayList<>();
		buildTrie(trie, pat);
		for (int i = 0; i < textArray.length; i++) {
			prefixTrieMatching(textArray, trie, i, result);

		}
		return result;
	}

	//Search the trie for pattern
	public void prefixTrieMatching(char[] textArray, List<TrieNode> trie, int i, List<Integer> result) {
		int position = i;
		char symbol = textArray[i];
		TrieNode currNode = trie.get(0);
		while (true) {
			if (IntStream.of(currNode.next).sum() == -4) {
				result.add(i);
				return;
			}
			else if (currNode.patternEnd == true) {
				result.add(i);
				return ;
			}
			else if (currNode.next[letterToIndex(symbol)] != TrieNode.NA) {
				int index = currNode.next[letterToIndex(symbol)];
				currNode = trie.get(index);
				position ++;
				if (position == textArray.length) {
					if (IntStream.of(currNode.next).sum() == - 4 )  {
						result.add(i);
					}
					else if (currNode.patternEnd == true) {
						result.add(i);
					}
					return;
				}
				symbol = textArray[position];

			} else {
				return;
			}
		}
	}

	//builds the trie
	public void buildTrie(List<TrieNode> trie, String[] patterns) {
		TrieNode root = new TrieNode();
		trie.add(root);
		for (int i = 0; i < patterns.length; i++) {
			TrieNode currTrieNode = trie.get(0);
			char[] pat = patterns[i].toCharArray();
			for (int j = 0; j < pat.length; j++) {
				char currSymbol = pat[j];
				if (currTrieNode.next[letterToIndex(currSymbol)] != TrieNode.NA) {
					int index = currTrieNode.next[letterToIndex(currSymbol)];
					currTrieNode = trie.get(index);
					if (j == pat.length - 1) {
						currTrieNode.patternEnd = true;
					}
				} else {
					TrieNode newTrieNode = new TrieNode();
					newTrieNode.value = currSymbol;
					currTrieNode.next[letterToIndex(currSymbol)] = trie.size();
					trie.add(newTrieNode);
					currTrieNode = newTrieNode;
					if (j == pat.length - 1) {
						currTrieNode.patternEnd = true;
					}
				}
			}
		}
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
		new Thread (new TrieMatchingExtended ()).start ();
	}
}
