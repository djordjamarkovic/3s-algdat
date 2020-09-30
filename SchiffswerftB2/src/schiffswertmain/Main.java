package schiffswertmain;

import inout.InOut;
import inout.InOutException;
import schiffe.Passagierschiff;
import schiffe.Schiff;
import schiffe.Tankschiff;
import werft.KonkursException;
import werft.Werft;

public class Main {

    public static void main(String[] args) {

        Werft dieWerft = new Werft();

        try {
            int choice;
            while (true) {
                //Spiele einen Monat
                // die Werft ist einen Monat ini Betrieb
                dieWerft.betriebFuerEinenMonat();
                // User-Aktion auswaehlen
                choice = InOut.readMenu("Was wollen Sie tun",
                        "Tankschiff bauen@"
                                + "Passagierschiff bauen@"
                                + "Frachtschiff bauen@"
                                + "Schiff verschrotten@"
                                + "Schiff streichen"
                                + "nichts tun@"
                                + "Spielende"
                );

                //TODO User-Aktion durchhführen
                switch (choice) {
                    case 1:
                        // Tankschiff bauen
                    {
                        Schiff x = new Tankschiff();
                        dieWerft.bezahlen(x.preis());
                        dieWerft.aufnehmen(x);
                    }
                    break;
                    case 2:
                        // Passagierschiff bauen
                    {
                        Schiff x = new Passagierschiff();
                        dieWerft.bezahlen(x.preis());
                        dieWerft.aufnehmen(x);
                    }
                    break;
                    case 3:
                        //TODO Frachtschiff bauen
                        break;
                    case 4:
                        //TODO Schiff verschrotten
                        break;
                    case 5:
                        //TODO Schiff streichen
                        break;
                    case 6:
                        // nichts tun
                        break;
                    case 7:
                        //Spielende
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
        }
    }
}
