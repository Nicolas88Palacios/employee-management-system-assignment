package employeemanagementsystem;

public class NewEmployee extends Employee implements Bonus {
    public NewEmployee(String fName, String lName, String dOb, Object gender, int salary, Object department) {
        super(fName, lName, dOb, gender, salary, department);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void addBonus() {
        throw new UnsupportedOperationException("Not supported yet.");
        // To change body of generated methods, choose Tools | Templates.
    }
}
