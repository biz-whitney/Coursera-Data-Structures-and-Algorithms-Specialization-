import java.util.*;

/*
Task. The goal of this problem is to implement the algorithm for computing the edit distance
    between two strings.
Input Format. Each of the two lines of the input contains a string consisting of lower case
    latin letters. Constraints. The length of both strings is at least 1 and at most 100.
Output Format. Output the edit distance between the given two strings.


######### Sample 1 ########
Input:
ab
ab

Output:
0


######### Sample 2 ########
Input:
short
ports

Output:
3


######### Sample 3 ########s
Input:
editing
distance

Output:
5

 */


class EditDistance {
  public static int EditDistance(String s, String t) {
    //write your code here
    int col = t.length() + 1;
    int row = s.length() + 1;
    int[][] matrix = new int[row][col];
    for (int i = 0; i < row; i ++) {
      matrix[i][0] = i;
    }
    for (int i = 0; i < col; i ++) {
      matrix[0][i] = i;
    }
    char[] sARRAY = s.toCharArray();
    char[] tARRAY = t.toCharArray();


    for (int i = 1; i < row; i++) {
        char oneChar = sARRAY[i - 1];
        for (int j = 1; j < col; j++) {
            char twoChar = tARRAY[j - 1];
            int matchSeq = matrix[i-1][j-1] + Sim(oneChar, twoChar);
            int gapOne = matrix[i][j -1] + 1;
            int gapTwo = matrix[i -1][j] + 1;
            matrix[i][j] = Math.min(matchSeq, Math.min(gapOne, gapTwo));
        }
    }

    return matrix[row - 1][col - 1];
  }


    private static int Sim(char one, char two) {
        if (one == two) {
            return 0;
        }
        return 1;
    }

  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);
    String s = scan.next();
    String t = scan.next();


    System.out.println(EditDistance(s, t));
  }

}
