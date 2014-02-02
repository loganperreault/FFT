package driver;

import polynomials.FFT;
import polynomials.Multiplication;
import polynomials.Tools;

public class FFTDriver {
	
	
	public static void main(String [] args) {
		
		double[] vector = new double[]{ 3, 5, 7, 10 };
		//FFT.transform(vector);
		
		//double[] a = new double[]{ 1, 2 };
		double[] a = new double[] {
				-0.03480425839330703,
			    0.07910192950176387,
			    0.7233322451735928,
			    0.1659819820667019
		};
		double[] b = new double[]{ 3, 2 };
		double[] c;
		
		c = Multiplication.standardMultiply(a, b);
//		Tools.printVector(c);
//		System.out.println();
		
		c = Multiplication.fftMultiply(a, b);
		Tools.printVector(c);
		
	}
	
	
}