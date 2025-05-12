import java.util.Scanner;

class UndefinedTransmissionException extends Exception {
    UndefinedTransmissionException(String errorMessage) {
        super(errorMessage);
    }
}

class InvalidCarCapacityException extends Exception {
    InvalidCarCapacityException(String errorMessage) {
        super(errorMessage);
    }
}

abstract class Vehicle {
    private String type;
    private String manufacturer;
    private String model;
    private int productionYear;
    private int dayCount;
    private double initialTax;

    public Vehicle(String type, String manufacturer, String model, int productionYear, int dayCount) {
        setType(type);
        setManufacturer(manufacturer);
        setModel(model);
        setProductionYear(productionYear);
        setDayCount(dayCount);
        setInitialTax(50000d);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public void setInitialTax(double initialTax) {
        this.initialTax = initialTax;
    }

    public String getType() {
        return this.type;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public int getProductionYear() {
        return this.productionYear;
    }

    public int getDayCount() {
        return this.dayCount;
    }

    public double getInitialTax() {
        return this.initialTax;
    }

    public String toString() {
        return String.format(
                "Merk: " + getManufacturer() + "\n" +
                        "Model: " + getModel() + "\n" +
                        "Tahun Produksi: " + getProductionYear());
    }

    public abstract void setDailyCost(double dailyCost);

    public abstract double getRentCost();
}

class Car extends Vehicle {
    private int capacity;
    private double dailyCost;
    private double costPerCapacity;

    public Car(String type, String manufacturer, String model, int productionYear, int dayCount, int capacity)
            throws InvalidCarCapacityException {
        super(type, manufacturer, model, productionYear, dayCount);
        setCapacity(capacity);
        setDailyCost(250000d);
        setCostPerCapacity(25000d);
    }

    public void setCapacity(int capacity) throws InvalidCarCapacityException {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new InvalidCarCapacityException("Invalid car capacity");
        }
    }

    @Override
    public void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }

    public void setCostPerCapacity(double costPerCapacity) {
        this.costPerCapacity = costPerCapacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public double getDailyCost() {
        return this.dailyCost;
    }

    public double getCostPerCapacity() {
        return this.costPerCapacity;
    }

    @Override
    public double getRentCost() {
        return getInitialTax() + (getDayCount() * getDailyCost()) + (getCapacity() * getCostPerCapacity());
    }

    @Override
    public String toString() {
        return String.format(
                super.toString() + "\n" +
                        "Kapasitas: " + getCapacity() + " penumpang\n" +
                        "Biaya Sewa: Rp%.0f",
                getRentCost());
    }
}

class Motorcycle extends Vehicle {
    private String transmission;
    private double dailyCost;

    public Motorcycle(String type, String manufacturer, String model, int productionYear, int dayCount,
            String transmission)
            throws UndefinedTransmissionException {
        super(type, manufacturer, model, productionYear, dayCount);
        setTransmission(transmission);
        setDailyCost(100000d);
    }

    public void setTransmission(String transmission) throws UndefinedTransmissionException {
        if (transmission.equals("Matic") || transmission.equals("Manual")) {
            this.transmission = transmission;
        } else {
            throw new UndefinedTransmissionException("Invalid transmission type");
        }
    }

    @Override
    public void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }

    public String getTransmission() {
        return this.transmission;
    }

    public double getDailyCost() {
        return this.dailyCost;
    }

    @Override
    public double getRentCost() {
        double result = getInitialTax() + (getDayCount() * getDailyCost());
        if (getTransmission().equals("Matic")) {
            result += (result * 0.2);
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format(
                super.toString() + "\n" +
                        "Jenis Transmisi: " + getTransmission() +
                        "Biaya Sewa: Rp%.0f",
                getRentCost());
    }
}

public class Rent {
    static Scanner scanner = new Scanner(System.in);

    private static Vehicle[] newRent(int count) {
        Vehicle[] vehicles = new Vehicle[count];
        for (int i = 0; i < count; i++) {
            String type = scanner.nextLine();
            String manufacturer = scanner.nextLine();
            String model = scanner.nextLine();
            int productionYear = Integer.parseUnsignedInt(scanner.nextLine());
            int dayCount = Integer.parseUnsignedInt(scanner.nextLine());
            switch (type) {
                case "1":
                    try {
                        int capacity = Integer.parseUnsignedInt(scanner.nextLine());
                        vehicles[i] = new Car(type, manufacturer, model, productionYear, dayCount, capacity);
                    } catch (InvalidCarCapacityException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    } catch (NumberFormatException f) {
                        System.out.println("Invalid integer");
                        System.exit(1);
                    }
                    break;
                case "2":
                    try {
                        String transmission = scanner.nextLine();
                        vehicles[i] = new Motorcycle(type, manufacturer, model, productionYear, dayCount, transmission);
                    } catch (UndefinedTransmissionException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                    break;
            }
        }

        return vehicles;
    }

    private static void displayInfo(Vehicle[] vehicles) {
        System.out.println("=============================");
        System.out.println("Informasi Kendaraan");
        System.out.println("=============================");
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println(vehicles[i]);
            System.out.println("=============================");
        }
    }

    public static void main(String[] args) {
        displayInfo(newRent(Integer.parseUnsignedInt(scanner.nextLine())));
    }
}
