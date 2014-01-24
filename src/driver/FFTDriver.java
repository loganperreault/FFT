package driver;

import polynomials.FFT;

public class FFTDriver {
	
	
	public static void main(String [] args) {
		double[] vector = new double[]{ 3, 5, 7, 10 };
		
		FFT.transform(vector);
	}
	
	
}