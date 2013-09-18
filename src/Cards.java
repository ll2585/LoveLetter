import java.util.ArrayList;


public class Cards {
	public static ArrayList<Card> deck = new ArrayList<Card>();
	public static final int[] cardCounts = {5, 2, 2, 2, 2, 1, 1, 1};
	public enum Type {
		Guard, Priest, Baron, Handmaid, Prince, King, Countess, Princess, Back;
		
	}
	public static final Card guard;
	public static final Card priest;
	public static final Card baron;
	public static final Card handmaid;
	public static final Card prince;
	public static final Card king;
	public static final Card countess;
	public static final Card princess;
	
	static {
		deck.add(guard = new CardImpl.Builder(Cards.Type.Guard, 1).build());
		deck.add(priest = new CardImpl.Builder(Cards.Type.Priest, 1).build());
		deck.add(baron = new CardImpl.Builder(Cards.Type.Baron, 1).build());
		deck.add(handmaid = new CardImpl.Builder(Cards.Type.Handmaid, 1).build());
		deck.add(prince = new CardImpl.Builder(Cards.Type.Prince, 1).build());
		deck.add(king = new CardImpl.Builder(Cards.Type.King, 1).build());
		deck.add(countess = new CardImpl.Builder(Cards.Type.Countess, 1).build());
		deck.add(princess = new CardImpl.Builder(Cards.Type.Princess, 1).build());
	}
}
