package Network_Flow;

import static java.lang.Math.min;

import java.util.*;

public class Testing {

    private static class Edge {
    public int from, to;
    public Edge residual;
    public long flow;
    public final long capacity;

    public Edge(int from, int to, long capacity) {
      this.from = from;
      this.to = to;
      this.capacity = capacity;
    }
    // unneeded exept for printing residual edge to console
    public boolean isResidual() {
      return capacity == 0;
    }

    public long remainingCapacity() {
      return capacity - flow;
    }

    public void augment(long bottleNeck) {
      flow += bottleNeck;
      residual.flow -= bottleNeck;
    }
    
  } 

  private abstract static class NetworkFlowSolverBase {

    // To avoid overflow, set infinity to a value less than Long.MAX_VALUE;
    static final long INF = Long.MAX_VALUE / 2;

    // Inputs: n = number of nodes, s = source, t = sink
    final int n;
    final int source, sink;

    // Indicates whether the network flow algorithm has ran. The solver only
    // needs to run once because it always yields the same result.
    protected boolean solved;

    // The maximum flow. Calculated by calling the {@link #solve} method.
    protected long maxFlow;

    // The adjacency list representing the flow graph.
    protected List<Edge>[] graph;

    /**
     * Creates an instance of a flow network solver. Use the {@link #addEdge} method to add edges to
     * the graph.
     *
     * @param n - The number of nodes in the graph including s and t.
     * @param s - The index of the source node, 0 <= s < n
     * @param t - The index of the sink node, 0 <= t < n and t != s
     */
    public NetworkFlowSolverBase(int n, int source, int sink) {
      this.n = n;
      this.source = source;
      this.sink = sink;
      initializeEmptyFlowGraph();
    }

    // Constructs an empty graph with n nodes including s and t.
    
    private void initializeEmptyFlowGraph() {
      graph = new List[n];
      for (int i = 0; i < n; i++) 
        graph[i] = new ArrayList<Edge>();
    }

    /**
     * Adds a directed edge (and its residual edge) to the flow graph.
     *
     * @param from - The index of the node the directed edge starts at.
     * @param to - The index of the node the directed edge ends at.
     * @param capacity - The capacity of the edge
     */
    // s -> 0, cap   0 -> s, 0
    // set residual to other edge
    public void addEdge(int from, int to, long capacity) {
      Edge e1 = new Edge(from, to, capacity);
      Edge e2 = new Edge(to, from, 0);
      e1.residual = e2;
      e2.residual = e1;
      graph[from].add(e1);
      graph[to].add(e2);
    }

    /**
     * Returns the residual graph after the solver has been executed. This allows you to inspect the
     * {@link Edge#flow} and {@link Edge#capacity} values of each edge. This is useful if you are
     * debugging or want to figure out which edges were used during the max flow.
     */
    public List<Edge>[] getGraph() {
      execute();
      return graph;
    }

    // Returns the maximum flow from the source to the sink.
    public long getMaxFlow() {
      execute();
      return maxFlow;
    }

    // Wrapper method that ensures we only call solve() once
    private void execute() {
      if (solved) return;
      solved = true;
      solve();
    }

    // Method to implement which solves the network flow problem.
    public abstract void solve();
  }
  private static class DinicsSolver extends NetworkFlowSolverBase {

    private int[] level;

    /**
     * Creates an instance of a flow network solver. Use the {@link #addEdge} method to add edges to
     * the graph.
     *
     * @param n - The number of nodes in the graph including source and sink nodes.
     * @param s - The index of the source node, 0 <= s < n
     * @param t - The index of the sink node, 0 <= t < n, t != s
     */
    public DinicsSolver(int n, int source, int sink) {
      super(n + 2, source, sink);
      level = new int[n + 2];
    }

    @Override
    public void solve() {
      // next[i] indicates the next edge index to take in the adjacency list for node i. This is
      // part
      // of the Shimon Even and Alon Itai optimization of pruning deads ends as part of the DFS
      // phase.
      int[] next = new int[n];

      while (bfs()) {
        Arrays.fill(next, 0);
        // Find max flow by adding all augmenting path flows.
        for (long f = dfs(source - 1, next, INF); f != 0; f = dfs(source - 1, next, INF)) {
          maxFlow += f;
        }
      }
    }

    // Do a BFS from source to sink and compute the depth/level of each node
    // which is the minimum number of edges from that node to the source.
    private boolean bfs() {
      Arrays.fill(level, -1);
      Deque<Integer> q = new ArrayDeque<>(n);
      q.offer(source - 1);
      level[source - 1] = 0;
      while (!q.isEmpty()) {
        int node = q.poll();
        for (Edge edge : graph[node]) {
          long cap = edge.remainingCapacity();
          if (cap > 0 && level[edge.to] == -1) {
            level[edge.to] = level[node] + 1;
            q.offer(edge.to);
          }
        }
      }
      // Return whether we were able to reach the sink node.
      return level[sink - 1] != -1;
    }

    private long dfs(int at, int[] next, long flow) {
      if (at == sink - 1) return flow;
      final int numEdges = graph[at].size();
      // if it hits a dead end or it can use that edge it itterates so you dont have to check each path that has already been 
      for (; next[at] < numEdges; next[at]++) {
        Edge edge = graph[at].get(next[at]);
        long cap = edge.remainingCapacity();
        if (cap > 0 && level[edge.to] == level[at] + 1) {

          long bottleNeck = dfs(edge.to, next, min(flow, cap));
          if (bottleNeck > 0) {
            edge.augment(bottleNeck);
            return bottleNeck;
          }
        }
      }
      return 0;
    }
  }

  public static void main(String[] args) {
    int n, s, t;
    int[] source = {0, 1};
    int[] sink = {4, 5};

    int[][] grid = {
    // a = row
    // b = col
    // c = num of bun
    {0, 0, 4, 6, 0, 0}, 
    {0, 0, 5, 2, 0, 0}, 
    {0, 0, 0, 0, 4, 4}, 
    {0, 0, 0, 0, 6, 6}, 
    {0, 0, 0, 0, 0, 0}, 
    {0, 0, 0, 0, 0, 0}
    };
    
    n = grid.length;
    // putting s and t (single sink and source node at end)
    s = n + 1;
    t = s + 1;

    NetworkFlowSolverBase solver;
    solver = new DinicsSolver(n, s, t);
    init(grid, source, sink, s, t, solver);
    
    

    // Prints: "Maximum flow: 30"
    System.out.printf("Maximum flow: %d\n", solver.getMaxFlow());
  }
  public static void init(int[][] grid, int[] source, int[] sink, int s, int t, NetworkFlowSolverBase solver){
    // turn grid into adj list (from, to, capacity)
    final long INF = Long.MAX_VALUE / 2;

    for(int i = 0; i < grid.length; i++){
        // start node 
        // end node
        // source
        if(contains(source,i)){
            solver.addEdge(s - 1,i,INF);
            // from i to j cap grid[i][j]
            // solver add egde (i, j, grid[i][j])
        }
        if(contains(sink, i)){
            solver.addEdge(i,t - 1,INF);
        }
    }
    for(int i = 0; i < grid.length; i++){
        for(int j = 0; j < grid[0].length; j++){
            if(grid[i][j] != 0){
                solver.addEdge(i,j,grid[i][j]);
            }
        }
    }
  }
  public static boolean contains(int[] array, int row){
    for (int rowIndex : array) {
	    if (rowIndex == row) {
            return true; 
	    }
    }
    return false;
  }
}
