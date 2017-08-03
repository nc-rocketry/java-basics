public class Summation extends WorkerThread<int[], Integer> {

    private int result;

    public Summation(int[] data) {
        super(data);
    }

    @Override
    public void run() {
        for (int value : getWork()) {
            result+= value;
        }
    }

    public Integer getResult() {
        return result;
    }

}
