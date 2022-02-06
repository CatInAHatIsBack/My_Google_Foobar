import static java.lang.Math.min;

import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class Solution {

    // this solution is a modified verison of williamfiset`s dinics algorithm
    static int n, s, t;

    static long maxFlow;
    // The adjacency list representing the flow graph.
    static List<Edge>[] graph;

    static int[] level;
    
    static long INF = Long.MAX_VALUE / 2;

    public static int solution(int[] source, int[] sink, int[][] grid){
        // the extra 2 nodes are super source and super sink 
        n = grid.length + 2;
        // putting s and t (single sink and source node at end)
        s = n - 1;
        t = n;
        level = new int[n];
        initializeEmptyFlowGraph();
        init(grid, source, sink, s, t);
        solve(s,t);
        
        return (int)maxFlow;
    }
    public static void solve(int source, int sink) {
        // next[i] indicates the next edge index to take in the adjacency list for node i. This is
        // part of the Shimon Even and Alon Itai optimization 
        //of pruning deads ends as part of the DFS phase.
        int[] next = new int[n];

        while (bfs(source, sink)) {
            Arrays.fill(next, 0);
            // Find max flow by adding all augmenting path flows.
            for (long f = dfs(source - 1, next, INF, sink); f != 0; f = dfs(source - 1, next, INF, sink)) {
                maxFlow += f;
            }
        }
    }

    // Do a BFS from source to sink and compute the depth/level of each node
    // which is the minimum number of edges from that node to the source.
    static boolean bfs(int source, int sink) {
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

    static long dfs(int at, int[] next, long flow, int sink) {
        if (at == sink - 1) return flow;
        final int numEdges = graph[at].size();
        // if it hits a dead end or it can use that edge it itterates so you dont have to check each path that has already been 
        for (; next[at] < numEdges; next[at]++) {
            Edge edge = graph[at].get(next[at]);
            long cap = edge.remainingCapacity();
            if (cap > 0 && level[edge.to] == level[at] + 1) {

                long bottleNeck = dfs(edge.to, next, min(flow, cap), sink);
                if (bottleNeck > 0) {
                    edge.augment(bottleNeck);
                    return bottleNeck;
                }
            }
        }
        return 0;
    }
    public static void initializeEmptyFlowGraph() {
        graph = new List[n];
        for (int i = 0; i < n; i++) 
            graph[i] = new ArrayList<Edge>();
    }
    public static void addEdge(int from, int to, long capacity) {
        Edge e1 = new Edge(from, to, capacity);
        Edge e2 = new Edge(to, from, 0);
        e1.residual = e2;
        e2.residual = e1;
        graph[from].add(e1);
        graph[to].add(e2);
    }
    public static void init(int[][] grid, int[] source, int[] sink, int s, int t){
        // turn grid into adj list (from, to, capacity)
        
        // adding a super source and super sink
        for(int i = 0; i < grid.length; i++){
            if(contains(source,i)){
                addEdge(s - 1,i,INF);
            }
            if(contains(sink, i)){
                addEdge(i,t - 1,INF);
            }
        }
        // transforming matrix to nodes with edges
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] != 0){
                    addEdge(i,j,grid[i][j]);
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
     
    // ================================================// 
    // ================================================// 
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
        public long remainingCapacity() {
            return capacity - flow;
        }
        public void augment(long bottleNeck) {
            flow += bottleNeck;
            residual.flow -= bottleNeck;
        }
    } 
    public static void main(String[] args) {
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
        int[] source = {0, 1};
        int[] sink = {4, 5};

        System.out.println(solution(source, sink, grid));
    }
}