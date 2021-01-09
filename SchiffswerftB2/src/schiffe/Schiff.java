package schiffe;

import definitions.Definitions;
import inout.InOut;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public abstract class Schiff {

    private static int naechsteFreieSchiffsNummer = 1;
    private double derHautzustand;
    private int dieStreichZahl;
    public int dieSchiffsNummer;

    public Schiff() {
        dieSchiffsNummer = naechsteFreieSchiffsNummer;
        naechsteFreieSchiffsNummer++;
        derHautzustand = 1.0;
        dieStreichZahl = 0;
    }

    abstract public double preis();

    public void anzeigen() {
        NumberFormat formatter = new DecimalFormat("#0.00%");
        InOut.printString("Schiffnummer: " + schiffsNummer()
                + " ist ein " + schiffsArt()
                + " Schiffshaut ist " + formatter.format(hautZustand()) + " intakt"
                + " wurde schon " + streichZahl() + " gestrichen."
        );
    }

    public void setDerHautzustand(double derHautzustand) {
        this.derHautzustand = derHautzustand;
    }

    private int streichZahl() {
        return dieStreichZahl;
    }

    private double hautZustand() { return derHautzustand; }

    protected abstract String schiffsArt();

    private int schiffsNummer() {
        return dieSchiffsNummer;
    }

    public boolean istSchadhaft() {
        if (derHautzustand < Definitions.SINKFAKTOR) {
            return true;
        } else {
            return false;
        }
    }

    abstract public double monatsGewinn();

    abstract public double repaintCost();

    public double schiffsHautAltert() {
        return derHautzustand = derHautzustand * Definitions.ROSTFAKTOR;
    }

    public void lackiereSchiff(Schiff x) {
        dieStreichZahl++;
        derHautzustand = 1 - x.dieStreichZahl * Definitions.STREICHVORGAENGE_FAKTOR;

        System.out.println("dieStreichZahl: "+dieStreichZahl);
        System.out.println("derHautzustand: "+derHautzustand);
    }
}
