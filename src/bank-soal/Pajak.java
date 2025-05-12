import java.util.Scanner;

class InputStruct {
    String name;
    String species;
    double salary;
}

interface Living {
    public double getTax();
}

interface Worker {
    public void setSalary(double amount);
}

abstract class Beings {
    private static int count;
    private String name;
    private double salary;

    public Beings(String name, double salary) {
        setName(name);
        setSalary(salary);
        ++count;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getCount() {
        return count;
    }

    public double getSalary() {
        return this.salary;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return String.format("%s (%s) membayar pajak sebesar %.1f",
                getName(),
                getClass().getSimpleName(),
                getTax());
    }

    public abstract double getTax();
}

class Humanoid extends Beings implements Living, Worker {
    public Humanoid(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double getTax() {
        return 0.1 * getSalary();
    }
}

class Reptiloid extends Beings implements Living, Worker {
    public Reptiloid(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double getTax() {
        return 500d;
    }
}

class Bot extends Beings implements Living, Worker {
    public Bot(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double getTax() {
        return 0d;
    }
}

public class Pajak {
    private static Scanner scanner = new Scanner(System.in);

    private static Beings[] newBeings(int count) {
        Beings[] beings = new Beings[count];

        for (int i = 0; i < count; i++) {
            InputStruct inputStruct = new InputStruct();

            inputStruct.name = scanner.next();
            inputStruct.species = scanner.next();
            inputStruct.salary = scanner.nextDouble();

            try {
                scanner.nextLine();
            } catch (Exception e) {
                // Fix java.util.NoSuchElementException
            }

            switch (inputStruct.species) {
                case "Humanoid":
                    beings[i] = new Humanoid(inputStruct.name, inputStruct.salary);
                    break;
                case "Reptiloid":
                    beings[i] = new Reptiloid(inputStruct.name, inputStruct.salary);
                    break;
                case "Bot":
                    beings[i] = new Bot(inputStruct.name, inputStruct.salary);
            }
        }

        scanner.close();

        return beings;
    }

    private static void displayBeings(Beings... beings) {
        for (int i = 0; i < beings.length; i++) {
            System.out.println(beings[i]);
        }

        System.out.printf(
                "Total alien terdaftar: %d\n",
                Beings.getCount());
    }

    public static void main(String[] args) {
        displayBeings(newBeings(Integer.parseUnsignedInt(scanner.nextLine())));
    }
}
