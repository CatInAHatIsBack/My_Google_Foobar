package Testing_Suite;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private static int n;
    private static int[][] dp;
    static List<Integer> max;  
    static List<Integer> fin;
    static int time;
    public static int[] solution(int[][] maze, int time){
        
        fin = new ArrayList<>();
        solutions(maze, time);
        for (int i = 0; i < n; i++){
              if (dp[i][i] < 0) {
                  int[] ret = new int[dp.length -2];
                  for(int j = 0; j < ret.length; j++){
                    ret[j] = j;
                  }
        
                  return ret;
              }
                
            }
        getPath(time);
        int[] fine = new int[fin.size()];
        for(int i = 0; i < fin.size(); i++){
            fine[i] = fin.get(i);
        }
        Arrays.sort(fine);
        return fine;

    }
    static void solutions(int[][] maze, int time){

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
        
          
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(dp[i][j]);
                }
                System.out.println();
            }
        
    
    }
    public static void getPath(int time){
    
        int current = 0;
        int total = 0;
        max = new ArrayList<Integer>();
        recursive(current,max,total, time);
        form();
        }
    public static void recursive(int current,List<Integer> in, int total, int time){
    List<Integer> visited = new ArrayList<>();
    for(int i = 0; i < in.size(); i++){
      visited.add(in.get(i));
    }
    visited.add(current); // adds 0 to start
    
    if(current == dp.length - 1){
      // compare to max
      if(max.size() < visited.size() && total <= time){
        max = visited;
        for(int i = 0; i < max.size(); i++){
          System.out.print(max.get(i) + " ");
        }
        System.out.println();
      }
    }
    for(int i = 0; i < dp.length; i++){
      if(current != i && !visited.contains(i)){
        recursive(i, visited, total + (int)dp[current][i], time);
      }
    }
    
  }
  public static void form(){
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
                        {9, 3, 2, 0, -3}, // bunny 2
                        {9, 3, 2, 2, 0}}; // bulkhead
        int[][] maze2 = {
        {0, 1, 1, 1, 1}, 
        {1, 0, 1, 1, 1}, 
        {1, 1, 0, 1, 1}, 
        {1, 1, 1, 0, 1}, 
        {1, 1, 1, 1, 0}};
        int time = 1;
        solution(maze, time);
    }
}


//public class Solution {
//    public static int[] solution(int[][] times, int times_limit) {
//        // Your code here
//    }
//}