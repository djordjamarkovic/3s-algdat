package modelcontroller;

import frames.DrawView;
import frames.DrawViewGraph;
import frames.DrawViewList;
import gui.GUIPanelMain;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelcontroller.commands.DrawModelCalculateCommand;
import modelcontroller.commands.DrawModelAddCommand;
import modelcontroller.commands.DrawModelClearCommand;
import modelcontroller.commands.DrawModelDeleteCommand;
import modelcontroller.commands.DrawModelNullCommand;
import modelcontroller.commands.DrawModelResetCommand;
import modelcontroller.commands.DrawModelSelectCommand;
import modelcontroller.events.DrawModelChangeEvent;

import shapes.Shape;
import shapes.ShapeLine;


public class DrawController implements DrawModelChangeListener, DrawModelChangeAnnouncer, MouseListener, MouseMotionListener, ActionListener , ListSelectionListener {
	
	private DrawModelChangeListener theFrame;
	private Point startPoint;
	private GUIPanelMain theGUI;
	private DrawModelCommandReceiver theCommandReceiver;
	private DrawModelChangeAnnouncer theCommandAnnouncer;
	
	public DrawController(GUIPanelMain main, DrawModelCommandReceiver c, DrawModelChangeAnnouncer x) {
		theGUI = main;
		theCommandReceiver = c;
		theCommandAnnouncer = x;
	}
	
	public void registerChangeListener(DrawModelChangeListener x) {
		theFrame = x;
		
	}

	public void drawModelChanged(DrawModelChangeEvent e) {
		theFrame.drawModelChanged(e);
		
	}

	public void mouseClicked(MouseEvent arg0) {
		//System.out.println("mouseClicked Pressed");						
	}

	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("mouseEntered Pressed");						
	}

	public void mouseExited(MouseEvent arg0) {
		//System.out.println("mouseExited Pressed");				
	}

	public void mousePressed(MouseEvent arg0) {
		startPoint = arg0.getPoint();
		//System.out.println("mousePressed Pressed");		
	}

	public void mouseReleased(MouseEvent arg0) {
		//System.out.println("mouseReleased Pressed");		
		Point endPoint = arg0.getPoint();
		//		if (startPoint != null) theShape = new Line(startPoint, endPoint, gui.getColor());
		if (startPoint != null) { 
			 theCommandReceiver.receiveCommand(new DrawModelAddCommand( startPoint, 
       		      														endPoint, 
       		      														theGUI.getColor()));
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		//System.out.println("mouseDragged Pressed");		
		Point endPoint = arg0.getPoint();
		Shape theShape;
		//		if (startPoint != null) theShape = new Line(startPoint, endPoint, gui.getColor());
		if (startPoint != null) { 
			 theShape = new ShapeLine(     startPoint, 
		                		           endPoint, 
		                		           theGUI.getColor());
			 theFrame.drawTempShape(theShape);
		}
		
	}

	public void mouseMoved(MouseEvent arg0) {
		// System.out.println("mouseMoved Pressed");		
	}

	public void drawTempShape(Shape s) {
		// Do Nothing
	}

	public void actionPerformed(ActionEvent arg0) {

		System.out.println("Command " + arg0.getActionCommand() + " received");

		if ("newgraph".equals(arg0.getActionCommand())) {
			DrawController controller = new DrawController(theGUI, theCommandReceiver, theCommandAnnouncer);

			DrawView v = new DrawViewGraph(controller);
			controller.registerChangeListener(v);
			theCommandAnnouncer.registerChangeListener(controller);

			v.setViewTitle("Drawing " + theCommandReceiver.getModelNumber());

			theGUI.addView(v);
			theCommandReceiver.receiveCommand(new DrawModelNullCommand());
		}
		else if ("newlist".equals(arg0.getActionCommand())) {
			DrawController controller = new DrawController(theGUI, theCommandReceiver, theCommandAnnouncer);

			DrawView v = new DrawViewList(controller);
			controller.registerChangeListener(v);
			theCommandAnnouncer.registerChangeListener(controller);

			v.setViewTitle("Drawing " + theCommandReceiver.getModelNumber());

			theGUI.addView(v);
			theCommandReceiver.receiveCommand(new DrawModelNullCommand());
		}
		else if ("delete".equals(arg0.getActionCommand())) {
				theCommandReceiver.receiveCommand(new DrawModelDeleteCommand(theFrame.getSelectedIndex()));
		}
		else if ("reset".equals(arg0.getActionCommand())) {
			theCommandReceiver.receiveCommand(new DrawModelResetCommand());
		}
		else if ("clear".equals(arg0.getActionCommand())) {
			theCommandReceiver.receiveCommand(new DrawModelClearCommand());
		}
		else if ("calculate".equals(arg0.getActionCommand())) {
			theCommandReceiver.receiveCommand(new DrawModelCalculateCommand());
		}
		else
			System.out.println("Unknown Command");
		
	}

	public int getSelectedIndex() {
		return theFrame.getSelectedIndex();
	}

	public void valueChanged(ListSelectionEvent arg0) {
		System.out.println("DrawModelSelectCommand(" + theFrame.getSelectedIndex() + ")");
		theCommandReceiver.receiveCommand(new DrawModelSelectCommand(theFrame.getSelectedIndex()));
	}

//	public void select(int selectedIndex) {
//		theCommandReceiver.receiveCommand(new DrawModelSelectCommand(selectedIndex));
//	}
	
}
