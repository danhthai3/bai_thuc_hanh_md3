package staff.model;

public class Staff {
    private int id;
    private String name;
    private String email;
    private String address;
    private double salary;
    private int departmentId;

    public Staff() {
    }

    public Staff(int id, String name, String email, String address, double salary, int departmentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public Staff(String name, String email, String address, double salary, int departmentId) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
