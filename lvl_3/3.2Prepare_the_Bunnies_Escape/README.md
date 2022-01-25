                                                                                                                                                            !CUI


There are a lot of difficult things about being undercover as Commander Lambda's personal assistant, but you have to say, the personal spa and private hot cocoa bar are pretty awesome.

New challenge "Prepare the Bunnies' Escape" added to your home folder.
Time to solve: 168 hours.


Prepare the Bunnies' Escape
===========================

You're awfully close to destroying the LAMBCHOP doomsday device and freeing Commander Lambda's bunny workers, but once they're free of the work duties the bunnies are going to need to escape Lambda's space station via the escape pods as quickly as possible. Unfortunately, the halls of the space station are a maze of corridors and dead ends that will be a deathtrap for the escaping bunnies. Fortunately, Commander Lambda has put you in charge of a remodeling project that will give you the opportunity to make things a little easier for the bunnies. Unfortunately (again), you can't just remove all obstacles between the bunnies and the escape pods - at most you can remove one wall per escape pod path, both to maintain structural integrity of the station and to avoid arousing Commander Lambda's suspicions. 

You have maps of parts of the space station, each starting at a work area exit and ending at the door to an escape pod. The map is represented as a matrix of 0s and 1s, where 0s are passable space and 1s are impassable walls. The door out of the station is at the top left (0,0) and the door into an escape pod is at the bottom right (w-1,h-1). 

Write a function solution(map) that generates the length of the shortest path from the station door to the escape pod, where you are allowed to remove one wall as part of your remodeling plans. The path length is the total number of nodes you pass through, counting both the entrance and exit nodes. The starting and ending positions are always passable (0). The map will always be solvable, though you may or may not need to remove a wall. The height and width of the map can be from 2 to 20. Moves can only be made in cardinal directions; no diagonal moves are allowed.

Languages
=========

To provide a Python solution, edit solution.py
To provide a Java solution, edit Solution.java

Test cases
==========
Your code should pass the following test cases.
Note that it may also be run against hidden test cases not shown here.

-- Java cases --
Input:  Solution.solution({ {0, 1, 1, 0}, 
                            {0, 0, 0, 1}, 
                            {1, 1, 0, 0},
                            {1, 1, 1, 0}})

Output: 7

Input:  Solution.solution({ {0, 0, 0, 0, 0, 0}, 
                            {1, 1, 1, 1, 1, 0}, 
                            {0, 0, 0, 0, 0, 0}, 
                            {0, 1, 1, 1, 1, 1}, 
                            {0, 1, 1, 1, 1, 1}, 
                            {0, 0, 0, 0, 0, 0}})
Output: 11



# Mine-

##  == Basic map == 
#### - Testing_Suite (coding ideas and notes)
    - Test_1 (Up and running visual maze - code not cleaned)
        - contains visuals
        - random maze generator (Maze_Generator)
        - uncleaned solution
#####        - Test_Maze_Visuals ( Testing basic Jpanel setup. Get it to Display a maze)

#### - Theory (basic notes about Graph_Theory, BackTracking, and Pathfinding noes i took while reading up on mazes)
    - BackTracking
    - Graph_Theory
    - Pathfinding



# Plan 

## - My Thoughts

    
    I considered solving this by Depth First Search (DFS),
    but while reading up i started considering the possibility 
    that doing (DFS) whith one removable wall would be :

        1) Extreamly inefficient, although i am not entirely sure.
        2) A Pain to both implement and work with.
    
    Thats why i went with Breath First Search (BFS) using a 
    Queue(first in first out), and keep a seperate 
    list for those that have already
    passed through a wall .



## How i plan to implement
FIO = figure it out
- Some of The values might be redundant but 
is there for either clearity while coding, 
or just part of a previous idea i had that i have not removed as of yet.

    Make the Binary Array into a grid of Cells so we can store some data in each:    

    - Cells
        booleans : 
            isCurrent (current cell in algo)
            isWall (FIO)
            isStart (FIO)
            isEnd (FIO)
            visitedByLightGray (light gray is before it passes through a wall)
            visitedByDarkGray (dark gray is after it passes through a wall)
            isPath (if cell is part of most eficcient)
        int : 
            i (what row it is in)
            j (what column it is in)
    - Queue
        - node head
        - node tail
        - basic functionality
    - Solver 
        grid (Cell[][])
        start and end pos
            - Get (^v<>)
            - If not null add to corresponding Queue
                - Light (before going through wall)
                - Dark (after going through wall)

            - Add to corresponding solution HashMap Dictionary
                - Used for backtracking through maze and finding path
                    -       
                         key0 : value0
                         key1 : value1
                         key2 : value2
                         key3 : value3

                        keyX-1 = valueX

                        get value of key
                        and put it back as value as key

                !! However, this will be hard as neither map contains the
                    full path if a wall is crossed.
                    Thats why i scan every Cell when backtracking since if 
                    the current backtracking cell is a wall, then i can send that value to the hashmap of the pre - walljump. That list will contain the fastest way to to 
                    get to that specific Cell. Then add them together

                



