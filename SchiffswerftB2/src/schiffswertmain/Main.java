package schiffswertmain;

import definitions.Definitions;
import inout.InOut;
import inout.InOutException;
import schiffe.Frachtschiff;
import schiffe.Passagierschiff;
import schiffe.Schiff;
import schiffe.Tankschiff;
import werft.KonkursException;
import werft.Werft;

public class Main {


    public static void main(String[] args) {
        run();
    }

    public static void run() {

        Werft dieWerft = new Werft();

        try {
            int choice;
            while (true) {
                // Spiele einen Monat
                // die Werft ist einen Monat in Betrieb
                dieWerft.betriebFuerEinenMonat();
                // User-Aktion auswaehlen
                choice = InOut.readMenu("Was wollen Sie tun",
                        "Tankschiff bauen (" + Definitions.TANKSCHIFF_PREIS + " Mio. EUR)@" // 1
                                + "Passagierschiff bauen (" + Definitions.PASSAGIERSCHIFF_PREIS + " Mio. EUR)@" // 2
                                + "Frachtschiff bauen (" + Definitions.FRACHTSCHIFF_PREIS + " Mio. EUR)@" // 3
                                + "Schiff verschrotten@" // 4
                                + "Schiff streichen@" // 5
                                + "nichts tun@" // 6
                                + "Spielende" // 7
                );

                // User-Aktion durchhführen
                // int shipNumber;
                // int repaintNumber;
                switch (choice) {
                    case 1: { // Tankschiff bauen
                        Schiff x = new Tankschiff();
                        dieWerft.bezahlen(x.preis());
                        dieWerft.aufnehmen(x);
                        break;
                    }
                    case 2: { // Passagierschiff bauen
                        Schiff x = new Passagierschiff();
                        dieWerft.bezahlen(x.preis());
                        dieWerft.aufnehmen(x);
                        break;
                    }
                    case 3: { // Frachtschiff bauen
                        Schiff x = new Frachtschiff();
                        dieWerft.bezahlen(x.preis());
                        dieWerft.aufnehmen(x);
                        break;
                    }
                    case 4: { // Schiff verschrotten
                        int shipNumber = InOut.readInt("Welches Schiff wollen Sie verschrotten? Please choose: ");
                        System.out.println("Gewaehlte Schiffsnummer: " + shipNumber);
                        dieWerft.verschrotten(shipNumber);
                        break;
                    }
                    case 5:
                        int repaintNumber = InOut.readInt("Welches Schiff wollen Sie neu lackieren? Please choose: ");
                        System.out.println("Gewaehlte Schiffsnummer: " + repaintNumber);
                        dieWerft.lackiereSchiff(repaintNumber);
                        break;
                    case 6: // nichts tun
                        break;
                    case 7: //Spielende
                        throw new SpielendeException();
                    default:
                        throw new IllegalStateException("Unexpected value: " + choice);
                }
            }
        } catch (InOutException e) {
            InOut.printString("Fehlereingabe! Das Spiel wurde beendet.");
        } catch (SpielendeException e) {
            InOut.printString("Das Spiel wurde beendet.");
        } catch (KonkursException e) {
            InOut.printString("Sie sind im Konkurs.");
        } catch (Exception e) {
            InOut.printString("Betrieb für einen Monat ist fehlerhaft.");
        }
    }
}
