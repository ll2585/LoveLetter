import java.util.ArrayList;



public class Player extends AbstractObserver{
	private ArrayList<Card> hand;
	private ArrayList<Card> playedCards;
	protected boolean isComputer;
	private Card selectedCard;
	
	public Player(){
		hand = new ArrayList<Card>();
		playedCards = new ArrayList<Card>();
		addEventListener(new PlayerListener());
		isComputer = false;
		selectedCard = null;
	}
	
	public boolean isComputer(){
		return isComputer;
	}
	
	public void setSelectedCard(Card c){
		selectedCard = c;
	}
	
	public void drawCard(Card c){
		hand.add(c);
	}
	
	public void playCardAt(int i){
		if(i < 0 || i > hand.size()){
			System.out.println("ERROR!!");
		}
		playedCards.add(hand.get(i));
		hand.remove(i);
	}
	
	public Card playedCard(){
		return playedCards.get(playedCards.size()-1);
	}
	
	public Card getCard(){
		return hand.get(0);
	}

	public Card getOtherCard() {
		return hand.get(1);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getPlayedCards() {
		return playedCards;
	}
	

	public void playCard(GameEvent event) {
		// TODO Auto-generated method stub
		for(int i = 0; i < hand.size(); i++){
			Card c = hand.get(i);
			if(c.getName().equals(event.getCard().getName())){
				playCardAt(i);
				return;
			}
		}
	}
	
	public void play(){
		
	}
	
	public String getName(){
		return "human";
	}
	
	public PlayerEvent getEvent(){
		PlayerEvent e = new PlayerEvent(this, selectedCard);
		return e;
	}
	
	private class PlayerListener extends GameListener{

		@Override
		protected void iDrewCard(GameEvent event) {
			// TODO Auto-generated method stub
			System.out.println("I drew a card: " + event.getCard().getName());
		}

		@Override
		protected void iPlayedCard(GameEvent event) {
			// TODO Auto-generated method stub
			System.out.println("I played a card: " + event.getCard().getName());
			playCard(event);
		}

		@Override
		protected void opponentPlayed(GameEvent event) {
			// TODO Auto-generated method stub
			System.out.println("He played a card: " + event.getCard().getName());
		}

		@Override
		protected void gameOver() {
			// TODO Auto-generated method stub
			
		}

		
	}

}
