import java.util.EventObject;



public abstract class GameListener implements CustomEventListener
{
	public void eventOccurred(EventObject event) {
		if(GameEvent.class.isInstance(event)){
			GameEvent e = (GameEvent) event;
			if(e.getType().equals(GameEvent.GameType.CardDraw)){
				iDrewCard(e);
			}else if(e.getType().equals(GameEvent.GameType.CardPlayed)){
				iPlayedCard(e);
			}else if(e.getType().equals(GameEvent.GameType.OpponentPlayed)){
				opponentPlayed(e);
			}else if(e.getType().equals(GameEvent.GameType.GameOver)){
				gameOver();
			}
		}else if(PlayerEvent.class.isInstance(event)){
			PlayerEvent e = (PlayerEvent) event;
			System.out.println("player event");
		}
	}

	protected abstract void iDrewCard(GameEvent event);
	protected abstract void iPlayedCard(GameEvent event);
	protected abstract void opponentPlayed(GameEvent event);
	protected abstract void gameOver();
}