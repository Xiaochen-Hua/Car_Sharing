package carsharing;

import java.util.List;

public interface CarDao {

    List<car> getAllcars(int company_id);
    void addcar(String name, int id, int company_id);
    int getAllcarsnum();
    List<car> carList();
    List<car> getAllavailablecars(int company_id, int customer_id);

}
