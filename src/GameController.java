// stucture/calc-mvc/CalcController.java - Controller
//    Handles user interaction with listeners.
//    Calls View and Model as needed.
// Fred Swartz -- December 2004

import java.awt.event.*;


public class GameController {
    //... The Controller needs to interact with both the Model and View.
    private GameModel m_model;
    private GameView  m_view;
    private boolean cardDrawListenersEnabled;
    private AbstractObserver gameControllerObserver;
    private boolean cardListenersEnabled;
    
    //========================================================== constructor
    /** Constructor */
    GameController(GameModel model, GameView view) {
        m_model = model;
        m_view  = view;
        cardDrawListenersEnabled = false;
        cardListenersEnabled = false;
        m_view.addDeckListener(deckListener());
        gameControllerObserver = new gameControllerObserver();
    	m_model.addObserver(gameControllerObserver);
        //m_view.addCardListener(cardListener(m_model.getPlayers()[0].getCard()));
    }

	private MouseListener deckListener() {
		// TODO Auto-generated method stub
		return new MouseListener() {

	        public void mouseClicked(MouseEvent e) {
	        	if(cardDrawListenersEnabled){
	        		m_model.playerDrawsCard(m_model.getCurrentPlayer(), m_model.getDeck().pop());
	        		//m_view.addDeckListener(deckListener());
	        	}
	        }

	        public void mousePressed(MouseEvent e) {
	        }

	        public void mouseReleased(MouseEvent e) {
	        }

	        public void mouseEntered(MouseEvent e) {
	        }

	        public void mouseExited(MouseEvent e) {
	        }
	    };
	}
	
	private MouseListener cardListener() {
		// TODO Auto-generated method stub
		return new MouseListener() {

	        public void mouseClicked(MouseEvent e) {
	        	if(cardListenersEnabled){
	        		m_model.dispatchNewGameEvent(GameEvent.GameType.CardPlayed, ((CardView) e.getSource()).getCard());
	        		System.out.println("clicked a card");
	        	}
	        }

	        public void mousePressed(MouseEvent e) {
	        }

	        public void mouseReleased(MouseEvent e) {
	        }

	        public void mouseEntered(MouseEvent e) {
	        }

	        public void mouseExited(MouseEvent e) {
	        }
	    };
	}
	public void enableCardSelection() {
		cardListenersEnabled = true;
		m_view.addCardListener(cardListener());
	}
	
	public void disableCardSelection() {
		cardListenersEnabled = false;
	}
	
	public void enableCardDraw() {
		System.out.println("??");
		cardDrawListenersEnabled = true;
	}
	
	public void disableCardDraw() {
		System.out.println("aha");
		cardDrawListenersEnabled = false;
	}
	
	private class gameControllerObserver extends AbstractObserver{
		
		public gameControllerObserver(){
			addEventListener(new GameControllerListener());
		}
		private class GameControllerListener extends GameListener{
	
			@Override
			protected void iDrewCard(GameEvent event) {
				// TODO Auto-generated method stub
				System.out.println("the controller says I drew a card: " + event.getCard().getName());
				enableCardSelection();
				disableCardDraw();

				//cardDrawn();
			}
	
			@Override
			protected void iPlayedCard(GameEvent event) {
				// TODO Auto-generated method stub
				System.out.println("the controller says I played a card: " + event.getCard().getName());
				disableCardSelection();
				disableCardDraw();
				m_model.resumeIPlayed();
			}
	
			@Override
			protected void opponentPlayed(GameEvent event) {
				// TODO Auto-generated method stub
				System.out.println("the controller says He played a card: " + event.getCard().getName());
				enableCardDraw();
				disableCardSelection();
				m_model.resumeOpponentPlayed();
				//opponentPlayedView();
			}

			@Override
			protected void gameOver() {
				// TODO Auto-generated method stub
				disableCardSelection();
				disableCardDraw();
				
			}
	
			
		}
		
	}


}