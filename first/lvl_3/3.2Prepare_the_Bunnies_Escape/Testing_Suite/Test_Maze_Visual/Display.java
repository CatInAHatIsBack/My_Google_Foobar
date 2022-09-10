package Testing_Suite.Test_Maze_Visual;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class Display extends JFrame implements ActionListener{

    Side_Panel side_Panel;
    Game_Canvas game_Canvas;
    int width = 1000;
    int height = 600;
    
    Display(){

        side_Panel = new Side_Panel(2*width/10, height);
        game_Canvas = new Game_Canvas(8 * width / 10, height);

        this.add(side_Panel, BorderLayout.WEST);
        this.add(game_Canvas, BorderLayout.EAST);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == side_Panel.refreshButton){
            this.remove(game_Canvas);
            game_Canvas = new Game_Canvas(8 * width / 10, height);
            this.add(game_Canvas);
            SwingUtilities.updateComponentTreeUI(this);
        } 
        if(e.getSource() == side_Panel.solveButton){
            game_Canvas.solve();
            SwingUtilities.updateComponentTreeUI(this);
        } 
        
    }
    
    
}
