package frames;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

import modelcontroller.DrawController;
import modelcontroller.DrawModelChangeListener;
import modelcontroller.DrawViewDataSupplier;
import modelcontroller.events.DrawModelChangeEvent;
import shapes.Shape;


public class DrawPanel extends JPanel implements DrawModelChangeListener {

	private static final long serialVersionUID = 1L;

	private DrawController theController;
	private DrawViewDataSupplier theShapes;

	private Shape tempShape;
		
	public DrawPanel(DrawController c) {
		super();
		theController = c;
		theShapes = null;
		tempShape = null;
		initialize();
	}

	private void initialize() {
		this.setPreferredSize(new java.awt.Dimension(1200, 1200));
		//this.setSize(300, 200);
		this.addMouseListener(theController);
		this.addMouseMotionListener(theController);
	}
	
	protected void paintComponent(Graphics g) {
//		Paint background if we're opaque.
//		System.out.println("MyPanel.paintComponent called");
		super.paintComponent(g);
		if (isOpaque()) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		}
		
		// g.setColor(Color.RED);		
		// g.drawLine(5,8,22,57);
		
//		Paint 20x20 grid.
		g.setColor(Color.GRAY);
//		drawGrid(g, 20);

		if (theShapes != null) {
			for (Shape s: theShapes.getShapes()) {
				s.draw(g);
			}
		}
		
		if (tempShape != null) {
			tempShape.draw(g);
			tempShape = null;
		}

		//g.setColor(Color.red);
		//g.fillOval(3, 3, 7, 7);
	}
	
//	Draws a 20x20 grid using the current color.
	private void drawGrid(Graphics g, int gridSpace) {
		Insets insets = getInsets();
		int firstX = insets.left;
		int firstY = insets.top;
		int lastX = getWidth() - insets.right;
		int lastY = getHeight() - insets.bottom;

		// Draw vertical lines.
		int x = firstX;
		while (x < lastX) {
			g.drawLine(x, firstY, x, lastY);
			x += gridSpace;
		}

		// Draw horizontal lines.
		int y = firstY;
		while (y < lastY) {
			g.drawLine(firstX, y, lastX, y);
			y += gridSpace;
		}
	}

	public void drawModelChanged(DrawModelChangeEvent e) {
//		System.out.println("MyPanel.drawModelChanged called");
		theShapes = e.getData();
		repaint();
		RepaintManager.currentManager(this).paintDirtyRegions();
		
	}

	public void drawTempShape(Shape s) {
		tempShape = s;
//		Graphics g = getGraphics();
//		if (g != null) paintComponent(g);
//		else repaint();
		repaint();
	}

	public int getSelectedIndex() {
		return -1;
	}
	
		
}
