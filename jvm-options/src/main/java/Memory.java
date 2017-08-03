import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Memory implements Runnable {

    private Thread thread;

    public Memory() {
        this.thread= new Thread(this);
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {

        while (true) {
            // the Runtime object follows the singleton pattern
            Runtime runtime= Runtime.getRuntime();

            long maxMemBytes= runtime.maxMemory() / 1024;
            long totalMemBytes= runtime.totalMemory() / 1024;
            long freeMemBytes= runtime.freeMemory() / 1024;
            long usedMemBytes= totalMemBytes - freeMemBytes;

            //System.out.println("JVM Max Heap Size: "+ toMb(maxMemBytes));
            //System.out.println("JVM currently allocated memory (mb): "+ toMb(maxMemBytes));
            //System.out.println("JVM currently using memory (mb): "+ toMb(usedMemBytes));
            NumberFormat pcnt= NumberFormat.getInstance();
            pcnt.setMaximumFractionDigits(2);

            System.out.println(formatBytes(usedMemBytes) +"/"+ formatBytes(maxMemBytes) +"mb used/max ("+ pcnt.format(usedMemBytes/((double)maxMemBytes)) +"%)");

            try { Thread.sleep(5000); } catch (InterruptedException irq) { break; }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        List<String> things= new LinkedList<>();
        byte[] buffer= new byte[256];

        Memory mem= new Memory();
        mem.start();

        while (true) {
            // just create another object... :)
            // eventually we'll run out of memory
            Random r= new Random();
            r.nextBytes(buffer);
            things.add(new String(buffer));

            if (things.size() % 1000000 == 0) {
                System.out.println(things.size() + " 256 byte strings in memory");
            }
        }
    }

    private static String formatBytes(long bytes) {
        String uom= "Kb";
        if (bytes > 1000) {
            bytes/= 1024;
            uom= "Mb";
            if (bytes > 1000) {
                bytes/= 1024;
                uom= "Gb";
            }
        }
        return bytes + uom;
    }


}
