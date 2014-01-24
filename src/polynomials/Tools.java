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
	
}
