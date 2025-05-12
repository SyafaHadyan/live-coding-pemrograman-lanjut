import java.util.Scanner;

abstract class Vehicle {
    private String owner;
    private String registrationNumber;
    private int productionYear;
    private String vehicleType;

    public Vehicle(String owner, String registrationNumber, int productionYear, String vehicleType) {
        setOwner(owner);
        setRegistrationNumber(registrationNumber);
        setProductionYear(productionYear);
        setVehicleType(vehicleType);
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public int getProductionYear() {
        return this.productionYear;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public String toString() {
        return String.format("Nama Pemilik: " + getOwner() + "\n" +
                "Nomor Kendaraan: " + getRegistrationNumber() + "\n" +
                "Tahun Produksi: " + getProductionYear() + "\n" +
                "Jenis Kendaraan: " + getVehicleType());
    }

    public abstract String getCapacity();
}

class GasCar extends Vehicle {
    private int tankCapacity;

    public GasCar(String owner, String registrationNumber, int productionYear, String vehicleType,
            int tankCapacity) {
        super(owner, registrationNumber, productionYear, vehicleType);
        setTankCapacity(tankCapacity);
    }

    public void setTankCapacity(int tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public int getTankCapacity() {
        return this.tankCapacity;
    }

    @Override
    public String getCapacity() {
        return "Kapasitas: " + getTankCapacity() + " liter";
    }
}

class ElectricCar extends Vehicle {
    private int batteryCapacity;

    public ElectricCar(String owner, String registrationNumber, int productionYear, String vehicleType,
            int batteryCapacity) {
        super(owner, registrationNumber, productionYear, vehicleType);
        setBatteryCapacity(batteryCapacity);
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public int getBatteryCapacity() {
        return this.batteryCapacity;
    }

    @Override
    public String getCapacity() {
        return "Kapasitas: " + getBatteryCapacity() + " kWh";
    }
}

class Motorcycle extends Vehicle {
    private int engineCapacity;

    public Motorcycle(String owner, String registrationNumber, int productionYear, String vehicleType,
            int engineCapacity) {
        super(owner, registrationNumber, productionYear, vehicleType);
        setEngineCapacity(engineCapacity);
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getEngineCapacity() {
        return this.engineCapacity;
    }

    @Override
    public String getCapacity() {
        return "Kapasitas: " + getEngineCapacity() + " cc";
    }
}

public class Dealer {
    static Scanner scanner = new Scanner(System.in);

    private static Vehicle[] register(int count) {
        Vehicle[] vehicles = new Vehicle[count];
        for (int i = 0; i < count; i++) {
            String vehicleType = scanner.next();
            String owner = scanner.next();
            String registrationNumber = scanner.next();
            int productionYear = scanner.nextInt();
            int capacity = scanner.nextInt();
            switch (vehicleType) {
                case "GasCar":
                    vehicleType = "Mobil Bensin";
                    vehicles[i] = new GasCar(owner, registrationNumber, productionYear, vehicleType, capacity);
                    break;
                case "ElectricCar":
                    vehicleType = "Mobil Listrik";
                    vehicles[i] = new ElectricCar(owner, registrationNumber, productionYear, vehicleType, capacity);
                    break;
                case "Motorcycle":
                    vehicleType = "Sepeda Motor";
                    vehicles[i] = new Motorcycle(owner, registrationNumber, productionYear, vehicleType, capacity);
                    break;
            }
        }

        return vehicles;
    }

    private static void displayInfo(Vehicle[] vehicles) {
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println(vehicles[i]);
            System.out.println(vehicles[i].getCapacity());
            System.out.println("---------------------------");
        }
    }

    public static void main(String[] args) {
        displayInfo(register(Integer.parseUnsignedInt(scanner.nextLine())));
    }
}
