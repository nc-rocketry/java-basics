import java.util.HashMap;
import java.util.Map;

public class ArrayOrganizer {

    private int moves= 0;

    public int getMoves() { return moves; }


    public int[] organize(int[] input) {

        int[] result = new int[input.length];
        // create a copy of the array so we can work with it and not mutate the input.
        System.arraycopy(input, 0, result, 0, input.length);

        // put the values of the input array into a map as keys
        // the value of the map will be the index within the input array
        // reason: this makes for faster access later, reducing cpu instruction cycles
        // cost: the map must be maintained during each move operation
        Map<Integer, Integer> map= mapIndexes(result); // O(n) == 1n

        // get the index for element == 0
        int z= map.get(0);

        for (int n= 1; n < result.length; n++) {  // O(n) = 3n
            if (z == 0) {
                if (result[n] != n) {
                    z= move(n, result, map);
                } else {
                    continue;
                }
            }

            z= move(map.get(z), result, map);

            if (result[n] != n) {
                z= move(n, result, map);
            }
        }

        if (result[0] != 0) {
            move(0, result, map);
        }

        return result;
    }

    public void resetMoveCounter() {
        this.moves= 0;
    }

    private static Map<Integer, Integer> mapIndexes(int[] input) {
        Map<Integer, Integer> map= new HashMap<>();
        for (int i= 0; i < input.length; i++) {
            map.put(input[i], i);
        }
        return map;
    }

    private int move(int j, int[] array, Map<Integer, Integer> map) {
        int zero= map.get(0);
        swap(zero, j, array, map);
        return j;
    }

    private void swap(int i, int j, int[] array, Map<Integer, Integer> map) {
        synchronized (array) {
            int t = array[i];
            array[i] = array[j];
            array[j] = t;
            map.put(array[i], i);
            map.put(array[j], j);
            this.moves++;
        }
    }


    public static void main(String[] args) {
        int[] input= ArrayTool.createRandomArray(8);
        ArrayOrganizer organizer= new ArrayOrganizer();
        System.out.println(ArrayTool.format(input));
        System.out.println(ArrayTool.format(organizer.organize(input)));
    }

}
