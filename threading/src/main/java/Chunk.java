/**
 * represents an abstract chunk of work to be given to a WorkerThread
 */
public class Chunk<T> {

    private T data;

    public Chunk(T data) {
        this.data= data;
    }

    public T getData() {
        return data;
    }
}
