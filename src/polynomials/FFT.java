package polynomials;

import org.apache.commons.math3.complex.Complex;

public class FFT {
	
	private static int padding = 4;
	private static int echo = 3;
	
	private static Complex[] fft(Complex[] a, int level) {
		int n = a.length;
		if (n == 1) {
			if (echo >= 3) {
				Tools.pad(level*padding);
				Tools.printVector(a);
			}
			return a;
		}
		
		Complex e = new Complex(Math.E);
		Complex principal = e.pow(Complex.I.multiply(2*Math.PI/n));
		principal = Tools.round(principal, 10);
		Complex omega = new Complex(1);
		
		int halfUp = (int)Math.ceil((double)n/2);
		int halfDown = n/2;
		
		Complex[] a0 = new Complex[halfUp];
		Complex[] a1 = new Complex[halfDown];
		for (int i = 0; i < a0.length; i++)
			a0[i] = a[i*2];
		for (int i = 0; i < a1.length; i++)
			a1[i] = a[i*2 + 1];
		
		if (echo >= 3) {
			Tools.pad(level*padding);
			Tools.printVector(a);
		}
		
		// recursive calls
		Complex[] y0 = fft(a0, level + 1);
		Complex[] y1 = fft(a1, level + 1);
		
		Complex[] y = new Complex[n];
		for (int k = 0; k < halfDown; k++) {
			y[k] = y0[k].add(omega.multiply(y1[k]));	// it's probably not OK to get the real value here
			y[k + halfDown] = y0[k].subtract(omega.multiply(y1[k]));
			omega = omega.multiply(principal);
		}
		
		if (echo >= 2) {
			System.out.print("Y = ");
			Tools.printVector(y);
		}
		
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
		
		return Tools.toDouble(fft(Tools.toComplex(a), 0));
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
