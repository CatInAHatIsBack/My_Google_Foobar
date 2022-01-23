package Build_Maze;

public class Maze_Solver {
    int[][] maze = {
            { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
            { 0, 0, 1, 0, 0, 1, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
            { 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 1, 0, 0, 0, 1, 1 },
    };
    Cell[][] grid;
    int rows;
    int cols;

    int start;
    int end;

    Display_Maze display;
    Maze_Solver(){
        this.rows = maze.length;
        this.cols = maze[0].length;
        this.grid = new Cell[rows][cols];
        
        init();
        printMaze();
        printToFrame();
        display = new Display_Maze(grid);
    }
    public void Solve(){

    }
    public void init(){
        // makes array of Cell class 
        // if maze i,j is 0 is not bomb
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i,j);
                if(maze[i][j] == 0){
                    grid[i][j].isWall = false;
                }
                else{
                    grid[i][j].isWall = true;
                }
                
            }
        }
        printMaze();
        Cell start = grid[0][0];
        start.isWall = false;
        Cell end = grid[rows - 1][cols - 1];
        end.isWall = false;
    }
    //  formatted print
    public void printMaze(){
        System.out.print(" int[][] maze = { \n");
        for(int i = 0; i < rows; i++) {
            System.out.print("{");
            for(int j = 0; j < cols; j++) {
                int cell;
                if(grid[i][j].isWall == false){
                    cell = 0;
                }
                else {
                    cell = 1;
                }
                if(j != cols - 1){
                    System.out.print(cell + ", ");
                }
                else {
                    System.out.print(cell);
                }
            }
            System.out.print("},");
            System.out.println();
        }
        System.out.println("};");
    }
    public void printToFrame(){

    }
    public static void main(String[] args) {
        new Maze_Solver();
        while(true){

        }
    }
}
