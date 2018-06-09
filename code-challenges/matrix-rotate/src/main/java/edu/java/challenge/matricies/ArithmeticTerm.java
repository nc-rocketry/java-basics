package edu.java.challenge.matricies;

public interface ArithmeticTerm<T> {

	public ArithmeticTerm<T> product(ArithmeticTerm<T> x);
	public ArithmeticTerm<T> sum(ArithmeticTerm<T> x);
	public T value();

}
