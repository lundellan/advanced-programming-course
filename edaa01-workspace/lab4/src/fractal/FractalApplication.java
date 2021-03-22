package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		
		Point bottomLeft = new Point(0, 0);
		Point bottomRight = new Point(300, 0);
		Point top = new Point(150, 300);
		
		fractals[0] = new Mountain(bottomLeft, bottomRight, top);
		fractals[1] = new Koch(300);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
