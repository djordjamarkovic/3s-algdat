package schiffe;

import definitions.Definitions;

public class Frachtschiff extends Schiff {
    @Override
    public double preis() {
        return Definitions.FRACHTSCHIFF_PREIS;
    }

    @Override
    protected String schiffsArt() {
        return "Frachtschiff";
    }

    @Override
    public double monatsGewinn() {
        return Definitions.FRACHTSCHHIFF_MONTHLY_PROFIT;
    }

    @Override
    public double repaintCost() {
        return Definitions.FRACHTSCHHIFF_REPAINT;
    }

}
