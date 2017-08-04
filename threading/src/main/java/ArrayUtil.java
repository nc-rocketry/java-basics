public class ArrayUtil<T> {


    /**
     * Divide an array into pieces
     *
     * Behavior:
     * This method should return an array of n pieces as specified by the input parameter.
     * However, the number of pieces could be > array.length.  In this condition the method
     *     should return each item of the source array as a single element of the result array.
     *     i.e.  input array size = 15, pieces = 20.  Method should return 15 pieces.
     *
     *
     * @param array int[]
     * @param pieces int
     * @return int[][]
     */
    public static int[][] split(int[] array, int pieces) {

        if (pieces > array.length) {
            pieces= array.length;
        }
        int offset= 0;
        int size= array.length / pieces;

        int[][] slices= new int[pieces][];

        for (int i= 0; i < pieces; i++) {
            if (array.length - offset < 2*size) {
                size= array.length - offset;
            }

            System.arraycopy(array, offset, slices[i]= new int[size], 0, size);
            offset+= size;
        }

        return slices;
    }

    public static void main(String[] args) {


        int[] numbers= new int[] {
                1,2,3,4,9,8,7,6,5,4,3,2,3,4,9,8,
                1,2,3,4,9,8,7,6,5,4,3,2,3,4,9,8,6,2,3,4,5,6,7,1,2,3,4,9,0,8,0,
                1,2,3,4,9,8,7,6,5,4,3,2,3,4,9,8,6,2,3,4,5,6,7,1,2,3,4,9,0,8,0,
                1,2,3,4,9,8,7,6,5,4,3,2,3,4,9,8,6,2,3,4,5,6,7,1,2,3,4,9,0,8,0,
                1,2,3,4,9,8,7,6,5,4,3,2,3,4,9,8,6,2,3,4,5,6,7,1,2,3,4,9,0,8,0,
                1,2,3,4,9,8,7,6,5,4,3,2,3,4,9,8,6,2,3,4,5,6,7,1,2,3,4,9,0,8,0,
                1,2,3,4,9,8,7,6,5,4,3,2,3,4,9,8,6,2,3,4,5,6,7,1,2,3,4,9,0,8,0,
                1,2,3,4,9,8,7,6,5,4,3,2,3,4,9,8,6,2,3,4,5,6,7,1,2,3,4,9,0,8,0,
                1,2,3,4,9,8,7,6,5,4,3,2,3,4,9,8,6,2,3,4,5,6,7,1,2,3,4,9,0,8,0,
                9,8,7,6,5,4,3,2,3,4,9,8,7
        };


        int i= 0;
        for (int[] piece : split(numbers, 500)) {
            System.out.print("piece #"+ i);

            String comma= "";
            System.out.print(" [");
            for (int n : piece) {
                System.out.print(comma + n);
                comma= ",";
            }
            System.out.print("]");

            System.out.println();

            i++;
        }

    }

}
