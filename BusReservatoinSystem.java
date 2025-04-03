import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Bus {
    private String bus_no;
    private int totalSeats;
    private double price;
    private String routeId;

    Bus(String bus_no, int totalSeats, double price, String routeId) {
        this.bus_no = bus_no;
        this.price = price;
        this.totalSeats = totalSeats;
        this.routeId = routeId;
    }

    public double getPrice() { return price; }
    public String getBus_no() { return bus_no; }
    public String getRouteId() { return routeId; }
    public int getTotalSeats() { return totalSeats; }
}

class Routes {
    private String route_id;
    private String source;
    private String destination;
    private List<Bus> availableBuses;

    Routes(String route_id, String source, String destination) {
        this.availableBuses = new ArrayList<>();
        this.source = source;
        this.route_id = route_id;
        this.destination = destination;
    }

    public void addBus(Bus bus) { availableBuses.add(bus); }

    public String getDestination() { return destination; }
    public String getSource() { return source; }
    public List<Bus> getAvailableBuses() { return availableBuses; }
    public String getRoute_id() { return route_id; }
}

class Booking {
    private int id;
    private String bus_no;
    private LocalDate date;
    private String route_id;

    Booking(int id, String bus_no, LocalDate date, String route_id) {
        this.id = id;
        this.bus_no = bus_no;
        this.date = date;
        this.route_id = route_id;
    }

    public int getId() { return id; }
    public String getBus_no() { return bus_no; }
    public String getRoute_id() { return route_id; }
    public LocalDate getDate() { return date; }
}

class BookingSystem {
    private List<Booking> bookingList;
    private List<Bus> busList;
    private List<Routes> routesList;

    public BookingSystem() {
        bookingList = new ArrayList<>();
        busList = new ArrayList<>();
        routesList = new ArrayList<>();
    }

    public List<Bus> getBusList() {
        return busList;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public List<Routes> getRoutesList() {
        return routesList;
    }

    public void addBooking(Booking b) { bookingList.add(b); }
    public void addBus(Bus bus) { busList.add(bus); }
    public void addRoute(Routes r) { routesList.add(r); }

    public int getAvailableSeats(LocalDate date, String busNo) {
        int totalSeats = 0;
        int booked = 0;

        for (Bus b : busList) {
            if (b.getBus_no().equals(busNo)) {
                totalSeats = b.getTotalSeats();
                break;
            }
        }

        for (Booking b : bookingList) {
            if (b.getBus_no().equals(busNo) && b.getDate().equals(date)) {
                booked++;
            }
        }

        return totalSeats - booked;
    }

    public void showAvailableBuses(String source, String dest, LocalDate date) {
        System.out.println("Available buses on the route " + source + " -> " + dest + " on " + date);
        for (Routes r : routesList) {
            if (r.getSource().equalsIgnoreCase(source) && r.getDestination().equalsIgnoreCase(dest)) {
                for (Bus b : r.getAvailableBuses()) {
                    System.out.println("Bus No: " + b.getBus_no() + ", Available Seats: " + getAvailableSeats(date, b.getBus_no()));
                }
            }
        }
    }
}

public class BusReservatoinSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingSystem system = new BookingSystem();

        // Adding Routes
        Routes route1 = new Routes("R101", "Chennai", "Bangalore");
        Routes route2 = new Routes("R102", "Delhi", "Mumbai");

        system.addRoute(route1);
        system.addRoute(route2);

        // Adding Buses
        Bus bus1 = new Bus("B100", 40, 500, "R101");
        Bus bus2 = new Bus("B200", 30, 600, "R102");

        system.addBus(bus1);
        system.addBus(bus2);

        route1.addBus(bus1);
        route2.addBus(bus2);

        // User Interaction
        while (true) {
            System.out.println("\n1. Show Available Buses");
            System.out.println("2. Book a Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter source: ");
                String source = scanner.nextLine();
                System.out.print("Enter destination: ");
                String dest = scanner.nextLine();
                System.out.print("Enter travel date (yyyy-mm-dd): ");
                LocalDate date = LocalDate.parse(scanner.nextLine());

                system.showAvailableBuses(source, dest, date);
            }
            else if (choice == 2) {
                System.out.print("Enter bus number: ");
                String busNo = scanner.nextLine();
                System.out.print("Enter travel date (yyyy-mm-dd): ");
                LocalDate date = LocalDate.parse(scanner.nextLine());

                int availableSeats = system.getAvailableSeats(date, busNo);
                if (availableSeats > 0) {
                    int bookingId = system.getBookingList().size() + 1;
                    Booking newBooking = new Booking(bookingId, busNo, date, "");
                    system.addBooking(newBooking);
                    System.out.println("Booking successful! Your ticket ID is: " + bookingId);
                } else {
                    System.out.println("No seats available!");
                }
            }
            else if (choice == 3) {
                System.out.println("Thank you for using the bus booking system!");
                break;
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
