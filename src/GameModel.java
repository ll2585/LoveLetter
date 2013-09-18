
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class GameModel  extends AbstractObservable{
	private Stack<Card> deck;
	private Player[] players;
	private Player curPlayer;
	private boolean gameOver;

    public GameModel(Player[] players, int startPlayerID) {
    	observers = new ArrayList<Observer>();
    	gameOver = false;
        makeDeck();
        this.players = players;
        for(Player p:players) addObserver(p);
        curPlayer = players[startPlayerID];
    }
    
    public void begin(){
        startDeal();
        startGame();
    }

	private void startGame() {
		nextRound();
	}
	

	private void nextRound(){
		playerDrawsCard(curPlayer, deck.pop());
		System.out.println(curPlayer.getName());
		curPlayer.play();
	}

	private void compPlay() {
		// TODO Auto-generated method stub
		curPlayer.playCardAt(0);
		dispatchNewGameEvent(GameEvent.GameType.OpponentPlayed,curPlayer.playedCard());
	}

	private void startDeal() {
		for(int i = 0; i < players.length; i++){
			playerDrawsCard(players[i], deck.pop());
		}
	}
	
	public void playerDrawsCard(Player p, Card c){
		p.drawCard(c);
		if(p.equals(players[0])){
			dispatchNewGameEvent(GameEvent.GameType.CardDraw, c);
		}
	}
	
	public Player[] getPlayers(){
		return players;
	}

	private void makeDeck() {
		// TODO Auto-generated method stub
		deck = new Stack<Card>();
		for(int i = 0; i < Cards.deck.size(); i++){
			for(int j = 0; j < Cards.cardCounts[i]; j++){
				deck.push(Cards.deck.get(i).instantiate());
			}
		}
		Collections.shuffle(deck);
	}
	
	public Card getTopCard(){
		return deck.peek();
	}
	
	public Stack<Card> getDeck(){
		return deck;
	}

	public Player getCurrentPlayer() {
		// TODO Auto-generated method stub
		return curPlayer;
	}
	
	private void checkEnd() {
		// TODO Auto-generated method stub
		System.out.println("the deck size is " + deck.size());
		if(deck.size()==0){
			endGame();
		}
	}

	public void resumeOpponentPlayed() {
		checkEnd();
	}
	
	public void resumeIPlayed() {
		checkEnd();
	}
	
	public synchronized void dispatchcurPlayerEvent() {
    	PlayerEvent e = curPlayer.getEvent();
        notifyObservers(e);
    }
    
    private void endGame() {
		System.out.println("Game over");
		gameOver = true;
		dispatchNewGameEvent(GameEvent.GameType.GameOver, null);
	}

	public synchronized void dispatchNewGameEvent(GameEvent.GameType type, Card c) {
    	GameEvent e = new GameEvent(this, type, c);
        notifyObservers(e);
    }
	
	
    

}