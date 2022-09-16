
# list
# my_list = [3,1,4]
# my_list.append(4)
# print(my_list)

# list2d = [[3,1,4], [2]]
# print(list2d)
# # index
# print(list2d[1])

# # adding number
# list2d.append(1)
# print(list2d)
# # adding list
# list2d.append([1,3,5])
# print(list2d)


# def solution():
#     my_list = [3,1,4]
#     my_max = 0

#     def r(list, i, my_max):
#         print(i)
#         if(i < len(my_list)):
#             print(i)
#             check(list, my_max)
#             print(len(my_list))
#             print(my_list[i])
#             r(list.append(my_list[i]), i+1, my_max)
#             r(list, i+1, my_max)
        

#     def check(list, my_max):
#         tot = 0
#         print( list)
#         print("whwh")
#         # if len(list) = 1:
#         # for i in list:
#         #     print(i)
#                 # tot +=
#         print(tot)
#         if tot%3 == 0:
#             my_max = max(tot,my_max)
#     r([0],0, my_max)
# solution()

# mylist = [3,4]
# def app(list):
#     list.append(2)
#     print(list)
#     list.append(mylist[1])
#     print(list)
# app([0])


def check(list):
    tot = 0
    Sum = sum(list)
    if Sum %3 ==0:
        list.sort(reverse=True)
        for i in list: 
            tot *= 10
            tot += i
    # print(tot)
    return tot
    
def solution(my_list):
    my_max = [0]
    def r(list, i ):
        num = check(list)
        my_max[0] = max(num, my_max[0])
        # print("my num")
        # print(my_max[0])
        if i >= len(my_list):
            # print("to long")
            # print(i)
            return
        r(list,i+1 )
        newlist= list.copy()
        newlist.append(my_list[i])
        r(newlist,i+1)
    
    r([],0 )
    # print("my num is")
    # print(my_max[0])

solution([3,2,1])

print()
# check([3,6,0])

