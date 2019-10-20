import java.util.*;
import java.lang.*;

/*
Task. Given a list of error-free reads, return an integer 𝑘 such that,
        when a de Bruijn graph is created from the 𝑘-length fragments
        of the reads, the de Bruijn graph has a single possible Eulerian
        Cycle.

Dataset. The input consist of 400 reads of length 100, each on a separate
        line. The reads contain no sequencing errors. Note that you are
        not given the 100-mer composition of the genome (i.e., some 100-mers
        may be missing).

Output. A single integer 𝑘 on one line.
 */

public class optimalK {
    int inputSize = 400;
    String[] reads = new String[inputSize];


    // finds the optimal k for a sequence
    public boolean isOptimized(int k) {
        int strLength = reads[2].length();
        Set<String> kmers = new HashSet<>();
        for (int i = 0; i < inputSize; i++) {
            String read = reads[i];
            for (int j = 0; j + k <= strLength; j++) {
                kmers.add(read.substring(j, j + k));
            }
        }
        Set<String> prefixes = new HashSet<>();
        Set<String> suffixes = new HashSet<>();
        for (int i = 0; i < kmers.size(); i++) {
            String kmer = kmers.iterator().next();
            int s = kmer.length();
            prefixes.add(kmer.substring(0, s - 1));
            suffixes.add(kmer.substring(1));
        }
        return suffixes.equals(prefixes);
    }

    public void findKmer() {
        int strLength = reads[2].length();
        for (int k = strLength - 1; k > 1; k--) {
            if (isOptimized(k)) {
                System.out.println(k + 1);
                break;
            }
        }
    }

    public static void main(String[] argv) {
        optimalK ok = new optimalK();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < ok.inputSize; i++) {
            ok.reads[i] = scanner.nextLine();
        }
        ok.findKmer();
    }
}
