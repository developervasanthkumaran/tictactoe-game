package model;

public class Player {
    private boolean isCrossSet,isNoughtSet;
    private char nought;
    private char cross;

    public char getNought() {
        return nought;
    }

    public void setNought() {
        isNoughtSet = true;
        this.nought = 'o';
    }

    public char getCross() {
        return cross;
    }

    public void setCross() {
        isCrossSet =true;
        this.cross = 'x';
    }

    public boolean isCrossSet() {
        return isCrossSet;
    }

    public boolean isNoughtSet() {
        return isNoughtSet;
    }

}
