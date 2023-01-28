package carsharing;

import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private CompanyDao companyDao;
    private CarDao carDao;
    private CustomerDao customerDao;
    public Menu(){
        sc = new Scanner(System.in);
        companyDao = new CompanyDaoImpl();
        carDao = new CarDaoImpl();
        customerDao = new CustomerDaoImpl();
    }
    public void startMainMenu(){
        while (true){
            System.out.println("1. Log in as a manager");
            System.out.println("2. Log in as a customer");
            System.out.println("3. Create a customer");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input){
                case 0:
                    System.exit(0);
                case 1:
                    startManagerMenu();
                case 2:
                    startCustomerList();
                case 3:
                    System.out.println("\nEnter the customer name:");
                    String cusname = sc.nextLine();
                    customerDao.addCustomer(cusname);
                    System.out.println("The customer was added!");

            }
        }
    }

    public void startCustomerList(){
        while(true){
            if(customerDao.getAllCustomer().isEmpty()){
                System.out.println("\nThe customer list is empty!\n");
                startMainMenu();
            }
            else{
                System.out.println("\nCustomer list:");
                for(Customer customer : customerDao.getAllCustomer()){
                    System.out.println(customer.getId() + ". " + customer.getName());
                }
                System.out.println("0. Back");
                int input = sc.nextInt();
                sc.nextLine();
                if(input == 0){
                    startMainMenu();
                }
                else{
                    System.out.println();
                    startCustomerMenu(input);
                }
            }
        }
    }

    public void startCustomerMenu(int input){

        while(true){
            System.out.println("1. Rent a car");
            System.out.println("2. Return a rented car");
            System.out.println("3. My rented car");
            System.out.println("0. Back");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 0:
                    startMainMenu();
                case 1:
                    if(customerDao.getRented_car_id(input) != 0){
                        System.out.println("You've already rented a car!");
                        break;
                    }
                    else{
                        startCustomerCompanyList(input);
                        break;
                    }

                case 2:
                    if(customerDao.getRented_car_id(input) != 0){
                        customerDao.returnCar(input);
                        System.out.println("You've returned a rented car!");
                        break;
                    }
                    else{
                        System.out.println("\nYou didn't rent a car!\n");
                        break;
                    }
                case 3:
                    if(customerDao.getRented_car_id(input) != 0){
                        System.out.println("Your rented car:");
                        int carid = customerDao.getRented_car_id(input);
                        car rented_car = carDao.carList().get(carid-1);
                        System.out.println(rented_car.getName());
                        System.out.println("Company:");
                        System.out.println(companyDao.getAllCompanies().get(rented_car.getCompany_id()).getName());
                    }
                    else{
                        System.out.println("\nYou didn't rent a car!\n");
                        break;
                    }

            }

        }
    }

    public void startCustomerCompanyList(int input){
        if(companyDao.getAllCompanies().isEmpty()){
            System.out.println("The company list is empty!");
            startCustomerMenu(input);
        }
        else{
            System.out.println("\nChoose a company:");
            for(Company company : companyDao.getAllCompanies()){
                System.out.println(company.getId() + ". " + company.getName());
            }
            System.out.println("0. Back");
            int companychoice = sc.nextInt();
            sc.nextLine();
            if(companychoice == 0){
                startCustomerMenu(input);
            }
            else{
                startCustomerCarList(input, companychoice);
            }
        }
    }

    public void startCustomerCarList(int customerID, int companyID){
        if(carDao.getAllavailablecars(companyID, customerID).isEmpty()){
            System.out.println("No available cars in the " +
                    companyDao.getAllCompanies().get(companyID - 1).getName() +
                    " company");
            startCustomerMenu(customerID);
        }
        else{
            System.out.println("\nChoose a car:");
            for(int i = 1; i <= carDao.getAllavailablecars(companyID, customerID).size(); i++){
                System.out.println(i + ". " + carDao.getAllavailablecars(companyID, customerID).get(i-1).getName());
            }
            System.out.println("0. Back");
            int carchoice = sc.nextInt();
            sc.nextLine();
            if(carchoice == 0){
                startCustomerMenu(customerID);
            }
            else{
                System.out.println("You rented \'" + carDao.getAllavailablecars(companyID, customerID).get(carchoice-1).getName() + "\'");
                customerDao.updateCustomerRented_car_id(carDao.getAllavailablecars(companyID, customerID).get(carchoice-1).getId(), customerID);
                startCustomerMenu(customerID);
            }

        }

    }

    public void startCompanyList(){
        while(true){
            if(companyDao.getAllCompanies().isEmpty()){
                System.out.println("\nThe company list is empty\n");
                startManagerMenu();
            }
            else{
                System.out.println("\nChoose the company:");
                for(Company company : companyDao.getAllCompanies()){
                    System.out.println(company.getId() + ". " + company.getName());
                }
                System.out.println("0. Back");
                int input = sc.nextInt();
                sc.nextLine();
                if(input == 0){
                    startManagerMenu();
                }
                else {
                    System.out.println();
                    System.out.println(companyDao.getCompany(input) + " company");
                    startCompanyMenu(input);
                }
            }
        }
    }

    public void startCompanyMenu(int company_id){
        while(true){
            System.out.println("1. Car list");
            System.out.println("2. Create a car");
            System.out.println("0. Back");
            int input = sc.nextInt();
            sc.nextLine();

            switch (input){
                case 0:
                    startManagerMenu();
                case 1:
                    if(carDao.getAllcars(company_id).isEmpty()){
                        System.out.println("\nThe car list is empty!\n");
                    }
                    else{
                        System.out.println("\nCar list:");
                        for(int i = 1; i <= carDao.getAllcars(company_id).size(); i++){
                            System.out.println(i + ". " + carDao.getAllcars(company_id).get(i-1).getName());
                        }
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.println("\nEnter the car name:");
                    String line = sc.nextLine();
                    carDao.addcar(line, carDao.getAllcarsnum()+1, company_id);
                    System.out.println("The car was added!\n");
                    break;
            }
        }
    }

    public void startManagerMenu(){
        while(true){
            System.out.println("1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input){
                case 0:
                    startMainMenu();
                case 1:
                    startCompanyList();
                case 2:
                    System.out.println("\nEnter the company name:");
                    String name = sc.nextLine();
                    companyDao.addCompany(name);
                    System.out.println("The company was created!\n");
                    break;
            }
        }
    }
}
