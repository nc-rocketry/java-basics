package edu.java.challenge.matricies.rotation;

import edu.java.challenge.matricies.NumericMatrix;

public class MatrixMaths {
	
	public static void main(String[] args) {
		
		NumericMatrix<Integer> m1= new NumericMatrix<>(
				new Integer[][] {
					{ 1, 2, 3 },
					{ 1, 2, 3 },
					{ 1, 2, 3 }
				});
		
		
		System.out.println(m1);
		System.out.println(m1.product(m1));
		System.out.println(m1.sum(m1));
	}

}
