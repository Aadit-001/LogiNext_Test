public class CustomerOrder {
    private int id;
    private int orderTime;
    private int travelTime;

    public CustomerOrder(int id, int orderTime, int travelTime) {
        this.id = id;
        this.orderTime = orderTime;
        this.travelTime = travelTime;
    }

    public int getId() { return id; }
    public int getOrderTime() { return orderTime; }
    public int getTravelTime() { return travelTime; }
}