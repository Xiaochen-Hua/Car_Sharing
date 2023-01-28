package carsharing;

public class car {
    private String name;
    private int id;
    private int company_id;

    car(String name, int id){
        this.name = name;
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id(){
        return company_id;
    }
    public void setCompany_id(int company_id){
        this.company_id = company_id;
    }
}
