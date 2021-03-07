package communicationProtocol;

public class IMQRequest extends IMQProtocol {
	private String[] userCommand;
	
	public String[] getUserCommand() {
		return userCommand;
	}
	
	public void setUserCommand(String[] userCommand) {
		this.userCommand=userCommand;
	}
}
