package mountain;

public class Side {

	private Point p1;
	private Point p2; 
	
	public Side(Point a, Point b) {
		this.p1 = a;
		this.p2 = b; 
	}
	
	@Override
	public boolean equals(Object a) {
		if (a instanceof Side) {
			Side b = (Side) a;
			return (p1.equals(b.p1) && (p2.equals(b.p2)) || (p1.equals(b.p2)) && (p2.equals(b.p1)));
		} else	{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}

}
