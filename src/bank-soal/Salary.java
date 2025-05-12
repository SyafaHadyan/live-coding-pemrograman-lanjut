import java.util.Scanner;
import java.time.LocalDate;

abstract class Employee {
    private String name;
    private String KTP;
    private LocalDate birthDate;
    private double salary;
    private boolean isBirthDayMonth;

    public Employee(String name, String KTP, LocalDate birthDate, double salary, boolean isBirthDayMonth) {
        setName(name);
        setKTP(KTP);
        setBirthDate(birthDate);
        setSalary(salary);
        setIsBirthdayMonth(isBirthDayMonth);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKTP(String KTP) {
        this.KTP = KTP;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setIsBirthdayMonth(boolean isBirthDayMonth) {
        this.isBirthDayMonth = isBirthDayMonth;
    }

    public String getName() {
        return this.name;
    }

    public String getKTP() {
        return this.KTP;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public double getSalary() {
        return this.salary;
    }

    public boolean isBirthDayMonth() {
        return this.isBirthDayMonth;
    }

    public abstract double getIncome();

    public abstract String toString();
}

class Manager extends Employee {
    private double allowances;

    public Manager(String name, String KTP, LocalDate birthDate, double salary, boolean isBirthDayMonth,
            double allowances) {
        super(name, KTP, birthDate, salary, isBirthDayMonth);
        setAllowances(allowances);
    }

    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }

    public double getAllowances() {
        return this.allowances;
    }

    @Override
    public double getIncome() {
        double result = getSalary() + getAllowances();
        if (isBirthDayMonth()) {
            result += getSalary() * 0.1;
        }

        return result;
    }

    @Override
    public String toString() {
        LocalDate birthDate = getBirthDate();
        return String.format(getName() + "\n" + getKTP() + "\n" + "%02d/%02d/%02d\n%.1f\n%.1f",
                birthDate.getDayOfMonth(), birthDate.getMonthValue(), birthDate.getYear(), getSalary(),
                getIncome());
    }
}

class PermanentStaff extends Employee {
    private int hoursWorked;
    private double hourlySalary;

    public PermanentStaff(String name, String KTP, LocalDate birthDate, double salary, boolean isBirthDayMonth,
            int hoursWorked, double hourlySalary) {
        super(name, KTP, birthDate, salary, isBirthDayMonth);
        setHoursWorked(hoursWorked);
        setHourlySalary(hourlySalary);
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setHourlySalary(double hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public int getHoursWorked() {
        return this.hoursWorked;
    }

    public double getHourlySalary() {
        return this.hourlySalary;
    }

    @Override
    public double getIncome() {
        return getSalary() + (getHoursWorked() * getHourlySalary());
    }

    @Override
    public String toString() {
        LocalDate birthDate = getBirthDate();
        return String.format(getName() + "\n" + getKTP() + "\n" + "%02d/%02d/%02d\n%.1f\n%.1f",
                birthDate.getDayOfMonth(), birthDate.getMonthValue(), birthDate.getYear(), getSalary(),
                getIncome());
    }
}

class ContractStaff extends Employee {
    private int projectCount;
    private double projectCost;

    public ContractStaff(String name, String KTP, LocalDate birthDate, double salary, boolean isBirthDayMonth,
            int projectCount, double projectCost) {
        super(name, KTP, birthDate, salary, isBirthDayMonth);
        setProjectCount(projectCount);
        setProjectCost(projectCost);
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public void setProjectCost(double projectCost) {
        this.projectCost = projectCost;
    }

    public int getProjectCount() {
        return this.projectCount;
    }

    public double getProjectCost() {
        return this.projectCost;
    }

    @Override
    public double getIncome() {
        return getSalary() * (getProjectCount() * getProjectCost());
    }

    @Override
    public String toString() {
        LocalDate birthDate = getBirthDate();
        return String.format(getName() + "\n" + getKTP() + "\n" + "%02d/%02d/%02d\n%.1f\n%.1f",
                birthDate.getDayOfMonth(), birthDate.getMonthValue(), birthDate.getYear(), getSalary(),
                getIncome());
    }
}

class Intern extends Employee {
    private int duration;

    public Intern(String name, String KTP, LocalDate birthDate, double salary, boolean isBirthDayMonth, int duration) {
        super(name, KTP, birthDate, salary, isBirthDayMonth);
        setDuration(duration);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public double getIncome() {
        return getDuration() * getSalary();
    }

    @Override
    public String toString() {
        LocalDate birthDate = getBirthDate();
        return String.format(getName() + "\n" + getKTP() + "\n" + "%02d/%02d/%02d\n%.1f\n%.1f",
                birthDate.getDayOfMonth(), birthDate.getMonthValue(), birthDate.getYear(), getSalary(),
                getIncome());
    }
}

public class Salary {
    static Scanner scanner = new Scanner(System.in);

    private static void handleInput() {
        String name = scanner.nextLine();
        String KTP = scanner.nextLine();
        String[] date = scanner.nextLine().split("/");
        LocalDate birthDate = LocalDate.of(Integer.parseUnsignedInt(date[2]), Integer.parseUnsignedInt(date[1]),
                Integer.parseUnsignedInt(date[0]));
        double salary = Double.parseDouble(scanner.nextLine());
        boolean isBirthDayMonth = Boolean.parseBoolean(scanner.nextLine());
        String employeeType = scanner.nextLine();
        switch (employeeType) {
            case "1":
                Employee manager = new Manager(name, KTP, birthDate, salary, isBirthDayMonth,
                        Double.parseDouble(scanner.nextLine()));
                System.out.println(manager);
                break;
            case "2":
                Employee permanentStaff = new PermanentStaff(name, KTP, birthDate, salary, isBirthDayMonth,
                        Integer.parseUnsignedInt(scanner.nextLine()), Double.parseDouble(scanner.nextLine()));
                System.out.println(permanentStaff);
                break;
            case "3":
                Employee contractStaff = new ContractStaff(name, KTP, birthDate, salary, isBirthDayMonth,
                        Integer.parseUnsignedInt(scanner.nextLine()), Double.parseDouble(scanner.nextLine()));
                System.out.println(contractStaff);
                break;
            case "4":
                Employee intern = new Intern(name, KTP, birthDate, salary, isBirthDayMonth,
                        Integer.parseUnsignedInt(scanner.nextLine()));
                System.out.println(intern);
                break;
            default:
                System.out.println("Null");
                break;
        }
        ;
    }

    public static void main(String[] args) {
        handleInput();
    }
}
