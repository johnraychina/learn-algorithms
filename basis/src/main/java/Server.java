import java.util.Random;

import algs4.Queue;
import algs4.StdOut;
import algs4.StdRandom;

/**
 * @author Zhang Yi
 */
public class Server {

    private Queue<String> list = new Queue<>();
    private int load;

    public static void main(String[] args) {
        int n = 100;

        Server[] servers = new Server[n];
        for (int i = 0; i < n; i++) { servers[i] = new Server(); }

        // generate n random jobs and assign to a random processor
        for (int j = 0; j < n; j++) {
            String user = "user" + j;
            new Random().nextInt();
            int i = StdRandom.uniform(n);
            servers[i].add(user);
        }

        // see how even the distribution is by printing out the
        // contents of each server
        for (int i = 0; i < n; i++) { StdOut.println(i + ": " + servers[i]); }
    }

    public void add(String user) {
        list.enqueue(user);
        load++;
    }

    // string representation
    public String toString() {
        // String s = String.format("%5d:  ", load);
        String s = "";
        for (String user : list) { s += user + " "; }
        return s;
    }
}
