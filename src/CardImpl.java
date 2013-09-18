

public class CardImpl implements Card {
	
	private int points;
	private Cards.Type type;
	private String name;
	private String description = "";
	
	protected CardImpl(Cards.Type type, int points) {
        this.points = points;
        this.type = type;
        this.name = type.toString();
    }
	
	public CardImpl(Builder builder) {
        this(builder.type, builder.points);
        points = builder.points;
    }
	
	public int getPoints() {
		// TODO Auto-generated method stub
		return points;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}
	
	public String getName(){
		return name;
	}
	
	public static class Builder {
	    protected int points;
	    protected Cards.Type type;
	    protected String description = "";

        public Builder(Cards.Type type, int points) {
        	this.points = points;
        	this.type = type;
        }
        
        public Builder description(String val) {
            description = val;
            return this;
        }
        
        public CardImpl build() {
            return new CardImpl(this);
        }

    }
	
	protected CardImpl() {
    }

	public CardImpl instantiate() {
		CardImpl c = new CardImpl();
        copyValues(c);
        return c;
	}
	
	protected void copyValues(CardImpl c) {
        c.type = type;
        c.name = name;
        c.description = description;
    }

	public Cards.Type getType() {
		// TODO Auto-generated method stub
		return type;
	}

	
}
