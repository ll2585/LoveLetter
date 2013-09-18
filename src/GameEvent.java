import java.util.EventObject;


public class GameEvent extends EventObject {
	public static enum GameType {
		OpponentPlayed, CardDraw, CardPlayed, GameOver;
	}
	
	private Card c;
	private GameType type;
    
    public GameEvent( Object source, GameType g, Card c ) {
        super( source );
        this.type = g;
        this.c = c;
    }
    
    public Card getCard() {
        return c;
    }
    
    public GameType getType() {
        return type;
    }
    
    public Cards.Type getCardType(){
    	return c.getType();
    }
}
