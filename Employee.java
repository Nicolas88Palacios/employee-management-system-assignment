package employeemanagementsystem;

public abstract class Employee  {
    protected String fName;
    protected String lName;
    protected String dOb;
    protected Object gender;
    protected int salary;
    protected  Object department;
    protected int id;
    static int idGenerator = 0001;
    protected int bonus;
    
    public Employee(String fName, String lName, String dOb, Object gender, int salary, Object department) {
        this.fName = fName;
        this.lName = lName;
        this.dOb = dOb;
        this.gender = gender;
        this.salary = salary;
        this.department = department;
        this.id = idGenerator;
        idGenerator++;
        this.bonus = 0;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getdOb() {
        return dOb;
    }

    public void setdOb(String dOb) {
        this.dOb = dOb;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Object getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(int idGenerator) {
        Employee.idGenerator = idGenerator;
    }

    @Override
    public String toString() {
        return  "*********************************************" +
                "\nEmployee ID: " + id +   
                "\nDepartment: " + department +
                "\nName: " + fName + " " + lName + 
                "\nDate of birth: " + dOb + 
                "\nGender: " + gender + 
                "\nSalary: " + salary + 
                "\nBonus: " + bonus +
                "\n*********************************************\n";
    }
    
}
