package Build_Maze;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class Game_Canvas extends JPanel implements ActionListener{

    Timer timer = new Timer(100, new ActionListener(){
        public void actionPerformed(ActionEvent e){
            repaint();
        }
    });
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

    

    Game_Canvas(Cell[][] grid, int rows, int cols, int resolution){
        this.grid = grid;
        this.rows = rows;
        this.cols = cols;
        this.resolution = resolution;

        this.height = rows * resolution;
        this.width = cols * resolution;

        
        
        this.setPreferredSize(new Dimension(width,height));
        
        start = grid[0][0];
        start.isStart = true;
        end = grid[rows - 1][cols - 1];
        end.isEnd = true;
        //init();
       
    }
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
        g2.setColor(Color.white);
        g2.fillRect(0 ,0, width, height);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.println(" paint");
                // checks if start or end cell;
                if(grid[i][j] != start && grid[i][j] != end){
                    if(grid[i][j].visited == true){
                        g2.setColor(gridColorVisited[0]);
                    }
                    if(grid[i][j].isWall){
                        // grid color black 
                        // is wall & unvisited
                        g2.setColor(gridColor[1]);
                    }
                    else if(!grid[i][j].isWall) {
                        // grid color light blue
                        // is not wall
                        g2.setColor(gridColor[0]);
                    }
                    if(grid[i][j].visited == true){
                        g2.setColor(gridColorVisited[0]);
                    }
                }
                else {
                    if(grid[i][j] == start) {
                        // grid is start 
                        // color green
                        g2.setColor(gridColorStartAndEnd[0]);
                    }
                    else{
                        // grid is end 
                        // color red
                        g2.setColor(gridColorStartAndEnd[1]);
                    }
                }
                g2.fillRect(resolution * j, resolution * i, resolution-1, resolution-1);
            }
        }    
    }
    public void tick(){
        
        //System.out.println("tick working" + " i : " + start.i + " j : " + start.j);
        BFS();
    }
    public void BFS(){
        Cell current;
        Cell currentAfterWall;
        Queue upNext = new Queue();
        Queue upNextAfterWall = new Queue();
        ArrayList<Cell> visited = new ArrayList<Cell>();
        upNext.add(start);
        
        // game loop
        while(!upNext.isEmpty()){
            current = upNext.peek();
            int i = current.i;
            int j = current.j;
            System.out.println("here");

            // still added to Queue if null
            if(getUp(current, i, j) != null){
                if(!grid[i - 1][j].visited){
                    if(grid[i - 1][j].isWall){
                        upNextAfterWall.add(getUp(current, i, j));
                    }
                    else{
                        upNext.add(getUp(current, i, j));
                    }
                    grid[i - 1][j].visited = true;
                }
                
            }
            if(getDown(current, i, j) != null){
                if(!grid[i + 1][j].visited){
                    if(grid[i + 1][j].isWall){
                        upNextAfterWall.add(getDown(current, i, j));
                    }
                    else{
                        upNext.add(getDown(current, i, j));
                    }
                    grid[i + 1][j].visited = true;
                }
            }
            if(getLeft(current, i, j) != null){
                if(!grid[i][j - 1].visited){
                    if(grid[i][j - 1].isWall){
                        upNextAfterWall.add(getLeft(current, i, j));
                    }
                    else{
                        upNext.add(getLeft(current, i, j));
                    }
                    grid[i][j - 1].visited = true;
                }
            }
            if(getRight(current, i, j) != null){
                if(!grid[i][j + 1].visited){
                    if(grid[i][j + 1].isWall){
                        upNextAfterWall.add(getRight(current, i, j));
                    }
                    else{
                        upNext.add(getRight(current, i, j));
                    }
                    grid[i][j + 1].visited = true;
                }
            }
            
            visited.add(current);
            upNext.remove();
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
        }
    }
    public Cell getUp(Cell current, int i, int j){
        if(i == 0){
            return null;
        }
        System.out.println( "Over : i : " + i + ", j : " + j);
        return grid[i - 1][j];
    }
    public Cell getDown(Cell current, int i, int j){
        if(i == this.rows - 1){
            return null;
        }
        System.out.println( "Under: i : " + i + ", j : " + j);
        return grid[i + 1][j];
    }
    public Cell getLeft(Cell current, int i, int j){
        if(j == 0){
            return null;
        }
        System.out.println( "Left: i : " + i + ", j : " + j);
        return grid[i][j - 1];
    }
    public Cell getRight(Cell current, int i, int j){
        if(j == cols - 1){
            return null;
        }
        System.out.println( "Right: i : " + i + ", j : " + j);
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
