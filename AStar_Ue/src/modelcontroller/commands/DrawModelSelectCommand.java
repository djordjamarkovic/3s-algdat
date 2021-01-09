package modelcontroller.commands;

public class DrawModelSelectCommand extends DrawModelCommand {

	
private int index;
	
	public DrawModelSelectCommand(int i) {
		index = i;
	}

	public int getIndex() {
		return index;
	}
}
