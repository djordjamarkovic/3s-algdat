package modelcontroller;

import modelcontroller.events.DrawModelChangeEvent;
import shapes.Shape;

public interface DrawModelChangeListener {

	void drawModelChanged(DrawModelChangeEvent e);
	
	void drawTempShape(Shape s);

	public int getSelectedIndex();
}
