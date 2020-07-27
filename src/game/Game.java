package game;

import logic_wiring.ILogic;
import gameLogic.SinglePlayerAiLogic;
import gameLogic.TwoPlayerGameLogic;
import model.Player;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {
    private ILogic iLogic;
    private boolean isMultiPlayer;
    private Player player1;
    private Player player2;
    private char[][] grid;
    public Game(boolean isMultiPlayer,int row,int col){
        setAsMultiPlayer(isMultiPlayer);
        setGameLogic();
        grid = new char[row][col];
        fillGridWithEmptyStates();
    }
   private void setGameLogic(){
        if(isMultiPlayer()){
            iLogic = new TwoPlayerGameLogic();
        }
        else iLogic = new SinglePlayerAiLogic();
    }
    private void fillGridWithEmptyStates(){
        for (char[] chars : grid) {
            for (int i = 0; i <grid.length ; i++) {
                chars[i]='-';
            }
        }
    }

    public void setPlayer1(Player player1) {
            this.player1 = player1;

    }

    public void setPlayer2(Player player2) {
        if(isMultiPlayer()) {
            this.player2 = player2;
        }else throw new IllegalCallerException();
    }

    private boolean isMultiPlayer() {
        return isMultiPlayer;
    }

    private void setAsMultiPlayer(boolean multiPlayer) {
        isMultiPlayer = multiPlayer;
    }

    private boolean isAllSet(){
        if(!isMultiPlayer())return true;
        if((player1!=null && player2!=null)) {
            return player1.isCrossSet() && player2.isNoughtSet()
        || player2.isCrossSet() && player1.isNoughtSet();
        }
    return false;
    }

    public void startGame(){
        if(isAllSet()){
            runGame();
        }
        else System.out.println("set all requirements correctly and try again");
    }

    private void getInput(char[][] board,char c,BufferedReader inputReader){
        try {
            String row = inputReader.readLine().trim();
            String col = inputReader.readLine().trim();
            int i = Integer.parseInt(row);
            int j = Integer.parseInt(col);
            if(board[i][j]=='-')
            {
                board[i][j] = c;
            }
            else {
                System.out.println("oops already filled!! try again with different position");
                getInput(board,c,inputReader);
            }

        }catch (Exception e){e.printStackTrace();}
    }

    private void runGame(){
        int chance=0;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
       do{
               if(iLogic.hasIncompleteStates(grid)) {
                   if (chance == 0) {
                       System.out.println("x turn: ");
                       getInput(grid, 'x', inputReader);
                       if (iLogic.evaluate(grid, 'x')) {
                           System.out.println("x wins");
                           return;
                       }
                       chance = 1;
                   } else {
                       if(!isMultiPlayer()){
                           System.out.println("ai turn: ");
                       }else {
                           System.out.println("o turn: ");
                           getInput(grid, 'o', inputReader);
                       }
                       if (iLogic.evaluate(grid, 'o')) {
                           System.out.println("o wins");
                           return;
                       }
                       chance = 0;
                   }
               }
               else {System.out.println("match tied");return;}
               showBoard(grid);
       }while(iLogic.isValid(grid));
    }

   private void showBoard(char[][] board){
        for (char[] chars : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
