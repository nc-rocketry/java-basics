package edu.java.challenge.matricies.rotation;

import edu.java.challenge.matricies.Matrix;

public class Runner {

	public static void main(String[] args) {
		
		
		
		Matrix<String> m1= new Matrix<>(
			new String[][] {
			    { " 1", " ^", " 2" }, 
			    { "<w", " c", "e>" }, 
			    { " 3", " V", " 4" }, 
			});
		
		Matrix<String> m2= new Matrix<>(
			new String[][] {
			    { " 1 ", "   ", "   ", " - ", " 0 " }, 
			    { "   ", " 1 ", "   ", " - ", " 0 " }, 
			    { "   ", "   ", " 0 ", " - ", " 0 " }, 
			    { "   ", "   ", "   ", " - ", " 0 " }, 
			    { "   ", "   ", "   ", " - ", " 0 " }, 
			});
		
		System.out.println(m1.toString());
		
		Matrix<String> m3= m2.copy();
		Matrix<String> m4= m1.apply(v -> m1.get(v.col, v.row));
		
		System.out.println("m3:\n"+ m3);
		
		for (int i= 0; i < 5; i++) {
			System.out.println("m3.rotate("+ i +"):");
		    System.out.println(m3= m3.rotate());
		}
		
		System.out.println("m4:");
		System.out.println(m4.toString());
		
		
		
	}

}
