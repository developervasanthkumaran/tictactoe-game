package gameLogic;

import logic_wiring.ILogic;

public class SinglePlayerAiLogic implements ILogic {
    private static int maxDepth = 100;
    private int evaluate(char[][] grid){
        int size = grid.length,col = size-1;
        int cross='x',nought='0';
        int xVal=0,oVal=0;

        for(int i = 0; i < size; i++) {
            if(grid[i][i]==cross){
                xVal+=cross;
            }
             if(grid[i][i]==nought)oVal+=nought;
        }
        if(xVal==cross*size)return +10;
        else if(oVal==nought*size)return -10;

        xVal =0;oVal=0 ;

        for (int i = 0,k=col; i < size ; i++,k--) {
            if(grid[i][k]==cross)xVal+=cross;
            if(grid[i][k]==nought)oVal+=nought;
        }
        if(xVal==cross*size)return +10;
        else if(oVal==nought*size)return -10;
        xVal=0;
        oVal=0;
        for (char[] chars : grid) {
            for (int j = 0; j < size; j++) {
                if (chars[j] == cross) {
                    xVal+=cross;
                }
                if(chars[j]==nought)oVal+=nought;
            }
            if(xVal==cross*size)return +10;
            else if(oVal==nought*size)return -10;
            else{ xVal=0;oVal=0;}
        }
        xVal=0;
        for (int i = 0; i <size ; i++) {
            for (char[] chars : grid) {
                if (chars[i] == cross) {
                    xVal+=cross;
                }
                if(chars[i]==nought)oVal+=nought;
            }
            if(xVal==cross*size)return +10;
            else if(oVal==nought*size)return -10;
            else {xVal=0;oVal=0;}
        }
        return 0;
    }


    @Override
    public boolean evaluate(char[][] grid, char player) {
        Move move = findBestMove(grid);
        if(player =='o') {
            grid[move.row][move.col] = 'o';
        }
        int size = grid.length,col = size-1;
        int pD=0;
        for(int i = 0; i < size; i++) {
            if(grid[i][i]==player){
                pD+=player;
            }
        }
        if(pD==player*size)return true;
        pD =0 ;
        for (int i = 0,k=col; i < size ; i++,k--) {
            if(grid[i][k]==player)pD+=player;
        }
        if(pD==player*size)return true;
        pD=0;
        for (char[] chars : grid) {
            for (int j = 0; j < size; j++) {
                if (chars[j] == player) {
                    pD+=player;
                }
            }
            if(pD==player*size)return true;
            else pD=0;
        }
        pD=0;
        for (int i = 0; i <size ; i++) {
            for (char[] chars : grid) {
                if (chars[i] == player) {
                    pD+=player;
                }
            }
            if(pD==player*size)return true;
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

    private int miniMax(char[][] board, int depth, boolean isMax)
    {
        int score = evaluate(board);
        if(depth>maxDepth)
            return score;

        if (score == 10)
            return score;

        if (score == -10)
            return score;

        if (!hasIncompleteStates(board))
            return 0;
        if (isMax)
        {
            int best = -100;
            for (int i = 0; i < board.length; i++)
            {
                for (int j = 0; j < board.length; j++)
                {
                    if (board[i][j]=='-')
                    {
                        board[i][j] = 'x';

                        best = Math.max(best, miniMax(board, depth + 1, true));

                        board[i][j] = '-';
                    }
                }
            }
            return best;
        }
        else
        {
            int best = 100;
            for (int i = 0; i < board.length; i++)
            {
                for (int j = 0; j < board.length; j++)
                {
                    if (board[i][j] == '-')
                    {
                        board[i][j] = 'o';
                        best = Math.min(best, miniMax(board, depth + 1, false));
                        board[i][j] = '-';
                    }
                }
            }
            return best;
        }
    }
    private Move findBestMove(char[][] board)
    {
        int bestVal = -100;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                if (board[i][j] == '-')
                {
                    board[i][j] = 'x';

                    int moveVal = miniMax(board, 0, false);

                    board[i][j] = '-';

                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }


    class Move{
        int row,col;
    }
}
