package Test_Maze_Visual;
public class Cell {
    boolean wall;
    boolean isStart;
    boolean isEnd;
    int i;
    int j; 
    
    Cell(int i, int j){
        this.wall = false;
        this.i = i;
        this.j = j;
    }
    public void makeWall(){
        if(this.wall == false){
            this.wall = true;
        }
        else{
            this.wall = false;
        }
    }
}
