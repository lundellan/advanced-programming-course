package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point bottomLeft;
	private Point bottomRight;
	private Point top;
	
	public Mountain(Point bottomLeft, Point bottomRight, Point top)	{
		super();
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
		this.top = top;
	}
	
	public String getTitle()	{
		return "Bergsfraktal";
	}
	
	public void draw(TurtleGraphics turtle)	{
		double length = lineLength(bottomLeft, bottomRight);
		double angle = lineAngle(bottomLeft, bottomRight);
		
		turtle.moveTo(turtle.getWidth() / 2.0 - length / 2.0,
				turtle.getHeight() / 2.0 + Math.sqrt(3.0) * length / 4.0);
		
		fractalLine(turtle, order, length, angle);
		
		double length2 = lineLength(bottomRight, top);
		double angle2 = lineAngle(bottomRight, top);
		
		fractalLine(turtle, order, length2, angle2);
		
		double length3 = lineLength(top, bottomLeft);
		double angle3 = lineAngle(top, bottomLeft);
		
		fractalLine(turtle, order, length3, angle3);
	}
	
	public void fractalLine(TurtleGraphics turtle, int order, double length, double angle)	{
		if (order == 0)	{
			turtle.penDown();
			turtle.setDirection(angle);
			turtle.forward(length);
		} else	{
			fractalLine(turtle, order, length, angle);
			fractalLine(turtle, order-1, length/2, angle+60.0);
		}
	}
	
	public double lineLength(Point startingPoint, Point endingPoint)	{
		double sideX = Math.abs(startingPoint.getX() - endingPoint.getX());
		double sideY = Math.abs(startingPoint.getY() - endingPoint.getY());
		return Math.sqrt(Math.pow(sideX, 2) + Math.pow(sideY, 2));
	}
	
	public double lineAngle(Point startingPoint, Point endingPoint)	{
		double sideX = Math.abs(startingPoint.getX() - endingPoint.getX());
		double sideY = Math.abs(startingPoint.getY() - endingPoint.getY());
		return Math.toDegrees(Math.atan(sideY / sideX));
	}
	
}
