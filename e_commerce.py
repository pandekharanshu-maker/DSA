#Program to manage e-commerce products 
cust_ID = int(input("Enter the customer ID: "))
IDs_List = [120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130]
import math as m

#Linear search to check if the customer ID exists in the list
for i in range(len(IDs_List)):
    if IDs_List[i] == cust_ID:
        print("Customer ID {} exists in the list.".format(cust_ID))
        break
else:
    print("Customer ID {} does not exist in the list.".format(cust_ID))

#Binary search to check if the customer ID exists in the list
IDs_List.sort()  # Ensure the list is sorted for binary search
low = 0 
high = len(IDs_List) - 1
while low <= high:
    mid = m.floor((low + high) / 2)
    if IDs_List[mid] == cust_ID:
        print("Customer ID {} exists in the list (Binary Search).".format(cust_ID))
        break
    elif IDs_List[mid] < cust_ID:
        low = mid + 1
    else:
        high = mid - 1
else:
    print("Customer ID {} does not exist in the list (Binary Search).".format(cust_ID))    