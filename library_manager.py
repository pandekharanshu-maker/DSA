# Program to manage library books and members
n = int(input("Enter the number of books: "))
books = []
borrowed_books = []
cnt = 0
for i in range(n):
    book = input("Enter the name of book {}: ".format(i + 1))
    books.append(book)
    borrowed_books.append(
        int(input("Enter the number of borrowed books for {}: ".format(book)))
    )
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
average = sum(borrowed_books) / len(borrowed_books)
max_borrowed = max(borrowed_books)
min_borrowed = min(borrowed_books)
a = borrowed_books.index(max_borrowed)
b = borrowed_books.index(min_borrowed)
max_borrowed_book = books[a]
min_borrowed_book = books[b]
print("Average number of borrowed books: {:.2f}".format(average))
print("Book with maximum borrowed copies: '{}' with {} copies.".format(
    max_borrowed_book, max_borrowed
))
print("Book with minimum borrowed copies: '{}' with {} copies.".format(
    min_borrowed_book, min_borrowed
))
print("Total number of books that have been borrowed at least once: {}".format(cnt))
print("Frequency of borrowed books:")
for i in range(len(borrowed_books)):
    c = 0
    # Check whether this value was already counted
    already_counted = False
    for j in range(i):
        if borrowed_books[i] == borrowed_books[j]:
            already_counted = True
            break
    if not already_counted:
        for j in range(len(borrowed_books)):
            if borrowed_books[i] == borrowed_books[j]:
                c += 1
        print("{} borrowed books: {} book(s)".format(
            borrowed_books[i], c
        ))
