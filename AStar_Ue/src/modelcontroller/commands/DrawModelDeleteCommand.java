package modelcontroller.commands;

public class DrawModelDeleteCommand extends DrawModelCommand {

	private int index;
	
	public DrawModelDeleteCommand(int i) {
		index = i;
	}

	public int getIndex() {
		return index;
	}
	
}
