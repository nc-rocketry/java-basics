import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.regex.*;
import java.util.stream.*;


public class Solution {

    private Grid grid;
    private XY start;
    private XY goal;

    public Solution(String[] rawGrid, int startX, int startY, int goalX, int goalY) {
        grid= new Grid(rawGrid);
        start= new XY(startX, startY);
        goal= new XY(goalX, goalY);
    }

    public int solve() {
        XY current= start;
        List<XY> history= new ArrayList<>();
        history.add(current);
        Integer bestSolution= null;
        while (!current.equals(goal)) {
            List<XY> moves= grid.getPossibleMoves(current, history);

            if (moves.size() == 0) {
                // no more moves, unsolvable
                return -1;
            }


            for (XY move : moves) {
                int solution= grid.solve(history, move, goal);
                if (bestSolution == null || solution < bestSolution) {
                    bestSolution= solution;
                    current= move;
                }

            }
        }

        return bestSolution.intValue();

    }
    private class XY implements Comparable<XY>{
        public int x;
        public int y;
        public XY(int x, int y) {
            this.x= x;
            this.y= y;
        }
        public int diff(XY from) {
            return (int) Math.sqrt( Math.pow((x - from.x), 2) - Math.pow((y - from.y), 2) );
        }
        public int compareTo(XY o) {
            return diff(o);
        }
        public boolean equals(XY o) {
            return x == o.x && y == o.y;
        }


        public String toString() {
            return x +","+ y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            XY xy = (XY) o;

            if (x != xy.x) return false;
            return y == xy.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    private class Square {
        public final XY coordinate;
        public final char value;
        public Square(XY coordinate, char value) {
            this.coordinate= coordinate;
            this.value= value;
        }

        // can only move north, south, east, or west from current position.
        public boolean canMoveTo(XY from) {
            if (value == 'X') { return false; }
            if (   ((coordinate.x + 1 == from.x) && (coordinate.y == from.y))
                || ((coordinate.x - 1 == from.x) && (coordinate.y == from.y))
                || ((coordinate.x == from.x) && (coordinate.y + 1 == from.y))
                || ((coordinate.x == from.x) && (coordinate.y - 1 == from.y))
            ) {
                return true;
            }
            return false;
        }

        public String toString() {
            return coordinate.toString() + "("+ value +")";
        }

    }

    private class Grid {
        public String[] grid;
        public char[][] map;
        public Set<Square> positions= new HashSet<>();
        int solution;
        public Grid(String[] grid) {
            this.solution= (int)Math.pow(grid.length, 2);
            this.grid= grid;
            this.map= new char[grid.length][];
            int r= 0;
            for (String row : grid) {
                map[r]= new char[row.length()];

                for (int i= 0; i < row.length(); i++) {
                    map[r][i]= row.charAt(i);
                    positions.add(new Square(new XY(r, i), map[r][i]));
                }
                r++;
            }


        }

        public String toString() {
            StringBuilder sb= new StringBuilder();
            for (char[] row : map) {
                for (char c : row) {
                    sb.append(" ").append(c).append(" ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        List<XY> getPossibleMoves(XY from, List<XY> history) {
            return positions.stream()
                    .filter(p -> p.canMoveTo(from))
                    .map(p -> p.coordinate)
                    .filter(new Predicate<XY>() {
                        @Override
                        public boolean test(XY xy) {
                            boolean c= !history.contains(xy);
                            return c;
                        }
                    })
                    .collect(Collectors.toList());
        }

        public int solve(List<XY> history, XY next, XY goal) {

            history.add(next);
            if (next.equals(goal)) {
                return history.size();
            } else {
                List<XY> moves= getPossibleMoves(next, history);

                for (XY move : moves) {
                    int s= solve(history, move, goal);

                    if (s < solution) {
                        solution = s;
                        return s;
                    } else {
                        history.remove(move);
                    }

                }
            }

            return solution;
        }
    }

    public static int min(int a, int b) {
        return (a < b ? a  : b);
    }


    // Complete the minimumMoves function below.
    static int minimumMoves(String[] rawGrid, int startX, int startY, int goalX, int goalY) {


        return new Solution(rawGrid, startX, startY, goalX, goalY).solve();




    }




    public static void main(String[] args) throws IOException {

        String[] grid = new String[] {
                "...",
                ".X.",
                "..."
        };

        String[] startXStartY = "0 0 0 2".split(" ");

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        System.out.println(String.valueOf(result));

    }
}

