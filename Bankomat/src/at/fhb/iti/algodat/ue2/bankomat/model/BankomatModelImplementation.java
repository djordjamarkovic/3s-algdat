package at.fhb.iti.algodat.ue2.bankomat.model;

import at.fhb.iti.algodat.ue2.bankomat.grafics.BankomatView;

import static at.fhb.iti.algodat.ue2.bankomat.model.BankomatState.*;

public class BankomatModelImplementation implements BankomatModel {

	private BankomatView theView;
	private BankomatState theState;
	private int theCode;

	public void setView(BankomatView view) {
		theView = view;
		theState = START;
		theCode = 0;
	}
	
	public void pressButtonDigit(int digit) {
		// TODO Auto-generated method stub
		theView.setText("Digit " + digit + " pressed.");
		switch (theState) {
		case DIGIT0:
			// TODO Do something according to state
			BankomatState target = DIGIT1;
			theState=DIGIT1;
			theCode=digit;
			theView.setText("Bitte geben Sie Ihren Code ein: *");
			System.out.println("Edge 2 from "+theState+" to "+target);
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
		// todo
		switch (theState) {
			case DIGIT1: {
				BankomatState target = DIGIT0;
				theCode=0;
				System.out.println("Edge (linie ins skizzes) from "+theState+" to "+target);
				theState=target;
				break;
		}
		}
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
		//theView.setText("Card pressed.");
		//theView.setKartenButtonLabel("Karte Button neu");
		//theView.setKartenText("Karte Text neu");
		switch(theState) {
			case START: {
				BankomatState target = DIGIT0;
				// TODO semantic

				System.out.println("Edge 1 from "+theState+" to "+target);
				theState = target;
			} break;
			case DIGIT0: {
				BankomatState target = DIGIT1;
				System.out.println("Edge 2 from "+theState+" to "+target);
				theState = target;
			} break;
			case DIGIT1: {
				BankomatState target = DIGIT2;
				System.out.println("Edge 3 from "+theState+" to "+target);
				theState = target;
			} break;
			case DIGIT2: {
				BankomatState target = DIGIT3;
				System.out.println("Edge 4 from "+theState+" to "+target);
				theState = target;
			} break;
			case DIGIT3: {
				BankomatState target = ENTER;
				System.out.println("Edge 5 from "+theState+" to "+target);
				theState = target;
			} break;
			case ENTER: {
				boolean code=true;
				if (code) {
					BankomatState target = WAITING;
					System.out.println("Edge 1 from "+theState+" to "+target);
					theState = target;
				} else if (!code) {
					BankomatState target = DIGIT0;
					System.out.println("Edge 1 from "+theState+" to "+target);
					theState = target;
				}
			} break;
		}
	}

	private String getEdge(Enum<BankomatState> enm) {
		/*
		BankomatState target = enm;
		System.out.println("Edge 1 from "+theState+" to "+target);
		theState = target;
		*/
		return "";
	}

}
