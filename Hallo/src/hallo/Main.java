package hallo;

import inout.InOut;
import inout.InOutException;

public class Main {
    // TODO Fields of Main

    public Main() {
        // TODO Initialization of fields of Main
    }

    // TODO Implement body of Main
    public static void main(String[] args) {
        try {
            //System.out.println("Hallo Welt");
            InOut.printString("Hallo Welt.");
            int i = 0;
            i = InOut.readInt("Bitte gib eine Zahlh ein: ");
            InOut.printString("" + i);
            //todo fix me 1
        } catch (InOutException e) {
            e.printStackTrace();
        }
    }

}
