package schiffe;

import definitions.Definitions;

public class Tankschiff extends Schiff {

    public Tankschiff() {
    }

    @Override
    public double preis() {
        return Definitions.TANKSCHIFF_PREIS;
    }

    @Override
    protected String schiffsArt() {
        return "Tankschiff";
    }


}
