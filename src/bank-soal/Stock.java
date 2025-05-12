import java.util.Scanner;

class NotEnoughStockException extends Exception {
    public NotEnoughStockException(String message) {
        super(message);
    }
}

class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        setName(name);
        setPrice(price);
        setStock(stock);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public void increaseStock(int amount) {
        this.stock += amount;
    }

    public void decreaseStock(int amount) throws NotEnoughStockException {
        if (this.stock >= amount) {
            this.stock -= amount;
        } else {
            throw new NotEnoughStockException("Stok " + getName() + " tidak mencukupi.");
        }
    }

    public double getTotalPrice() {
        return getStock() * getPrice();
    }

    public String toString() {
        return String.format("Nama: " + getName() + ", Total Harga: $%.1f, Stok: " + getStock(), getTotalPrice());
    }
}

public class Stock {
    static final int DEFAULT_INPUT_COUNT = 2;

    static Scanner scanner = new Scanner(System.in);

    private static Product[] handleInput(int count) {
        Product[] products = new Product[count];
        for (int i = 0; i < count; i++) {
            String name = scanner.nextLine();
            double price = Double.parseDouble(scanner.nextLine());
            int stock = Integer.parseUnsignedInt(scanner.nextLine());
            products[i] = new Product(name, price, stock);
        }

        return products;
    }

    private static String[] handleStock(Product... products) {
        String[] errors = new String[products.length];
        for (int i = 0; i < products.length; i++) {
            products[i].increaseStock(Integer.parseUnsignedInt(scanner.nextLine()));
            try {
                products[i].decreaseStock(Integer.parseUnsignedInt(scanner.nextLine()));
            } catch (NotEnoughStockException e) {
                errors[i] = e.getMessage();
            }
        }

        return errors;
    }

    private static void displayInfo(String[] errors, Product... products) {
        for (int i = 0; i < errors.length; i++) {
            if (errors[i] != null) {
                System.out.println(errors[i]);
            }
        }

        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i]);
        }
    }

    public static void main(String[] args) {
        Product[] products = handleInput(DEFAULT_INPUT_COUNT);
        displayInfo(handleStock(products), products);
    }
}
