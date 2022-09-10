package Testing_Suite.Test_1;

import java.util.Random;


public class Binary_Map {
    int[][] grid;
    int width;
    int height;    
    int probability;

    Random random;
    Binary_Map(int probability,int width, int height){     
        this.width = width;        
        this.height = height;
        this.probability = probability;
        this.grid = new int[height][width];
        
        this.random = new Random();

    }
    // initialise random binary map 
    // Obstacle == 1
    // Safe == 0
    public void initialize(){
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (random.nextInt(probability) != 1) {
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = 1;
                }
            }
        }
    }
    public void printMaze(){
        System.out.print(" int[][] maze = { \n");
        for(int i = 0; i < height; i++) {
            System.out.print("{");
            for(int j = 0; j < width; j++) {
                System.out.print(this.grid[i][j] + ", ");
            }
            System.out.print("},");
            System.out.println();
        }
        System.out.println("};");
    }
}
