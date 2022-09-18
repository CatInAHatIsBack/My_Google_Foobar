# def solution(start, queue):
#     return foo(start, queue)
    

# def foo(start, add):
#     skip = 0
#     result = 0 
#     while add > 0:
#         for i in range(0, add):
#             result ^=start 
#             start+=1
#         start+=skip
#         skip+=1
#         add-=1
#     return result 


# # print (solution(17,4))


# num = 0
# for i in range(0, 30):
#     num^=i
#     # print(i)
#     # print(i)
#     print(num) 


def XOR(n):
    val = n % 4
    if val == 0:
        return n
    if val == 1:
        return 1
    if val == 2:
        return n + 1
    return 0

def answer(start, length):
    if length == 1:
        return start
    val = 0
    
    for i in range(length):
        elements = length - i
        front = start + length*(i) - 1
        val = val ^ XOR(front + elements) ^ XOR(front)
        
    return val

s = 18
l = 5
print("answer")
print(answer(s,l))

