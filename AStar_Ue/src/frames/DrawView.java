package frames;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import modelcontroller.DrawController;
import modelcontroller.DrawModelChangeListener;



public abstract class DrawView extends JInternalFrame implements DrawModelChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton jGraphButton = null;
	private JButton jListButton = null;

	protected DrawController theController = null;
	protected Component jPanel = null;
	private JScrollPane jScrollPane = null;
	private JButton jResetButton = null;
	private JButton jCalculateButton = null;
	private JButton jClearButton = null;
	/**
	 * This is the xxx default constructor
	 * @param main 
	 */
	public DrawView(DrawController c) {
		super();
		theController = c;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(359, 273);
		this.setResizable(true);
		this.setName("Draw");
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJToolBar(), BorderLayout.NORTH);
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
			// jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}


	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	protected JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.add(getJGraphButton());
			jToolBar.add(getJListButton());
			jToolBar.add(getJResetButton());
			jToolBar.add(getJCalculateButton());
			jToolBar.add(getJClearButton());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jGraphButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJGraphButton() {
		if (jGraphButton == null) {
			jGraphButton = new JButton();
			jGraphButton.setText("New Graph View");
			jGraphButton.setActionCommand("newgraph");
			jGraphButton.addActionListener(theController);
		}
		return jGraphButton;
	}
	
	
	public void setViewTitle(String string) {
		this.setTitle(string);
	}

	/**
	 * This method initializes jListButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJListButton() {
		if (jListButton == null) {
			jListButton = new JButton();
			jListButton.setText("New List View");
			jListButton.setActionCommand("newlist");
			jListButton.addActionListener(theController);
		}
		return jListButton;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	protected abstract Component getJPanel();

	public abstract int getSelectedIndex();

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJPanel());
			jScrollPane.setPreferredSize(new java.awt.Dimension(400, 400));
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jResetButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJResetButton() {
		if (jResetButton == null) {
			jResetButton = new JButton();
			jResetButton.setText("Reset");
			jResetButton.setActionCommand("reset");
			jResetButton.addActionListener(theController);

		}
		return jResetButton;
	}

	/**
	 * This method initializes jCalculateButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJCalculateButton() {
		if (jCalculateButton == null) {
			jCalculateButton = new JButton();
			jCalculateButton.setText("Calculate");
			jCalculateButton.setActionCommand("calculate");
			jCalculateButton.addActionListener(theController);

		}
		return jCalculateButton;
	}

	/**
	 * This method initializes jClearButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJClearButton() {
		if (jClearButton == null) {
			jClearButton = new JButton();
			jClearButton.setText("Clear");
			jClearButton.setActionCommand("clear");
			jClearButton.addActionListener(theController);

		}
		return jClearButton;
	}

	
}  //  @jve:decl-index=0:visual-constraint="10,10"
