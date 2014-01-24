package polynomials;

import org.apache.commons.math3.complex.Complex;

public class FFT {
	
	private static int padding = 4;
	
	private static double[] fft(double[] a, int level) {
		int n = a.length;
		if (n == 1) {
			Tools.pad(level*padding);
			Tools.printVector(a);
			return a;
		}
		
		Complex e = new Complex(Math.E);
		Complex principal = e.pow(Complex.I.multiply(2*Math.PI/n));
		principal = Tools.round(principal, 10);
		Complex omega = new Complex(1);
		
		int halfUp = (int)Math.ceil((double)n/2);
		int halfDown = n/2;
		
		double[] a0 = new double[halfUp];
		double[] a1 = new double[halfDown];
		for (int i = 0; i < a0.length; i++)
			a0[i] = a[i*2];
		for (int i = 0; i < a1.length; i++)
			a1[i] = a[i*2 + 1];
		
		Tools.pad(level*padding);
		Tools.printVector(a);
		
		// recursive calls
		double[] y0 = fft(a0, level + 1);
		double[] y1 = fft(a1, level + 1);
		
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
	
	public static double[] transform(double[] vector) {
		
		// find the smallest power of two that will contain this vector
		int exp = 1;
		while (Math.pow(2, exp) < vector.length)
			exp++;
		// pad the vector with zeros
		double[] a = new double[(int)Math.pow(2, exp)];
		for (int i = 0; i < vector.length; i++) {
			a[a.length - vector.length + i] = vector[i];
		}
		
		return fft(a, 0);
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
