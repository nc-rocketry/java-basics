package interview;

public class TestSort {


    public static void main(String[] args) {

        // int[] array = new int[]{ 1, 10, 3, 2, 7, 0, 2, -1 };
        // int[] array = new int[]{ 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        int[] array = new int[]{ 1, 2, 3, 4, 5 };

        MaximizeSpend.sortAndCalculate(array);

        for (int i : array) {
            System.out.println(i);
        }
    }
}
