import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
Task. You have a program which is parallelized and uses 𝑛 independent threads to process the given 
    list of 𝑚 jobs. Threads take jobs in the order they are given in the input. If there is a free 
    thread, it immediately takes the next job from the list. If a thread has started processing a 
    job, it doesn’t interrupt or stop until it finishes processing the job. If several threads try
    to take jobs from the list simultaneously, the thread with smaller index takes the job. For each 
    job you know exactly how long will it take any thread to process this job, and this time is the 
    same for all the threads. You need to determine for each job which thread will process it and 
    when will it start processing.

Input Format. The first line of the input contains integers 𝑛 and 𝑚. The second line contains 𝑚 
    integers 𝑡𝑖 — the times in seconds it takes any thread to process 𝑖-th job. The times are given 
    in the same order as they are in the list from which threads take jobs. Threads are indexed 
    starting from 0.

Output Format. Output exactly 𝑚 lines. 𝑖-th line (0-based index is used) should contain two 
    space- separated integers — the 0-based index of the thread which will process the 𝑖-th job 
    and the time in seconds when it will start processing that job.
 
####### Sample 1 ########
Input:
2 5
1 2 3 4 5

Output:
0 0
1 0
0 1
1 2
0 4

*/

public class JobQueue {
    private int numWorkers;
    private int[] jobs;
    Comparator<Worker> WorkerComparator= new Comparator<Worker>() {
        @Override
        public int compare(Worker o1, Worker o2) {
            if (o1.time > o2.time) {
                return 1;
            }
            else if (o1.time == o2.time && o1.id > o2.id) {
                return 1;
            }
            return -1;
        }
    };
    PriorityQueue<Worker> que = new PriorityQueue<>(WorkerComparator);

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    //class for the worker
    private class Worker {
        int id;
        long time;

        Worker(int id, long time) {
            this.id = id;
            this.time = time;
        }
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        for (int i = 0; i < numWorkers; i ++) {
            long zero = 0;
            Worker makeWorker = new Worker(i, zero);
            que.add(makeWorker);
        }
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            if (jobs[i] == 0) {
                Worker curWorker = que.peek();
                assignedWorker[i] = curWorker.id;
                startTime[i] = curWorker.time;
            } else {
                Worker curWorker = que.poll();
                assignedWorker[i] = curWorker.id;
                startTime[i] = curWorker.time;
                curWorker.time += Long.valueOf(jobs[i]);
                que.add(curWorker);

            }
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
