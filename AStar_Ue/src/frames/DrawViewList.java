package frames;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import modelcontroller.DrawController;
import modelcontroller.events.DrawModelChangeEvent;
import modelcontroller.events.DrawModelChangeGraphEvent;
import shapes.Shape;



public class DrawViewList extends DrawView {

	private static final long serialVersionUID = 1L;

	private JButton jDelButton;
	
	public DrawViewList(DrawController controller) {
		super(controller);
		getJToolBar().add(getDelButton());
	}

	protected Component getJPanel() {
		if (jPanel == null) {
			JList j = new JList();
			j.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//			j.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
//				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
//					theController.select(getSelectedIndex());
//				}
//			});
			j.addListSelectionListener(theController);
			jPanel = j;
		}
		return jPanel;
	}

	private JList getJList() {
		return (JList) jPanel;
	}
	
	
	public void drawModelChanged(DrawModelChangeEvent e) {
		if ( ! ( e instanceof DrawModelChangeGraphEvent) ) {
			getJList().setListData(new Vector<Shape>(e.getData().getShapes()));
			getJList().repaint();
		}
	}

	public void drawTempShape(Shape s) {
		// Do nothing
	}
	
	private JButton getDelButton() {
		if (jDelButton == null) {
			jDelButton = new JButton();
			jDelButton.setText("Delete");
			jDelButton.setActionCommand("delete");
			jDelButton.addActionListener(theController);
		}
		return jDelButton;
	}

	@Override
	public int getSelectedIndex() {
		return getJList().getSelectedIndex();
	}

	
	
}  
