package Build_Maze;

import java.util.Random;

public class Maze_Generator {

    public Random random; 
    public int probability;
    int width;
    int height;

    Binary_Map binary_Map;

    // Generate Maze
    Maze_Generator(int probability, int width, int height){
        // gets probability of wall

        this.width = width;
        this.height = height;
        this.probability = probability;     
        random = new Random(); 
        init();
    }
    public void init(){
        // init new bin Map of maze obstacles
        this.binary_Map = new Binary_Map(probability ,width, height);
        this.binary_Map.initialize(); 
        this.binary_Map.printMaze();
    }
    
    //=======================================================================//
    //=======================================================================//
    //=========================== Binary Map =========================================//
    
    public static void main(String[] args) {
        // probability(1/x), width, height

        new Maze_Generator(5, 10, 10);
        while(true){

        }
    }
}