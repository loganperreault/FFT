package polynomials;

import org.apache.commons.math3.complex.Complex;

public class FFT {
	
	public static double[] transform(double[] vector) {
		double[] a = vector;
		int n = a.length;
		if (n == 1)
			return a;
		
		Complex e = new Complex(Math.E);
		Complex principal = e.pow(Complex.I.multiply(2*Math.PI/n));
		Complex omega = new Complex(1);
		
		int halfUp = (int)Math.ceil((double)n/2);
		int halfDown = n/2;
		
		double[] a0 = new double[halfUp];
		double[] a1 = new double[halfDown];
		for (int i = 0; i < a0.length; i++)
			a0[i] = a[i*2];
		for (int i = 0; i < a1.length; i++)
			a1[i] = a[i*2 + 1];
		
		Tools.printVector(a);
		
		// recursive calls
		double[] y0 = transform(a0);
		double[] y1 = transform(a1);
		
		double[] y = new double[n];
		for (int k = 0; k < halfDown; k++) {
			y[k] = y0[k] + omega.multiply(y1[k]).getReal();	// it's probably not OK to get the real value here
			y[k + halfDown] = y0[k] - omega.multiply(y1[k]).getReal();
			omega = omega.multiply(principal);
		}
		
		System.out.print("Y = ");
		Tools.printVector(y);
		
		return y;
	}
	
	public static int[] transform(int[] vector) {
		double[] a = new double[vector.length];
		for (int i = 0; i < vector.length; i++)
			a[i] = vector[i];
		a = transform(a);
		for (int i = 0; i < vector.length; i++)
			vector[i] = (int) a[i];
		return vector;
	}
	
}
