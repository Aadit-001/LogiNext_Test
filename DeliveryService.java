import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryService {
    private final Connection connection;

    public DeliveryService(Connection connection) {
        this.connection = connection;
    }

    public List<CustomerOrder> getOrdersSortedByTime() throws SQLException {
        List<CustomerOrder> orders = new ArrayList<>();
        String sql = "SELECT customer_id, order_time, travel_time FROM customers ORDER BY order_time";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                orders.add(new CustomerOrder(
                    rs.getInt("customer_id"), rs.getInt("order_time"), rs.getInt("travel_time")
                ));
            }
        }
        return orders;
    }

    public List<Driver> getAllDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT driver_id FROM drivers ORDER BY driver_id";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("driver_id"), 0));
            }
        }
        return drivers;
    }

    public void updateAssignedDriver(int customerId, Integer driverId) throws SQLException {
        String sql = "UPDATE customers SET assigned_driver_id = ? WHERE customer_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            if (driverId != null) {
                pstmt.setInt(1, driverId);
            } else {
                pstmt.setNull(1, java.sql.Types.INTEGER);
            }
            pstmt.setInt(2, customerId);
            pstmt.executeUpdate();
        }
    }

    public void assignAllOrders() throws SQLException {
        List<CustomerOrder> orders = getOrdersSortedByTime();
        List<Driver> drivers = getAllDrivers();

        for (CustomerOrder order : orders) {
            boolean assigned = false;
            for (Driver driver : drivers) {
                if (driver.getAvailabilityTime() <= order.getOrderTime()) {
                    System.out.println("C" + order.getId() + " - D" + driver.getId());
                    driver.setAvailabilityTime((long) order.getOrderTime() + order.getTravelTime());
                    updateAssignedDriver(order.getId(), driver.getId());
                    assigned = true;
                    break;
                }
            }

            if (!assigned) {
                System.out.println("C" + order.getId() + " - No Food :-(");
                updateAssignedDriver(order.getId(), null);
            }
        }
    }
}