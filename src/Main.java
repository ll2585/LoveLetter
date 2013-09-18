import javax.swing.*;

public class Main {
    //... Create model, view, and controller.  They are
    //    created once here and passed to the parts that
    //    need them so there is only one copy of each.
    public static void main(String[] args) {
        Player[] players = {new Player(), new ComputerPlayer()};
    	GameModel      model      = new GameModel(players, 1);
        GameView       view       = new GameView(model);
        GameController controller = new GameController(model, view);
        model.begin();
        view.setVisible(true);
    }
}