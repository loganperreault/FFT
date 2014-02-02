package polynomials;

import org.apache.commons.math3.complex.Complex;

public class Tools {

	public static void printVector(Object[] vector) {
		System.out.print("[" + vector[0]);
		for (int i = 1; i < vector.length; i++)
			System.out.print(","+vector[i]);
		System.out.println("]");
	}
	
	public static void printVector(double[] vector) {
		System.out.print("[" + vector[0]);
		for (int i = 1; i < vector.length; i++)
			System.out.print(","+vector[i]);
		System.out.println("]");
	}
	
	public static void printVector(int[] vector) {
		System.out.print("[" + vector[0]);
		for (int i = 1; i < vector.length; i++)
			System.out.print(","+vector[i]);
		System.out.println("]");
	}
	
	public static Complex round(Complex value, int decimal) {
		double real = value.getReal();
		double imaginary = value.getImaginary();
		return new Complex(round(real, decimal), round(imaginary, decimal));
	}
	
	public static double round(double value, int decimal) {
		int factor = (int) Math.pow(10, decimal);
		return (double)Math.round(value * factor) / factor;
	}
	
	public static void pad(int padding) {
		for (int i = 0; i < padding; i++)
			System.out.print(" ");
	}
	
	public static Complex[] toComplex(double[] vector) {
		Complex[] complex = new Complex[vector.length];
		for (int i = 0; i < vector.length; i++)
			complex[i] = new Complex(vector[i]);
		return complex;
	}
	
	public static Complex[] toComplex(int[] vector) {
		Complex[] complex = new Complex[vector.length];
		for (int i = 0; i < vector.length; i++)
			complex[i] = new Complex(vector[i]);
		return complex;
	}
	
	public static double[] toDouble(Complex[] vector) {
		double[] primitive = new double[vector.length];
		for (int i = 0; i < vector.length; i++) {
			primitive[i] = vector[i].getReal();
			if (vector[i].getImaginary() != 0.0)
				System.out.println("WARNING: complex component "+vector[i].getImaginary()+" is being discarded!");
		}
		return primitive;
	}
	
	public static int[] toInteger(Complex[] vector) {
		int[] primitive = new int[vector.length];
		for (int i = 0; i < vector.length; i++)
			primitive[i] = (int) vector[i].getReal();
		return primitive;
	}
	
	public static double[] toDouble(int[] vector) {
		double[] primitive = new double[vector.length];
		for (int i = 0; i < vector.length; i++)
			primitive[i] = vector[i];
		return primitive;
	}
	
	public static int[] toInteger(double[] vector) {
		int[] primitive = new int[vector.length];
		for (int i = 0; i < vector.length; i++)
			primitive[i] = (int) vector[i];
		return primitive;
	}
	
}
