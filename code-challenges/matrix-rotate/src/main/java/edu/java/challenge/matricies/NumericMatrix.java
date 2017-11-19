package edu.java.challenge.matricies;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/** specific implementation of a matrix that holds integer values **/
public class NumericMatrix<T> extends Matrix<ArithmeticTerm<T>> implements ArithmeticMatrix<ArithmeticTerm<T>> {

	private final ArithmeticTermFactory<T> FACTORY= new ArithmeticTermFactory<>();

	public NumericMatrix(T[][] data) {
		super(data.length, data[0].length);
		for (int r= 0; r < data.length; r++) {
			for (int c= 0; c < data[r].length; c++) {
			    set(r, c, FACTORY.create(data[r][c]));
			}
		}
	}
	
	public NumericMatrix(List<Node<ArithmeticTerm<T>>> matrix) {
		super(matrix);
	}

	@Override
	public Matrix<ArithmeticTerm<T>> product(Matrix<ArithmeticTerm<T>> m) {
		
		List<Node<ArithmeticTerm<T>>> matrix= new ArrayList<>();

		for (int r= 0; r < rows(); r++) { // for each row in this matrix
			
			Vector<ArithmeticTerm<T>> row= getRow(r);

			for (int c= 0; c < m.columns(); c++) { // for each column in that matrix
				Node<ArithmeticTerm<T>> node= new Node<>(r, c, FACTORY.create(row.get(r).product(m.get(r,c)).value()));
				matrix.add(node);
			}

		}
		
		return new NumericMatrix<T>(matrix);
	}
	
	@Override
	public Matrix<ArithmeticTerm<T>> sum(Matrix<ArithmeticTerm<T>> m) {
		List<Node<ArithmeticTerm<T>>> matrix= new ArrayList<>();

		for (int r= 0; r < rows(); r++) { // for each row in this matrix
			
			Vector<ArithmeticTerm<T>> row= getRow(r);

			for (int c= 0; c < m.columns(); c++) { // for each column in that matrix
				Node<ArithmeticTerm<T>> node= new Node<>(r, c, FACTORY.create(row.get(r).product(m.get(r,c)).value()));
				matrix.add(node);
			}

		}
		
		return new NumericMatrix<T>(matrix);
	}
	
}
