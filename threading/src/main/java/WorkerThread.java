public abstract class WorkerThread<I, R> implements Runnable {

    private Chunk<I> work;

    public WorkerThread(I work) {
        this.work= new Chunk<I>(work);
    }

    public I getWork() {
        return work.getData();
    }

    /** do the work that this worker should do **/
    public abstract void run();

    public abstract R getResult();

}
