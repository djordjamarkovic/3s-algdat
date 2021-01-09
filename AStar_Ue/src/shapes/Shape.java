package shapes;

import java.awt.Color;
import java.awt.Graphics;

import astar.Definitions;

import modelcontroller.DrawModel;

public abstract class Shape {

	private static int shapeCounter = 0;
	
	
	protected Color currentColor;
	
	protected int shapeNumber;


	private DrawModel theModel;
	
	public Shape(DrawModel m) {
		shapeNumber = shapeCounter ;
		shapeCounter ++;
		theModel = m;
	}

	public Shape() {
		shapeNumber = shapeCounter ;
		shapeCounter ++;
	}

	public abstract void draw(Graphics g);
	
	public abstract String getName(); 
	
	public abstract String toString();
	
	public void setColor(Color c) {
		currentColor = c;
		if (theModel != null) theModel.notifyListeners();
	}
	
	public void setColorQuiet(Color c) {
		currentColor = c;
	}

	public void notifyModel() {
		if (theModel != null) theModel.notifyListeners();		
	}
	
	public void setModel(DrawModel m) {
		theModel = m;
	}
	
	public void blink() {
		Color s = currentColor;
		this.setColor(Color.YELLOW);
		try {	Thread.sleep(Definitions.BlinkDelay); } catch (InterruptedException e) {}
		this.setColor(s);
		try {	Thread.sleep(Definitions.BlinkDelay); } catch (InterruptedException e) {}
	}	
	
	public int hashCode() {
		return 0;
	};
}
