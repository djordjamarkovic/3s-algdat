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
                theState = target;
                break;
            case DIGIT1:
                target = DIGIT2;
                theCode = theCode * 10 + digit;
                theView.setText("Bitte geben Sie Ihren Code ein: **");
                System.out.println("Edge 3 from " + theState + " to " + target);
                theState = target;
                break;
            case DIGIT2:
                target = DIGIT3;
                theCode = theCode * 10 + digit;
                theView.setText("Bitte geben Sie Ihren Code ein: ***");
                System.out.println("Edge 4 from " + theState + " to " + target);
                theState = target;
                break;
            case DIGIT3:
                target = ENTER;
                theCode = theCode * 10 + digit;
                theView.setText("Bitte geben Sie Ihren Code ein: ****. Mit ENTER bestaetigen.");
                System.out.println("Edge 5 from " + theState + " to " + target);
                theState = target;
                break;
            case CORRECT:
                target = CORRECT;
                theView.setText("Bitte geben Sie Ihren Code ein: ****");
                System.out.println("Edge 23 from " + theState + " to " + target);
                break;
            default:
                theView.setText("Bitte legen Sie eine Karte ein.");
                break;
        }
    }

    public void pressButtonCancel() {
        theView.setText("Bitte geben Sie Ihren Code ein: ");
        BankomatState target = START;
        theCode = 0;
        switch (theState) {
            case DIGIT0:
                System.out.println("Edge 26 from " + theState + " to " + target);
                break;
            case DIGIT1:
                System.out.println("Edge 8 from " + theState + " to " + target);
                break;
            case DIGIT2:
                System.out.println("Edge 10 from " + theState + " to " + target);
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
            case CORRECT:
                System.out.println("Edge 19 from " + theState + " to " + target);
                break;
            default:
                theView.setText("Cancel pressed.");
                break;
        }
        theState = target;
        theView.setText("Bitte drücken Sie \"Kartenschacht\" belegt nochmal.");
    }

    public void pressButtonDelete() {
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
                theState = target;
                break;
            default:
                theView.setText("Delete pressed.");
                break;
        }
    }

    public void pressButtonEnter() {
        switch (theState) {
            case ENTER:
                BankomatState target;
                if (isCodeCorrect(theCode)) {
                    target = WAITING;
                    theView.setText("Ihr Code ist korrekt. Weiter mit ENTER.");
                    System.out.println("Edge 16 from " + theState + " to " + target);
                    theState = target;
                    break;
                } else if (!isCodeCorrect(theCode)) {
                    target = DIGIT0;
                    theView.setText("Ihr Code ist nicht korrekt. Bitte wiederholen:");
                    System.out.println("Edge 17 from " + theState + " to " + target);
                    theState = target;
                    break;
                }
                break;
            case WAITING:
                target = CORRECT;
                theView.setText("Geld abheben? Weiter mit ENTER.");
                System.out.println("Edge 22 from " + theState + " to " + target);
                theState = target;
                break;
            case CORRECT:
                target = CARDSLOT;
                theView.setText("Karte rausschieben (3PIEPS). Weiter mit \"Karte entfernen\".");
                System.out.println("Edge 25 from " + theState + " to " + target);

                theView.setText("Bitte Ihre Karte entfernen.");
                theView.setKartenButtonLabel("Karte entfernen");

                theState = target;
                break;
            default:
                theView.setText("Enter pressed.");
                break;
        }
    }

    public void pressButtonMoney() {
        theView.setGeldladeText("Geldlade ist leer.");
        BankomatState target;
        switch (theState) {
            case MONEY:
                target = START;
                theView.setText("Auf Wiedersehen!");
                System.out.println("Edge 20 from " + theState + " to " + target);
                theState = target;
                break;
            default:
                theView.setText("Money pressed.");
                break;
        }
    }

    public void pressButtonCard() {
        switch (theState) {
            case START: {
                BankomatState target = DIGIT0;
                System.out.println("Edge 1 from " + theState + " to " + target);
                theView.setText("Karte einlegen gedrückt. Bitte Code eingeben!");
                theView.setKartenButtonLabel("Kartenschacht belegt");
                theView.setKartenText("Kartenschacht belegt.");
                theState = target;
                break;
            }
            case CARDSLOT: {
                BankomatState target = MONEY;
                System.out.println("Edge 15 from " + theState + " to " + target);
                theView.setText("Geld in Geldlade legen. Weiter mit \"Geld entnehmen\".");
                theView.setKartenText("Kartenschacht frei.");
                theView.setKartenButtonLabel("Karte einlegen");
                theView.setGeldladeText("Geldlade ist voll.");
                theState = target;
                break;
            }
        }
    }

    public boolean isCodeCorrect(int i) {
        boolean result = false;
        if (i == CORRECT_CODE) {
            result = true;
        } else if (i != CORRECT_CODE) {
            result = false;
        }
        return result;
    }

    /* obsolet
    private String getEdge(Enum<BankomatState> enm) {
		BankomatState target = enm;
		System.out.println("Edge 1 from "+theState+" to "+target);
		theState = target;
        return "";
    }
    */
}
