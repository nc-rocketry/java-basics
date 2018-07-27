import java.util.Arrays;


/**
 * Code Challenge:
 * Write an algorithm given an array of N integers to perform the following operation:
 *
 * Result array should contain the product of all elements in the source array except for the i'th value
 *
 * input: [ 2, 3, 5 ]
 * resulting computation: [ input[1] * input[2], input[0] * input[2], input[0] * input[1] ]
 * output: [ 15, 10, 6 ]
 */
public class ArrayMultiplier {

    public static void main(String[] args) {

        //int[] array= new int[] { 1, 3, 4, 5, 8, 2, 4, 9 };
        int[] array= new ArrayOrganizer().organize(ArrayTool.createRandomArray(20));
        System.out.println(ArrayTool.format(array));
        System.out.println(ArrayTool.format(computeProduct(array)));

    }

    public static int[] computeProduct(int[] input) {
        int zeros= 0;
        int[] result= new int[input.length];
        int totalProduct= 1;
        int zeroIndex= -1;

        for (int i= 0; i < input.length; i++) {
            if (input[i] == 0) {
                zeroIndex= i;
                if (++zeros > 1) { break; }
            } else {
                totalProduct *= (input[i] == 0 ? 1 : input[i]);
            }
        }

        if (zeros > 1) {
            // result array will contain all zeros
            return zeroArray(input.length);
        } else if (zeros == 1) {
            result= zeroArray(input.length);
            result[zeroIndex]= totalProduct;
        }

        for (int i= 0; i < input.length; i++) {
            if (zeros > 0) {
                if (input[i] == 0) {
                    result[i] = totalProduct;
                } else {
                    result[i] = 0;
                }

            } else {
                if (input[i] == 0) {
                    result[i] = totalProduct;
                } else {
                    result[i] = totalProduct / input[i];
                }
            }
        }

        return result;
    }

    private static int[] zeroArray(int n) {
        int[] array= new int[n];
        for (int i= 0; i < n; i++) {
            array[i]= 0;
        }
        return array;
    }


}

