public class Driver {
    private int id;
    private long availabilityTime;

    public Driver(int id, long availabilityTime) {
        this.id = id;
        this.availabilityTime = availabilityTime;
    }

    public int getId() { return id; }
    public long getAvailabilityTime() { return availabilityTime; }
    public void setAvailabilityTime(long availabilityTime) { this.availabilityTime = availabilityTime; }
}