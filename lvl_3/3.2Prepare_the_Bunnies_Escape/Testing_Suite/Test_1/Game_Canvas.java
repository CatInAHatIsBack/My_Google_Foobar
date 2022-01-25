package Testing_Suite.Test_1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;




public class Game_Canvas extends JPanel implements ActionListener{

    
    // Color of start and end
    Color[] gridColorStartAndEnd = {Color.green, Color.red};

    // grid colors unvisited
    // lightblue is 0 & not wall 
    // black is 1 & wall
    Color[] gridColor = {new Color(0,200,255), Color.black};

    // Colors of current 
    // current and not wall = green
    // current and wall     = blue
    Color[] gridColorCurrent = {Color.orange, Color.blue};

    // Colors of visited
    // visited and not wall = light gray
    // visited and wall     = dark gray
    // if wall already visited 
    // visited and not wall = gray
    Color[] gridColorVisited = {Color.lightGray, Color.darkGray, Color.gray};

    int height;
    int width;
    int rows;
    int cols;
    int resolution;
    
    Cell[][] grid;
    Cell start;
    Cell end;

    ArrayList<Cell> fin;
    ArrayList<Cell> finval;

    ArrayList<Cell> finDark;
    ArrayList<Cell> finvalDark;
    
    public Map<Cell, Cell> solution; 
    public Queue upNext;

    public Map<Cell, Cell> solutionDark; 
    public Queue upNextDark;

    boolean first = true;
    Cell firstDarkCell;
    int finalCount;

    Game_Canvas(Cell[][] grid, int rows, int cols, int resolution){
        this.grid = grid;
        this.rows = rows;
        this.cols = cols;
        this.resolution = resolution;

        this.height = rows * resolution;
        this.width = cols * resolution;

        fin = new ArrayList<Cell>();
        finval = new ArrayList<Cell>();
        finDark = new ArrayList<Cell>();
        finvalDark = new ArrayList<Cell>();
        
        
        this.setPreferredSize(new Dimension(width,height));
        
        start = grid[0][0];
        start.isStart = true;
        end = grid[rows - 1][cols - 1];
        end.isEnd = true;
        // /init();
        solution = new HashMap<Cell, Cell>();
        upNext = new Queue();

        solutionDark = new HashMap<Cell, Cell>();
        upNextDark = new Queue();
        
        upNext.add(start);
    }
    public Timer timer = new Timer(10, new ActionListener(){
        public void actionPerformed(ActionEvent e){
            solve();
        }
    });
    void start(){
        timer.start();
    }
    void stop(){
        timer.stop();
    }
    void reset(){
    }
    @Override
    protected void paintComponent(Graphics g){
        // super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g.create();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                // checks if start or end cell;
                if(grid[i][j].isWall){
                    g2.setColor(gridColor[1]);
                }
                else{
                    g2.setColor(gridColor[0]);
                }
                if(grid[i][j].visitedByLightGray){
                    g2.setColor(gridColorVisited[0]);
                }
                if(grid[i][j].isStart){
                    // is start color blue
                    g2.setColor(gridColorStartAndEnd[0]);
                }
                else if(grid[i][j].isEnd){
                    g2.setColor(gridColorStartAndEnd[1]);
                }
                if(grid[i][j].isPath){
                    g2.setColor(Color.green);
                }
                g2.fillRect(resolution * j, resolution * i, resolution-1, resolution-1);
                g2.setFont(new Font("TimesRoman", Font.BOLD, 20));
                g2.setColor(Color.red);
                g2.drawString(i + " : " + j,resolution * j + 10, resolution * i + 35);
            }
        }    
    }
    public void paintGrid(Graphics2D g2){
        
    }
    public void tick(){
        timer.start();
        //System.out.println("tick working" + " i : " + start.i + " j : " + start.j);
        
    }
    public void solve(){
        BFS();
    }
    /**
        if not null and not visited by light and not wall{

        }
        if not null and not not visited by light and is wall{
            make dark gray
        }
        if not null and is light and not visited by light but visited by dark{
            
        } 
        if not null and is dark and visited by light and not wall{
            dont add
        }
        if not null and is dark and is wall{
            dont add
        }
        if not null and is 

        cell {
            visited by light {
                if is light and visited by light dont add
                if is lightn and visited by dark add
            }
            visited by dark {
                if is dark dont add
            }
            is wall 
        }
      
     */
    public void BFS(){
        Cell current;
        if(!upNext.isEmpty()){
            current = upNext.peek();

            Cell up = getUp(current);
            if(up != null && !up.visitedByLightGray){
                if(!up.isWall){
                    solution.put(up, current);
                    upNext.add(up);
                    up.visitedByLightGray = true;
                    if(up.isEnd){
                        System.out.println();
                        System.out.println("===================");
                        getSolution(up);
                    }
                }
                if(up.isWall && !up.visitedByDarkGray && !up.visitedByLightGray){
                    solution.put(up, current);
                    upNextDark.add(up);
                }
                
            }
            
            Cell down = getDown(current);
            if(down != null && !down.visitedByLightGray){
                if(!down.isWall){
                    solution.put(down, current);
                    upNext.add(down);
                    down.visitedByLightGray = true;
                    if(down.isEnd){
                        System.out.println();
                        System.out.println("===================");
                        getSolution(down);
                    }
                }
                if(down.isWall && !down.visitedByDarkGray && !down.visitedByLightGray){
                    solution.put(down, current);
                    upNextDark.add(down);
                }
            }
            Cell left = getLeft(current);
            if(left!= null && !left.visitedByLightGray){
                if(!left.isWall){
                    solution.put(left, current);
                    upNext.add(left);
                    left.visitedByLightGray = true;
                    if(left.isEnd){
                        System.out.println();
                        System.out.println("===================");
                        getSolution(left);
                     }
                }
                if(left.isWall && !left.visitedByDarkGray && !left.visitedByLightGray){
                    solution.put(left, current);
                    upNextDark.add(left);
                }
            }      
            Cell right = getRight(current);
            if(right != null && !right.visitedByLightGray){
                if(!right.isWall){
                    solution.put(right, current);
                    upNext.add(right);
                    right.visitedByLightGray = true;
                    if(right.isEnd){
                        System.out.println();
                        System.out.println("===================");
                        getSolution(right);
                    }
                }
                if(right.isWall && !right.visitedByDarkGray && !right.visitedByLightGray){
                    solution.put(right, current);
                    upNextDark.add(right);
                }
            }
            

            upNext.remove();
            // game loop
            repaint();
        }
        else{
            for(Map.Entry<Cell,Cell> entry : solution.entrySet()){
                // Cell key = entry.getKey();
                // Cell value = entry.getValue();
                //System.out.println("From : " + key.i + " : " + key.j + "  |  To" + value.i + " : " + value.j);
            }
        }
        if(!upNextDark.isEmpty()){
            current = upNextDark.peek();

            Cell up = getUp(current);
            if(up != null && !up.visitedByLightGray && !up.visitedByDarkGray){
                if(!up.isWall){
                    solutionDark.put(up, current);
                    upNextDark.add(up);
                    up.visitedByDarkGray = true;
                    if(up.isEnd){
                        System.out.println();
                        System.out.println("===================");
                        System.out.println("dark");
                        getSolutionDark(up);
                    }
                }

            }
            Cell down = getDown(current);
            if(down != null && !down.visitedByLightGray && !down.visitedByDarkGray){
                if(!down.isWall){
                    solutionDark.put(down, current);
                    upNextDark.add(down);
                    down.visitedByDarkGray = true;
                    if(down.isEnd){
                        System.out.println();
                        System.out.println("===================");
                        System.out.println("dark");
                        getSolutionDark(down);
                    }
                }
                
            }
            Cell left = getLeft(current);
            if(left != null && !left.visitedByLightGray && !left.visitedByDarkGray){
                if(!left.isWall){
                    solutionDark.put(left, current);
                    upNextDark.add(left);
                    left.visitedByDarkGray = true;
                    if(left.isEnd){
                        System.out.println();
                        System.out.println("===================");
                        System.out.println("dark");
                        getSolutionDark(left);
                    }
                }
            }
            Cell right = getRight(current);
            if(right != null && !right.visitedByLightGray && !right.visitedByDarkGray){
                if(!right.isWall){
                    solutionDark.put(right, current);
                    upNextDark.add(right);
                    right.visitedByDarkGray = true;
                    if(right.isEnd){
                        System.out.println();
                        System.out.println("===================");
                        System.out.println("dark");
                        getSolutionDark(right);
                    }
                }
            }
            upNextDark.remove();

        }
        
    }
    private void getSolutionDark(Cell current){
        
        
        // get value of end in map 
        // put value of ends prev in back in loop
        // until value == start
        current.isPath = true;
        finDark.add(current);
        finvalDark.add(solutionDark.get(current));

        if(solutionDark.get(current).isWall){
            getSolution(solutionDark.get(current));
            
            for( int i = 0; i < finDark.size(); i++){
                
                Cell key = finDark.get(i);
                Cell value = finvalDark.get(i);
                System.out.println("From : " + key.i + " : " + key.j + "  |  To " + value.i + " : " + value.j);
            }
            System.out.println();
            System.out.println(finalCount);
            System.out.println("===================");
            System.out.println("fin dark");
            System.out.println();
        }
        else{
            repaint();
            finalCount++;
            getSolutionDark(solutionDark.get(current));
            
        }
         
    }
    private void getSolution(Cell current){
        
        // get value of end in map 
        // put value of ends prev in back in loop
        // until value == start
        current.isPath = true;
        fin.add(current);
        finval.add(solution.get(current));

        if(solution.get(current) == start){
            for( int i = 0; i < fin.size(); i++){
                Cell key = fin.get(i);
                Cell value = finval.get(i);
                System.out.println("From : " + key.i + " : " + key.j + "  |  To " + value.i + " : " + value.j);
            }
            System.out.println();
            System.out.println("===================");
            System.out.println("fin light");
            System.out.println();
        }
        else{
            repaint();
            finalCount++;
            getSolution(solution.get(current));
        }
         
    }
    
    public Cell getUp(Cell current){
        int i = current.i;
        int j = current.j;
        if(i == 0){
            return null;
        }
        System.out.println( "Over : i : " + (i - 1) + ", j : " + j);
        return grid[i - 1][j];
    }
    public Cell getDown(Cell current){
        int i = current.i;
        int j = current.j;
        if(i == this.rows - 1){
            return null;
        }
        System.out.println( "Under: i : " + (i + 1) + ", j : " + j);
        return grid[i + 1][j];
    }
    public Cell getLeft(Cell current){
        int i = current.i;
        int j = current.j;
        if(j == 0){
            return null;
        }
        System.out.println( "Left: i : " + i + ", j : " + (j - 1));
        return grid[i][j - 1];
    }
    public Cell getRight(Cell current){
        int i = current.i;
        int j = current.j;
        if(j == cols - 1){
            return null;
        }
        System.out.println( "Right: i : " + i + ", j : " + (j + 1));
        return grid[i][j + 1];
    }
    public void showQueeWorking(){
        Queue upNext = new Queue();
        upNext.add(start);
        upNext.add(end);
        upNext.remove();
        System.out.println(upNext.peek().i + " : " + upNext.peek().j);
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}
