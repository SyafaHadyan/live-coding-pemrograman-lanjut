import java.util.Scanner;

class VehicleStructInput {
    int type;
    String brand;
    String model;
    double baseCost;
    int duration;
    int additional;
}

abstract class EngineVehicle {
    public abstract String getRegistrationNumber();

    public abstract int getType();

    public abstract String getBrand();

    public abstract String getModel();

    public abstract double getBaseCost();

    public abstract int getDuration();
}

abstract class Vehicle extends EngineVehicle {
    private final String REGISTRATION_NUMBER_PREFIX = "VEH";

    private static int count;
    private String registrationNumber;
    private int type;
    private String brand;
    private String model;
    private double baseCost;
    private int duration;

    public Vehicle(
            int type,
            String brand,
            String model,
            double baseCost,
            int duration) {
        setRegistrationNumber(this.REGISTRATION_NUMBER_PREFIX + ++count);
        setType(type);
        setBrand(brand);
        setModel(model);
        setBaseCost(baseCost);
        setDuration(duration);
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBaseCost(double baseCost) {
        this.baseCost = baseCost;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public double getBaseCost() {
        return this.baseCost;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    public String toString() {
        return String.format(
                "Nomor Registrasi: %s\nMerek: %s\nModel: %s\nTarif Dasar: Rp %.1f\n",
                getRegistrationNumber(),
                getBrand(),
                getModel(),
                getBaseCost());
    }

    public abstract double getTotalCost();
}

class Car extends Vehicle {
    private final int PASSGENGER_COUNT_DISCOUNT = 4;
    private final int DISCOUNT_PERCENTAGE = 10;

    private int passenger;

    public Car(
            int type,
            String brand,
            String model,
            double baseCost,
            int duration,
            int passenger) {
        super(type, brand, model, baseCost, duration);
        setPassenger(passenger);
    }

    public Car(VehicleStructInput vehicleStructInput) {
        super(
                vehicleStructInput.type,
                vehicleStructInput.brand,
                vehicleStructInput.model,
                vehicleStructInput.baseCost,
                vehicleStructInput.duration);
        setPassenger(vehicleStructInput.additional);
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public int getPassenger() {
        return this.passenger;
    }

    @Override
    public double getTotalCost() {
        double result = getBaseCost() * getDuration();

        if (getPassenger() > this.PASSGENGER_COUNT_DISCOUNT) {
            result = ((100 - this.DISCOUNT_PERCENTAGE) / 100d) * result;
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format(
                super.toString() + "Kapasitas Penumpang: %d\nBiaya Sewa: Rp %.1f\n",
                getPassenger(),
                getTotalCost());
    }
}

class Motorcycle extends Vehicle {
    private final double DRIVER_EXTRA_COST = 100000;

    private boolean withDriver;

    public Motorcycle(
            int type,
            String brand,
            String model,
            double baseCost,
            int duration,
            boolean withDriver) {
        super(type, brand, model, baseCost, duration);
        setWithDriver(withDriver);
    }

    public Motorcycle(VehicleStructInput vehicleStructInput) {
        super(
                vehicleStructInput.type,
                vehicleStructInput.brand,
                vehicleStructInput.model,
                vehicleStructInput.baseCost,
                vehicleStructInput.duration);
        setWithDriver(vehicleStructInput.additional == 1);
    }

    public void setWithDriver(boolean withDriver) {
        this.withDriver = withDriver;
    }

    public boolean getWithDriver() {
        return this.withDriver;
    }

    public String getWithDriverString() {
        if (getWithDriver()) {
            return "Ya";
        } else {
            return "Tidak";
        }
    }

    @Override
    public double getTotalCost() {
        double result = (getBaseCost() * getDuration());

        if (getWithDriver()) {
            result += (this.DRIVER_EXTRA_COST * getDuration());
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format(
                super.toString() + "Dengan Driver: %s\nBiaya Sewa: Rp %.1f\n",
                getWithDriverString(),
                getTotalCost());
    }
}

public class Sewain {
    final static Scanner scanner = new Scanner(System.in);

    private static Vehicle[] newVehicle(int count) {
        Vehicle[] vehicles = new Vehicle[count];

        for (int i = 0; i < count; i++) {
            VehicleStructInput vehicleStructInput = new VehicleStructInput();

            vehicleStructInput.type = Integer.parseUnsignedInt(scanner.nextLine());
            vehicleStructInput.brand = scanner.nextLine();
            vehicleStructInput.model = scanner.nextLine();
            vehicleStructInput.baseCost = Double.parseDouble(scanner.nextLine());
            vehicleStructInput.additional = Integer.parseUnsignedInt(scanner.nextLine());
            vehicleStructInput.duration = Integer.parseUnsignedInt(scanner.nextLine());

            switch (vehicleStructInput.type) {
                case 1:
                    vehicles[i] = new Car(vehicleStructInput);
                    break;
                case 2:
                    vehicles[i] = new Motorcycle(vehicleStructInput);
                    break;
            }
        }

        return vehicles;
    }

    private static void displayVehicle(Vehicle... vehicles) {
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println(vehicles[i]);
        }

        System.out.printf(
                "Total Kendaraan: %d\n",
                Vehicle.getCount());
    }

    public static void main(String[] args) {
        displayVehicle(newVehicle(Integer.parseUnsignedInt(scanner.nextLine())));
    }
}
