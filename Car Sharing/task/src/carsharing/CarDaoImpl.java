package carsharing;

import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao{

    public CarDaoImpl(){
    }

    public List<car> getAllcars(int company_id){
        List<car> cars = Database.getAllCars(company_id);
        return cars;
    }

    public List<car> getAllavailablecars(int company_id, int customer_id){
        return Database.getAllCarsAvailable(company_id, customer_id);
    }



    public List<car> carList(){
        List<car> carlist = Database.getAllCarsnum();
        return carlist;
    }

    public int getAllcarsnum(){
        List<car> cars = Database.getAllCarsnum();
        return cars.size();
    }
    public void addcar(String name, int id, int company_id){
        Database.addcar(name, id, company_id);
    }
}
