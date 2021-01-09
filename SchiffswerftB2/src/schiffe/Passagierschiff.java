package schiffe;

import definitions.Definitions;

public class Passagierschiff extends Schiff {
    @Override
    public double preis() {
        return Definitions.PASSAGIERSCHIFF_PREIS;
    }

    @Override
    protected String schiffsArt() {
        return "Passagierschiff";
    }

    @Override
    public double monatsGewinn() {
        return Definitions.PASSAGIERSCHIFF_MONTHLY_PROFIT;
    }

    @Override
    public double repaintCost() {
        return Definitions.PASSAGIERSCHIFF_REPAINT;
    }
}
