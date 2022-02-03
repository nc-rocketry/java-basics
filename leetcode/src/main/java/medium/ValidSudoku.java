package medium;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidSudoku {


    public class Solution {

        public boolean isValidSudoku(char[][] board) {

            Map<Integer, Integer> map = new HashMap<>();

            char[][] rotated = rotate(board);
            char[] _3x3 = new char[9];
            int n = 0;
            for (int i = 0; i < board.length - 3; i+=3) {

                for (int j = i; j < i + 3; j++) {
                    if (!check(board[j]) || !check(rotated[j])) {
                        return false;
                    }

                    _3x3[n++] = board[i][j];
                    _3x3[n++] = board[i+1][j];
                    _3x3[n++] = board[i+2][j];

                }
                if (!check(_3x3)) {
                    System.out.println(i +": false");
                    print(_3x3);
                    return false;
                }
                n = 0;
            }
            return true;

        }


    }


    public static void main(String[] args) throws Exception {

        boolean valid = new ValidSudoku().new Solution().isValidSudoku(board(args));

        System.out.println(valid);
    }



    public static char[][] board(String[] args) {

        int r = 0;
        char[][] board = new char[args.length][];

        for (String row : args) {

            String[] elements = row.split(",");
            board[r] = new char[elements.length];

            int col = 0;
            for (Character c : Arrays.stream(elements).map(s -> s.charAt(0)).collect(Collectors.toList())) {
                board[r][col] = c;
            }

            r++;
        }

        return board;

    }

    public static boolean check(char[] row) {
        // does not repeat
        Set<Character> numbers = new HashSet<>();
        System.out.print("check: ");
        print(row);
        System.out.println();


        for (char c : row) {
            if (c == '.') continue;
            System.out.println(c + " in "+ numbers + " ? "+ (numbers.contains(c)));
            if (!numbers.contains(c)) {
                numbers.add(c);
            } else {
                // duplicate found, this is invalid
                return false;
            }

        }

        return true;
    }


    public static char[][] rotate(char[][] board) {
        char[][] result = new char[board.length][board.length];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                result[c][r] = board[r][c];
            }
        }
        return result;
    }


    public static void print(char[] array) {
        String comma = "";
        for (char c : array) {
            System.out.print(comma + c);
            comma = ",";
        }
    }
}
