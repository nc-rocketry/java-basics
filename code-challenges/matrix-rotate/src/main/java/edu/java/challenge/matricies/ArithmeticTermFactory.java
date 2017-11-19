package edu.java.challenge.matricies;

public class ArithmeticTermFactory<T> {
	
	
	public static ArithmeticTerm<Integer> asInt(final Integer value) {
		return new ArithmeticTerm<Integer>() {
			@Override
			public Integer value() { return value; }
			public String toString() { return ""+ value; }
			
			@Override
			public ArithmeticTerm<Integer> sum(ArithmeticTerm<Integer> x) {
				return asInt(value + x.value());
			}
			
			@Override
			public ArithmeticTerm<Integer> product(ArithmeticTerm<Integer> x) {
				return asInt(value * x.value());
			}
			
		};
	}

	public static ArithmeticTerm<Long> asLong(final Long value) {
		return new ArithmeticTerm<Long>() {
			@Override
			public Long value() { return value; }
			public String toString() { return ""+ value; }
			
			@Override
			public ArithmeticTerm<Long> sum(ArithmeticTerm<Long> x) {
				return asLong(value + x.value());
			}
			
			@Override
			public ArithmeticTerm<Long> product(ArithmeticTerm<Long> x) {
				return asLong(value * x.value());
			}

		};
	}

	public static ArithmeticTerm<Double> asDouble(final Double value) {
		return new ArithmeticTerm<Double>() {
			@Override
			public Double value() { return value; }
			public String toString() { return ""+ value; }
			
			@Override
			public ArithmeticTerm<Double> sum(ArithmeticTerm<Double> x) {
				return asDouble(value + x.value());
			}
			
			@Override
			public ArithmeticTerm<Double> product(ArithmeticTerm<Double> x) {
				return asDouble(value * x.value());
			}

		};
	}

	public ArithmeticTerm<T> create(T value) { 
		if (value instanceof Integer) {
			return (ArithmeticTerm<T>)asInt((Integer)value);
		}
		return (ArithmeticTerm<T>)asInt((Integer)value);
	}

	public ArithmeticTerm<Integer> create(Integer value) { return asInt(value); }
	public ArithmeticTerm<Long> create(Long value) { return asLong(value); }
	public ArithmeticTerm<Double> create(Double value) { return asDouble(value); }
}
