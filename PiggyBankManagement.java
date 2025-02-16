import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Scanner;

class User{
    private int id;
    private String name;
    private String email;
    private String passweod;
    private List<Piggy> piggyBanks;
    private Map<String , Double> expenseTracker;

    public User(String email,  int id, String name, String passweod) {
        this.email = email;
        this.expenseTracker = new HashMap<>();
        this.id = id;
        this.name = name;
        this.passweod = passweod;
        this.piggyBanks = new ArrayList<>();
    }

    public void addExpense(String cat , Double d)
    {
        expenseTracker.put(cat, expenseTracker.getOrDefault(cat,0.0)+d);
    }

    public void CreatePiggyBan(String objective, double target, int id, LocalDate endDate,String description)
    {
        piggyBanks.add(new Piggy(id, objective, target, endDate, description));
    }
    public void getDetailsPiggyBank()
    {
        for(Piggy p:piggyBanks)
        {
            System.out.println(p.getId() + " " + p.getObjective() + " " + p.getSavingsGoal().getDaysLeft() + " " + p.getBalance());
        }
    }

    public void addFunds(Double amount, int id)
    {
        for(Piggy p:piggyBanks)
        {
            if(p.getId()==id)
            {
                p.addFunds(amount);
            }
        }
    }

    public void withDraw(Double amount, int id,String description, String cat)
    {
        for(Piggy p:piggyBanks)
        {
            if(p.getId()==id)
            {
                p.withdraw(amount,description,cat);
            }
        }
    }

}

class Piggy{
    private int id;
    private String objective;
    private double targetAmount;
    private double balance;
    private List<SavingsChallenge> savingsChallenge;
    private SavingsGoal savingsGoal;

    Piggy(int id,String objective, double targetAmount , LocalDate endDate , String Description)
    {
        this.id = id;
        this.objective = objective;
        this.targetAmount = targetAmount;
        this.savingsChallenge = new ArrayList<>();
        this.balance  = 0.0;
        this.savingsGoal = new SavingsGoal(LocalDate.now(),endDate, Description);
    }

    public int getId() {
        return id;
    }

    public String getObjective() {
        return objective;
    }

    public List<SavingsChallenge> getSavingsChallenge() {
        return savingsChallenge;
    }

    public SavingsGoal getSavingsGoal() {
        return savingsGoal;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public double getBalance(){return balance;}


    public void addFunds(Double amount) {
        balance+=amount;
    }

    public void withdraw(Double amount,String descripotion, String category)
    {
        if(balance>amount)
        {
            savingsChallenge.add(new SavingsChallenge(amount,category,descripotion));
            balance-=amount;
        }
    }
}

class SavingsChallenge{
    static int count=0;
    private int challenge_no;
    private String category;
    private Double amount;
    private String Description;

    public SavingsChallenge(Double amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.challenge_no = ++count;
        Description = description;
    }
}


class SavingsGoal {
    private LocalDate stdate;
    private String description;
    private LocalDate endDate;
    private int daysLeft;

    // Constructor
    SavingsGoal(LocalDate stdate, LocalDate endDate, String description) {
        this.stdate = stdate;  // Use the provided start date
        this.endDate = endDate;
        this.description = description;
        this.daysLeft = (int) ChronoUnit.DAYS.between(stdate, endDate); // Calculate days left
    }

    // Getters
    public LocalDate getStartDate() { return stdate; }
    public LocalDate getEndDate() { return endDate; }
    public String getDescription() { return description; }
    public int getDaysLeft() { return daysLeft; }

    // Display details
    public void displayGoal() {
        System.out.println("Savings Goal: " + description);
        System.out.println("Start Date: " + stdate);
        System.out.println("End Date: " + endDate);
        System.out.println("Days Left: " + daysLeft);
    }
}
class Budget{

    private double totalBudget;
    private Map<String, Double> allotmentMap;

    Budget(double savings)
    {
        this.totalBudget = savings;
        this.allotmentMap = new HashMap<>();
    }

    public void addExpense(String cat , Double d)
    {
        allotmentMap.put(cat, allotmentMap.getOrDefault(cat ,0.0)+d);
    }
}



public class PiggyBankManagement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();

        User user = new User(email, 1, name, password);

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Create a Piggy Bank");
            System.out.println("2. Add Expense");
            System.out.println("3. Add Funds to Piggy Bank");
            System.out.println("4. Withdraw Funds from Piggy Bank");
            System.out.println("5. View Piggy Bank Details");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createPiggyBank(user, scanner);
                    break;
                case 2:
                    addExpense(user, scanner);
                    break;
                case 3:
                    addFunds(user, scanner);
                    break;
                case 4:
                    withdrawFunds(user, scanner);
                    break;
                case 5:
                    user.getDetailsPiggyBank();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting Piggy Bank App...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void createPiggyBank(User user, Scanner scanner) {
        System.out.print("Enter Piggy Bank Objective: ");
        String objective = scanner.nextLine();
        System.out.print("Enter Target Amount: ");
        double targetAmount = scanner.nextDouble();
        System.out.print("Enter Number of Days for Goal: ");
        int days = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        LocalDate endDate = LocalDate.now().plusDays(days);
        user.CreatePiggyBan(objective, targetAmount, user.hashCode(), endDate, description);
        System.out.println("Piggy Bank Created Successfully!");
    }

    private static void addExpense(User user, Scanner scanner) {
        System.out.print("Enter Expense Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Expense Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        user.addExpense(category, amount);
        System.out.println("Expense Added Successfully!");
    }

    private static void addFunds(User user, Scanner scanner) {
        System.out.print("Enter Piggy Bank ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Amount to Deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        user.addFunds(amount, id);
        System.out.println("Funds Deposited Successfully!");
    }

    private static void withdrawFunds(User user, Scanner scanner) {
        System.out.print("Enter Piggy Bank ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Amount to Withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        user.withDraw(amount, id, description, category);
        System.out.println("Withdrawal Successful!");
    }
}

