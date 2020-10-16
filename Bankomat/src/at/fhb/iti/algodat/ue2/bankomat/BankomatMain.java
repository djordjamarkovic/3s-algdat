package at.fhb.iti.algodat.ue2.bankomat;

import at.fhb.iti.algodat.ue2.bankomat.grafics.BankomatMainFrame;
import at.fhb.iti.algodat.ue2.bankomat.model.BankomatModelImplementation;

public class BankomatMain {

	
	public static void main(String[] args) {
		
		BankomatModelImplementation bankomatModel = new BankomatModelImplementation();
		BankomatMainFrame window = new BankomatMainFrame(bankomatModel);
		bankomatModel.setView(window);
		window.runFrame();

		
	}
	

}
