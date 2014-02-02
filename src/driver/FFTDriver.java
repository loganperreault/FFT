package driver;

import java.util.Random;

import polynomials.FFT;
import polynomials.Multiplication;
import polynomials.Tools;

public class FFTDriver {
	
	private static Random random = new Random(11235);
	private static boolean echo = true;
	
	public static void main(String [] args) {
		
		double[] a,b;
		
//		a = new double[]{ 1, 2 };
//		b = new double[]{ 3, 2 };
//		test(a, b);
		
		int length = 4;
		a = new double[length];
		b = new double[length];
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(100);
			b[i] = random.nextInt(100);
		}
		test(a, b);
		
	}
	
	public static long test(double[] a, double[] b) {
	
		if (echo) {
			System.out.println("ORIGINAL VECTORS:");
			Tools.printVector(a);
			Tools.printVector(b);
		}
		
		long startTime = System.currentTimeMillis();
		double[] standard = Multiplication.standardMultiply(a, b);
		long standardTime = System.currentTimeMillis() - startTime;
		
		startTime = System.currentTimeMillis();
		double[] fft      = Multiplication.fftMultiply(a, b);
		long fftTime = System.currentTimeMillis() - startTime;
		
		if (echo) {
			System.out.println("RESULTS:");
			Tools.printVector(standard);
			Tools.printVector(fft);
		}
		
		for (int i = 0; i < standard.length; i++)
			if (standard[i] != fft[i])
				throw new IllegalArgumentException("Invalid Multiplication");
		
		long improvement = standardTime - fftTime;
		
		System.out.println("Improvement from FFT: "+improvement);
		
		return improvement;
	}
	
	
}