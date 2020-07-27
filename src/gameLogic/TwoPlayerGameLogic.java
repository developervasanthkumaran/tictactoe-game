package gameLogic;

import logic_wiring.ILogic;

public class TwoPlayerGameLogic implements ILogic {
    @Override
    public boolean evaluate(char[][] grid, char cross) {
        int size = grid.length,col = size-1;
        int pD=0;
        for(int i = 0; i < size; i++) {
            if(grid[i][i]==cross){
                pD+=cross;
            }
        }
        if(pD==cross*size)return true;
        pD =0 ;
        for (int i = 0,k=col; i < size ; i++,k--) {
            if(grid[i][k]==cross)pD+=cross;
        }
        if(pD==cross*size)return true;
        pD=0;
        for (char[] chars : grid) {
            for (int j = 0; j < size; j++) {
                if (chars[j] == cross) {
                    pD+=cross;
                }
            }
            if(pD==cross*size)return true;
            else pD=0;
        }
        pD=0;
        for (int i = 0; i <size ; i++) {
            for (char[] chars : grid) {
                if (chars[i] == cross) {
                    pD+=cross;
                }
            }
            if(pD==cross*size)return true;
            else pD=0;
        }
        return false;
    }

    @Override
    public boolean isValid(char[][] grid) {
        int xCount=0;
        int oCount=0;
        for (char[] chars : grid) {
            for (int j = 0; j < grid.length; j++) {
                if (chars[j] == 'x') xCount++;
                if (chars[j] == 'o') oCount++;
            }
        }
        if(xCount==oCount || xCount == oCount+1){
            return true;
        }
        else return oCount == xCount + 1;
    }

    @Override
    public boolean hasIncompleteStates(char[][] board){
        for (char[] chars : board) {
            for (int i = 0; i <board.length ; i++) {
                if(chars[i]=='-')return true;
            }
        }
    return false;
    }

}
