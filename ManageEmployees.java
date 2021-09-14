package employeemanagementsystem;

import java.util.*;

public class ManageEmployees {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Employee> myEmp = new ArrayList<>();

    public static void menuA() {

        System.out.println("*************************************************");
        System.out.println("Welcome to EmployeeManagementSystem (Pro edition)");
        System.out.println("*************************************************\n");

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println(" 1. Manage employee");
            System.out.println(" 2. Show statistics");
            System.out.println(" 0. Exit");

            System.out.print("INPUT: ");

            String s = sc.nextLine();

            try {
                int input = Integer.parseInt(s);
                switch (input) {
                    case 1:
                        menuB();
                        break;
                    case 2:
                        Statistics.statMenu();
                        break;
                    case 0:
                        goodBye();
                    default:
                        errorMessage();
                }
            } catch (NumberFormatException e) {
                errorMessage();
            }

        }

    }

    public static void menuB() {

        String s;
        while (true) {
            System.out.println("\n****************************");
            System.out.println(" 1. Register new employee");
            System.out.println(" 2. Search");
            System.out.println(" 3. Display all employee");
            System.out.println(" 0. Back to main menu");
            System.out.println("****************************\n");

            try {
                System.out.print("INPUT: ");
                s = sc.nextLine();

                int input = Integer.parseInt(s);

                switch (input) {
                    case 0:
                        menuA();
                        break;
                    case 1:
                        addEmployee();
                        addList();
                        break;
                    case 2:
                        searchEmployee();
                        break;
                    case 3:
                        System.out.println(myEmp);
                        System.out.println("Total amount of employees: " + myEmp.size() + "\n");
                        break;

                    default:
                        errorMessage();
                        break;
                }
            } catch (NumberFormatException e) {
                errorMessage();
            }
        }

    }

    public static void errorMessage() {
        System.out.println("\n*****ERROR! Wrong input*****\n");
    }

    public static void addEmployee() {

        String department = "";
        String gender = "";
        int salary = 0;

        System.out.println("Enter first Name"); // Adds first nameame
        String fName = sc.nextLine();

        System.out.println("Enter last Name"); // Adds last name
        String lName = sc.nextLine();

        while (true) { // Adds gender
            System.out.println("Enter gender");
            System.out.println(" 1. Male");
            System.out.println(" 2. Female");

            String input = sc.nextLine();
            if (!input.equals("1") && !input.equals("2")) {
                errorMessage();
            } else {
                int genderInput = Integer.parseInt(input);
                switch (genderInput) {
                    case 1:
                        gender = "Male";
                        break;
                    case 2:
                        gender = "Female";
                        break;
                }
                break;
            }
        }

        String dOb = dateOfBirth(); // Date of birth genom en separat metod

        while (true) {
            System.out.println("Enter salary"); // Adds salary
            try {
                salary = sc.nextInt();
                break;
            } catch (Exception e) {
                errorMessage();
            }
            sc.nextLine();
        }

        while (true) { // Adds depatrment
            System.out.println("Choose department");
            System.out.println(" 1. Management");
            System.out.println(" 2. Development");
            System.out.println(" 3. Technicians");
            String input = sc.nextLine();
            if (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                errorMessage();
            } else {
                int depInput = Integer.parseInt(input);
                switch (depInput) {
                    case 1:
                        department = "Management";
                        break;
                    case 2:
                        department = "Development";
                        break;
                    case 3:
                        department = "Technicians";
                        break;
                }
                break;
            }
        }

        Employee emp = new NewEmployee(fName, lName, dOb, gender, salary, department); // Creates a new Employee
        myEmp.add(emp);

        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        System.out.println("*********************************************"); // Prints out created employee
        System.out.println("************ New employee added *************");
        System.out.println(emp.toString());
    }

    public static String dateOfBirth() { // Method to add Date of birth
        int year = 0;
        int month = 0;
        int day = 0;
        while (true) { // Limits input to 2 last digits of year
            System.out.println("Date of birth 'YYMMDD'");
            System.out.print("Enter year: 'YY' ");
            try {
                year = Integer.parseInt(sc.nextLine());
                if (year >= 100) {
                    errorMessage();
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                errorMessage();
            }
        }

        while (true) { // adds month
            System.out.print("Enter month: ");
            String m = sc.nextLine();
            try {
                month = Integer.parseInt(m);
                if (month >= 13 || month <= 0) {
                    errorMessage();
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                errorMessage();
            }
        }

        while (true) { // adds day
            System.out.print("Enter day: ");
            String d = sc.nextLine();
            try {
                day = Integer.parseInt(d);
                if (day <= 0 || day >= 32) {
                    errorMessage();
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                errorMessage();
            }
        }
        // Formats output into 2 digits and fills empty with 0. Example: month 1 shows
        // as month 01
        return String.format("%02d", year) + "/" + String.format("%02d", month) + "/" + String.format("%02d", day);
    }

    static void searchEmployee() { // Search Employee Method
        while (true) {
            // int input = 0;
            System.out.println("\n***************************");
            System.out.println(" 1. Search by name");
            System.out.println(" 2. Search by ID");
            System.out.println(" 3. Search by department");
            System.out.println(" 4. Update info of employee");
            System.out.println(" 0. Back to main menu");
            System.out.println("****************************\n");
            String a = sc.nextLine();
            switch (a) {
                case "1":
                    searchByName();
                    break;
                case "2":
                    searchByID();
                    break;
                case "3":
                    searchByDepartment();
                    break;
                case "4":
                    updateInfo();
                    break;
                case "0":
                    menuA();
                    break;
                default:
                    errorMessage();

            }
        }
    }

    private static void searchByName() {
        int index = 0;
        System.out.println("Enter first name");
        String name = sc.nextLine();

        for (Employee emp : myEmp) {
            if (emp.getfName().equalsIgnoreCase(name)) {
                index = index + 1;
                System.out.println("Employee found:");
                System.out.println(emp.toString());
            }
        }
        if (index == 0) {
            System.out.println("Employee not found");
        }

    }

    public static void addList() {

        Employee emp = new NewEmployee("Magnus", "Gustavsson", "12345", "Male", 40000, "Technician");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment()));

        emp = new NewEmployee("Anders", "Sten", "12345", "Male", 20000, "Management");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        emp = new NewEmployee("Eva", "Granhagen", "12345", "Female", 18000, "Management");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        emp = new NewEmployee("Jenny", "Arnold", "12345", "Female", 15000, "Management");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        emp = new NewEmployee("Doris", "Svensson", "12345", "Female", 25000, "Management");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        emp = new NewEmployee("Rita", "Shmidt", "12345", "Female", 30000, "Management");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        emp = new NewEmployee("Dima", "Ten", "12345", "Male", 25000, "Technician");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        emp = new NewEmployee("Sara", "Lund", "12345", "Female", 11000, "Technician");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        emp = new NewEmployee("Alexander", "Gustafsson", "12345", "Male", 36520, "Management");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        emp = new NewEmployee("Gustav", "Erikson", "12345", "Male", 25000, "Development");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        emp = new NewEmployee("Mona", "Stenbäck", "12345", "Female", 24500, "Management");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

        emp = new NewEmployee("Anna", "Lundqvist", "12345", "Female", 40000, "Management");
        myEmp.add(emp);
        emp.setBonus(addBonus(emp.getDepartment())); // Adds bonus

    }

    private static void searchByID() {

        int index = 0;
        System.out.println("Enter employee ID");
        String i = sc.nextLine();
        int id = Integer.parseInt(i);
        System.out.println("Employee found");
        System.out.println(myEmp.get(id - 1).toString());
    }

    public static void updateInfo() {
        int id;
        while (true) {
            System.out.println("find a person by ID");
            String s = sc.nextLine();

            try {
                id = Integer.parseInt(s);
                System.out.println("Found:");
                System.out.println(myEmp.get(id - 1).toString());
                break;

            } catch (InputMismatchException e) {
                System.out.println("Something is wrong");
            }
        }

        System.out.println("*******************************");
        System.out.println("What would you like to update?");
        System.out.println(" 1. Update name");
        System.out.println(" 2. Update depatment");
        System.out.println(" 3. Update salary");
        System.out.println(" 4. Remove employee");
        System.out.println(" 0. Back to main menu");
        System.out.println("*******************************\n");

        String choice = sc.nextLine();

        switch (choice) {
            case "1": // Enter new name
                System.out.println("Enter new name");
                String newName = sc.nextLine();
                myEmp.get(id - 1).setfName(newName);
                System.out.println("Employee name updated");
                System.out.println(myEmp.get(id - 1).toString());
                break;
            case "2": // choose new depatrment
                System.out.println("Choose new department");
                System.out.println(" 1. Management");
                System.out.println(" 2. Development");
                System.out.println(" 3. Technician");
                String newDep = sc.nextLine();
                switch (newDep) {
                    case "1":
                        myEmp.get(id - 1).setDepartment("Management");
                        myEmp.get(id - 1).setBonus(addBonus("Management"));
                        System.out.println(myEmp.get(id - 1).toString());
                        break;
                    case "2":
                        myEmp.get(id - 1).setDepartment("Development");
                        myEmp.get(id - 1).setBonus(addBonus("Development"));
                        System.out.println(myEmp.get(id - 1).toString());
                        break;
                    case "3":
                        myEmp.get(id - 1).setDepartment("Technician");
                        myEmp.get(id - 1).setBonus(addBonus("Technician"));
                        System.out.println(myEmp.get(id - 1).toString());
                        break;

                    default:
                        errorMessage();

                }
                System.out.println("Department info updated");
                System.out.println(myEmp.get(id - 1).toString());
                break;
            case "3":
                // Update salary
                newSalary(id);
                break;
            case "4":
                removeEmp();
                break;
            case "0":
                menuA();
                break;
            default:

                break;
        }
    }

    public static void newSalary(int id) {

        while (true) {
            System.out.print("Enter new salary: ");
            System.out.println("");
            String i = sc.nextLine();
            try {
                int newSalary = Integer.parseInt(i);

                myEmp.get(id - 1).setSalary(newSalary);
                System.out.println(myEmp.get(id - 1).toString());
                System.out.println("Salary updated");
                break;
            } catch (NumberFormatException e) {
                errorMessage();
            }
        }
    }

    private static void searchByDepartment() {
        while (true) {
            String m = "";
            String d = "";
            String t = "";
            int total = 0;
            System.out.println("Choose department");
            System.out.println(" 1. Management");
            System.out.println(" 2. Development");
            System.out.println(" 3. Technicians");
            System.out.println(" 0. Go back to previous menu");
            String depInput = sc.nextLine();

            switch (depInput) {
                case "1":
                    for (Employee emp : myEmp) {
                        if (emp.getDepartment() == "Management") {
                            m += emp.toString() + "\n";
                            total += 1;
                        }
                    }
                    System.out.println("List of employees in Management:\n" + m);
                    System.out.println("Total amount of employees in Management: " + total + "\n");
                    break;
                case "2":
                    for (Employee emp : myEmp) {
                        if (emp.getDepartment() == "Development") {
                            d += emp.toString() + "\n";
                            total += 1;
                        }
                    }
                    System.out.println("List of employees in Development:\n" + d);
                    System.out.println("Total amount of employees in Development: " + total + "\n");
                    break;
                case "3":
                    for (Employee emp : myEmp) {
                        if (emp.getDepartment() == "Technicians") {
                            t += emp.toString() + "\n";
                            total += 1;
                        }
                    }
                    System.out.println("List of employees in Technicians:\n" + t);
                    System.out.println("Total amount of employees in Technicians: " + total + "\n");
                    break;
                case "0":
                    searchEmployee();
                    break;
                default:
                    errorMessage();
            }
        }
    }

    public static int addBonus(Object emp) {

        int bonus = 0;

        if (emp.equals("Management")) {
            bonus += 5000;

        } else if (emp.equals("Development")) {
            bonus += 3000;
        } else {
            bonus += 1000;
        }
        return bonus;

    }

    public static void removeEmp() {
        System.out.println("Enter employee ID");
        String i = sc.nextLine();
        int id = Integer.parseInt(i);
        System.out.println("Are you sure you want to delete this employee?");
        System.out.println(" 1. YES");
        System.out.println(" 2. NO");
        System.out.println(" 0. Return to main menu");

        System.out.println(myEmp.get(id - 1).toString());
        i = sc.nextLine();
        switch (i) {
            case "1":
                myEmp.get(id - 1).setfName("Empty");
                myEmp.get(id - 1).setlName("Empty");
                myEmp.get(id - 1).setGender("Empty");
                myEmp.get(id - 1).setDepartment("Status: Terminated");
                myEmp.get(id - 1).setdOb("Empty");
                myEmp.get(id - 1).setSalary(0);
                myEmp.get(id - 1).setBonus(0);

                // myEmp.remove(myEmp.get(id -1)); // This method ruins searchByID function
                // after deletion of one employee
                System.out.println("Employee deleted");
                break;
            case "2":
                menuB();
                break;
            default:
                menuA();
                break;
        }
    }

    public static void goodBye() {
        System.out.println("----------------------------------------------------------");
        System.out.println("Thank you for using EmployeeManagementSystem (Pro edition)");
        System.out.println("----------------------------------------------------------");
        System.exit(0);
    }
}