
public class ComputerPlayer extends Player{

	public ComputerPlayer(){
		super();
		isComputer = true;
	}
	
	public void play(){
		System.out.println("The computer plays");
		setSelectedCard(getCard());
	}
	
	
	public String getName(){
		return "comp";
	}
	
}
