import game.Game;
import model.Player;

public class TicTacToe {

     void singlePlayer(){
        Game game = new Game(false,3,3);
        Player p1 = new Player();
        p1.setCross();
        game.setPlayer1(p1);
        game.startGame();
    }

     void multiPlayer(){
        Game game = new Game(true,3,3);
        Player p1 = new Player();
        p1.setCross();
        Player p2 = new Player();
        p2.setNought();
        game.setPlayer1(p1);
        game.setPlayer2(p2);
        game.startGame();
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.singlePlayer();
    }
}
