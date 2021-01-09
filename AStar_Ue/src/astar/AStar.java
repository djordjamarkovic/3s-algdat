package astar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelcontroller.DrawModel;
import modelcontroller.commands.DrawModelResetCommand;
import shapes.Shape;
import shapes.ShapeLine;
import shapes.ShapePoint;


public class AStar {

	public static void calculate(List<ShapeLine> lineList, Set<ShapePoint> pointSet, ShapePoint startPoint, ShapePoint endPoint) {
		
		Set<ShapePoint> closedList = new HashSet<ShapePoint>();
		Set<ShapePoint> openList = new HashSet<ShapePoint>();
		
		////////////////////////////////////////////////////////
		// Delete this and replace by algorithm
		
		//ArrayList<Shape> myShapeList = new ArrayList<Shape>();
		//myShapeList.addAll(lineList);
		//myShapeList.addAll(pointSet);
		
		//myShapeList.remove(startPoint);
		//myShapeList.remove(endPoint);
				
		//Collections.shuffle(myShapeList);

		////////////////////////////////////////////////////////
		// AStern Algorithmus

		// Zusicherung: Jeder Punkt in der Openlist und in der ClosedList hat Heruistik und Rueckweg!!

		// initialisierung
		startPoint.setRoute(null);
		startPoint.setHeuristics(0+startPoint.distance(endPoint));
		openList.add(startPoint);

		while (!closedList.contains(endPoint) && !openList.isEmpty()) { // Scheifenbedingung closedlist has endpoint && !openlist.isempty
			ShapePoint expandPoint = findMinimalPoint(openList); // finde den Punk in der OpenList mit minimaler Heuristik
			// fuer alle PUnkt, die vom expanPoint in einem Scritt erreichbar sind:
			// workPoint
			for(ShapeLine workLine : lineList) { // for(ShapeLine workLine : lineList)
				if(workLine.hasPoint(expandPoint)) {
					ShapePoint workPoint = workLine.traverse(expandPoint);
					{
						// main block; expandPoint, workPoint, workLine;
						// 1) expandPoint in die closedList
						openList.remove(expandPoint);
						closedList.add(expandPoint);
						savesetColor(expandPoint, startPoint, endPoint, Color.BLACK);

						// workline zum blinken bringen
						workLine.blink();

						//2.) fuer alle workPoints(s)
						if(closedList.contains(workPoint)) { //	2a) workPoint in der closedList
															 //		-> tu gar nix
							// intentionally do nothing
						} else if (openList.contains(workPoint)) { //	2b) workPoint in openList
							// it's complicated
							double oldPathLength = pathLength(workPoint);
							double newPathLength = pathLength(expandPoint) + workLine.length();

							// if (newPath.length < oldPath.length )
							// setze die Route vom workPoint neu auf rot + setze neue Heuristik
							if(newPathLength < oldPathLength) {
								// gelb setzen beim anfang
								workLine.setColor(Color.YELLOW);
								// setze neue Route und Heruistik fuer workPoint
								workPoint.setRoute(workLine);
								// workPoint.setHeuristics(pathLength(workPoint)+workPoint.getHeuristics()); // meins, funkt
								workPoint.setHeuristics(pathLength(workPoint)+workPoint.distance(endPoint));
								// farbe zuruecksetzten auf schwarz
								workLine.setColor(Color.BLACK);
							}

						} else { //	2c) (else) workPoint in unknownList
							//		workPoint.setHeuristics(...)
							//		workPoint.setRoute(...)
							//		tu workPoint in die openList

							workPoint.setHeuristics(workPoint.distance(endPoint)+pathLength(expandPoint)+workLine.length());
							workPoint.setRoute(workLine);
							openList.add(workPoint);

							// nur damit start & end point nicht gefaerbt werden.
							savesetColor(workPoint, startPoint, endPoint, Color.CYAN);
						}
					}
				}
			}

			if(closedList.contains(endPoint)) {
				colorPath(endPoint, Color.GREEN);
			}
		}


		/*
		for (Shape x : myShapeList) {
			x.blink();
			x.setColor(Color.MAGENTA);
		}
		*/

		////////////////////////////////////////////////////////
		
	}
		
	
	private static void savesetColor(ShapePoint point, ShapePoint startPoint, ShapePoint endPoint, Color color) {
		if ( ! point.equals(startPoint) && ! point.equals(endPoint) ) point.setColor(color); 
		
	}


	private static void colorPath(ShapePoint end, Color c) {
		if (end.getRoute() != null) {
			end.getRoute().setColor(c);
			colorPath(end.getRoute().traverse(end), c);
		}
	}


	private static double pathLength(ShapePoint end) {
		if (end.getRoute() == null) return 0;
		else return end.getRoute().length() + pathLength(end.getRoute().traverse(end));
	}


	private static ShapePoint findMinimalPoint(Set<ShapePoint> openList) {
		ShapePoint minimalPoint = null;
		for (ShapePoint x : openList) {
			if (x.getHeuristics() != 0.0) {
				if  (minimalPoint == null) minimalPoint = x;
				else if (minimalPoint.getHeuristics() > x.getHeuristics()) minimalPoint = x;
			}
		}
		return minimalPoint;
	}

}
