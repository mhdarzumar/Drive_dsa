//import java.util.*;
//
//abstract class Users {
//    protected int user_id;
//    protected String name;
//    protected String email;
//    protected String password;
//    protected String role;
//
//    Users(int user_id, String name, String email, String password, String role) {
//        this.user_id = user_id;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }
//
//    public abstract void displayInfo();
//}
//
//class Product {
//    private int p_id;
//    private double price;
//    private String name;
//    private String category;
//
//    Product(int id, double price, String name, String category) {
//        this.p_id = id;
//        this.price = price;
//        this.name = name;
//        this.category = category;
//    }
//
//    public int getP_id() {
//        return p_id;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    @Override
//    public String toString() {
//        return p_id + " | " + name + " | ₹" + price + " | " + category;
//    }
//}
//
//class Cart {
//    private List<Product> productList = new ArrayList<>();
//
//    public void addProduct(Product product) {
//        productList.add(product);
//    }
//
//    public void viewCart() {
//        if (productList.isEmpty()) {
//            System.out.println("Cart is empty.");
//        } else {
//            for (Product p : productList) {
//                System.out.println(p);
//            }
//        }
//    }
//
//    public double getTotalPrice() {
//        return productList.stream().mapToDouble(Product::getPrice).sum();
//    }
//
//    public void checkout() {
//        if (productList.isEmpty()) {
//            System.out.println("Cart is empty! Add products before checkout.");
//        } else {
//            System.out.println("Total Price: ₹" + getTotalPrice());
//            productList.clear();
//            System.out.println("Checkout complete. Thank you!");
//        }
//    }
//}
//
//class Customers extends Users {
//    private Cart cart = new Cart();
//
//    Customers(int user_id, String name, String email, String password) {
//        super(user_id, name, email, password, "Customer");
//    }
//
//    public Cart getCart() {
//        return cart;
//    }
//
//    @Override
//    public void displayInfo() {
//        System.out.println(user_id + " | " + name + " | " + email + " | " + role);
//    }
//}
//
//class Admin extends Users {
//    Admin(int user_id, String name, String email, String password) {
//        super(user_id, name, email, password, "Admin");
//    }
//
//    public void addProduct(Product product, List<Product> productList) {
//        productList.add(product);
//        System.out.println("Product added successfully.");
//    }
//
//    public void removeProduct(int productId, List<Product> productList) {
//        productList.removeIf(p -> p.getP_id() == productId);
//        System.out.println("Product removed successfully.");
//    }
//
//    public void updateProduct(int productId, double price, String name, String category, List<Product> productList) {
//        for (Product p : productList) {
//            if (p.getP_id() == productId) {
//                productList.remove(p);
//                productList.add(new Product(productId, price, name, category));
//                System.out.println("Product updated successfully.");
//                return;
//            }
//        }
//        System.out.println("Product not found!");
//    }
//
//    @Override
//    public void displayInfo() {
//        System.out.println(user_id + " | " + name + " | " + email + " | " + role);
//    }
//}
//
//public class Ecommerse {
//    private static Scanner scanner = new Scanner(System.in);
//    private static List<Users> users = new ArrayList<>();
//    private static List<Product> products = new ArrayList<>();
//    private static Users loggedInUser = null;
//
//    public static void main(String[] args) {
//        users.add(new Admin(1, "Admin1", "admin@example.com", "admin123"));
//
//        while (true) {
//            showMainMenu();
//        }
//    }
//
//    private static void showMainMenu() {
//        System.out.println("\nWelcome to E-commerce System");
//        System.out.println("1. Login");
//        System.out.println("2. Register");
//        System.out.println("3. Exit");
//        System.out.print("Choose an option: ");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1 -> login();
//            case 2 -> register();
//            case 3 -> {
//                System.out.println("Exiting... Thank you!");
//                System.exit(0);
//            }
//            default -> System.out.println("Invalid choice. Try again.");
//        }
//    }
//
//    private static void login() {
//        System.out.print("Enter Email: ");
//        String email = scanner.nextLine();
//        System.out.print("Enter Password: ");
//        String password = scanner.nextLine();
//
//        for (Users user : users) {
//            if (user.email.equals(email) && user.password.equals(password)) {
//                System.out.println("Login Successful!");
//                loggedInUser = user;
//
//                if (user instanceof Admin admin) {
//                    adminMenu(admin);
//                } else if (user instanceof Customers customer) {
//                    customerMenu(customer);
//                }
//                return;
//            }
//        }
//        System.out.println("Invalid Credentials.");
//    }
//
//    private static void customerMenu(Customers customer) {
//        while (true) {
//            System.out.println("\nCustomer Menu:");
//            System.out.println("1. View Products");
//            System.out.println("2. Add Product to Cart");
//            System.out.println("3. View Cart");
//            System.out.println("4. Checkout");
//            System.out.println("5. Logout");
//            System.out.print("Choose an option: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1 -> viewProducts();
//                case 2 -> addToCart(customer);
//                case 3 -> customer.getCart().viewCart();
//                case 4 -> customer.getCart().checkout();
//                case 5 -> {
//                    logout();
//                    return;
//                }
//                default -> System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }
//
//    private static void viewProducts() {
//        if (products.isEmpty()) {
//            System.out.println("No products available.");
//        } else {
//            System.out.println("\nAvailable Products:");
//            for (Product p : products) {
//                System.out.println(p);
//            }
//        }
//    }
//
//    private static void addToCart(Customers customer) {
//        viewProducts();
//
//        if (products.isEmpty()) return;
//
//        System.out.print("Enter Product ID to add to cart: ");
//        int productId = scanner.nextInt();
//        scanner.nextLine();
//
//        for (Product p : products) {
//            if (p.getP_id() == productId) {
//                customer.getCart().addProduct(p);
//                System.out.println("Product added to cart.");
//                return;
//            }
//        }
//        System.out.println("Product not found!");
//    }
//
//
//    private static void register() {
//        System.out.print("Enter Name: ");
//        String name = scanner.nextLine();
//        System.out.print("Enter Email: ");
//        String email = scanner.nextLine();
//        System.out.print("Enter Password: ");
//        String password = scanner.nextLine();
//
//        int newUserId = users.size() + 1;
//        Customers newCustomer = new Customers(newUserId, name, email, password);
//        users.add(newCustomer);
//        System.out.println("Registration Successful! You can now login.");
//    }
//
//    private static void logout() {
//        System.out.println("Logging out...");
//        loggedInUser = null;
//    }
//
//    private static void adminMenu(Admin admin) {
//        while (true) {
//            System.out.println("\nAdmin Menu:");
//            System.out.println("1. Add Product");
//            System.out.println("2. Remove Product");
//            System.out.println("3. Update Product");
//            System.out.println("4. Logout");
//            System.out.print("Choose an option: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1 -> {
//                    System.out.print("Enter Product ID: ");
//                    int id = scanner.nextInt();
//                    System.out.print("Enter Price: ");
//                    double price = scanner.nextDouble();
//                    scanner.nextLine();
//                    System.out.print("Enter Name: ");
//                    String name = scanner.nextLine();
//                    System.out.print("Enter Category: ");
//                    String category = scanner.nextLine();
//                    admin.addProduct(new Product(id, price, name, category), products);
//                }
//                case 2 -> {
//                    System.out.print("Enter Product ID to Remove: ");
//                    int id = scanner.nextInt();
//                    admin.removeProduct(id, products);
//                }
//                case 3 -> {
//                    System.out.print("Enter Product ID to Update: ");
//                    int id = scanner.nextInt();
//                    scanner.nextLine();
//                    System.out.print("Enter New Price: ");
//                    double price = scanner.nextDouble();
//                    scanner.nextLine();
//                    System.out.print("Enter New Name: ");
//                    String name = scanner.nextLine();
//                    System.out.print("Enter New Category: ");
//                    String category = scanner.nextLine();
//                    admin.updateProduct(id, price, name, category, products);
//                }
//                case 4 -> {
//                    logout();
//                    return;
//                }
//                default -> System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }
//}
