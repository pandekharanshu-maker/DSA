#Program to manage library books and members
n = int(input("Enter the number of books: "))
books = []
borrowed_books = []
cnt = 0
for i in range(n):
    book = input("Enter the name of book {}: ".format(i + 1))
    books.append(book)
    borrowed_books.append(int(input("Enter the number of borrowed books for {}: ".format(book))))
    if borrowed_books[i] > 0:
        cnt += 1   
    count = 0
    for j in range(i):
        if books[i] == books[j]:
            count += 1
    if count > 0:
        print("The book '{}' has already been entered.".format(books[i]))
        books.pop()
        borrowed_books.pop()
        i -= 1        
average = sum(borrowed_books) / n
x = max(borrowed_books)
y = min(borrowed_books)
a = borrowed_books.index(x)
b = borrowed_books.index(y)
max_borrowed_book = books[a]
min_borrowed_book = books[b]
print("Average number of borrowed books: {:.2f}".format(average))
print("Book with maximum borrowed copies: '{}' with {} copies.".format(max_borrowed_book, x))
print("Book with minimum borrowed copies: '{}' with {} copies.".format(min_borrowed_book, y))
print("Total number of books that have been borrowed at least once: {}".format(cnt))    
print("Frequency of borrowed books: {}".format(len([book for book in books if book in borrowed_books])))