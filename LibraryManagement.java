//import java.util.*;
//
//class LibraryManager {
//    private List<Book> booklist;
//    private List<Person> personList;
//    private List<Loan> loanList;
//
//    public LibraryManager() {
//        booklist = new ArrayList<>();
//        personList = new ArrayList<>();
//        loanList = new ArrayList<>();
//    }
//
//    public List<Book> getBooklist() {
//        return booklist;
//    }
//
//    public void addBook(Book b) {
//        booklist.add(b);
//    }
//
//    public void registerPerson(Person u) {
//        personList.add(u);
//    }
//
//    public void removePerson(Person p) {
//        personList.remove(p);
//    }
//
//    public boolean isAvailable(Book b) {
//        return booklist.contains(b) && b.isAvailable();
//    }
//
//    public int returnLoan(User user, Book b, Date submitDate) {
//        Loan foundLoan = null;
//        int fine = 0;
//
//        for (Loan l : loanList) {
//            if (l.getPerson().equals(user) && l.getBook().equals(b)) {
//                fine += l.calculateFine(submitDate);
//                foundLoan = l;
//                break;
//            }
//        }
//
//        if (foundLoan != null) {
//            b.returnBook();
//            loanList.remove(foundLoan);
//        }
//        return fine;
//    }
//
//    public void issueLoan(User user, Book b, Date d) {
//        Loan l = new Loan(user, b, d);
//        loanList.add(l);
//        b.borrowBook();
//    }
//}
//
//class Book {
//    private static int counter = 0;
//    private int id;
//    private String name;
//    private String email;
//    private long isBn;
//    private String authorname;
//    private boolean available;
//
//    public String getAuthorname() {
//        return authorname;
//    }
//
//    public boolean isAvailable() {
//        return available;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public long getIsBn() {
//        return isBn;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    Book(String name, String email, Long isBn, String authorname) {
//        this.id = ++counter;
//        this.authorname = authorname;
//        this.name = name;
//        this.isBn = isBn;
//        this.email = email;
//        this.available = true;
//    }
//
//    public void returnBook() {
//        available = true;
//    }
//
//    public void borrowBook() {
//        available = false;
//    }
//
//    @Override
//    public String toString() {
//        return id + ". " + name + " by " + authorname + " [" + (available ? "Available" : "Borrowed") + "]";
//    }
//}
//
//abstract class Person {
//    private static int counter = 0;
//    private int id;
//    private String u_name;
//    private String u_pass;
//    private String u_email;
//
//    Person(String u_name, String u_pass, String u_email) {
//        this.id = ++counter;
//        this.u_name = u_name;
//        this.u_email = u_email;
//        this.u_pass = u_pass;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return u_name;
//    }
//
//    @Override
//    public String toString() {
//        return id + ". " + u_name + " (" + u_email + ")";
//    }
//}
//
//class User extends Person {
//    private List<Book> borrowList;
//
//    User(String u_name, String u_pass, String u_email) {
//        super(u_name, u_pass, u_email);
//        borrowList = new ArrayList<>();
//    }
//
//    public void borrowBook(Book b, LibraryManager lb, Date d) {
//        lb.issueLoan(this, b, d);
//    }
//
//    public void returnBook(Book b, LibraryManager lb, Date d) {
//        if (!lb.isAvailable(b)) {
//            int fine = lb.returnLoan(this, b, d);
//            System.out.println("📖 Book returned! Fine: $" + fine);
//        }
//    }
//}
//
//class Librarian extends Person {
//    Librarian(String u_name, String u_pass, String u_email) {
//        super(u_name, u_pass, u_email);
//    }
//
//    public void addBooks(Book b, LibraryManager lb) {
//        lb.getBooklist().add(b);
//    }
//
//    public void removeBook(Book b, LibraryManager lb) {
//        lb.getBooklist().remove(b);
//    }
//
//    public void registerUser(LibraryManager lb, User u) {
//        lb.registerPerson(u);
//    }
//
//    public void removeUser(LibraryManager lb, User u) {
//        lb.removePerson(u);
//    }
//}
//
//class Loan {
//    private Book book;
//    private Person person;
//    private Date dueDate;
//
//    public Loan(User user, Book b, Date stDate) {
//        this.person = user;
//        this.dueDate = stDate;
//        this.book = b;
//    }
//
//    public int calculateFine(Date returnDate) {
//        long daysLate = (returnDate.getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24);
//        return (daysLate > 0) ? (int) daysLate * 5 : 0;
//    }
//
//    public Book getBook() {
//        return book;
//    }
//
//    public Person getPerson() {
//        return person;
//    }
//
//    public Date getDueDate() {
//        return dueDate;
//    }
//}
//4
//
//public class LibraryManagement {
//    static Scanner scanner = new Scanner(System.in);
//    static LibraryManager library = new LibraryManager();
//    static Librarian librarian = new Librarian("Admin", "admin123", "admin@library.com");
//    static List<User> users = new ArrayList<>(); // To store registered users
//
//    public static void main(String[] args) {
//        while (true) {
//            System.out.println("\n===== 📚 LIBRARY MANAGEMENT SYSTEM =====");
//            System.out.println("1. 📖 View Books");
//            System.out.println("2. 👥 View Users");
//            System.out.println("3. ➕ Add Book");
//            System.out.println("4. 🆕 Register User");
//            System.out.println("5. 📤 Borrow Book");
//            System.out.println("6. 🔄 Return Book");
//            System.out.println("7. ❌ Exit");
//            System.out.print("Enter your choice: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1:
//                    viewBooks();
//                    break;
//                case 2:
//                    viewUsers();
//                    break;
//                case 3:
//                    addBook();
//                    break;
//                case 4:
//                    registerUser();
//                    break;
//                case 5:
//                    borrowBook();
//                    break;
//                case 6:
//                    returnBook();
//                    break;
//                case 7:
//                    System.out.println("📕 Exiting... Goodbye!");
//                    return;
//                default:
//                    System.out.println("❌ Invalid choice! Please try again.");
//            }
//        }
//    }
//
//    private static void viewBooks() {
//        if (library.getBooklist().isEmpty()) {
//            System.out.println("⚠️ No books available.");
//            return;
//        }
//        for (Book b : library.getBooklist()) {
//            System.out.println(b);
//        }
//    }
//
//    private static void viewUsers() {
//        if (users.isEmpty()) {
//            System.out.println("⚠️ No users registered.");
//            return;
//        }
//        for (User u : users) {
//            System.out.println(u);
//        }
//    }
//
//    private static void addBook() {
//        System.out.print("Enter book name: ");
//        String name = scanner.nextLine();
//        System.out.print("Enter author name: ");
//        String author = scanner.nextLine();
//        System.out.print("Enter ISBN: ");
//        long isbn = scanner.nextLong();
//        scanner.nextLine();
//        Book book = new Book(name, "", isbn, author);
//        librarian.addBooks(book, library);
//        System.out.println("✅ Book added!");
//    }
//
//    private static void registerUser() {
//        System.out.print("Enter user name: ");
//        String name = scanner.nextLine();
//        System.out.print("Enter password: ");
//        String pass = scanner.nextLine();
//        System.out.print("Enter email: ");
//        String email = scanner.nextLine();
//        User user = new User(name, pass, email);
//        users.add(user);
//        librarian.registerUser(library, user);
//        System.out.println("✅ User registered!");
//    }
//
//    private static void borrowBook() {
//        if (users.isEmpty() || library.getBooklist().isEmpty()) {
//            System.out.println("⚠️ No users or books available.");
//            return;
//        }
//
//        System.out.println("Enter user ID: ");
//        int userId = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        User user = findUserById(userId);
//        if (user == null) {
//            System.out.println("⚠️ User not found!");
//            return;
//        }
//
//        System.out.println("Enter book ID to borrow: ");
//        int bookId = scanner.nextInt();
//        scanner.nextLine();
//
//        Book book = findBookById(bookId);
//        if (book == null || !book.isAvailable()) {
//            System.out.println("⚠️ Book not available!");
//            return;
//        }
//
//        library.issueLoan(user, book, new Date());
//        System.out.println("📖 Book borrowed successfully!");
//    }
//
//    private static void returnBook() {
//        if (users.isEmpty() || library.getBooklist().isEmpty()) {
//            System.out.println("⚠️ No users or books available.");
//            return;
//        }
//
//        System.out.println("Enter user ID: ");
//        int userId = scanner.nextInt();
//        scanner.nextLine();
//
//        User user = findUserById(userId);
//        if (user == null) {
//            System.out.println("⚠️ User not found!");
//            return;
//        }
//
//        System.out.println("Enter book ID to return: ");
//        int bookId = scanner.nextInt();
//        scanner.nextLine();
//
//        Book book = findBookById(bookId);
//        if (book == null || library.isAvailable(book)) {
//            System.out.println("⚠️ This book is not borrowed!");
//            return;
//        }
//
//        int fine = library.returnLoan(user, book, new Date());
//        System.out.println("📖 Book returned! Fine: $" + fine);
//    }
//
//    private static User findUserById(int userId) {
//        for (User u : users) {
//            if (u.getId() == userId) {
//                return u;
//            }
//        }
//        return null;
//    }
//
//    private static Book findBookById(int bookId) {
//        for (Book b : library.getBooklist()) {
//            if (b.getId() == bookId) {
//                return b;
//            }
//        }
//        return null;
//    }
//}
//
