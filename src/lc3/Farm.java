import java.util.Scanner;

class Goat {
    private String name;
    private String food;
    private int age;
    private double milkProduction;

    public Goat() {
        this.name = "tidak memiliki nama";
        this.food = "Reguler";
        this.age = 0;
    }

    public Goat(String name, String food, int age) {
        this.name = name;
        this.food = food;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getFood() {
        return this.food;
    }

    public int getAge() {
        return this.age;
    }

    public double getMilkProduction() {
        if (age >= 0 && age <= 5) {
            this.milkProduction = 1.0d;
        } else if (age >= 6 && age <= 12) {
            this.milkProduction = 2.0d;
        } else if (age >= 13 && age <= 18) {
            this.milkProduction = 1.5d;
        } else {
            this.milkProduction = 0d;
        }
        if (this.food.equals("Premium")) {
            this.milkProduction += this.milkProduction * 0.1;
        } else if (this.food.equals("Super")) {
            this.milkProduction += this.milkProduction * 0.2;
        }
        return this.milkProduction;
    }

    public void getInfo() {
        if (this.age < 0) {
            System.out.println("Kambing belum lahir.");
        } else if (this.age > 18) {
            System.out.println("Kambing terlalu tua.");
        } else if (this.name.isEmpty()) {
            System.out.println("Kambing tidak memiliki nama.");
        } else {
            System.out.printf(
                    "%s%s%s%.1f%s\n",
                    "Kambing ", this.name, " akan menghasilkan ", getMilkProduction(), " liter susu per hari.");
        }
    }
}

public class Farm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Goat[] goat = new Goat[Integer.parseInt(input.nextLine())];
        for (int i = 0; i < goat.length; i++) {
            goat[i] = new Goat(input.nextLine(), input.nextLine(), Integer.parseInt(input.nextLine()));
        }
        input.close();
        for (int i = 0; i < goat.length; i++) {
            goat[i].getInfo();
        }
    }
}
