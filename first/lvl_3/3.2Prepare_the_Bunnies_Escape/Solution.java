import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    static Cell[][] grid; 
    static Cell end;
    static Cell start;
    
    static int row;
    static int column;
    
    public static Map<Cell, Cell> solution; 
    public static Queue upNext;

    public static Map<Cell, Cell> solutionDark; 
    public static Queue upNextDark;

    static int stepCount = 0;
    static int lightCount = 1;
    static int darkCount = 0;

    static boolean foundSolution = false;

    public static int solution(int[][] maze){
        // row & col for modularity
        row = maze.length;
        column = maze[0].length;
        grid = new Cell[row][column];

        // Dictionary to keep track of fasteset way to that cell
        // Used to backTrack and find solution
        solution = new HashMap<Cell, Cell>();
        solutionDark = new HashMap<Cell, Cell>();

        // Queue (fifo) 
        upNext = new Queue();
        upNextDark = new Queue();
        
        // Init grid Cells
        init(maze);

        // setting start and end cell
        start = grid[0][0];
        start.makeStart();
        end = grid[row - 1][column - 1];
        end.isEnd = true;

        // BreathFirstSearch
        BFS();

        // counting how many cells have the calue isPath
        countPath();

        return stepCount;
    }

    // =========================================//
    // ============== functions ================//
    // =========================================//
    static void countPath(){
        for (int i = 0; i < row ;i++) {
            for (int j = 0; j < column; j++) {
                if(grid[i][j].isPath){
                    // uncomment to print path
                    // int row = grid[i][j].i;
                    // int column = grid[i][j].j;
                    // System.out.println(row + " : " + column);
                    stepCount++;
                }
            } 
        }
        // System.out.println(stepCount);
    }
    // =========================================//
    // ============== main loop ================//
    static void BFS(){
        Cell current;
        upNext.add(start);
        while(!foundSolution){
            // counts num of light ticks
            int num = lightCount;
            lightCount = 0;
            // light tick
            for(int i = 0; i < num; i++){
                current = upNext.peek();
                tick(current);
            }
            // counts num of dark tick
            num = darkCount;
            darkCount = 0;
            // dark tick
            if(!upNextDark.isEmpty()){
                for(int i = 0; i < num; i++){
                    current = upNextDark.peek();
                    tickDark(current);
                }
            }
        }
    }
    // =========================================//
    // ================= tick ==================//
    static void tickDark(Cell current){
        // gets cells around and evaluates
        List<Cell> cross = getCross(current);
        for (int i = 0; i < cross.size(); i++) {
            evaluateDark(cross.get(i),current, i );
        }
        // removes from queue
        upNextDark.remove();
    }
    static void tick(Cell current){
        // gets cells around and evaluates
        List<Cell> cross = getCross(current);
        for (int i = 0; i < cross.size(); i++) {
            evaluate(cross.get(i),current, i);
        }
        // removes from queue
        upNext.remove();
    }
    // =========================================//
    // =============== Evaluate ================//
    static void evaluate(Cell current, Cell previous, int i){
        if(current != null && !current.visitedByLightGray){
            if(!current.isWall){
                solution.put(current, previous);
                upNext.add(current);
                lightCount++;
                current.visitedByLightGray = true;
                if(current.isEnd){
                    getSolution(current);
                    return;
                }
            }
            if(current.isWall && 
               !current.visitedByDarkGray){
                    solution.put(current, previous);
                    solutionDark.put(current, previous);
                    upNextDark.add(current);
                    darkCount++;
                    current.visitedByDarkGray = true;
            }
        }
    }
    static void evaluateDark(Cell current, Cell previous, int i){
        if(current != null && !current.visitedByLightGray && !current.visitedByDarkGray){
            if(!current.isWall){
                solutionDark.put(current, previous);
                upNextDark.add(current);
                darkCount++;
                current.visitedByDarkGray = true;
                if(current.isEnd){
                    getSolutionDark(current);
                }
            }
        }   
    }
    // ==============================================//
    // =============== get solutions ================//
    private static void getSolutionDark(Cell current){
        foundSolution = true;
        current.isPath = true;
        if(solutionDark.get(current).isWall){
            getSolution(solutionDark.get(current));
            return;
        }
        else{
            getSolutionDark(solutionDark.get(current));
        }
         
    }
    private static void getSolution(Cell current){
        foundSolution = true;
        current.isPath = true;
        if(solution.get(current) == start){
            return;     
        }
        else{
            getSolution(solution.get(current));
        }
    }
    // =========================================//
    // ========== Cross around Cell ============//
    // =========================================//
    static List<Cell> getCross(Cell current){
        List<Cell> cross = new ArrayList<Cell>();
        Cell up = up(current);
            if(up != null){
                cross.add(up);
            }
        Cell down = down(current);
            if(down != null){
                cross.add(down);
            }
        Cell left = left(current);
            if(left != null){
                cross.add(left);
            }
        Cell right = right(current);
            if(right != null){
                cross.add(right);
            }
        return cross;
    }
    // ================ ^v<> ==============//
    static Cell up(Cell current){
        int i = current.i;
        int j = current.j;
        if(i == 0){
            return null;
        }
        return grid[i - 1][j];
    }
    static Cell down(Cell current){
        int i = current.i;
        int j = current.j;
        if(i == row - 1){
            return null;
        }
        return grid[i + 1][j];
    }
    static Cell left(Cell current){
        int i = current.i;
        int j = current.j;
        if(j == 0){
            return null;
        }
        return grid[i][j - 1];
    }
    static Cell right(Cell current){
        int i = current.i;
        int j = current.j;
        if(j == column - 1){
            return null;
        }
        return grid[i][j + 1];
    }
    // ==================== init ===============//
    static void init(int[][] maze) {
        for (int i = 0; i < row ;i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = new Cell(i,j);
                // initialising grid and setting if wall or not
                if(maze[i][j] == 0){
                    grid[i][j].isWall = false;
                }
                else{
                    grid[i][j].isWall = true;
                }
            } 
        }
    }
    // ============= classes ==============//
    // ====================================//
    
    // ============== Cell ================//
    static class Cell{
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
        void makeStart(){
            start.visitedByLightGray = true;
            start.visitedByDarkGray = true;
            start.isStart = true;
            start.isPath = true; 
        }
    }
    // ============ Queue =================//
    static class Queue {
        private static class Node {
            Cell cell;
            private Node next;
            private Node (Cell cell){
                this.cell = cell;
            }
        }
        int len;
        private Node head;
        private Node tail;
        public boolean isEmpty() {
            return head == null;
        }
        public Cell peek(){
            return head.cell;
        }
        public void add(Cell cell){
            Node node = new Node(cell);
            if(tail != null){
                tail.next = node;
            }
            tail = node;
            if(head == null){
                head = node;
            }
        }
        public Cell remove(){
            Cell cell = head.cell;
            head = head.next;
            if(head == null){
                tail = null;
            }
            return cell;
        }
    } 
    // ====================================//
    // ====================================//
    // ====================================//
    public static void main(String[] args) {
        int[][] maze = {
            { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
            { 0, 0, 1, 0, 0, 1, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
            { 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1, 0, 1, 0, 1 },
            { 0, 0, 1, 0, 1, 0, 0, 0, 1, 0 },
        };
        int[][] maze2 = {{0, 1, 1, 0},
                         {0, 0, 0, 1},
                         {1, 1, 0, 0},
                         {1, 1, 1, 0}
        };
        int[][] maze3 = {{0, 0, 0, 0, 0, 0}, 
                         {1, 1, 1, 1, 1, 0},
                         {0, 0, 0, 0, 0, 0}, 
                         {0, 1, 1, 1, 1, 1},
                         {0, 1, 1, 1, 1, 1},
                         {0, 0, 0, 0, 0, 0}
        };
        Solution.solution(maze3);
    }
}
