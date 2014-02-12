package driver;

import java.util.Random;

import polynomials.FFT;
import polynomials.Multiplication;
import polynomials.Tools;

public class FFTDriver {
	
	private static Random random = new Random(11235);
	private static boolean echo = true;
	
	public static void main(String [] args) {
		
		int[] x,y;
		double[] a,b;
		
//		x = new int[]{ 1, 2, 8, 2, 5, 9 };
//		y = new int[]{ 4, 2, 8, 2, 5, 2 };
//		test(x, y);
		
//		double[] x = new double[] {
//			-0.03480425839330703,
//		    0.07910192950176387,
//		    0.7233322451735928,
//		    0.1659819820667019	
//		};
//		test(x, x);
		
		int length = 100000;
		int range = 10;
		
		x = new int[length];
		y = new int[length];
		for (int i = 0; i < length; i++) {
			x[i] = random.nextInt(range);
			y[i] = random.nextInt(range);
		}
		test(x, y);
		
//		a = new double[length];
//		b = new double[length];
//		for (int i = 0; i < a.length; i++) {
//			a[i] = random.nextInt(range);
//			b[i] = random.nextInt(range);
//		}
//		test(a, b);
		
	}
	
	public static long test(int[] a, int[] b) {
		
		long startTime = System.currentTimeMillis();
		int[] standard = Multiplication.standardMultiply(a, b);
		long standardTime = System.currentTimeMillis() - startTime;
		
		startTime = System.currentTimeMillis();
		int[] fft      = Multiplication.fftMultiply(a, b);
		long fftTime = System.currentTimeMillis() - startTime;
		
		if (echo) {
			System.out.println("RESULTS:");
			Tools.printVector(standard);
			Tools.printVector(fft);
		}
		
		for (int i = 0; i < standard.length; i++)
			if (!Tools.compare(standard[i],fft[i]))
				throw new IllegalArgumentException("Invalid Multiplication");
		
		long improvement = standardTime - fftTime;
		
		System.out.println("Improvement from FFT: "+improvement);
		
		return improvement;
	}
	
	public static long test(double[] a, double[] b) {
		
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
			if (!Tools.compare(standard[i],fft[i]))
				throw new IllegalArgumentException("Invalid Multiplication");
		
		long improvement = standardTime - fftTime;
		
		System.out.println("Improvement from FFT: "+improvement);
		
		return improvement;
	}
	
	
}