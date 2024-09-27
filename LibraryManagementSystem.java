import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


class Book {
    int id;
    String title;
    String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}

class Library {
    LinkedList<Book> books = new LinkedList<>();
    Queue<String> waitQueue = new LinkedList<>();
    Stack<Book> returnStack = new Stack<>();

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.title + " has been added to the library.");
    }

    // Issue a book to a user
    public void issueBook(String user, int bookId) {
        Book bookToIssue = null;
        for (Book book : books) {
            if (book.id == bookId) {
                bookToIssue = book;
                break;
            }
        }

        if (bookToIssue != null) {
            books.remove(bookToIssue);
            System.out.println("Book issued to " + user + ": " + bookToIssue);
        } else {
            System.out.println("Book is not available. Adding " + user + " to the waitlist.");
            waitQueue.add(user);
        }
    }

    // Return a book to the library
    public void returnBook(Book book) {
        returnStack.push(book);
        books.add(book);
        System.out.println(book.title + " has been returned.");
    }

    // Display all available books in the library
    public void displayAvailableBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Available books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Display users in the wait queue
    public void displayWaitQueue() {
        if (waitQueue.isEmpty()) {
            System.out.println("No users in the waitlist.");
        } else {
            System.out.println("Waitlist: " + waitQueue);
        }
    }

    // Show the order in which books were returned
    public void showReturnOrder() {
        if (returnStack.isEmpty()) {
            System.out.println("No books have been returned yet.");
        } else {
            System.out.println("Books return order:");
            for (Book book : returnStack) {
                System.out.println(book);
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Available Books");
            System.out.println("5. Display Wait Queue");
            System.out.println("6. Show Return Order");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();  // consume newline
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter book author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    sc.nextLine();  // consume newline
                    String user = sc.nextLine();
                    System.out.print("Enter book ID to issue: ");
                    int bookId = sc.nextInt();
                    library.issueBook(user, bookId);
                    break;
                case 3:
                    System.out.print("Enter book ID: ");
                    int returnBookId = sc.nextInt();
                    sc.nextLine();  // consume newline
                    System.out.print("Enter book title: ");
                    String returnTitle = sc.nextLine();
                    System.out.print("Enter book author: ");
                    String returnAuthor = sc.nextLine();
                    library.returnBook(new Book(returnBookId, returnTitle, returnAuthor));
                    break;
                case 4:
                    library.displayAvailableBooks();
                    break;
                case 5:
                    library.displayWaitQueue();
                    break;
                case 6:
                    library.showReturnOrder();
                    break;
                case 7:
                    System.out.println("Exiting the system.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
