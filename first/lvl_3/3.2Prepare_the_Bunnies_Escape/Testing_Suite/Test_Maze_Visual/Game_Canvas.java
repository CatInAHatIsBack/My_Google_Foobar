package Testing_Suite.Test_Maze_Visual;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class Game_Canvas extends JPanel implements MouseListener{   // mouselistener to get x,y of mouse

    int width;
    int height;
    Cell[][] grid;
    int resolution = 30;
    int rows;
    int cols;
    Game_Canvas(int width, int height){
        this.height = height;
        this.width = width;
        this.rows = height/resolution;
        this.cols = width/resolution;
        this.grid = new Cell[rows][cols];

        this.setPreferredSize(new Dimension(width, height));
        this.addMouseListener(this);
        //this.setBackground(Color.BLUE);
        
        init();
    }
    @Override
    protected void paintComponent(Graphics g){
        //super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j].isStart){
                    g2.setColor(Color.green);
                }
                else if(grid[i][j].isEnd){
                    g2.setColor(Color.red);
                }
                else if(grid[i][j].wall == false){
                    g2.setColor(Color.white);
                }
                else{
                    g2.setColor(Color.black);
                }
                g2.fillRect(resolution * j, resolution * i, resolution-1, resolution-1);
            }
        }    
    }
    
    public void init(){
        // initialize empty grid of cells
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                grid[i][j] = new Cell(i,j);
                if(i == 0 && j == 0){
                    grid[i][j].isStart = true;
                }
                if(i == rows-1 && j == cols-1){
                    grid[i][j].isEnd = true;
                }
               
            }
        }

    }
    public int getRow(Point p){
        return ((int)(p.getY()/resolution));
    }
    public int getColumn(Point p){
        return ((int)(p.getX()/resolution));
    }
    public void getCell(Point p){
        int column = getColumn(p);
        int row = getRow(p);
        System.out.println(" row : " + row + " column : " + column);
    }
    public void makeWall(Point p){
        int row = getRow(p);
        int column = getColumn(p);
        grid[row][column].makeWall();
        System.out.println(" row : " + row + " column : " + column);
    }


    public void solve(){

    }
    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    public void mousePressed(MouseEvent e) {
        Point p = new Point(e.getPoint());
        if(SwingUtilities.isRightMouseButton(e)){
            getCell(p);
        }
        if(SwingUtilities.isLeftMouseButton(e)){
            makeWall(p);
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
}
