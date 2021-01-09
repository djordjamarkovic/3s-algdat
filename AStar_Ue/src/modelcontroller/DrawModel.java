package modelcontroller;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import astar.*;


import modelcontroller.commands.DrawModelAddCommand;
import modelcontroller.commands.DrawModelCalculateCommand;
import modelcontroller.commands.DrawModelClearCommand;
import modelcontroller.commands.DrawModelCommand;
import modelcontroller.commands.DrawModelDeleteCommand;
import modelcontroller.commands.DrawModelNullCommand;
import modelcontroller.commands.DrawModelResetCommand;
import modelcontroller.commands.DrawModelSelectCommand;
import modelcontroller.events.DrawModelChangeEvent;
import modelcontroller.events.DrawModelChangeGraphEvent;

import shapes.Shape;
import shapes.ShapeLine;
import shapes.ShapePoint;


public class DrawModel implements DrawModelCommandReceiver, DrawModelChangeAnnouncer, DrawViewDataSupplier {

	private static int modelCounter = 0;
	
	private int modelNumber;
	
	private List<ShapeLine> lineList;
    private Set<ShapePoint> pointSet;
	
    private ShapePoint startPoint;
    private ShapePoint endPoint;
	
	private List<DrawModelChangeListener> theListeners;
	
	public DrawModel(){
		modelNumber = modelCounter;
		modelCounter ++;
		lineList = new ArrayList<ShapeLine>();
		pointSet = new HashSet<ShapePoint>();
		startPoint = null;
		endPoint = null;
		
		theListeners = new ArrayList<DrawModelChangeListener>();
	}
	
	private void calculate(List<ShapeLine> lineList, Set<ShapePoint> pointSet, ShapePoint startPoint, ShapePoint endPoint) {
		AStar.calculate(lineList, pointSet, startPoint, endPoint);		
	}

	public void registerChangeListener(DrawModelChangeListener l) {
		theListeners.add(l);
	}
	
	private void notifyAll(DrawModelChangeEvent e) {
		for (DrawModelChangeListener l : theListeners) {
			l.drawModelChanged(e);
		}
	}

	public void receiveCommand(DrawModelCommand x) {
		if (x instanceof DrawModelAddCommand) {
			Point p1 = ((DrawModelAddCommand) x).getP1();
			Point p2 = ((DrawModelAddCommand) x).getP2();
			Color c = ((DrawModelAddCommand) x).getColor();
			addLine(p1,p2,c);
			resetModelColors();
			notifyAll(new DrawModelChangeEvent(this));
		}
		else if (x instanceof DrawModelDeleteCommand) {
			int i = ((DrawModelDeleteCommand) x).getIndex();
			System.out.println("DrawModelDeleteCommand(" + i + ")");
			if ( i >= 0 && i < lineList.size()) lineList.remove( i );
			deleteOrphanPoints();
			resetModelColors();
			notifyAll(new DrawModelChangeEvent(this));
		}
		else if (x instanceof DrawModelSelectCommand) {
			int i = ((DrawModelSelectCommand) x).getIndex();
			System.out.println("DrawModelSelectCommand(" + i + ")");
			if ( i >= 0 && i < lineList.size()) {  
				resetModelColors();
				lineList.get( i ).setColor(Color.CYAN);
			}
			notifyAll(new DrawModelChangeGraphEvent(this));
		}
		else if (x instanceof DrawModelResetCommand) {
			resetModelColors();
			notifyAll(new DrawModelChangeEvent(this));
		}
		else if (x instanceof DrawModelClearCommand) {
			lineList = new ArrayList<ShapeLine>();
			pointSet = new HashSet<ShapePoint>();
			startPoint = null;
			endPoint = null;
			notifyAll(new DrawModelChangeEvent(this));
		}
		else if (x instanceof DrawModelCalculateCommand) {
			if (startPoint != null && endPoint != null) {
				resetModelColors();
				calculate(lineList,pointSet,startPoint, endPoint);
			}
			notifyAll(new DrawModelChangeEvent(this));
		}
		else if (x instanceof DrawModelNullCommand) {
			// Do nothing 
			resetModelColors();
			notifyAll(new DrawModelChangeEvent(this));
		}
		else 
			System.out.println("Unknown Command");
	}

	private void deleteOrphanPoints() {
		Set<ShapePoint> orphans = new HashSet<ShapePoint>(pointSet);
		for (ShapeLine x: lineList) {
			orphans.remove(x.getPoint1());
			orphans.remove(x.getPoint2());
		}
		for (ShapePoint y: orphans) {
			pointSet.remove(y);
		}
		
		if (pointSet.size() == 0) {
			startPoint = null;
			endPoint = null;
		} else 	if (pointSet.size() == 1){
			List<ShapePoint> list = new ArrayList<ShapePoint>(pointSet);
			if (startPoint == null || ! pointSet.contains(startPoint)) {
				startPoint = list.get(0);
				startPoint.setColor(Color.GREEN);
			}			
			endPoint = null;
		} else
		{
			List<ShapePoint> list = new ArrayList<ShapePoint>(pointSet);
			if (startPoint == null || ! pointSet.contains(startPoint)) {
				startPoint = list.get(0);
				startPoint.setColor(Color.GREEN);
			}			
			Iterator<ShapePoint> x = list.iterator();
			while (endPoint == null || ! pointSet.contains(endPoint) ) {
				ShapePoint y = x.next();
				if (y != startPoint ) {
					endPoint = y; 
					endPoint.setColorQuiet(Color.RED);
				}
			}			
		}
			
		
		
	}

	private void resetModelColors() {
		if (startPoint != null) startPoint.setColorQuiet(Color.GREEN);
		if (endPoint != null) endPoint.setColorQuiet(Color.RED);		

		for (ShapeLine x: lineList) {
			x.setColorQuiet(Color.BLUE);
		}
		
		for (ShapePoint x: pointSet) {
			if ( ! x.equals(startPoint) && ! x.equals(endPoint) )	x.setColorQuiet(Color.BLUE);
		}
		
	}

	private void addLine(Point p1, Point p2, Color c) {
		ShapePoint pp1 = getNearOrNewPoint(p1,c);
		if (startPoint == null) { 
			startPoint = pp1;
		}

		pointSet.add(pp1);
		pp1.setModel(this);

		ShapePoint pp2 = getNearOrNewPoint(p2,c);
		
		if ( startPoint != null && pp2 != startPoint ) {
			if (endPoint != null ) 
				endPoint.setColorQuiet(c);
			endPoint = pp2;
		}
			
		pointSet.add(pp2);	
		pp2.setModel(this);
		
		if (startPoint != null) startPoint.setColorQuiet(Color.GREEN);
		if (endPoint != null) endPoint.setColorQuiet(Color.RED);

		if (pp1 != pp2) {
			ShapeLine x = new ShapeLine(pp1,pp2,c);
			lineList.add(x);
			x.setModel(this);
		}

	}
	
	private ShapePoint getNearOrNewPoint(Point p, Color c) {
		ShapePoint pp = getNearestPoint(p);
		if (pp != null && pp.distance(p) < 12 ) { 
			pp.setColorQuiet(c);
			return pp;  
		} else {
			return new ShapePoint(p, c);
		}
		
	}

	private ShapePoint getNearestPoint(Point p) {
		ShapePoint result = null;
		int mindist = Integer.MAX_VALUE;
		for (ShapePoint x: pointSet) {
			if (x.distance(p) < mindist) {
				mindist = (int) x.distance(p);
				result = x;
			}
		}
		return result;
	}

	public List<Shape> getShapes() {
		List<Shape> l =  new ArrayList<Shape>(lineList);
		l.addAll(pointSet);
		return l;
	}
	
	public int getModelNumber() {
		return modelNumber;
	}

	public void notifyListeners() {
		notifyAll(new DrawModelChangeEvent(this));
	}
	
}
