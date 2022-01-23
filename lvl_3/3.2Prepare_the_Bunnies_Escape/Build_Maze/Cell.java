package Build_Maze;


public class Cell {

    boolean isCurrent;
    boolean isWall;
    boolean isStart;
    boolean isEnd;
    boolean visited;
    int i;
    int j;

    Cell(int i, int j){
        this.i = i;
        this.j = j;
    }
    
}
