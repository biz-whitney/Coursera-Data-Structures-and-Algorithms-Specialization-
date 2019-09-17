import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.HashMap;

/*
Task. In this task your goal is to implement a simple phone book manager. It should be able to 
    process the following types of user’s queries:

        ∙ add number name. It means that the user adds a person with name name and phone number 
        number to the phone book. If there exists a user with such number already, then your manager 
        has to overwrite the corresponding name.
        ∙ del number. It means that the manager should erase a person with number number from the 
        phone book. If there is no such person, then it should just ignore the query.
        ∙ find number. It means that the user looks for a person with phone number number. The 
        manager should reply with the appropriate name, or with string “not found" (without quotes) 
        if there is no such person in the book.

Input Format. There is a single integer 𝑁 in the first line — the number of queries. It’s followed 
    by 𝑁 lines, each of them contains one query in the format described above.

Output Format. Print the result of each find query — the name corresponding to the phone number or 
    “not found" (without quotes) if there is no person in the phone book with such phone number. 
    Output one result per line in the same order as the find queries are given in the input.

####### Sample 1 ########
Input:
12
add 911 police 
add 76213 Mom 
add 17239 Bob 
find 76213
find 910
find 911
del 910
del 911
find 911
find 76213
add 76213 daddy 
find 76213

Output:
Mom
not found 
police 
not found 
Mom
daddy

*/

public class PhoneBook {

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    private List<Contact> contacts = new ArrayList<>();
    private HashMap<Integer, String> numbers = new HashMap<Integer, String>();

    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }


    private void processQuery(Query query) {
        if (query.type.equals("add")) {
            if (numbers.containsKey(query.number)) {
                numbers.remove(query.number);
                numbers.put(query.number, query.name);
            }
            else {
                numbers.put(query.number, query.name);
            }
        }
        else if (query.type.equals("del")) {
            if (numbers.containsKey(query.number)) {
                numbers.remove(query.number);
            }
        } else {
            String response = "not found";
            if (numbers.containsKey(query.number)) {
                response = numbers.get(query.number);
            }
            writeResponse(response);
        }
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}