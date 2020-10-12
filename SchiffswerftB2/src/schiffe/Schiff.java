package schiffe;

import definitions.Definitions;
import inout.InOut;

public abstract class Schiff {

    private static int naechsteFreieSchiffsNummer = 1;
    private int dieSchiffsNummer;
    private double derHautzustand;
    private int dieStreichZahl;

    public Schiff() {
        dieSchiffsNummer = naechsteFreieSchiffsNummer;
        naechsteFreieSchiffsNummer++;
        derHautzustand = 1.0;
        dieStreichZahl = 0;
    }

    abstract public double preis();

    public void anzeigen() {
        InOut.printString("Schiffnummer: " + schiffsNummer()
                + " ist ein " + schiffsArt()
                + " Schiffshaut ist " + hautZustand() + " intakt"
                + " wurde schon " + streichZahl() + " gestrichen."
        );
    }

    private int streichZahl() {
        return dieStreichZahl;
    }

    private double hautZustand() {
        return derHautzustand;
    }

    protected abstract String schiffsArt();

    private int schiffsNummer() {
        return dieSchiffsNummer;
    }

    public boolean istSchadhaft() {
        //TODO
        return false;
    }

    abstract public double monatsGewinn();

    abstract public double repaint();

    public double schiffsHautAltert() {
        return derHautzustand = derHautzustand * Definitions.ROSTFAKTOR;
    }
}
