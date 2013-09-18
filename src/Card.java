
public interface Card {

	int getPoints();
	String getDescription();
	CardImpl instantiate();
	Cards.Type getType();
	String getName();
	
}
