package at.fhb.iti.algodat.ue2.bankomat.model;

import at.fhb.iti.algodat.ue2.bankomat.grafics.BankomatView;

import static at.fhb.iti.algodat.ue2.bankomat.model.BankomatState.*;

public class BankomatModelImplementation implements BankomatModel {

    private static final int CORRECT_CODE = 1234;
    private BankomatView theView;
    private BankomatState theState;
    private int theCode;

    public void setView(BankomatView view) {
        theView = view;
        theState = START;
        theCode = 0;
    }

    public void pressButtonDigit(int digit) {
        theView.setText("Digit " + digit + " pressed.");
        BankomatState target;
        switch (theState) {
            case DIGIT0:
                target = DIGIT1;
                theCode += digit;
                theView.setText("Bitte geben Sie Ihren Code ein: *");
                System.out.println("Edge 2 from " + theState + " to " + target);
                System.out.println("Code ist: " + theCode);
                theState = target;
                break;
            case DIGIT1:
                target = DIGIT2;
                theCode = theCode * 10 + digit;
                theView.setText("Bitte geben Sie Ihren Code ein: **");
                System.out.println("Edge 3 from " + theState + " to " + target);
                System.out.println("Code ist: " + theCode);
                theState = target;
                break;
            case DIGIT2:
                target = DIGIT3;
                theCode = theCode * 10 + digit;
                theView.setText("Bitte geben Sie Ihren Code ein: ***");
                System.out.println("Edge 4 from " + theState + " to " + target);
                System.out.println("Code ist: " + theCode);
                theState = target;
                break;
            case DIGIT3:
                target = ENTER;
                theCode = theCode * 10 + digit;
                theView.setText("Bitte geben Sie Ihren Code ein: ****");
                System.out.println("Edge 5 from " + theState + " to " + target);
                System.out.println("Code ist: " + theCode);
                theState = target;
                break;
            case CORRECT:
                target = CORRECT;
                theView.setText("Bitte geben Sie Ihren Code ein: ****");
                System.out.println("Edge 23 from " + theState + " to " + target);
                break;
            default:
                break;
        }
    }

    public void pressButtonCancel() {
        //theView.setText("Cancel pressed.");
        theView.setText("Bitte geben Sie Ihren Code ein: ");
        BankomatState target = START;
        theCode = 0;
        switch (theState) {
            case DIGIT0:
                System.out.println("Edge ??? from " + theState + " to " + target);
                break;
            case DIGIT1:
                System.out.println("Edge 10 from " + theState + " to " + target);
                break;
            case DIGIT2:
                System.out.println("Edge 8 from " + theState + " to " + target);
                break;
            case DIGIT3:
                System.out.println("Edge 12 from " + theState + " to " + target);
                break;
            case ENTER:
                System.out.println("Edge 14 from " + theState + " to " + target);
                break;
            case WAITING:
                System.out.println("Edge 18 from " + theState + " to " + target);
                break;
            default:
                break;
        }
        theState = target;
        theView.setText("Bitte drücken Sie \"Kartenschacht\" belegt nochmal.");
    }

    public void pressButtonDelete() {
        theView.setText("Delete pressed.");
        BankomatState target;
        switch (theState) {
            case DIGIT0:
                target = DIGIT0;
                theCode = 0;
                theView.setText("Bitte geben Sie Ihren Code ein: ");
                System.out.println("Edge 6 from " + theState + " to " + target);
                System.out.println("Code ist: " + theCode);
                theState = target;
                break;
            case DIGIT1:
                target = DIGIT0;
                theCode = theCode / 10;
                theView.setText("Bitte geben Sie Ihren Code ein: ");
                System.out.println("Edge 7 from " + theState + " to " + target);
                System.out.println("Code ist: " + theCode);
                theState = target;
                break;
            case DIGIT2:
                target = DIGIT1;
                theCode = theCode / 10;
                theView.setText("Bitte geben Sie Ihren Code ein: *");
                System.out.println("Edge 9 from " + theState + " to " + target);
                System.out.println("Code ist: " + theCode);
                theState = target;
                break;
            case DIGIT3:
                target = DIGIT2;
                theCode = theCode / 10;
                theView.setText("Bitte geben Sie Ihren Code ein: **");
                System.out.println("Edge 11 from " + theState + " to " + target);
                System.out.println("Code ist: " + theCode);
                theState = target;
                break;
            case ENTER:
                target = DIGIT3;
                theCode = theCode / 10;
                theView.setText("Bitte geben Sie Ihren Code ein: ***");
                System.out.println("Edge 13 from " + theState + " to " + target);
                System.out.println("Code ist: " + theCode);
                theState = target;
                break;
            case WAITING:
                target = WAITING;
                theView.setText("Bitte geben Sie Ihren Code ein: ***");
                System.out.println("Edge 21 from " + theState + " to " + target);
                System.out.println("Code ist: " + theCode);
                theState = target;
                break;
            case CORRECT:
                target = CORRECT;
                theView.setText("Bitte geben Sie Ihren Code ein: ****");
                System.out.println("Edge 24 from " + theState + " to " + target);
                break;
            default:
                break;
        }
    }

    public void pressButtonEnter() {
        theView.setText("Enter pressed.");
        switch (theState) {
            case ENTER:
                BankomatState target;
                if (theCode == CORRECT_CODE) {
                    target = WAITING;
                    theView.setText("Ihr Code ist korrekt. Geld mit ENTER abheben? ");
                    System.out.println("Edge 17 from " + theState + " to " + target);
                    theState = target;
                    break;
                } else if (theCode != CORRECT_CODE) {
                    target = DIGIT0;
                    theView.setText("Ihr Code ist nicht korrekt. Bitte wiederholen:");
                    System.out.println("Edge 16 from " + theState + " to " + target);
                    theState = target;
                    break;
                }
                break;
            case WAITING:
                target = CORRECT;
                theView.setText("Karte rausschieben.");
                System.out.println("Edge 22 from " + theState + " to " + target);
                theState = target;
                break;
            case CORRECT:
                target = MONEYBOX;
                theView.setText("Geld in die Geldlade legen.");
                System.out.println("Edge 25 from " + theState + " to " + target);
                theState = target;
                break;
            case MONEYBOX:
                target = MONEY;
                theView.setText("Geld aus Geldlade nehmen. BIPS BIBPS BIPS");
                System.out.println("Edge 26 from " + theState + " to " + target);
                theState = target;
                break;
            case MONEY:
                target = START;
                theView.setText("Auf Wiedersehen!");
                System.out.println("Edge 20 from " + theState + " to " + target);
                theState = target;
                break;
            default:
                break;
        }
    }

    public void pressButtonMoney() {
        theView.setText("Money pressed.");
        theView.setGeldladeText("Geldlade neuer Text");
    }

    public void pressButtonCard() {
        theView.setText("Karte einlegen gedrückt.");
        theView.setKartenButtonLabel("Kartenschacht belegt");
        theView.setKartenText("Kartenschacht belegt.");

        switch (theState) {
            case START: {
                BankomatState target = DIGIT0;
                // TODO semantic

                System.out.println("Edge 1 from " + theState + " to " + target);
                theState = target;
            }
            break;
            case DIGIT0: {
                BankomatState target = DIGIT1;
                System.out.println("Edge 2 from " + theState + " to " + target);
                theState = target;
            }
            break;
            case DIGIT1: {
                BankomatState target = DIGIT2;
                System.out.println("Edge 3 from " + theState + " to " + target);
                theState = target;
            }
            break;
            case DIGIT2: {
                BankomatState target = DIGIT3;
                System.out.println("Edge 4 from " + theState + " to " + target);
                theState = target;
            }
            break;
            case DIGIT3: {
                BankomatState target = ENTER;
                System.out.println("Edge 5 from " + theState + " to " + target);
                theState = target;
            }
            break;
            case ENTER: {
                boolean code = true;
                if (code) {
                    BankomatState target = WAITING;
                    System.out.println("Edge 1 from " + theState + " to " + target);
                    theState = target;
                } else if (!code) {
                    BankomatState target = DIGIT0;
                    System.out.println("Edge 1 from " + theState + " to " + target);
                    theState = target;
                }
            }
            break;
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
