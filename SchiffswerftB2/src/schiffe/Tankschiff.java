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

    @Override
    public double monatsGewinn() {
        return Definitions.TANKSCHIFF_MONTHLY_PROFIT;
    }

    @Override
    public double repaintCost() {
        return Definitions.TANKSCHIFF_REPAINT;
    }


}
