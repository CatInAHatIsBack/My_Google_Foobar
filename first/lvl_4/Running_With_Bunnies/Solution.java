import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private static int n;
    private static int[][] dp;
    static List<Integer> max;  

    public static int[] solution(int[][] maze, int time){
        dp = maze;
        max = new ArrayList<Integer>();
        
        solutions(maze, time);
        // checks diagonal
        // if diagonal is negative return all bunnies
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
        int[] fin = form();
        Arrays.sort(fin);
        return fin;
    }
    static void solutions(int[][] maze, int time){
        n = maze.length; 
        for (int k = 0; k < n; k++) {
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
              if (dp[i][k] + dp[k][j] < dp[i][j]) {
                dp[i][j] = dp[i][k] + dp[k][j];
              }
            }
          }
        }    
    }
    public static void getPath(int time){
        int current = 0;
        int total = 0;
        recursive(current,max,total, time);
    }
    // checks to see how many bunnies can be rescued
    public static void recursive(int current,List<Integer> in, int total, int time){

    // new path list
    List<Integer> visited = new ArrayList<>();
    for(int i = 0; i < in.size(); i++){
      visited.add(in.get(i));
    }
    // add current to visited
    visited.add(current); 

      // checks against current longest 
    if(current == dp.length - 1 && max.size() < visited.size() && total <= time){
        max = visited;
    }
    else{
        // call rec on all cells
        for(int i = 0; i < dp.length; i++){
            if(current != i && !visited.contains(i)){
            recursive(i, visited, total + dp[current][i], time);
            }
        }
    }
  }
  public static int[] form(){
    max.remove(max.size()-1);
    max.remove(0);
    int[] fin = new int[max.size()];
    for(int i = 0; i < max.size(); i++){
        fin[i] = (max.get(i)-1);
    }
    return fin;
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
