package werft;

import definitions.Definitions;
import schiffe.Schiff;

import java.util.ArrayList;
import java.util.List;

public class Werft {

    private Kassa dieKassa;
    private List<Schiff> dieSchiffe;

    public Werft() {
        dieKassa = new Kassa();
        dieSchiffe = new ArrayList<Schiff>();
    }

    public void betriebFuerEinenMonat() throws Exception, KonkursException {
        schadhafteSchiffenSinken();
        schiffeFahrenHerumUndMachenGewinn();
        zustandDerWerftAnzeigen();
    }

    public void zustandDerWerftAnzeigen() {
        dieKassa.anzeigen();
        for (Schiff x : dieSchiffe) {
            x.anzeigen();
        }
    }

    private void schiffeFahrenHerumUndMachenGewinn() throws Exception {
        for (Schiff x : dieSchiffe) {
            dieKassa.einnehmen(x.monatsGewinn());
            x.schiffsHautAltert();
        }
    }

    private void schadhafteSchiffenSinken() throws KonkursException {
        List<Schiff> schadhafteSchiffe = new ArrayList<Schiff>();
        for (Schiff x : dieSchiffe) {
            if (x.istSchadhaft()) {
                schadhafteSchiffe.add(x);
            }
        }
        for (Schiff x : schadhafteSchiffe) {
            // Schiff sinkt
            // todo
            dieKassa.ausgeben(x.preis() * Definitions.BERGEKOSTEN_FAKTOR);
            dieSchiffe.remove(x);
        }
    }

    public void bezahlen(double preis) throws KonkursException {
        dieKassa.ausgeben(preis);
    }

    public void aufnehmen(Schiff x) {
        dieSchiffe.add(x);
    }

    public void verschrotten(int shipNumber) {
        //todo
        dieSchiffe.remove(shipNumber);
    }
    public String getShips() {
        return  dieSchiffe.toString();
    }

}
