package at.fhb.iti.algodat.ue2.bankomat.grafics;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import at.fhb.iti.algodat.ue2.bankomat.model.BankomatModel;

public class BankomatMainFrame implements BankomatView {

	private JFrame frmBankomat;
	private JTextField txtFeld;
	private JPanel buttonPanel;
	private JPanel cardNMoneyPanel;
	private JPanel numberButtonPanel;
	private JButton btnEnter;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn0;
	private JButton btnDel;
	private JButton btnCancel;
	private JLabel lblKarte;
	private JButton btnKarte;
	private JLabel lblNewLabel;
	private JPanel cardPanel;
	private JPanel moneyPanel;
	private JTextField textGeldlade;
	private JButton btnGeldlade;
	private JPanel cardPanelInt;
	private JPanel moneyPanelInt;
	private BankomatModel bankomatModel;

	/**
	 * Create the application.
	 * @param bankomatModel 
	 */
	public BankomatMainFrame(BankomatModel b) {
		bankomatModel = b;
		initialize();
	}

    public void runFrame () {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				frmBankomat.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
    }


	public void setText(String text) {
		txtFeld.setText(text);	
	}

	public void setKartenText(String text) {
		lblKarte.setText(text);	
	}

	public void setKartenButtonLabel(String text) {
		btnKarte.setText(text);	
	}

	public void setGeldladeText(String text) {
		textGeldlade.setText(text);	
	}

    
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBankomat = new JFrame();
		frmBankomat.setTitle("Bankomat Algodat 2013");
		frmBankomat.setBounds(100, 100, 450, 300);
		frmBankomat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel northPanel = new JPanel();
		frmBankomat.getContentPane().add(northPanel, BorderLayout.NORTH);
		
		txtFeld = new JTextField();
		txtFeld.setText("Willkommen beim Bankomaten!");
		txtFeld.setEditable(false);
		northPanel.add(txtFeld);
		txtFeld.setColumns(30);
		
		buttonPanel = new JPanel();
		frmBankomat.getContentPane().add(buttonPanel, BorderLayout.WEST);
		buttonPanel.setLayout(new BorderLayout(0, 0));
		
		numberButtonPanel = new JPanel();
		buttonPanel.add(numberButtonPanel, BorderLayout.CENTER);
		numberButtonPanel.setLayout(new GridLayout(4, 3, 0, 0));
		
		btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDigit(7);
			}
		});
		numberButtonPanel.add(btn7);
		
		btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDigit(8);
			}
		});
		numberButtonPanel.add(btn8);
		
		btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDigit(9);
			}
		});
		numberButtonPanel.add(btn9);
		
		btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDigit(4);
			}
		});
		numberButtonPanel.add(btn4);
		
		btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDigit(5);
			}
		});
		numberButtonPanel.add(btn5);
		
		btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDigit(6);
			}
		});
		numberButtonPanel.add(btn6);
		
		btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDigit(1);
			}
		});
		numberButtonPanel.add(btn1);
		
		btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDigit(2);
			}
		});
		numberButtonPanel.add(btn2);
		
		btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDigit(3);
			}
		});
		numberButtonPanel.add(btn3);
		
		btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDigit(0);
			}
		});
		numberButtonPanel.add(btn0);
		
		btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonDelete();
			}
		});
		numberButtonPanel.add(btnDel);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonCancel();
			}
		});
		numberButtonPanel.add(btnCancel);
		
		btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonEnter();
			}
		});
		buttonPanel.add(btnEnter, BorderLayout.SOUTH);
		
		cardNMoneyPanel = new JPanel();
		frmBankomat.getContentPane().add(cardNMoneyPanel, BorderLayout.CENTER);
		cardNMoneyPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		cardPanel = new JPanel();
		cardNMoneyPanel.add(cardPanel);
		
		cardPanelInt = new JPanel();
		cardPanel.add(cardPanelInt);
		cardPanelInt.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblKarte = new JLabel("Kartenschacht leer");
		cardPanelInt.add(lblKarte);
		
		btnKarte = new JButton("Karte einlegen");
		btnKarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonCard();
			}
		});

		cardPanelInt.add(btnKarte);
		
		moneyPanel = new JPanel();
		cardNMoneyPanel.add(moneyPanel);
		
		moneyPanelInt = new JPanel();
		moneyPanel.add(moneyPanelInt);
		moneyPanelInt.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNewLabel = new JLabel("Geldlade");
		moneyPanelInt.add(lblNewLabel);
		
		textGeldlade = new JTextField();
		moneyPanelInt.add(textGeldlade);
		textGeldlade.setEditable(false);
		textGeldlade.setColumns(10);
		
		btnGeldlade = new JButton("Geld entnehmen");
		btnGeldlade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankomatModel.pressButtonMoney();
			}
		});
		moneyPanelInt.add(btnGeldlade);
	}

}
