package driver;

import polynomials.FFT;
import polynomials.Multiplication;
import polynomials.Tools;

public class FFTDriver {
	
	
	public static void main(String [] args) {
		
		double[] vector = new double[]{ 3, 5, 7, 10 };
		//FFT.transform(vector);
		
		double[] a = new double[]{ 1, 2, 3 };
		double[] b = new double[]{ 3, 2, 2 };
		double[] c = Multiplication.standardMultiply(a, b);
		
		Tools.printVector(c);
		
	}
	
	
}