#Program to demonstrate bubble sorting {Time complexity of bubble sort = O(n^2)}
salary = []
n = int(input("Enter number of elements: "))
is_Swap = False
for x in range (n):
    array = float(input(f"Enter {x+1} element: "))
    salary.append(array)
for i in range (0,n-1):
    for j in range (0,n-i-1):
        if (salary[j] > salary[j+1]):
            salary[j],salary[j+1] = salary[j+1],salary[j]
            is_Swap = True
for f in range (n-1,n-6,-1):
    print(salary[f])            
print("Did Swapping took place (returns True/False): ",is_Swap)
#Selection Sorting {Time complexity of Selection Sort = O(n^2)}
for y in range (0,n-1):
    si = y
    for z in range (y+1,n):
        if (salary[z] < salary[si]):
            si = z
    salary[si],salary[y] = salary[y],salary[si]
for w in range (n-1,n-6,-1):
    print(salary[w])