package modelcontroller.commands;

import java.awt.Color;
import java.awt.Point;

public class DrawModelAddCommand extends DrawModelCommand {

	private Point theP1;
	private Point theP2;
	private Color theColor;
	
	public DrawModelAddCommand(Point p1, Point p2, Color c) {
		super();
		theP1 = p1;
		theP2 = p2;
		theColor = c;
	}
	
	public Point getP1(){
		return theP1;
	}

	public Point getP2(){
		return theP2;
	}

	public Color getColor(){
		return theColor;
	}


}
