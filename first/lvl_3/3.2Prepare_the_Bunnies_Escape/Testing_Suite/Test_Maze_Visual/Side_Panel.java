package Testing_Suite.Test_Maze_Visual;
import javax.swing.JButton;
import javax.swing.JPanel;
//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Side_Panel extends JPanel{
    int width;
    int height;
    JButton solveButton;
    JButton refreshButton;
    Side_Panel(int width, int height){
        this.width = width;
        this.height = height;
        this.setBackground(Color.white);
        
        refreshButton = new JButton("Refresh");
        refreshButton.setFocusable(false);
        refreshButton.setBackground(Color.white);
        solveButton = new JButton("Solve");
        solveButton.setFocusable(false);
        solveButton.setBackground(Color.white);

        this.add(refreshButton);
        this.add(solveButton);
        //this.setLayout(new BorderLayout(20,20));
        this.setPreferredSize(new Dimension(width, height));
        

    }
    
}
