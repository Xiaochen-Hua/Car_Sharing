package carsharing;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{
    public void addCustomer(String name){
        Database.addCustomer(name);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return Database.getAllCustomers();
    }

    @Override
    public void returnCar(int customer_id){
        Database.returncar(customer_id);
    }
    @Override
    public void updateCustomerRented_car_id(int id, int customer_id){
        Database.updateRented_Car_Id(id, customer_id);
    }

    @Override
    public int getRented_car_id(int customer_id){
        return Database.getRented_car_id(customer_id);
    }
}
