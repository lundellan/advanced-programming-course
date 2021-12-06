package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.*;

public class IrregularMountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private double dev;
	private Map<Side, Point> sides = new HashMap<Side, Point>();

	public IrregularMountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
	}

	public String getTitle() {
		return "Oregelbundet berg";
	}

	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle, order, a, b, c, dev);
	}

	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		if (order == 0) {
			turtle.penUp();
			turtle.moveTo(a.getX(), a.getY());
			turtle.penDown();
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			Point midAB = midPoint(a, b, dev);
			Point midBC = midPoint(b, c, dev);
			Point midAC = midPoint(a, c, dev);

			fractalTriangle(turtle, order - 1, a, midAB, midAC, dev / 2);
			fractalTriangle(turtle, order - 1, b, midAB, midBC, dev / 2);
			fractalTriangle(turtle, order - 1, c, midBC, midAC, dev / 2);
			fractalTriangle(turtle, order - 1, midAB, midBC, midAC, dev / 2);
		}
	}

	private Point midPoint(Point a, Point b, double dev) {
		double rand = RandomUtilities.randFunc(dev);
		Side newSide = new Side(a, b);

		if (sides.containsKey(newSide)) {
			return sides.remove(newSide);

		} else {
			int midX = ((a.getX() + b.getX()) / 2);
			int midY = (int) ((a.getY() + b.getY() + rand) / 2);
			Point newPoint = new Point(midX, midY);
			sides.put(newSide, newPoint);
			return newPoint;
		}
	}

}
