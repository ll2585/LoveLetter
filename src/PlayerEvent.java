import java.util.EventObject;


public class PlayerEvent extends EventObject {
	
	private Card c;
	private Player target;
	private Cards.Type guess;
    
    public PlayerEvent( Object source, Card c, Player target ) {
        this(source, c, null, null);
    }
    
    public PlayerEvent( Object source, Card c) {
        this( source, c, null );
    }
    
    public PlayerEvent( Object source, Card c, Player target,  Cards.Type guess) {
    	super( source );
        this.c = c;
        this.target = target;
        this.guess = guess;
    }
    
    public Card getCard() {
        return c;
    }
    
    public Player getTarget() {
        return target;
    }

    public Cards.Type getGuess(){
    	return guess;
    }
    
    public Cards.Type getCardType(){
    	return c.getType();
    }
}
