# Binary Tree, BST, Heap and Hash

### Binary Tree 
    - Hierarchical data struct
    - Bin tree nodes can hold at most 2 children(left and right child)
    - Implemented with links

#### Binary Tree Representation
    - Represented by pointer to topmost node in tree (root)
    - root is null if empty
    - contains : 
        data
        pointer to left child
        pointer to right child

Traversal : 
    - DFT (Depth first traversal)
        In-order(left-root-right)
        Preorder(root-left-right)
        Postorder(left-right-root)

    - BFS (Breadth-First-Traversal)
        Level order traversal 

Maximum number of nodes = 2h + 1 – 1.
Here h is height of a tree. Height is considered 
as the maximum number of edges on a path from root to leaf.

Minimum possible height =  ceil(Log2(n+1)) - 1  

In Binary tree, number of leaf nodes is always one 
more than nodes with two children.

Time Complexity of Tree Traversal: O(n)

Searcing :    
    - Can get it to O(log n) by sorting 
    - Very efficient

Binary Search Tree 
In Binary Search Tree is a Binary Tree with the following additional properties: 
1. The left subtree of a node contains only nodes with keys less than the node’s key. 
2. The right subtree of a node contains only nodes with keys greater than the node’s key. 
3. The left and right subtree each must also be a binary search tree. 

Time Complexities: 

Search :  O(h)
Insertion : O(h)
Deletion : O(h)
Extra Space : O(n) for pointers

h: Height of BST
n: Number of nodes in BST

If Binary Search Tree is Height Balanced, 
then h = O(Log n) 

Self-Balancing BSTs such as AVL Tree, Red-Black
Tree and Splay Tree make sure that height of BST 
remains O(Log n)

BST provides moderate access/search (quicker than Linked List and slower than arrays). 
BST provides moderate insertion/deletion (quicker than Arrays and slower than Linked Lists).


#### Trees
      tree
      ----
       j    <-- root
     /   \
    f      k  
  /   \      \
 a     h      z    <-- leaves   

ex : 

file system
-----------
     /    <-- root
  /      \
...       home
      /          \
   ugrad        course
    /       /      |     \
  ...      cs101  cs112  cs113




## Todo :
    - Binary heap
    - Hashing functions
    - Hash table
    - Collision Handling
    - Chaining
    - Open Addressing