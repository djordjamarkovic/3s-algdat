package modelcontroller;

import modelcontroller.commands.DrawModelCommand;

public interface DrawModelCommandReceiver {
	
	public void receiveCommand(DrawModelCommand x);
	
	public int getModelNumber();

}
