/****************************************************************************
 *  Execution:  java QuickFindUF input.txt
 *
 *  Quick-find algorithm.
 *
 ****************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickFindUF {
    private int[] id;
    private int count;

    // create data structure to hold N isolated components 0 through N-1
    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }

    }

    // return number of connected components
    public int count() {
        return count;
    }

    // check if elements p and q are in connected component
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // connect p and q
    public void union(int p, int q) {
        if (connected(p, q)){
            return;
        }
        int pId = id[p];
        for (int i = 0; i < id.length; i++)
            if (id[i] == pId) {
                id[i] = id[q];
            }
        count--;
    }

    public static void main(String[] args) throws FileNotFoundException {

        try {
            Scanner s = new Scanner(new File(args[0]));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            return;
        }

        Scanner s = new Scanner(new File(args[0]));

        int N = s.nextInt();
        QuickFindUF uf = new QuickFindUF(N);

        // read in a sequence of pairs of integers (each in the range 0 to N-1),
        // calling connected() for each pair to check if they are already connected
        // if not connected call union() and print the pair.
        while (s.hasNextInt()) {
            int p = s.nextInt();
            int q = s.nextInt();
            if (uf.connected(p, q)){
                continue;
            }
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println("# components: " + uf.count());
        s.close();
    }

}