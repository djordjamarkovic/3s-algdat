package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class ShapePoint extends Shape {

	protected Point position;
	private ShapeLine lineToPredecessor;
	private double currentHeuristics;
	
	private final static int size = 10;
	
	public ShapePoint(Point pos, Color c) {
		position = pos;
		currentColor = c;
		lineToPredecessor = null;
		currentHeuristics = 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(currentColor);
		g.fillRect(position.x - size/2,position.y - size/2, size, size);
	}

	@Override
	public String getName() {
		return "line";
	}
	
	public String toString() {
		return shapeNumber + ": " + getName() + " pos: " + position ;
	}
	
	public double distance(Point p) {
			double xx = new Double(position.x - p.x);  
			double yy = new Double(position.y - p.y);
			return Math.sqrt(xx * xx + yy * yy); 
	}

	public double distance(ShapePoint p) {
		double xx = new Double(position.x - p.position.x);  
		double yy = new Double(position.y - p.position.y);
		return Math.sqrt(xx * xx + yy * yy); 
	}

	public Point getPoint() {
		return position;
	}
	
	public void setRoute(ShapeLine line) {
		lineToPredecessor = line;
	}

	public ShapeLine getRoute() {
		return lineToPredecessor;
	}

	public void setHeuristics(double d) {
		currentHeuristics = d;
	}

	public double getHeuristics() {
		return currentHeuristics;
	}
	
}

