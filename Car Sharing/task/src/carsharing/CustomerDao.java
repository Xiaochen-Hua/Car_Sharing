package carsharing;
import java.util.List;

public interface CustomerDao {
    void addCustomer(String name);
    List<Customer> getAllCustomer();
    void returnCar(int customer_id);
    void updateCustomerRented_car_id(int id, int customer_id);
    int getRented_car_id(int customer_id);
}
