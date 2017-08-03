import java.util.ArrayList;

public class ThreadManager {

    private ArrayList<Summation> workers= new ArrayList<Summation>();
    private ArrayList<Thread> threads= new ArrayList<Thread>();

    public ThreadManager(int nWorkers) {

        // TODO: split the work into slices, hand slices to the threads
        int[] slice= new int[] {1,2,3,4,5,6,7,8,9,10};

        int chunkSize= 50;
        int offset= 0;

        for (int i= 0; i < nWorkers; i++) {
            //int[] slice= new int[chunkSize];
            //System.arraycopy(work, offset, slice, 0, chunkSize);
            workers.add(new Summation(slice));
            Thread thread= new Thread(workers.get(workers.size() - 1));
            threads.add(thread);
            offset+= chunkSize;
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
