//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//abstract class Users{
//    protected int id;
//    protected String name;
//    protected String email;
//    protected String pass;
//    protected String role;
//
//    Users(int id, String name, String email, String password,String role){
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.pass = password;
//        this.role = role;
//    }
//
//    abstract void displayInfo();
//    abstract boolean Login();
//    abstract boolean SignUp();
//}
//class Customers extends Users{
//    List<Order> ordersList;
//    List<String> address_list;
//    FoodManager foodManager;
//    List<Food> cart;
//
//    Customers(int id, String name, String email, String password, String role, FoodManager foodManager) {
//        super(id, name, email, password, role);
//        ordersList = new ArrayList<>();
//        address_list = new ArrayList<>();
//        this.foodManager = foodManager;
//    }
//    public void showAllFood(){
//        foodManager.showAllFoods();
//    }
//    public void addToCart(Food f){
//        cart.add(f);
//    }
//    public Double getSum(){
//       Double sum=0.0;
//       for(Food f:cart){
//           sum+=f.getPrice();
//       }
//       return sum;
//    }
//    public void placeOrder(){
//        ordersList.add(new Order(cart , , ))
//    }
//    @Override
//    void displayInfo() {
//
//    }
//    @Override
//    boolean Login() {
//        return false;
//    }
//    @Override
//    boolean SignUp() {
//        return false;
//    }
//}
//class ResManager extends Users{
//
//    List<Food> resfoodList;
//    List<Order> orderRecivedList;
//    int res_id;
//    FoodManager foodManager;
//
//
//    ResManager(int id, String name, String email, String password, String role, FoodManager foodManager) {
//        super(id, name, email, password, role);
//        resfoodList = new ArrayList<>();
//        orderRecivedList = new ArrayList<>();
//        this.res_id = id;
//        this.foodManager = foodManager;
//    }
//
//    public void AddFood(String description, int id, String name, Double price){
//        Food f = new Food(description,id, name ,price,res_id);
//        resfoodList.add(f);
//        foodManager.addFoods(f);
//    }
//    public void removeFood(int id){
//        for(Food f:resfoodList){
//            if(f.getRes_id()==id){
//                resfoodList.remove(f);
//                foodManager.removeFood(f);
//            }
//        }
//    }
//    public int getRes_id(){return res_id;}
//
//    @Override
//    void displayInfo() {
//
//    }
//    @Override
//    boolean Login() {
//        return false;
//    }
//    @Override
//    boolean SignUp() {
//        return false;
//    }
//}
//class DeliveryBoy extends Users{
//
//    List<Order> ordersList;
//    boolean isAvailable;
//
//    DeliveryBoy(int id, String name, String email, String password, String role) {
//        super(id, name, email, password, role);
//    }
//    public boolean getIsAvailable(){return isAvailable;}
//
//    @Override
//    void displayInfo() {
//
//    }
//    @Override
//    boolean Login() {
//        return false;
//    }
//    @Override
//    boolean SignUp() {
//        return false;
//    }
//}
//
//
//class FoodManager{
//    List<ResManager> resManagerList;
//    List<Food> foodList;
//    List<DeliveryBoy> d_Boy;
//
//    public FoodManager() {
//        this.d_Boy = new ArrayList<>();
//        this.foodList = new ArrayList<>();
//        this.resManagerList = new ArrayList<>();
//    }
//
//    public void addFoods(Food f) {
//        foodList.add(f);
//    }
//    public void removeFood(Food f) {
//        foodList.remove(f);
//    }
//    public void showAllFoods() {
//        for(Food f:foodList){
//            displayInfo(f);
//        }
//    }
//    public void displayInfo(Food f) {
//        System.out.println("===== Food Details =====");
//        System.out.println("Food ID       : " + f.getId());
//        System.out.println("Name          : " + f.getName());
//        System.out.println("Description   : " + f.getDescription());
//        System.out.println("Price         : $" + String.format("%.2f", f.getPrice()));
//        System.out.println("Restaurant ID : " + f.getRes_id());
//        System.out.println("========================");
//    }
//    public ResManager findRes(int res_id){
//        for(ResManager res:resManagerList){
//            if(res.getRes_id()==res_id){
//                return res;
//            }
//        }
//        return null;
//    }
//    public DeliveryBoy findAvailable_dboy() {
//        for(DeliveryBoy b:d_Boy){
//            if(b.getIsAvailable()){
//                return b;
//            }
//        }
//        return null;
//    }
//}
//class Food{
//    private int id;
//    private String name;
//    private String description;
//    private int res_id;
//    private Double price;
//
//    public String getDescription() {
//        return description;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public int getRes_id() {
//        return res_id;
//    }
//
//    public Food(String description, int id, String name, Double price, int res_id) {
//        this.description = description;
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.res_id = res_id;
//    }
//}
//class Order{
//    static int order_id=0;
//    Double price;
//    List<Food> foodlist;
//    DeliveryBoy d_boy;
//    ResManager res_manager;
//    FoodManager foodManager;
//
//    public Order(List<Food> foodlist, Double price, FoodManager foodManager, int res_id) {
//
//        this.foodlist = foodlist;
//        this.order_id = this.order_id++;
//        this.price = price;
//        this.foodManager = foodManager;
//        res_manager = foodManager.findRes(res_id);
//        d_boy = foodManager.findAvailable_dboy();
//
//    }
//
//
//}
//
//
//public class FoodDelivery {
//}
