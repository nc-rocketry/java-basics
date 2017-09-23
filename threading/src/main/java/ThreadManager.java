import java.util.ArrayList;

public class ThreadManager {

    private ArrayList<Summation> workers= new ArrayList<Summation>();
    private ArrayList<Thread> threads= new ArrayList<Thread>();
    private final int nWorkers= 10;

    public ThreadManager(int[] work) {
        for (int[] slice : ArrayUtil.split(work, nWorkers)) {
            String comma= "";
            System.out.print(slice + " [ ");
            for (int i : slice) { System.out.print(comma + i); comma= ", "; }
            System.out.println(" ]");
            workers.add(new Summation(slice));
            Thread thread= new Thread(workers.get(workers.size() - 1));
            threads.add(thread);
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public boolean isFinished() {
        for (Thread thread : threads) {
            if (thread.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public int getResult() {
        int total= 0;

        for(Summation s : workers) {
            total+= s.getResult();
        }

        return total;
    }
}
