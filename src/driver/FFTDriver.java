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
		
		a = new double[]{ 1, 2, 8, 2 };
		b = new double[]{ 4, 2, 8, 2 };
		
//		a = new double[]{ 1, 2 };
//		b = new double[]{ 4, 2 };
		
//		Tools.printVector(a);
//		Tools.printVector(FFT.inverse(FFT.transform(a)));
//		Tools.printVector(b);
//		Tools.printVector(FFT.inverse(FFT.transform(b)));
//		Tools.printVector(FFT.online(Tools.toComplex(a)));
		// the online version gets the same results, it looks as though this a problem in the Multiplication class
//		Tools.printVector(FFT.transform(a));
//		Tools.printVector(FFT.transform(b));
		System.out.println();
		
		test(a, b);
		
//		double[] x = new double[] {
//			-0.03480425839330703,
//		    0.07910192950176387,
//		    0.7233322451735928,
//		    0.1659819820667019	
//		};
//		test(x, x);
//		
//		int length = 2;
//		a = new double[length];
//		b = new double[length];
//		for (int i = 0; i < a.length; i++) {
//			a[i] = random.nextInt(100);
//			b[i] = random.nextInt(100);
//		}
//		test(a, b);
		
	}
	
	public static long test(double[] a, double[] b) {
	
		if (echo) {
//			System.out.println("ORIGINAL VECTORS:");
//			Tools.printVector(a);
//			Tools.printVector(b);
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
			if (!Tools.compare(standard[i],fft[i]))
				throw new IllegalArgumentException("Invalid Multiplication");
		
		long improvement = standardTime - fftTime;
		
		System.out.println("Improvement from FFT: "+improvement);
		
		return improvement;
	}
	
	
}