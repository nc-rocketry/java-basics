import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


public class Solution {

    private final int[][] arr;

    public Solution(int[][] arr) {
        this.arr= arr;

    }

    public int solve() {
        Integer max= null;
        for(Hourglass h : findHourglasses(arr)) {
            int sum= h.sum();
            if (max == null || max < sum) {
                max= sum;
            }
        }
        return max.intValue();
    }

    private class Hourglass implements Iterable<Integer> {
        private final int[][] data;
        public final int row;
        public final int col;
        public Hourglass(int[][] data, int row, int col) {
            this.row= row;
            this.col= col;
            this.data= data;
        }

        public int sum() {
            int sum= 0;
            for (int value : this) {
                sum += value;
            }
            return sum;
        }

        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                final int r= row;
                final int c= col;
                int[][] operations= new int[][] { {0,0}, {0,1}, {0,2}, {1,1}, {2,0}, {2,1}, {2,2} };
                int instruction= 0;

                @Override
                public boolean hasNext() {
                    return instruction < operations.length;
                }

                public Integer next() {
                    int[] offset= operations[instruction++];
                    return data[r + offset[0]][c + offset[1]];
                }
            };
        }
    }

    // Complete the hourglassSum function below.
    public static int hourglassSum(int[][] arr) {
        return new Solution(arr).solve();
    }

    private List<Hourglass> findHourglasses(int[][] arr) {
        List<Hourglass> found= new ArrayList<>();

        for (int i = 0; i < arr.length - 2; i++){
            for (int j = 0; j < arr[i].length - 2; j++) {
                found.add(new Hourglass(arr, i, j));
            }
        }
        return found;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] arr = new int[][] {
                { -1, -1, 0, -9, -2, -2 },
                { -2, -1, -6, -8, -2, -5 },
                { -1, -1, -1, -2, -3, -4 },
                { -1, -9, -2, -4, -4, -5 },
                { -7, -3, -3, -2, -9, -9 },
                { -1, -3, -1, -2, -4, -5 }
        };

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
