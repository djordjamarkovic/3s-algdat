package werft;

import definitions.Definitions;
import inout.InOut;

public class Kassa {

    private double kassaStand;

    public Kassa() {
        kassaStand = Definitions.ANFANGS_KASSASTAND;
    }

    public void ausgeben(double preis) throws KonkursException {
        kassaStand = kassaStand - preis;
        if (kassaStand < 0.0) {
            throw new KonkursException();
        }
    }

    public void anzeigen() {
        InOut.printString("Kassastand ist " + kassaStand + " Mio. EUR.");
    }

    public void einnehmen(double profit) throws Exception {
        if (profit < 0.0) {
            throw new Exception("Profit: "+profit+" is negativ." );
        }
        kassaStand = kassaStand + profit;
    }
}
