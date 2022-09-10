# Graph Theory

- Study of graphs in mathematics
- made of of vertices (also called nodes or points) 
- connected by edges (also called links or lines)


## Directed vs Undirected Graphs

### - Undirected Graphs
- An undirected graph is a set of nodes and a set of links between the nodes.
- Each node is called a vertex, each link is called an edge, and each edge connects two vertices.
- The order of the two connected vertices is unimportant.
- An undirected graph is a finite set of vertices together with a finite set of edges. Both sets might be empty, which is called the empty graph.

### - Directed Graphs
- A directed graph is a finite set of vertices together with a finite set of edges. Both sets might be empty, which is called the empty graph.
- Each edge is associated with two vertices, called its source and target vertices.
- We say that the edge connects its source to its target.
- The order of the two connected vertices is important.

#### - Terminology
- Loop: an edge that connects a vertex to itself.
- Path: a sequence of vertices, p0, p1, ..., pm, such that each adjacent pair of vertices  pi and pi+1 are connected by an edge.
- Cycle: a simple path with no repeated vertices or edges other than the starting and ending vertices. A cycle in a directed graph is called a directed cycle.
- Multiple edges: in principle, a graph can have two or more edges connecting the same two vertices in the same direction.
- Simple graphs: the graphs that have no loops and no multiple edges. In fact, many applications require only simple directed graphs or even simple undirected graphs.


## Representing graphs with an ***Adjacency Matrmatrix***

### - Definition
- An adjacency matrix is a square grid of true/false values that represent the edges of a graph.
- If the graph contains n vertices, then the grid contains n rows and n columns.
- For two vertex numbers i and j, the component at row i and column j is true if there is an edge from vertex i to vertex j; otherwise, the component is false.

## Representing graphs with an ***Edge Lists***

### - Definition
- A directed graph with n vertices can be represented by n different linked lists.
- List number i provides the connections for vertex i.
- For each entry j in list number i, there is an edge from i to j.

## Representing graphs with an ***Edge Sets***
- To represent a graph with n vertices, we can declare an array of n sets of integers. For example:
- IntSet[] connections = new IntSet[10]; // 10 vertices
- A set such as connections[i] contains the vertex numbers of all the vertices to which vertex i is connected.

## When to use which
- If the space is available, then an adjacency matrix is easier to implement and is generally easier to use than edge lists or edge sets.

### - Considerations for choosing Representations
1). Adding or removing edges
2). Checking whether a particular edge is present
3). Adding or removing edges

- Both (1) and (2) require only a small constant amount of time with the adjacency matrices.
- Both (1) and (2) require O(n) operations with the edge list representation in the worst case (where n is the number of vertices).
- With edge sets, both (1) and (2) might require O(n) operations -- which could be cut to O(log n) by using a fast set representation. Which one?
- With (3), edge lists and edge sets (O(e), where e is the number of edges that have vertex i as their source) are more efficient than adjacency matrix (O(n)).


## Graph Traversal
**The two most common ways of traversing a grap are :**
    - Breath-first
        - Expand the shallowest unexpanded (unmarked) node.
        - Implementation: queue.
    - Depth-first 
        - Expand the deepest unexpanded (unmarked) node.
        - Implementation: stack or recursion.
        
Note: the algorithm must not enter a repetitive cycle. To prevent this, the algorithm needs to mark each vertex as it is processed.


## Adjacency matrix
- In graph theory and computer science, an adjacency matrix is a square matrix used to represent a finite graph. The elements of the matrix indicate whether pairs of vertices are adjacent or not in the graph.

- In the special case of a finite simple graph, the adjacency matrix is a (0,1)-matrix with zeros on its diagonal. If the graph is undirected (i.e. all of its edges are bidirectional), the adjacency matrix is symmetric. The relationship between a graph and the eigenvalues and eigenvectors of its adjacency matrix is studied in spectral graph theory.

- The adjacency matrix of a graph should be distinguished from its incidence matrix, a different matrix representation whose elements indicate whether vertex–edge pairs are incident or not, and its degree matrix, which contains information about the degree of each vertex.


## - My Takeaway
- In undirected graphs the order is not important
- In directed graphs the order is important
- Choose representation based on expectations of operation frequency



## - Links
- https://en.wikipedia.org/wiki/Pathfinding
- https://www.cpp.edu/~ftang/courses/CS241/notes/graph.htm
- https://en.wikipedia.org/wiki/Glossary_of_graph_theory#Weighted_graphs_and_networks
- https://en.wikipedia.org/wiki/Graph_theory
- https://en.wikipedia.org/wiki/Adjacency_matrix