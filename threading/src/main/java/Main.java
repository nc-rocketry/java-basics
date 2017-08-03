public class Main {

    public static void main(String[] args) {



        ThreadManager manager= new ThreadManager(15);

        manager.start();

        while (!manager.isFinished()) {
            try { Thread.sleep(200); } catch (InterruptedException ignore) { }
        }

        System.out.println("Result: "+ manager.getResult());

    }
}
