package at.fhb.iti.algodat.ue2.bankomat.model;

import at.fhb.iti.algodat.ue2.bankomat.grafics.BankomatView;

import static at.fhb.iti.algodat.ue2.bankomat.model.BankomatState.*;

public class BankomatModelImplementation implements BankomatModel {

	private BankomatView theView;
	private BankomatState theState;

	public void setView(BankomatView view) {
		theView = view;
		theState = AVAILABLE;
	}
	
	public void pressButtonDigit(int digit) {
		// TODO Auto-generated method stub
		theView.setText("Digit " + digit + " pressed.");
		switch (theState) {
		case AVAILABLE:
			// TODO Do something according to state
			theState=STATE1;
			break;
		default:
			break;
		}
	}

	public void pressButtonCancel() {
		// TODO Auto-generated method stub
		theView.setText("Cancel pressed.");		
	}

	public void pressButtonDelete() {
		// TODO Auto-generated method stub
		theView.setText("Delete pressed.");				
	}

	public void pressButtonEnter() {
		// TODO Auto-generated method stub
		theView.setText("Enter pressed.");				
		
	}

	public void pressButtonMoney() {
		// TODO Auto-generated method stub
		theView.setText("Money pressed.");	
		theView.setGeldladeText("Geldlade neuer Text");
	}

	public void pressButtonCard() {
		// TODO Auto-generated method stub
		theView.setText("Card pressed.");
		theView.setKartenButtonLabel("Karte Button neu");
		theView.setKartenText("Karte Text neu");
	}


}
