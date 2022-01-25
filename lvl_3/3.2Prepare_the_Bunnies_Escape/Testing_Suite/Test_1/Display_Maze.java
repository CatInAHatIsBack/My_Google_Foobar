package Testing_Suite.Test_1;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Display_Maze extends JFrame implements MouseListener{

    
    Cell[][] grid; 
    
    int rows;
    int cols;
    int resolution = 60;
    

    Game_Canvas canvas;
    Display_Maze(Cell[][] grid){
        this.grid = grid;
        this.rows =grid.length;
        this.cols = grid[0].length;
        

        this.canvas = new Game_Canvas(grid, rows, cols, resolution);
        
        this.addMouseListener(this); 
        this.add(canvas);
        this.setVisible(true);
        this.pack();
         
        init();

    }
        // for (int i = 0; i < this.height; i++) {
        //     for (int j = 0; j < this.width; j++) {

        //     }
        // }
 
    public void init(){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
            }
        }
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

    @Override
    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e) 
           || SwingUtilities.isLeftMouseButton(e)) {
            canvas.tick();
            canvas.start();
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}
