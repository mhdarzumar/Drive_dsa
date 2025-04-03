package PiggyBank;

import java.util.*;

class User{
    private static int i =0;
    private int id;
    private String name;
    private String email;
    private String pass;
    private List<PiggyBank> piggyBankList;
    private ExpenseTrack expenseTrack;

    public User(String email , int id, String name, String pass) {
        this.email = email;
        this.expenseTrack = new ExpenseTrack();
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.piggyBankList = new ArrayList<>();
    }

    public void updateProfile(String name, String pass){
        this.name = name;
        this.pass = pass;
    }

}
class PiggyBank {
    private static int j=0;
    private int id;
    private Double balance;
    private Double targetAmount;
    private String Objective;
    private List<Challenge> challengeList;
    private List<String> accountStatement;

    public PiggyBank( int id, String objective, Double targetAmount) {
        this.balance = 0.0;
        this.id = id;
        Objective = objective;
        this.targetAmount = targetAmount;
        this.accountStatement = new ArrayList<>();
    }
    public void depositFunds(double d){
        balance += d;
        accountStatement.add("deposited "+d);
    }
    public boolean WithDrawFunds(double d){
        if(d<=balance && challengeList.isEmpty()){
            balance -= d;
            accountStatement.add("withDraw "+d);
            return true;
        }
        return false;
    }
    public void AddChallenge(Challenge c){
        challengeList.add(c);
    }
    public Challenge getDetails(int c_id){
        for(Challenge c:challengeList){
            if(c.getId()==c_id){
                return c;
            }
        }
        return null;
    }
    public List<String> getAccountStatement(){
        return accountStatement;
    }
}
class Challenge{
    private int id;
    private Double targetAmount;
    private Double amountLeft;
    private Date stDate;
    private Date endDate;

    public Challenge(Double amountLeft, Date endDate, int id, Date stDate, Double targetAmount) {
        this.amountLeft = amountLeft;
        this.endDate = endDate;
        this.id = id;
        this.stDate = stDate;
        this.targetAmount = targetAmount;
    }
    public int getId() {
        return id;
    }
    public Double getAmountLeft() {
        return amountLeft;
    }
}
class ExpenseTrack{
     Map<String , Double> expenses;
     Map<String , Double> budgeting;

     ExpenseTrack(){
         expenses = new HashMap<>();
         budgeting = new HashMap<>();
     }
     public void addExpense(String s , Double d){
         expenses.put(s, expenses.getOrDefault(s,0.0)+d);
     }
     public void addBudgeting(String s , Double d){
         budgeting.put(s, budgeting.getOrDefault(s, 0.0)+d);
     }
    public void trackExpense() {
        System.out.println("Category\tBudgeted\tSpent\tRemaining");
        for (String category : budgeting.keySet()) {
            double budget = budgeting.getOrDefault(category, 0.0);
            double spent = expenses.getOrDefault(category, 0.0);
            double remaining = budget - spent;
            System.out.println(category + "\t" + budget + "\t" + spent + "\t" + remaining);
        }

        // Also check for expense categories that were not budgeted
        for (String category : expenses.keySet()) {
            if (!budgeting.containsKey(category)) {
                double spent = expenses.get(category);
                System.out.println(category + "\t" + "0.0" + "\t" + spent + "\t" + (-spent));
            }
        }
    }
}


public class PiggyBankApp {
    Map<User , String> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("login  1 or signup 2 ");
        int op = sc.nextInt();
        if(op==1){
            System.out.println("type email and ")
        }
    }
}
