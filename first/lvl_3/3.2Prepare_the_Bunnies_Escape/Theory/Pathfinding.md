Pathfinding
=================

- Pathfinding or pathing is the plotting, by a computer application, of the shortest route between two points. It is a more practical variant on solving mazes. This field of research is based heavily on Dijkstra's algorithm for finding the shortest path on a weighted graph.
- Pathfinding is closely related to the shortest path problem, within graph theory, which examines how to identify the path that best meets some criteria (shortest, cheapest, fastest, etc) between two points in a large network.
- At its core, a pathfinding method searches a graph by starting at one vertex and exploring adjacent nodes until the destination node is reached, generally with the intent of finding the cheapest route. Although graph searching methods such as a breadth-first search would find a route if given enough time, other methods, which "explore" the graph, would tend to reach the destination sooner. An analogy would be a person walking across a room; rather than examining every possible route in advance, the person would generally walk in the direction of the destination and only deviate from the path to avoid an obstruction, and make deviations as minor as possible.

Algorithms
=================

### A* Aearch Algorithm

Solves for single-pair shortest path using heuristics to try to speed up the search.

### Dijksta's Algorithm - O(n**2) where n is num of vertices
#### - Solves the single-source shortest path problem with non-negative edge weight.Solves si

- Algorithm for finding shortest paths between nodes in a graph.
- "The algorithm exists in many variants. Dijkstra's original algorithm found the shortest path between two given nodes,[6] but a more common variant fixes a single node as the "source" node and finds shortest paths from the source to all other nodes in the graph, producing a shortest-path tree."

    

### Bellman-Ford Algorithm - O(n*e) n is number of vertices and e is edges
#### - Solves the single-source problem if edge weights may be negative.


### Floyd-Warshall Algorithm
#### - Solves all pairs shortest paths

Heuristics
============
## Manhattan distance
    - sym of diff in goals x&y pos
        h = abs(Current_Cell_X - goal.x) + abs(Current_Cell_Y - goal.y)
## Diagonal distance
## Euclidean distance

General
=================

## Shortest path problem
- In graph theory, the shortest path problem is the problem of finding a path between two vertices (or nodes) in a graph such that the sum of the weights of its constituent edges is minimized.
- The problem of finding the shortest path between two intersections on a road map may be modeled as a special case of the shortest path problem in graphs, where the vertices correspond to intersections and the edges correspond to road segments, each weighted by the length of the segment.

### "Shortest-path tree" 
        - Given a connected, undirected graph G, a shortest-path tree rooted at vertex v is a spanning tree T of G, such that the path distance from root v to any other vertex u in T is the shortest path distance from v to u in G.
        - In connected graphs where shortest paths are well-defined (i.e. where there are no negative-length cycles), we may construct a shortest-path tree using the following algorithm:

            1) Compute dist(u), the shortest-path distance from root v to vertex u in G using Dijkstra's algorithm or Bellman–Ford algorithm.
            2) For all non-root vertices u, we can assign to u a parent vertex pu such that pu is connected to u, and that dist(pu) + edge_dist(pu,u) = dist(u). In case multiple choices for pu exist, choose pu for which there exists a shortest path from v to pu with as few edges as possible; this tie-breaking rule is needed to prevent loops when there exist zero-length cycles.
            3) Construct the shortest-path tree using the edges between each node and its parent.
        - The above algorithm guarantees the existence of shortest-path trees. Like minimum spanning trees, shortest-path trees in general are not unique.
        - In graphs for which all edges weights equal one, shortest path trees coincide with breadth-first search trees.
        - In graphs that have negative cycles, the set of shortest simple paths from v to all other vertices do not necessarily form a tree.    




## Links
- https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm