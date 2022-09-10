package Testing_Suite;

// Import Java's special constants ∞ and -∞ which behave
// as you expect them to when you do arithmetic. For example,
// ∞ + ∞ = ∞, ∞ + x = ∞, -∞ + x = -∞ and ∞ + -∞ = Nan
import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshallSolver {

  private int n;
  private boolean solved;
  private double[][] dp;
  private Integer[][] next;
  List<Integer> max;

  public FloydWarshallSolver(double[][] matrix) {
    n = matrix.length;
    dp = new double[n][n];
    next = new Integer[n][n];

    // Copy input matrix and setup 'next' matrix for path reconstruction.
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] != POSITIVE_INFINITY) next[i][j] = j;
        dp[i][j] = matrix[i][j];
      }
    }
  }

  /**
   * Runs Floyd-Warshall to compute the shortest distance between every pair of nodes.
   *
   * @return The solved All Pairs Shortest Path (APSP) matrix.
   */
  public double[][] getApspMatrix() {
    solve();
    return dp;
  }

  // Executes the Floyd-Warshall algorithm.
  public void solve() {
    if (solved) return;

    // Compute all pairs shortest paths.
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (dp[i][k] + dp[k][j] < dp[i][j]) {
            dp[i][j] = dp[i][k] + dp[k][j];
            next[i][j] = next[i][k];
          }
        }
      }
    }

    // Identify negative cycles by propagating the value 'NEGATIVE_INFINITY'
    // to every edge that is part of or reaches into a negative cycle.
    for (int k = 0; k < n; k++)
      for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
        // checks middle
        // sets to neg inf
          if (dp[i][k] != POSITIVE_INFINITY && dp[k][j] != POSITIVE_INFINITY && dp[k][k] < 0) {
            dp[i][j] = NEGATIVE_INFINITY;
          }
    
    getPath();
    solved = true;
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
          System.out.print(max.get(i) - 1 + " ");
        }
        System.out.println();
    
  }
  // current is start
  // recursive(current, visited)
  // visited [] 
  // add current to visited
  // for all in current row that are not visited && not current
  // put in queue
  // if is end check if length of visited is greater than current max len 
  

 

  public static void main(String[] args) {
    // Construct graph.
    int n = 5;
    double[][] m = {{0, 2, 2, 2, -1}, // Start
                    {9, 0, 2, 2, -1}, // bunny 0
                    {9, 3, 0, 2, -1}, // bunny 1
                    {9, 3, 2, 0, -1}, // bunny 2
                    {9, 3, 2, 2, 0}}; // bulkhead

    FloydWarshallSolver solver = new FloydWarshallSolver(m);
    double[][] dist = solver.getApspMatrix();

    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        System.out.print( " " + dist[i][j]);
      }
      System.out.println();
    }
    
      
      
  }
}