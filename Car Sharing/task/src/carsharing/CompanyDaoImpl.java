package carsharing;

import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    public CompanyDaoImpl(){
    }

    @Override
    public List<Company> getAllCompanies(){
        List<Company> companies = Database.getAllCompanies();
        return companies;
    }

    @Override
    public void addCompany(String name){
        Company company = new Company(name, getAllCompanies().size()+1);
        Database.update(company.getName(), company.getId());
    }

    @Override
    public String getCompany(int id){
        List<Company> companies = Database.getAllCompanies();
        return companies.get(id - 1).getName();
    }
}
