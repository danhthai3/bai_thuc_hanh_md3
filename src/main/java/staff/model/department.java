package staff.model;

public class department {
    private int id;
    private int staff_id;
    private String name_department;

    public department(int staff_id, String name_department) {
        this.staff_id = staff_id;
        this.name_department = name_department;
    }

    public department(int id, int staff_id, String name_department) {
        this.id = id;
        this.staff_id = staff_id;
        this.name_department = name_department;
    }

    public department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getName_department() {
        return name_department;
    }

    public void setName_department(String name_department) {
        this.name_department = name_department;
    }
}
