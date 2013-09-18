// structure/calc-mvc/CalcView.java - View component
//    Presentation only.  No user actions.
// Fred Swartz -- December 2004

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class GameView extends JFrame {
    //... Constants
    private static final String INITIAL_VALUE = "1";
    
    //... Components
    private JTextField m_userInputTf = new JTextField(5);
    private JTextField m_totalTf     = new JTextField(20);
    private JButton    m_multiplyBtn = new JButton("Multiply");
    private JButton    m_clearBtn    = new JButton("Clear");
    public final Coordinates topleft = new Coordinates(100,100);
    
    private GameModel m_model;
    private CardView deckLabel;
    private ArrayList<CardView> playerCardsCardView;
    private ArrayList<CardView> opponentCardsCardView;
    private ArrayList<CardView> playerPlayedCardsCardView;
    
    public static final Map<Cards.Type, String> imageMap;
    
	private AbstractObserver gameObserver;
	
    static
    {
    	imageMap = new HashMap<Cards.Type, String>();
    	imageMap.put(Cards.Type.Guard, "guard.jpg");
    	imageMap.put(Cards.Type.Priest, "priest.jpg");
    	imageMap.put(Cards.Type.Baron, "baron.jpg");
    	imageMap.put(Cards.Type.Handmaid, "handmaid.jpg");
    	imageMap.put(Cards.Type.Prince, "prince.jpg");
    	imageMap.put(Cards.Type.King, "king.jpg");
    	imageMap.put(Cards.Type.Countess, "countess.jpg");
    	imageMap.put(Cards.Type.Princess, "princess.jpg");
    	imageMap.put(Cards.Type.Back, "deck.jpg");
    }
    
    private final String playerCards = "playerCards";
    private final String opponentCards = "opponentCards";
    private final String playerPlayedCards = "playerPlayedCards";
    

    
    private JPanel gamePanel;
    private JPanel opponentPlayedCards;
    private JPanel deckPanel;
    private JPanel myPlayedPanel;
    private JPanel myHand;
    
    private Map<String, JPanel> panelMap;
    private Map<String, ArrayList<CardView>> cardMap;

    //======================================================= constructor
    /** Constructor */
    public  GameView(GameModel model) {/*
        //... Set up the logic
        m_model = model;
        m_model.setValue(INITIAL_VALUE);
        
        //... Initialize components
        m_totalTf.setText(m_model.getValue());
        m_totalTf.setEditable(false);
        
        //... Layout the components.      
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Input"));
        content.add(m_userInputTf);
        content.add(m_multiplyBtn);
        content.add(new JLabel("Total"));
        content.add(m_totalTf);
        content.add(m_clearBtn);*/
    	m_model = model;
    	gameObserver = new GameViewObserver();
    	m_model.addObserver(gameObserver);
    	setUpGamePanel();
    	setUpPanelMap();
		drawDeck();
		cardDrawn();
		
        setTitle("My Empty Frame");
		setSize(1000,800); // default size is 0,0
        /*
        //... finalize layout
        this.setContentPane(content);
        this.pack();
        
        this.setTitle("Simple Calc - MVC");
        // The window closing event should probably be passed to the 
        // Controller in a real program, but this is a short example.*/
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
	public AbstractObserver getGameViewObserver() {
		return gameObserver;
	}
    
    private void setUpPanelMap() {
    	panelMap = new HashMap<String, JPanel>();
    	panelMap.put(playerCards, myHand);
    	panelMap.put(opponentCards, opponentPlayedCards);
    	panelMap.put(playerPlayedCards, myPlayedPanel);
    	
    	cardMap = new HashMap<String, ArrayList<CardView>>();
    	cardMap.put(playerCards, playerCardsCardView);
    	cardMap.put(opponentCards, opponentCardsCardView);
    	cardMap.put(playerPlayedCards, playerPlayedCardsCardView);
	}
    
	private void setUpGamePanel() {
		gamePanel = getGamePanel();
    	opponentPlayedCards = getOpponentPanel();
		deckPanel = getDeckPanel();
		myPlayedPanel = getMyPanel();
		myHand = getHandPanel();
		gamePanel.add(opponentPlayedCards);
		gamePanel.add(deckPanel);
		gamePanel.add(myPlayedPanel);
		gamePanel.add(myHand);
		add(gamePanel);
	}

	private JPanel getHandPanel() {
    	JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new FlowLayout());
    	return panel;
	}

	private JPanel getMyPanel() {
    	JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new FlowLayout());
    	return panel;
	}

	private JPanel getDeckPanel() {
    	JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new FlowLayout());
    	return panel;
	}

	private JPanel getOpponentPanel() {
    	JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new FlowLayout());
    	return panel;
	}

	private void drawDeck() {
    	Card c = new CardImpl.Builder(Cards.Type.Back, 0).build();
    	deckLabel = new CardView(c);
    	deckPanel.add(deckLabel);
	}

    void addDeckListener(MouseListener mal) {
    	deckLabel.addMouseListener(mal);
    }
    
    void addCardListener(MouseListener mal) {
    	ArrayList<CardView> temp = cardMap.get(playerCards);
    	for(int i = 0; i < temp.size(); i++){
    		temp.get(i).addMouseListener(mal);
    	}
    }

    


	private JPanel getGamePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    	return panel;
	}

    
    void reset() {
        m_totalTf.setText(INITIAL_VALUE);
    }
    
    String getUserInput() {
        return m_userInputTf.getText();
    }
    
    void setTotal(String newTotal) {
        m_totalTf.setText(newTotal);
    }
    
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }


	public void refresh() {

		revalidate();
		repaint();
	}
	
	public void cardDrawn(){
		setCards(m_model.getPlayers()[0].getHand(), playerCards);
	}
	
	public void cardPlayed(){
		setCards(m_model.getPlayers()[0].getPlayedCards(), playerPlayedCards);
		setCards(m_model.getPlayers()[0].getHand(), playerCards);
	}
	
	public void opponentPlayedView(){
		setCards(m_model.getPlayers()[1].getPlayedCards(), opponentCards);
	}
	


	private void removeLabels(ArrayList<Card> cardsToRemove, String type) {
		ArrayList<CardView> temp = cardMap.get(type);
		for(CardView c:temp){
			panelMap.get(type).remove(c);
		}
	}

	private void drawCards(ArrayList<Card> cardsToDraw, String type) {
	
		ArrayList<CardView> temp = cardMap.get(type);
		for(CardView c:temp){
			panelMap.get(type).add(c);
		}
		refresh();
	}
	
	private void setCards(ArrayList<Card> cardsToSet, String type) {
		ArrayList<CardView> temp = cardMap.get(type);
		if(temp != null){
			removeLabels(cardsToSet, type);
		}
		ArrayList<Card> tempCardHand = cardsToSet;
		temp = new ArrayList<CardView>();
		cardMap.put(type, temp);

		for(Card c:tempCardHand){
			temp.add(new CardView(c));
		}
		drawCards(cardsToSet, type);
	}
	
    

	private class GameViewObserver extends AbstractObserver{
	
		public GameViewObserver(){
			addEventListener(new GameViewListener());
		}
		private class GameViewListener extends GameListener{
	
			@Override
			protected void iDrewCard(GameEvent event) {
				// TODO Auto-generated method stub
				System.out.println("the view says I drew a card: " + event.getCard().getName());
				cardDrawn();
			}
	
			@Override
			protected void iPlayedCard(GameEvent event) {
				// TODO Auto-generated method stub
				System.out.println("the view says I played a card: " + event.getCard().getName());
				cardPlayed();
			}
	
			@Override
			protected void opponentPlayed(GameEvent event) {
				// TODO Auto-generated method stub
				System.out.println("the view says He played a card: " + event.getCard().getName());
				opponentPlayedView();
			}

			@Override
			protected void gameOver() {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(gamePanel, "Game over.");
			}
	
			
		}
	}
}