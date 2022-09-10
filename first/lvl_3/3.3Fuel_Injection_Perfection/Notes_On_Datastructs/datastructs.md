# Linear data struct

## Arrays
    - Homogenous elements
    - Contiguos locations
    - Acces time: O(1)
    - Search Time: O(n) for sequential
                    O(log n) for bin search if sorted
    - Insertion time: O(n)
    - Deletion Time: O(n)

## Linked list
    - Singly linked list
        1 -> 2 -> 3 -> Null
    - Doubly linked list
        Null <- 1 <-> 2 <-> 3 -> Null
    - Circular linked list
        1 -> 2 -> 3 -> 1

Accessing time of an element : O(n)
Search time of an element : O(n)
Insertion of an Element : O(1) [If we are at the position 
                                where we have to insert 
                                an element] 
Deletion of an Element : O(1) [If we know address of node
                               previous the node to be 
                               deleted] 
                               
    - One big drawback of a linked list is, random access is not allowed. With arrays, we can access i’th element in O(1) time. In the linked list, it takes Θ(i) time. 

### Stack
    - A stack or LIFO (last in, first out)
    - Operations: push, which adds an element to the collection, and pop, which removes the last element that was added. 

use cases
    - Maintaining function calls
    - Reversing a word
    - ex : undo uperation 

Insertion : O(1)
Deletion :  O(1)
Access Time : O(n) [Worst Case]
Insertion and Deletion are allowed on one end. 

### Queue
    - A queue or FIFO (first in, first out)
    - 

Insertion : O(1)
Deletion  : O(1)
Access Time : O(n) [Worst Case]

