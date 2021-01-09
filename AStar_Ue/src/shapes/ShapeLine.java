package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ShapeLine extends Shape {

	protected ShapePoint upperleft;
	protected ShapePoint lowerright;
	
	public ShapeLine(Point ul, Point lr, Color c) {
		upperleft = new ShapePoint(ul,c);
		lowerright = new ShapePoint(lr,c);
		currentColor = c;
	}

	public ShapeLine(ShapePoint pp1, ShapePoint pp2, Color c) {
		upperleft = pp1;
		lowerright = pp2;
		currentColor = c;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(currentColor);
		g.drawLine(upperleft.getPoint().x,upperleft.getPoint().y,lowerright.getPoint().x,lowerright.getPoint().y);
	}

	@Override
	public String getName() {
		return "line";
	}
	
	public String toString() {
		return shapeNumber + ": " + getName() + " ul: " + upperleft.getPoint() +  " lr: " + upperleft.getPoint()  ;
	}
	
	public ShapePoint getPoint1() { return upperleft; }
	public ShapePoint getPoint2() { return lowerright; }
	
	public ShapePoint traverse(ShapePoint p) {
		if      (p == upperleft) return lowerright;
		else if (p == lowerright) return upperleft;
		return null;
	}

	public boolean hasPoint(ShapePoint p) {
		if      (p == upperleft) return true;
		else if (p == lowerright) return true;
		else return false;
	}
	
	public double length() {
		double xx = new Double(upperleft.getPoint().x - lowerright.getPoint().x);  
		double yy = new Double(upperleft.getPoint().y - lowerright.getPoint().y);
		return Math.sqrt(xx * xx + yy * yy); 
	}


}
