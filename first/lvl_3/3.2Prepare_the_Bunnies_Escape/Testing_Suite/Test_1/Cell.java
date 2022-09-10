package Testing_Suite.Test_1;


public class Cell {

    boolean isCurrent;
    boolean isWall;
    boolean isStart;
    boolean isEnd;
    boolean visitedByLightGray;
    boolean visitedByDarkGray;
    boolean isPath;
    int i;
    int j;

    Cell(int i, int j){
        this.i = i;
        this.j = j;
    }
    
}
