package polynomials;

import org.apache.commons.math3.complex.Complex;

public class FFT {
	
	private static int padding = 4;
	private static int echo = 1;
	
	private static Complex[] fft(Complex[] a, boolean inverse, int level) {
		int n = a.length;
		if (n == 1) {
			if (echo >= 3) {
				Tools.pad(level*padding);
				Tools.printVector(a);
			}
			return a;
		}
		
		Complex e = new Complex(Math.E);
		Complex principal = e.pow(Complex.I.multiply(2*Math.PI/n)).conjugate();
		principal = Tools.round(principal, 10);
		Complex omega = new Complex(1);
		if (inverse)
			principal = principal.pow(-1);
		
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
		Complex[] y0 = fft(a0, inverse, level + 1);
		Complex[] y1 = fft(a1, inverse, level + 1);
		
		// combine y0 and y1 back into a single vector
		Complex[] y = new Complex[n];
		//System.out.println(halfDown+" / "+n);
		for (int k = 0; k < halfDown; k++) {
			omega = Tools.round(omega, 5);
			if (echo >= 5) {
				double kth = -2 * k * Math.PI / n;
	            Complex wk = Tools.round(new Complex(Math.cos(kth), Math.sin(kth)),5);
	            System.out.println("CHECK: "+wk+" vs "+omega);
			}
			
			y[k] 			= y0[k].add(omega.multiply(y1[k]));
			y[k + halfDown] = y0[k].subtract(omega.multiply(y1[k]));
			if (inverse) {
				if (n == 4) {
//					System.out.println(y[k]+" --> "+y[k].divide(halfDown));
//					System.out.println(y[k + halfDown]+" ==> "+y[k + halfDown].divide(halfDown));
				}
				y[k] = y[k].divide(n);
				y[k + halfDown] = y[k + halfDown].divide(n);
			}
			
			omega = omega.multiply(principal);
		}
		
		if (echo >= 2) {
			System.out.print("Y = ");
			Tools.printVector(y);
		}
		
		// y is assumed to be a column vector
		return y;
	}
	
	private static Complex[] invfft(Complex[] x) {
		int N = x.length;
        Complex[] y = new Complex[N];

        // take conjugate
        for (int i = 0; i < N; i++) {
            y[i] = x[i].conjugate();
        }

        // compute forward FFT
        y = fft(y, false, 0);

        // take conjugate again
        for (int i = 0; i < N; i++) {
            y[i] = y[i].conjugate();
        }

        // divide by N
        for (int i = 0; i < N; i++) {
            y[i] = y[i].multiply(1.0 / N);
        }

        return y;
	}
	
	private static Complex[] transform(Complex[] vector, boolean inverse) {
		
		/*
		// find the smallest power of two that will contain the resulting vector
		int exp = 1;
		//while (Math.pow(2, exp) < vector.length * 2)
		while (Math.pow(2, exp) < vector.length)
			exp++;
		// pad the vector with zeros
		Complex[] a = new Complex[(int)Math.pow(2, exp)];
		for (int i = 0; i < a.length; i++) {
			if (i < a.length - vector.length)
				a[i] = new Complex(0);
			else
				a[i] = vector[a.length - i - 1];
		}
		Tools.printVector(a);
		
		if (echo == 2)
			Tools.printVector(a);
		*/
		
		return fft(vector, inverse, 0);
	}
	
	public static Complex[] transform(double[] vector) {
		return transform(Tools.toComplex(vector), false);
	}
	
	public static double[] inverse(Complex[] vector) {
		double[] inv = Tools.toDouble(transform(vector, true));
		for (int i = 0; i < vector.length; i++)
			inv[i] *= 2;
		return inv;
	}
	
	public static Complex[] transform(int[] vector) {
		double[] a = new double[vector.length];
		for (int i = 0; i < vector.length; i++)
			a[i] = vector[i];
		return transform(a);
	}
	
}
