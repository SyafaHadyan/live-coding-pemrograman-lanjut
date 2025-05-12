import java.util.Scanner;

abstract class Vehicle {
    private String name;
    private long speed;
    private int passengerCount;
    static int vehicleCount;

    public Vehicle(String name, long speed, int passengerCount) {
        setName(name);
        setSpeed(speed);
        setPassengerCount(passengerCount);
        ++vehicleCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public String getName() {
        return this.name;
    }

    public long getSpeed() {
        return this.speed;
    }

    public int getPassengerCount() {
        return this.passengerCount;
    }

    public static int getVehicleCount() {
        return vehicleCount;
    }

    public abstract String jalan();
}

class Car extends Vehicle {
    public Car(String name, long speed, int passengerCount) {
        super(name, speed, passengerCount);
    }

    @Override
    public String jalan() {
        return String.format("[Mobil] " + getName() + " berjalan dengan kecepatan " + getSpeed() + " km/jam, membawa "
                + getPassengerCount() + " penumpang.");
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String name, long speed, int passengerCount) {
        super(name, speed, passengerCount);
    }

    @Override
    public String jalan() {
        return String.format("[Motor] " + getName() + " berjalan dengan kecepatan " + getSpeed() + " km/jam, membawa "
                + getPassengerCount() + " penumpang.");
    }
}

class Truck extends Vehicle {
    private long load;
    private String loadType;

    public Truck(String name, long speed, int passengerCount, long load, String loadType) {
        super(name, speed, passengerCount);
        setLoad(load);
        setLoadType(loadType);
    }

    public void setLoad(long load) {
        this.load = load;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public long getLoad() {
        return this.load;
    }

    public String getLoadType() {
        return this.loadType;
    }

    @Override
    public String jalan() {
        return String.format("[Truk] " + getName() + " berjalan dengan kecepatan " + getSpeed() + " km/jam, membawa "
                + getPassengerCount() + " penumpang.\n" + getName() + " memuat barang seberat " + getLoad()
                + " kg berupa " + getLoadType() + ".");
    }
}

public class SimVc {
    static Scanner scanner = new Scanner(System.in);

    private static Vehicle[] newVehicle(int count) {
        Vehicle[] vehicles = new Vehicle[count];
        for (int i = 0; i < count; i++) {
            String vehicleType = scanner.next();
            String name = scanner.next();
            long speed = scanner.nextLong();
            int passengerCount = scanner.nextInt();

            switch (vehicleType) {
                case "Mobil":
                    vehicles[i] = new Car(name, speed, passengerCount);
                    break;
                case "Motor":
                    vehicles[i] = new Motorcycle(name, speed, passengerCount);
                    break;
                case "Truk":
                    long load = scanner.nextLong();
                    String loadType = scanner.next();
                    vehicles[i] = new Truck(name, speed, passengerCount, load, loadType);
                    break;
            }
        }

        return vehicles;
    }

    private static void displayVehicles(Vehicle... vehicles) {
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println(vehicles[i].jalan());
        }

        System.out.println("Total kendaraan: " + Vehicle.getVehicleCount());
    }

    public static void main(String[] args) {
        try {
            displayVehicles(newVehicle(Integer.parseUnsignedInt(scanner.nextLine())));
        } catch (Exception e) {
            System.out.println("Total kendaraan: " + Vehicle.getVehicleCount());
        }
    }
}
