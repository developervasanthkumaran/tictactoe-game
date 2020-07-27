package logic_wiring;

public interface ILogic {
    boolean evaluate(char[][] grid,char cross);
    boolean isValid(char[][] grid);
    boolean hasIncompleteStates(char[][] board);
}
