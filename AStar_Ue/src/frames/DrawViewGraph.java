package frames;
import java.awt.Component;
import java.awt.Dimension;

import modelcontroller.DrawController;
import modelcontroller.events.DrawModelChangeEvent;
import shapes.Shape;



public class DrawViewGraph extends DrawView {

	private static final long serialVersionUID = 1L;

	public DrawViewGraph(DrawController controller) {
		super(controller);
	}

	protected Component getJPanel() {
		if (jPanel == null) {
			jPanel = new DrawPanel(theController);
			jPanel.setPreferredSize(new Dimension(400, 400));
		}
		return jPanel;
	}

	private DrawPanel getDrawPanel() {
		return (DrawPanel) getJPanel();
	}
	
	
	public void drawModelChanged(DrawModelChangeEvent e) {
		getDrawPanel().drawModelChanged(e);
	}

	public void drawTempShape(Shape s) {
		getDrawPanel().drawTempShape(s);
	}

	@Override
	public int getSelectedIndex() {
		return -1; // Dummy, not used
	}

}  
