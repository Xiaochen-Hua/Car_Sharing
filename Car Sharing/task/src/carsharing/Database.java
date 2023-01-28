package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static  String DB_URL = "jdbc:h2:file:./src/carsharing/db/";

    public static void init(String name){
        Connection conn = null;
        Statement stmt = null;
        DB_URL = DB_URL + name;
        try{
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database ...");
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);

            System.out.println("Creating table in given database ...");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS COMPANY" +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) UNIQUE NOT NULL)";
            stmt.executeUpdate(sql);

            String carsql = "CREATE TABLE IF NOT EXISTS CAR" +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) UNIQUE NOT NULL, " +
                    "COMPANY_ID INT NOT NULL, " +
                    "CONSTRAINT fk_company " +
                    "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY (ID))";
            stmt.executeUpdate(carsql);

            String customersql = "CREATE TABLE IF NOT EXISTS CUSTOMER" +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) UNIQUE NOT NULL, " +
                    "RENTED_CAR_ID INT, " +
                    "CONSTRAINT fk_car " +
                    "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR (ID))";
            stmt.executeUpdate(customersql);

            System.out.println("Created table in given database ...");

            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public static List<car> getAllCars(int company_id){
        List<car> cars = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM CAR " +
                    "WHERE COMPANY_ID = " + company_id ;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                car newcar = new car(rs.getString(2), rs.getInt(1));
                cars.add(newcar);
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cars;
    }
    public static List<car> getAllCarsAvailable(int company_id, int customer_id){
        List<car> cars = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "SELECT CAR.ID, CAR.NAME, CAR.COMPANY_ID FROM CAR LEFT JOIN CUSTOMER" +
                    " ON CAR.ID = CUSTOMER.RENTED_CAR_ID WHERE CUSTOMER.NAME IS NULL AND CAR.COMPANY_ID = " +
                    company_id ;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                car newcar = new car(rs.getString(2), rs.getInt(1));
                cars.add(newcar);
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cars;
    }

    public static void updateRented_Car_Id(int car_id, int customer_id){
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "UPDATE CUSTOMER" +
                    " SET RENTED_CAR_ID = " + car_id +
                    " WHERE ID = " + customer_id;
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



    public static List<car> getAllCarsnum(){
        List<car> cars = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM CAR ";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                car newcar = new car(rs.getString(2), rs.getInt(1));
                cars.add(newcar);
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cars;
    }

    public static int getRented_car_id(int customer_id){
        int rented_car_id = 0;
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "SELECT RENTED_CAR_ID FROM CUSTOMER " +
                    "WHERE ID = " + customer_id;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                if(rs.wasNull()){
                    rented_car_id = 0;
                    stmt.close();
                    conn.close();
                    return rented_car_id;

                }
                else{
                    rented_car_id = rs.getInt(1);
                    stmt.close();
                    conn.close();
                    return rented_car_id;
                }
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rented_car_id;
    }

    public static List<Company> getAllCompanies(){
        List<Company> companies = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM COMPANY";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Company company = new Company(rs.getString(2), rs.getInt(1));
                companies.add(company);
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return companies;
    }

    public static List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM CUSTOMER";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Customer customer = new Customer(rs.getString(2), rs.getInt(1));
                customers.add(customer);
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return customers;
    }

    public static void returncar(int customer_id){
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "UPDATE CUSTOMER " +
                    "SET RENTED_CAR_ID = NULL" +
                    " WHERE ID = " + customer_id;
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void addCustomer(String name){
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "INSERT INTO CUSTOMER " +
                    "VALUES ( " + (getAllCustomers().size()+1) + ", \'" + name
                    + "\', NULL )";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void addcar(String name, int id, int company_id){
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "INSERT INTO CAR " +
                    "VALUES ( " + id + ", \'" + name
                    + "\', " + company_id + " )";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void update(String name, int id){
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            String sql = "INSERT INTO COMPANY " +
                    "VALUES ( " + id + ", \'" + name
                    + "\')";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
