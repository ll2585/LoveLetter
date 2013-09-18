import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class CardView extends JLabel{

	private Card card;
	
	public CardView(Card c){
		card = c;
		drawCard();
	}

	private void drawCard() {
		// TODO Auto-generated method stub
		try {
			Cards.Type cardType = card.getType();
			BufferedImage myPicture;
			myPicture = ImageIO.read(new File(GameView.imageMap.get(cardType)));
			setIcon(new ImageIcon(myPicture));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public Card getCard(){
		return card;
	}
}
