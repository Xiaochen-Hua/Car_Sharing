package carsharing;
import java.util.List;

public interface CompanyDao {
    List<Company> getAllCompanies();
    void addCompany(String name);

    String getCompany(int id);

}
