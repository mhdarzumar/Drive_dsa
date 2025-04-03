import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class AccountHolder {
    private int userid;
    private String name;
    private String email;
    private String password;
    private List<PiggyBanks> piggyBanksList;
    private Map<String, Double> expenseTrack;
    private Budgeting budgeting;

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public AccountHolder(String email, String name, String password, int userid , Double Total_Budget) {
        this.email = email;
        this.expenseTrack = new HashMap<>();
        this.name = name;
        this.piggyBanksList = new ArrayList<>();
        this.userid = userid;
        this.password = password;
        this.budgeting = new Budgeting(Total_Budget);
    }

    public Budgeting getBudgeting(){
        return budgeting;
    }

    public int getUserid(){
        return userid;
    }

    public List<PiggyBanks> getPiggyBankDetails() {
        return piggyBanksList;
    }

    public void addPiggyBank(int id, String objective, Double totalAmount, LocalDate endDate, String description) {
        piggyBanksList.add(new PiggyBanks(id, objective, totalAmount, endDate, description));
    }

    public Map<String, Double> getExpenseTrack() {
        return expenseTrack;
    }
}

class PiggyBanks {
    private int id;
    private Double balance;
    private String objective;
    private List<SavingChallenge> savingChallengeList;
    private SavingsGoals savingsGoal;

    public PiggyBanks(int id, String objective, Double totalAmount, LocalDate endDate, String description) {
        this.balance = 0.0;
        this.id = id;
        this.objective = objective;
        this.savingChallengeList = new ArrayList<>();
        this.savingsGoal = new SavingsGoals(totalAmount, endDate, description);
    }

    public Double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public String getObjective() {
        return objective;
    }

    public List<SavingChallenge> getSavingChallengeList() {
        return savingChallengeList;
    }

    public Double addFunds(Double amount) {
        balance += amount;
        return balance;
    }

    public Double addFunds(Double amount,int id){
        for(SavingChallenge s:savingChallengeList){
            if(s.getId()==id){
                s.setBalance(s.getBalance()+amount);
                balance+=amount;
                return s.getBalance();
            }
        }
        return 0.0;
    }

    public Double withdrawFunds(Double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds in Piggy Bank " + id);
            return balance;
        }
        balance -= amount;
        return balance;
    }

    public void addSavingsChallenge(String cat, LocalDate dataEnd, LocalDate dateSt, Double goal, int i-+d) {
        savingChallengeList.add(new SavingChallenge(cat, dataEnd, dateSt, goal, id));
    }

    // Returns true if there is any challenge where the balance is less than the goal (i.e., still active)
    public boolean checkActiveChallege() {
        for (SavingChallenge sc : savingChallengeList) {
            if (sc.getBalance() < sc.getGoal()) {
                return true;
            }
        }
        return false;
    }
}

class SavingsGoals {
    private Double totalamount;
    private LocalDate stdate;
    private LocalDate endDate;
    private String description;

    public SavingsGoals(Double totalamount, LocalDate endDate, String description) {
        this.stdate = LocalDate.now();
        this.totalamount = totalamount;
        this.description = description;
        this.endDate = endDate;
    }
}

class SavingChallenge {
    private int id;
    private LocalDate dateSt;
    private LocalDate dataEnd;
    private Double goal;
    private String cat;
    private Double balance;

    public SavingChallenge(String cat, LocalDate dataEnd, LocalDate dateSt, Double goal, int id) {
        this.cat = cat;
        this.dataEnd = dataEnd;
        this.dateSt = dateSt;
        this.goal = goal;
        this.id = id;
        this.balance = 0.0;
    }

    public Double getBalance() {
        return balance;
    }

    public void addFunds(Double amount){
        this.balance = amount;
    }

    public String getCat() {
        return cat;
    }

    public LocalDate getDataEnd() {
        return dataEnd;
    }

    public LocalDate getDateSt() {
        return dateSt;
    }

    public Double getGoal() {
        return goal;
    }

    public int getId() {
        return id;
    }

    public void setBalance(double v) {
        this.balance = v;
    }
}

class Budgeting {
    private Double totalBudget;
    private Map<String, Double> expenseAllotment;

    public Budgeting(Double totalBudget) {
        this.totalBudget = totalBudget;
        this.expenseAllotment = new HashMap<>();
    }

    public Map<String, Double> getExpenseAllotment() {
        return expenseAllotment;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }
}

class Process {
    private List<AccountHolder> aclist;

    Process(){
        aclist = new ArrayList<>();
    }



    // Constructor to initialize the process with account and budgeting details
    public void addUsers(String email, String name, String password , int userid, Double totalBudget) {
        this.aclist.add(new AccountHolder(email, name,password, userid, totalBudget));
    }

    public void allotExpense(int user_id , String cat, Double amount) {
        AccountHolder sc = getByUserId(user_id);
        sc.getBudgeting().getExpenseAllotment().put(cat, amount);
    }

    public AccountHolder getByUserId(int user_id){
        for(AccountHolder ac : aclist){
            if(ac.getUserid()==user_id){
                return ac;
            }
        }
        return null;
    }

    public void getPiggyBankDetails(int ac_id) {
        List<PiggyBanks> piggyBanks = getByUserId(ac_id).getPiggyBankDetails();
        for (PiggyBanks p : piggyBanks) {
            System.out.println("Piggy Bank ID: " + p.getId());
            System.out.println("Objective: " + p.getObjective());
            System.out.println("Current Balance: " + p.getBalance());
            if (p.getSavingChallengeList().isEmpty()) {
                System.out.println("  No Savings Challenges.");
            } else {
                for (SavingChallenge sc : p.getSavingChallengeList()) {
                    System.out.println("  Challenge ID: " + sc.getId());
                    System.out.println("  Category: " + sc.getCat());
                    System.out.println("  Start Date: " + sc.getDateSt());
                    System.out.println("  End Date: " + sc.getDataEnd());
                    System.out.println("  Goal: " + sc.getGoal());
                    System.out.println("  Challenge Balance: " + sc.getBalance());
                }
            }
        }
    }

    public Double addFunds(int ac_id, Double amount, int id, int saveChallengId) {
        for (PiggyBanks pb : getByUserId(ac_id).getPiggyBankDetails()) {
            if (pb.getId() == id) {
                if(saveChallengId!=0) {
                    return pb.addFunds(amount,saveChallengId);
                }
                else{
                    return pb.addFunds(amount);
                }
            }
        }
        return 0.0;
    }

    public Double withDrawFunds(int ac_id , Double amount, int id) {
        for (PiggyBanks pb : getByUserId(ac_id).getPiggyBankDetails()) {
            if (pb.getId() == id) {
                return pb.withdrawFunds(amount);
            }
        }
        return 0.0;
    }

    public void listAvailablePiggyBank(int ac_id) {
        for (PiggyBanks p : getByUserId(ac_id).getPiggyBankDetails()) {
            System.out.println("Piggy Bank ID: " + p.getId() + " | Active Challenge: " + p.checkActiveChallege());
        }
    }

    public void addSavingsChallenge(int ac_id, int pid, String cat, LocalDate dataEnd, LocalDate dateSt, Double goal, int id) {
        boolean found = false;
        for (PiggyBanks p : getByUserId(ac_id).getPiggyBankDetails()) {
            if (p.getId() == pid) {
                p.addSavingsChallenge(cat, dataEnd, dateSt, goal, id);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Piggy Bank with ID " + pid + " not found.");
        }
    }

    public void addPiggyBanks(int ac_id, int id, String objective, Double totalAmount, LocalDate endDate, String description) {
        getByUserId(ac_id).addPiggyBank(id, objective, totalAmount, endDate, description);
    }

    public void addExpense(int ac_id , String cat, Double amount) {
        getByUserId(ac_id).getExpenseTrack().put(cat, amount);
    }

    public void getOverAllExpenseData(int ac_id) {
        Map<String, Double> m1 = getByUserId(ac_id).getBudgeting().getExpenseAllotment();
        Map<String, Double> m2 = getByUserId(ac_id).getExpenseTrack();

        System.out.println("Expense Category | Budgeted Amount | Actual Spending | Status");
        System.out.println("------------------------------------------------------------");

        for (String category : m1.keySet()) {
            double allotted = m1.get(category);
            double spent = m2.getOrDefault(category, 0.0);
            String status = (spent > allotted) ? "Overspent" : (spent < allotted) ? "Under Budget" : "On Budget";
            System.out.printf("%-16s | %-15.2f | %-15.2f | %s%n", category, allotted, spent, status);
        }
    }

    public int login(String email, String pass) {
        if(aclist.isEmpty()){
            return -1;
        }
        for(AccountHolder ac:aclist){
            if(ac.getEmail().equals(email) && pass.equals(ac.getPassword())){
                return ac.getUserid();
            }
        }
        return -1;
    }


}

//public class PiggyBank {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to the Piggy Bank App!");
//
//        System.out.println(" 1 .login or 2. register");
//
//        Process process = new Process();
//
//        int s = scanner.nextInt();
//        int userId=-1;
//        scanner.nextLine();
//        boolean exit = false;
//        if(s==1){
//            System.out.print("Enter email and password");
//            String email = scanner.nextLine();
//            String pass =  scanner.nextLine();
//            userId = process.login(email, pass);
//            if(userId==-1){
//                System.out.println("user name not found ");
//                exit =true;
//            }
//        }
//        else{
//            System.out.println("Enter your email: ");
//            String email = scanner.nextLine();
//            System.out.println("Enter your name: ");
//            String name = scanner.nextLine();
//            System.out.println("Enter your password: ");
//            String password = scanner.nextLine();
//            System.out.println("Enter your userid: ");
//            int userid = scanner.nextInt();
//            System.out.println("Enter your total budget: ");
//            double totalBudget = scanner.nextDouble();
//            process.addUsers(email, name, password, userid, totalBudget);
//            userId = userid;
//            scanner.nextLine();
//        }
//
//
//        while (!exit) {
//            System.out.println("\nMenu:");
//            System.out.println("1. Add Piggy Bank");
//            System.out.println("2. Add Funds to Piggy Bank");
//            System.out.println("3. Withdraw Funds from Piggy Bank");
//            System.out.println("4. Add Savings Challenge");
//            System.out.println("5. Allot Expense");
//            System.out.println("6. Add Actual Expense");
//            System.out.println("7. Show Piggy Bank Details");
//            System.out.println("8. List Available Piggy Banks");
//            System.out.println("9. Show Overall Expense Data");
//            System.out.println("10. logout");
//            System.out.println("0. Exit");
//            System.out.print("Enter your choice: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // consume newline
//
//            switch (choice) {
//                case 1:
//                    System.out.print("Enter Piggy Bank ID: ");
//                    int pid = scanner.nextInt();
//                    scanner.nextLine();
//                    System.out.print("Enter objective: ");
//                    String objective = scanner.nextLine();
//                    System.out.print("Enter total amount (goal): ");
//                    double totalAmount = scanner.nextDouble();
//                    scanner.nextLine();
//                    System.out.print("Enter end date (YYYY-MM-DD): ");
//                    LocalDate endDate = LocalDate.parse(scanner.nextLine());
//                    System.out.print("Enter description: ");
//                    String description = scanner.nextLine();
//                    process.addPiggyBanks(userId, pid, objective, totalAmount, endDate, description);
//                    System.out.println("Piggy Bank added.");
//                    break;
//                case 2:
//                    System.out.print("Enter Piggy Bank ID to add funds: ");
//                    int pidFunds = scanner.nextInt();
//                    System.out.print("Enter amount to add: ");
//                    double funds = scanner.nextDouble();
//                    System.out.println("Are you want to achive any challenge if yes show me number else enter 0");
//                    int id = scanner.nextInt();
//                    double newBalance = process.addFunds(userId, funds, pidFunds,id);
//                    System.out.println("New Balance: " + newBalance);
//                    break;
//                case 3:
//                    System.out.print("Enter Piggy Bank ID to withdraw funds: ");
//                    int pidWithdraw = scanner.nextInt();
//                    System.out.print("Enter amount to withdraw: ");
//                    double withdrawAmount = scanner.nextDouble();
//                    double newBalanceAfterWithdraw = process.withDrawFunds(userId, withdrawAmount, pidWithdraw);
//                    System.out.println("New Balance: " + newBalanceAfterWithdraw);
//                    break;
//                case 4:
//                    System.out.print("Enter Piggy Bank ID to add challenge: ");
//                    int pidChallenge = scanner.nextInt();
//                    scanner.nextLine();
//                    System.out.print("Enter challenge category: ");
//                    String cat = scanner.nextLine();
//                    System.out.print("Enter challenge start date (YYYY-MM-DD): ");
//                    LocalDate startDate = LocalDate.parse(scanner.nextLine());
//                    System.out.print("Enter challenge end date (YYYY-MM-DD): ");
//                    LocalDate challengeEndDate = LocalDate.parse(scanner.nextLine());
//                    System.out.print("Enter goal for challenge: ");
//                    double challengeGoal = scanner.nextDouble();
//                    System.out.print("Enter challenge ID: ");
//                    int challengeId = scanner.nextInt();
//                    scanner.nextLine();
//                    process.addSavingsChallenge(userId , pidChallenge, cat, challengeEndDate, startDate, challengeGoal, challengeId);
//                    System.out.println("Savings Challenge added.");
//                    break;
//                case 5:
//                    System.out.print("Enter expense category: ");
//                    String expenseCat = scanner.nextLine();
//                    System.out.print("Enter allotted amount: ");
//                    double allottedAmount = scanner.nextDouble();
//                    scanner.nextLine();
//                    process.allotExpense(userId, expenseCat, allottedAmount);
//                    System.out.println("Expense allotted.");
//                    break;
//                case 6:
//                    System.out.print("Enter expense category: ");
//                    String expenseCat2 = scanner.nextLine();
//                    System.out.print("Enter actual expense amount: ");
//                    double expenseAmount = scanner.nextDouble();
//                    scanner.nextLine();
//                    process.addExpense(userId , expenseCat2, expenseAmount);
//                    System.out.println("Expense added.");
//                    break;
//                case 7:
//                    process.getPiggyBankDetails(userId);
//                    break;
//                case 8:
//                    process.listAvailablePiggyBank(userId);
//                    break;
//                case 9:
//                    process.getOverAllExpenseData(userId);
//                    break;
//                case 10:
//                    System.out.println(" 1 .login or 2. register");
//
//                    s = scanner.nextInt();
//                    userId=-1;
//                    scanner.nextLine();
//                    if(s==1){
//                        System.out.print("Enter email and password");
//                        String email = scanner.nextLine();
//                        String pass =  scanner.nextLine();
//                        userId = process.login(email, pass);
//                        if(userId==-1){
//                            System.out.println("user name not found ");
//                            exit =true;
//                        }
//                    }
//                    else{
//                        System.out.println("Enter your email: ");
//                        String email = scanner.nextLine();
//                        System.out.println("Enter your name: ");
//                        String name = scanner.nextLine();
//                        System.out.println("Enter your password: ");
//                        String password = scanner.nextLine();
//                        System.out.println("Enter your userid: ");
//                        int userid = scanner.nextInt();
//                        System.out.println("Enter your total budget: ");
//                        double totalBudget = scanner.nextDouble();
//                        process.addUsers(email, name, password, userid, totalBudget);
//                        userId = userid;
//                        scanner.nextLine();
//                    }
//                    break;
//                case 0:
//                    exit = true;
//                    System.out.println("Exiting application. Goodbye!");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//        scanner.close();
//    }
//
//
//}
