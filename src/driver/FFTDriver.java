package driver;

import polynomials.FFT;

public class FFTDriver {
	
	
	public static void main(String [] args) {
		double[] vector = new double[]{ 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
		
		FFT.transform(vector);
	}
	
	
}