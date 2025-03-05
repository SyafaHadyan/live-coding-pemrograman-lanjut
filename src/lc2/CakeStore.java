import java.util.Scanner;

class Cake {
    private String name;
    private long cost;
    private String type;
    private String size;
    private long calories;

    public Cake() {
        this.name = "Default Cake Name";
        this.cost = 35000;
        this.type = "Strawberry";
        this.size = "Large";
        this.calories = 640;
    }

    public Cake(String name, long cost) {
        this.name = name;
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    public String getName() {
        return this.name;
    }

    public String getCost() {
        String[] cost = String.valueOf(this.cost).split("");
        String costDigit = "";
        String result = "Rp";
        int counter = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            costDigit = costDigit.concat(cost[i]);
            counter++;
            if (counter % 3 == 0 && i != 0) {
                costDigit = costDigit.concat(".");
            }
        }
        counter = costDigit.length() - 1;
        for (int i = 0; i < costDigit.length(); i++) {
            result = result.concat(String.valueOf(costDigit.charAt(counter--)));
        }
        return result;
    }

    public String getType() {
        return this.type;
    }

    public String getSize() {
        return this.size;
    }

    public String getCalories() {
        return String.valueOf(this.calories + "kkal");
    }
}

public class CakeStore {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cakeCount = Integer.parseInt(input.nextLine());
        Cake[] cake = new Cake[cakeCount];
        for (int i = 0; i < cakeCount; i++) {
            Cake current = new Cake(input.nextLine(), Long.parseLong(input.nextLine()));
            String type = input.nextLine();
            if (!(type.equals("Original"))) {
                current.setSize(input.nextLine());
                current.setCalories(Long.parseLong(input.nextLine()));
            }
            current.setType(type);
            cake[i] = current;
        }
        input.close();
        System.out.println("========================================");
        System.out.println("              DAFTAR MENU");
        System.out.println("========================================");
        for (int i = 0; i < cakeCount; i++) {
            String type = cake[i].getType();
            System.out.println("Nama Kue         : " + cake[i].getName());
            System.out.println("Harga Kue        : " + cake[i].getCost());
            System.out.println("Rasa Kue         : " + type);
            if (!(type.equals("Original"))) {
                System.out.println("Ukuran Kue       : " + cake[i].getSize());
                System.out.println("Kalori Kue       : " + cake[i].getCalories());
            }
            System.out.println("----------------------------------------");
        }
    }
}
