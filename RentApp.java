import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class User {
    private static int count = 0;
    private int id;
    private String email;
    private String pass;

    public User(String email, String pass) {
        this.email = email;
        this.id = ++count;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }
}

class Admin extends User {
    private List<Vehicle> availableVehicles;
    private List<Customer> customerList;
    private List<Owner> ownerList;

    public Admin(String email, String pass) {
        super(email, pass);
        this.availableVehicles = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.ownerList = new ArrayList<>();
    }

    public List<Vehicle> getAvailableVehicles() {
        return availableVehicles;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }
}

class Customer extends User {
    private List<Vehicle> rentedVehicles;
    private Operation operation;

    public Customer(String email, String pass, Operation operation) {
        super(email, pass);
        this.operation = operation;
        this.rentedVehicles = new ArrayList<>();
    }

    public List<Vehicle> getAvailableVehicles() {
        return operation.getAvailableVehicles();
    }

    public boolean rentVehicle(int vId) {
        return operation.rentVehicle(this, vId);
    }

    public boolean returnVehicle(int vId) {
        return operation.returnVehicle(this, vId);
    }

    public boolean cancelVehicle(int vId) {
        return operation.cancelVehicle(this, vId);
    }
}

class Owner extends User {
    private List<Vehicle> vehicleList;
    private double earnings;
    private Operation operation;

    public Owner(String email, String pass, Operation operation) {
        super(email, pass);
        this.earnings = 0.0;
        this.vehicleList = new ArrayList<>();
        this.operation = operation;
    }

    public double getEarnings() {
        return earnings;
    }

    public void addVehicle(Vehicle v) {
        vehicleList.add(v);
        operation.addVehicle(v);
    }

    public void removeVehicle(int vId) {
        vehicleList.removeIf(v -> v.getVehicleId() == vId);
        operation.removeVehicle(vId);
    }

    public void updateEarnings(double amount) {
        this.earnings += amount;
    }
}

class Operation {
    private Admin admin;
    private Map<Customer, List<Vehicle>> rentedVehicles;

    public Operation(Admin admin) {
        this.admin = admin;
        this.rentedVehicles = new HashMap<>();
    }

    public List<Vehicle> getAvailableVehicles() {
        return admin.getAvailableVehicles();
    }

    public boolean rentVehicle(Customer customer, int vId) {
        for (Vehicle v : admin.getAvailableVehicles()) {
            if (v.getVehicleId() == vId && v.isAvailable()) {
                v.setAvailable(false);
                rentedVehicles.putIfAbsent(customer, new ArrayList<>());
                rentedVehicles.get(customer).add(v);

                // Update owner's earnings
                for (Owner owner : admin.getOwnerList()) {
                    if (owner.getEarnings() > 0) {
                        owner.updateEarnings(v.getPricePerDay());
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean returnVehicle(Customer customer, int vId) {
        if (rentedVehicles.containsKey(customer)) {
            List<Vehicle> customerVehicles = rentedVehicles.get(customer);
            for (Vehicle v : customerVehicles) {
                if (v.getVehicleId() == vId && !v.isAvailable()) {
                    v.setAvailable(true);
                    customerVehicles.remove(v);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cancelVehicle(Customer customer, int vId) {
        if (rentedVehicles.containsKey(customer)) {
            List<Vehicle> customerVehicles = rentedVehicles.get(customer);
            for (Vehicle v : customerVehicles) {
                if (v.getVehicleId() == vId && !v.isAvailable()) {
                    v.setAvailable(true);
                    customerVehicles.remove(v);
                    return true;
                }
            }
        }
        return false;
    }

    public void addVehicle(Vehicle v) {
        admin.getAvailableVehicles().add(v);
    }

    public void removeVehicle(int vId) {
        admin.getAvailableVehicles().removeIf(v -> v.getVehicleId() == vId);
    }
}

abstract class Vehicle {
    private static int count = 0;
    private int vehicleId;
    private String vehicleNumber;
    private boolean isAvailable;
    private String type;
    private double pricePerDay;

    public Vehicle(boolean isAvailable, String type, String vehicleNumber, double pricePerDay) {
        this.isAvailable = isAvailable;
        this.type = type;
        this.vehicleId = ++count;
        this.vehicleNumber = vehicleNumber;
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getType() {
        return type;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }
}

class Car extends Vehicle {
    public Car(boolean isAvailable, String vehicleNumber, double pricePerDay) {
        super(isAvailable, "Car", vehicleNumber, pricePerDay);
    }
}

class Bike extends Vehicle {
    public Bike(boolean isAvailable, String vehicleNumber, double pricePerDay) {
        super(isAvailable, "Bike", vehicleNumber, pricePerDay);
    }
}

public class RentApp {
    public static void main(String[] args) {
        Admin admin = new Admin("admin@rental.com", "admin123");
        Operation operation = new Operation(admin);

        Owner owner1 = new Owner("owner1@rental.com", "pass123", operation);
        Customer customer1 = new Customer("customer1@rental.com", "custpass", operation);

        Car car1 = new Car(true, "TN-01-1234", 500);
        Bike bike1 = new Bike(true, "TN-02-5678", 200);

        owner1.addVehicle(car1);
        owner1.addVehicle(bike1);

        System.out.println("Available Vehicles: " + operation.getAvailableVehicles().size());

        customer1.rentVehicle(car1.getVehicleId());
        System.out.println("Customer rented Car, Available Vehicles: " + operation.getAvailableVehicles().size());

        customer1.returnVehicle(car1.getVehicleId());
        System.out.println("Customer returned Car, Available Vehicles: " + operation.getAvailableVehicles().size());
    }
}
