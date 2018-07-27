import java.util.LinkedList;
import java.util.Random;

public class ArrayTool {

    public static String format(int[] array) {
        StringBuilder sb= new StringBuilder("[");

        String delim= "";

        for (int i= 0; i < array.length; i++) {
            // sb.append(delim + i +":"+ array[i]);
            sb.append(delim + array[i]);
            delim= ",";
        }

        sb.append("]");

        return sb.toString();
    }

    public static int[] createRandomArray(int n) {
        return createRandomArray(n, 5);
    }

    public static int[] createRandomArray(int n, long seed) {
        Random rnd= new Random(seed);
        LinkedList<Integer> set= new LinkedList<>();
        int[] array= new int[n];

        for (int i= 0; i < n; i++) {
            set.add(i);
        }

        for (int i= 0; i < n; i++) {
            array[i]= set.remove(rnd.nextInt(set.size()));
        }

        return array;
    }

}
