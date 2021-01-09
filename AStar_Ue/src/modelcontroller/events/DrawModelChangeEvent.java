package modelcontroller.events;

import modelcontroller.DrawViewDataSupplier;

public class DrawModelChangeEvent {
	
	private DrawViewDataSupplier theData;

	public DrawModelChangeEvent(DrawViewDataSupplier m){
		theData = m;
	}
	
	public DrawViewDataSupplier getData() {
		return theData;
	}
	
}
