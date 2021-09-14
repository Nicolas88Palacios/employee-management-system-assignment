package employeemanagementsystem;

import static employeemanagementsystem.ManageEmployees.myEmp;
import static employeemanagementsystem.ManageEmployees.sc;

public class Statistics {
    public static void statMenu() {
        while (true) {
            System.out.println("\n*************************************");
            System.out.println(" 1. Average wage at company");
            System.out.println(" 2. Highest and lowest salary");
            System.out.println(" 3. Total bonus");
            System.out.println(" 4. Women in percentage");
            System.out.println(" 5. Men in percentage (per category)");
            System.out.println(" 0. Back to main menu");
            System.out.println("*************************************\n");

            String input = sc.nextLine();

            switch (input) {
                case "0":
                    ManageEmployees.menuA();
                    break;
                case "1":
                    averageWage();
                    break;
                case "2":
                    highestLowestSalary();
                    break;
                case "3":
                    totalBonus();
                    break;
                case "4":
                    percentageWomen();
                    break;
                case "5":
                    percentageMen();
                    break;
                default:
                    ManageEmployees.errorMessage();
            }

        }
    }

    public static void averageWage() {

        int sum = 0;
        for (Employee emp : myEmp) {
            sum = sum + emp.getSalary() + emp.getBonus();

        }
        System.out.println("-----------------------------------------------");
        System.out.println("Average wage at company is: " + sum / myEmp.size());
        System.out.println("Average salary at company: " + sum / myEmp.size());
        System.out.println("-----------------------------------------------\n");

    }

    public static void highestLowestSalary() {
        int highest = 0;
        int lowest = myEmp.get(0).getSalary();
        int idHighest = 0;
        int idLowest = 0;

        for (Employee emp : myEmp) {

            if (emp.getSalary() > highest) {
                highest = emp.getSalary();
                idHighest = emp.getId();
            }
        }
        for (Employee emp : myEmp) {
            if (emp.getSalary() < lowest) {
                lowest = emp.getSalary();
                idLowest = emp.getId();
            }
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Highest salary: " + highest + "\nLowest salary: " + lowest);
        System.out.println("---------------------------------------");
        System.out.println("Highest salary belongs to\n" + myEmp.get(idHighest - 1).toString());
        System.out.println("---------------------------------------");
        System.out.println("Lowest salary belongs to\n" + myEmp.get(idLowest - 1).toString());
        System.out.println("-----------------------------------------------\n");
    }

    public static void totalBonus() {
        int total = 0;
        int totalM = 0;
        int totalD = 0;
        int totalT = 0;

        for (Employee emp : myEmp) {
            total = total + emp.getBonus();
            if (emp.getDepartment().equals("Management")) {
                totalM = totalM + emp.getBonus();
            } else if (emp.getDepartment().equals("Development")) {
                totalD = totalD + emp.getBonus();
            } else if (emp.getDepartment().equals("Technician")) {
                totalT = totalT + emp.getBonus();
            }
        }
        
        System.out.println("-----------------------------------------------");
        System.out.println("Total bonus in Management: " + totalM);
        System.out.println("Total bonus in Development: " + totalD);
        System.out.println("Total bonus i Technicians: " + totalT);
        System.out.println("Total bonus at this company: " + total);
        System.out.println("-----------------------------------------------\n");

    }

    public static void percentageWomen() {
        int women = 0;

        for (Employee emp : myEmp) {
            if (emp.getGender().equals("Female")) {
                women = women + 1;
            }
        }
        double procent = (women * 100) / myEmp.size();
        String result = String.format("%.2f", procent);
        System.out.println("--------------------------------------------------");
        System.out.println("Percentage of Women at this company: " + result + " %");
        System.out.println("--------------------------------------------------\n");

    }

    public static void percentageMen() {
        double totalD = 0;
        double totalM = 0;
        double totalT = 0;
        double management = 0;
        double developer = 0;
        double tech = 0;
        for (Employee emp : myEmp) {
            if (emp.getDepartment().equals("Management")) {
                totalM = totalM + 1;
                if (emp.getGender().equals("Male") && emp.getDepartment().equals("Management")) {
                    management = management + 1;
                }
            } else if (emp.getDepartment().equals("Development")) {
                totalD = totalD + 1;
                if (emp.getGender().equals("Male") && emp.getDepartment().equals("Development")) {
                    developer = developer + 1;
                }
            } else if (emp.getDepartment().equals("Technician")) {
                totalT = totalT + 1;
                if (emp.getGender().equals("Male") && emp.getDepartment().equals("Technician")) {
                    tech = tech + 1;
                }
            }
        }

        double manPerc = (management * 100) / totalM;
        double manPercDev = ((developer * 100) / totalD);
        double manPercTech = ((tech * 100) / totalT);

        String resultatM = String.format("%.2f", manPerc);
        String resultatD = String.format("%.2f", manPercDev);
        String resultatT = String.format("%.2f", manPercTech);

        System.out.println("--------------------------------------------------");
        System.out.println("\nMen working in management: " + resultatM + " %" + "\nMen working in development: "
                + resultatD + " %" + "\nMen working in technician: " + resultatT + " %");
        System.out.println("--------------------------------------------------\n");

    }

}
