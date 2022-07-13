package member.dto;

public class Employees {
    private int employees_id;
    private String emp_name;
    private String email;

    public Employees(int employees_id, String emp_name, String email) {
        this.employees_id = employees_id;
        this.emp_name = emp_name;
        this.email = email;
    }
    public Employees(){}

    public int getEmployees_id() {
        return employees_id;
    }

    public void setEmployees_id(int employees_id) {
        this.employees_id = employees_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean matchPassword(String pwd){
        return password.equals(pwd);
    }
}
