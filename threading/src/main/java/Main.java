public class Main {

    public static void main(String[] args) {


        int[] work= new int[] {1,2,3,4,5,7,6,5,4,3,9,8,7,6,1,23,4,0};

        ThreadManager manager= new ThreadManager(work);

        manager.start();

        while (!manager.isFinished()) {
            try { Thread.sleep(200); } catch (InterruptedException ignore) { }
        }

        System.out.println("Result: "+ manager.getResult());

    }
}
