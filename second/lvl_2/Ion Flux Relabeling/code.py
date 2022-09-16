# class Node:
#     def __init__(self, root=None):
#         self.root = root
#         self.left = self.right = None

# def postorder(height, nums):
#     if height == 1:
#         return Node(nums.pop())
#     node = Node()
#     node.root = nums.pop()
#     node.right = postorder(height-1, nums)
#     node.left = postorder(height-1, nums)
#     return node

# height = 5
# tree = postorder(height, list(range(1, 2 ** height)))

# r = tree
# # print(r.root)
# def bfs(tree):
#     l = []
#     q = [tree]
    
#     while q:
#         curr = q.pop(0)
#         print(curr.root)
#         l.append(curr.root)
#         if curr.left:
#             q.append(curr.left)
#         if curr.right:
#             q.append(curr.right)
#     return l

# print(bfs(tree))

def getPostOrderParent(h, index):

    ## maximum index
    subTree = 2**h - 1
    if subTree <= index:
        return -1
    

    else:
        nodesToLeft = 0
        result = -1 
        
        # left subtree is curr//2 + nodesToLeft
        # right subtree is left + subTree
        while True:
            if subTree == 0:
               return result 
            
            subTree = subTree >> 1
            
            left_node = nodesToLeft + subTree
            
            right_node = left_node + subTree
            
            # right_node + 1 is parent node
            if (left_node == index) or (right_node == index):
                return right_node + 1 
            
            # search right subtree
            if (index > left_node):
                nodesToLeft = left_node
   

def solution(h, q):
    return [ getPostOrderParent(h, n) for n in q ]

print(solution(3,[7,1]))