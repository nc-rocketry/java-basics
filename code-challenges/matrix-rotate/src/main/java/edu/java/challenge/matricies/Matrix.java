package edu.java.challenge.matricies;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;
import java.util.stream.Collectors;

/** represent a matrix in a most fundamental way.  A two-dimensional array. **/
public class Matrix<T> {
    
    private List<List<Node<T>>> matrix;

    public class Node<V> {
        // tiny inner-class to store data at each location in the matrix
        public final V value;
        public final int row;
        public final int col;
        public Node(int r, int c, V value) { 
            this.row= r;
            this.col= c;
            this.value= value; 
        }
        public String toString() { 
            return "("+ row +","+ col +")=>"+ (value != null ? value.toString() : null);
        }
    }
    
    // construct an empty rXc matrix with null values
    public Matrix(int rows, int columns) {
        this.matrix= new LinkedList<>();
        // initialize each cell
        for (int r= 0; r < rows; r++) {
            LinkedList<Matrix<T>.Node<T>> row = new LinkedList<Node<T>>();
            matrix.add(r, row);
            for (int c= 0; c < columns; c++) {
                row.add(new Node<T>(r, c, null));
            }
        }
    }

    // construct and fill a new matrix given the provided size and data
    public Matrix(int rows, int columns, List<List<T>> data) {
        this.matrix= new LinkedList<>();
        for (int r= 0; r < rows; r++) {
            LinkedList<Matrix<T>.Node<T>> row = new LinkedList<Node<T>>();
            this.matrix.add(r, row);
            for (int c= 0; c < columns; c++) {
                row.add(new Node<T>(r,c,data.get(r).get(c)));
            }
        }
    }

    // construct and fill a new matrix given the array[][]
    public Matrix(T[][] data) {
        int rows= data.length;
        int columns= data[0].length;
        this.matrix= new LinkedList<>();
        for (int r= 0; r < rows; r++) {
            LinkedList<Node<T>> row= new LinkedList<>();
            for (int c= 0; c < columns; c++) {
                row.add(new Node<T>(r,c,data[r][c]));
            }
            this.matrix.add(row);
        }
    }
    
    // PRIVATE
    // construct a matrix from a list of nodes.
    protected Matrix(List<Node<T>> result) {
        this.matrix= new LinkedList<>();
        for (Node<T> node : result) {
            int r= node.row;
            int c= node.col;
            
            while (r >= matrix.size()) {
                matrix.add(new LinkedList<>());
            }
            
            List<Node<T>> row = matrix.get(r);

            while (c >= row.size()) {
                row.add(new Node<T>(r, c, null));
            }
            row.set(c, new Node<T>(r, c, node.value));
        }
    }

    public String toString() {
        StringBuilder str= new StringBuilder();
        for (int r= 0; r < matrix.size(); r++) {
            for (int c= 0; c < matrix.get(r).size(); c++) {
                Node<T> node= matrix.get(r).get(c);
                str.append(" {"+ node.value +"} ");
            }
            str.append("\n");
        }
        
        return str.toString();
    }
    
    public Matrix<T> apply(Function<Node<T>, T> f) {
        List<Node<T>> result = matrix.stream().flatMap(row -> 
                row.stream().map(node -> 
                    new Node<T>(node.row, node.col, f.apply(node))
                )).collect(Collectors.toList());
        return new Matrix<T>(result);
    }
    
    public Matrix<T> copy() {
        return this.apply(v -> v.value);
    }
    
    
    // rotate the matrix 90 counter clockwise
    // [ 0 1 ]       [ 1 3 ]
    // [ 2 3 ]  ==>> [ 0 2 ]

    // counter clockwise rotate
    // [ 1 . . ]       [ . . 3 ]      [ 3 . . ]      [ . . 1 ]
    // [ . 2 . ]       [ . 2 . ]      [ . 2 . ]      [ . 2 . ]
    // [ . . 3 ]  ==>> [ 1 . . ] ==>> [ . . 1 ] ==>> [ 3 . . ]
    
    public Matrix<T> rotate() {
        int rows= rows();
        List<Node<T>> cells= new LinkedList<>();
        int c= 0;
        for (int r= 0; r < rows; r++) {
            cells.addAll(rotate(matrix.get(r), c++));
        }
        return new Matrix<T>(cells);
    }
    
    // internal helper for rotate
    private List<Node<T>> rotate(List<Node<T>> row, int column) {
        List<Node<T>> cells= new LinkedList<>();
        int r= 0;
        // go backwards through the columns
        for (int c= row.size() - 1; c >= 0; c--) {
            Node<T> node= row.get(c);
            cells.add(new Node<T>(r++, column, node.value));
        }
        return cells;
    }
    
    public Matrix<T> inverse() {
        return this.apply(v -> get(v.col, v.row));
    }
    
    public Vector<T> getRow(int r) {
        return new Vector<>(matrix.get(r).stream().map(n -> n.value).collect(Collectors.toList()));
    }

    public Vector<T> getColumn(int c) {
        Vector<T> column= new Vector<>(columns());
        for (List<Node<T>> row : matrix) {
            column.add(row.get(c).value);
        }
        return column;
    }

    public T get(int r, int c) {
        return matrix.get(r).get(c).value;
    }

    public T set(int r, int c, T value) {
        T old= matrix.get(r).get(c).value;
        matrix.get(r).set(c, new Node<T>(r, c, value));
        return old;
    }

    public int rows() { return matrix.size(); }
    public int columns() { return matrix.get(0).size(); }
}
