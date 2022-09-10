package Testing_Suite;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

import java.util.ArrayList;
import java.util.List;

public class Testing_1 {

    private int n;
    
    private int[][] dp;
    List<Integer> max;  
    List<Integer> fin;

    public Testing_1(int[][] maze, int time){
        fin = new ArrayList<>();
        solution(maze, time);
        getPath();
        

    }
    
    void solution(int[][] maze, int time){

        /**
         * for each
         */
        n = maze.length;
        dp = new int[n][n];
        

        // Copy input matrix and setup 'next' matrix for path reconstruction.
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            dp[i][j] = maze[i][j];
          }
        } 
        
        for (int k = 0; k < n; k++) {
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
              if (dp[i][k] + dp[k][j] < dp[i][j]) {
                dp[i][j] = dp[i][k] + dp[k][j];
              }
            }
          }
        }
        // Identify negative cycles by propagating the value 'NEGATIVE_INFINITY'
        // to every edge that is part of or reaches into a negative cycle.
        for (int k = 0; k < n; k++){
          for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
            // checks middle
            // sets to neg inf
              if (dp[i][k] != POSITIVE_INFINITY && dp[k][j] != POSITIVE_INFINITY && dp[k][k] < 0) {
                dp[i][j] = (int) NEGATIVE_INFINITY;
              }

            }
            }
        }
        
    
    }
    public void getPath(){
    
        int current = 0;
        int total = 0;
        max = new ArrayList<Integer>();
        recursive(current,max,total);
        form();
        }
    public void recursive(int current,List<Integer> in, int total){
    List<Integer> visited = new ArrayList<>();
    for(int i = 0; i < in.size(); i++){
      visited.add(in.get(i));
    }
    visited.add(current); // adds 0 to start
    
    if(current == 4){
      // compare to max
      if(max.size() < visited.size() && total <= 1){
        max = visited;
        for(int i = 0; i < max.size(); i++){
          System.out.print(max.get(i) + " ");
        }
        System.out.println();
      }
    }
    for(int i = 0; i < dp.length; i++){
      if(current != i && !visited.contains(i)){
        recursive(i, visited, total + (int)dp[current][i]);
      }
    }
    
  }
  public void form(){
    max.remove(max.size()-1);
    max.remove(0);
    
    for(int i = 0; i < max.size(); i++){
        fin.add(max.get(i)-1);
          System.out.print(fin.get(i) + " ");
        }
        System.out.println();
    
  }



    public static void main(String[] args) {
        int[][] maze = {{0, 2, 2, 2, -1}, // Start
                        {9, 0, 2, 2, -1}, // bunny 0
                        {9, 3, 0, 2, -1}, // bunny 1
                        {9, 3, 2, 0, -1}, // bunny 2
                        {9, 3, 2, 2, 0}}; // bulkhead
        int time = 1;
        Testing_1 test = new Testing_1(maze, time);
        
    }
}
