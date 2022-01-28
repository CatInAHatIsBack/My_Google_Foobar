package Test_Visuals;

import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Side_Panel extends JPanel{
    int height;
    int width;
    JButton addOne;
    JButton minOne;
    JButton divTwo;
    JButton reset;
    Side_Panel(int width, int height){

        this.height = height;
        this.width = width;
        
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLUE); 
    }
}
