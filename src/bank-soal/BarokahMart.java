import java.util.Scanner;

class Summary {
    String distributorName;
    String mostValuableName;
    double mostValuablePrice;
    double cleaningProductTotalValue;
    double toolProductTotalValue;
    double totalValue;
    long validStock;
    long invalidStock;
}

class ProductInfo {
    String type;
    String name;
    String productCode;
    double price;
    long stock;
}

class ProductDisplaySummary {
    Store store;
    Product[] products;
}

class Store {
    private String distributorName;

    public Store(String distributorName) {
        setDistributorName(distributorName);
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getDistributorName() {
        return this.distributorName;
    }
}

abstract class Product {
    private String type;
    private String name;
    private String productCode;
    private double price;
    private long stock;

    public Product(String type, String name, double price, long stock, String productCode) {
        setType(type);
        setName(name);
        setPrice(price);
        setStock(stock);
        setProductCode(productCode);
    }

    public Product(ProductInfo productInfo) {
        setType(productInfo.type);
        setName(productInfo.name);
        setPrice(productInfo.price);
        setStock(productInfo.stock);
        setProductCode(productInfo.productCode);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public long getStock() {
        return this.stock;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public abstract double getStockValue();

    public String toString() {
        return String.format(
                "Product: %s\nPrice: %.1f\nQuantity: %d",
                getName(),
                getPrice(),
                getStock());
    }
}

class CleaningProduct extends Product {
    public CleaningProduct(String type, String name, double price, long stock, String productCode) {
        super(type, name, price, stock, productCode);
    }

    public CleaningProduct(ProductInfo productInfo) {
        super(productInfo);
    }

    @Override
    public double getStockValue() {
        return getPrice() * getStock() * 0.9;
    }

    @Override
    public String toString() {
        return String.format(
                super.toString() + "\nStock Value: Rp %.1f",
                getStockValue());
    }
}

class ToolProduct extends Product {
    public ToolProduct(String type, String name, double price, long stock, String productCode) {
        super(type, name, price, stock, productCode);
    }

    public ToolProduct(ProductInfo productInfo) {
        super(productInfo);
    }

    @Override
    public double getStockValue() {
        return (getPrice() + 100) * getStock();
    }

    @Override
    public String toString() {
        return String.format(
                super.toString() + "\nStock Value: Rp %.1f",
                getStockValue());
    }
}

class UncategorizedProduct extends Product {
    public UncategorizedProduct(String type, String name, double price, long stock, String productCode) {
        super(type, name, price, stock, productCode);
    }

    public UncategorizedProduct(ProductInfo productInfo) {
        super(productInfo);
    }

    @Override
    public double getStockValue() {
        return getPrice() * getStock() * 0.9;
    }

    @Override
    public String toString() {
        return String.format(
                super.toString() + "\nStock Value: Rp %.1f",
                getStockValue());
    }
}

public class BarokahMart {
    static Scanner scanner = new Scanner(System.in);

    private static Store initializeDistributor() {
        return new Store(scanner.nextLine());
    }

    private static Product[] inputProduct(int count) {
        Product[] products = new Product[count];

        for (int i = 0; i < count; i++) {
            ProductInfo productInfo = new ProductInfo();

            productInfo.type = scanner.next();
            productInfo.name = scanner.next();
            productInfo.price = scanner.nextDouble();
            productInfo.stock = scanner.nextLong();
            productInfo.productCode = scanner.next();

            if (productInfo.productCode.length() > 7) {
                products[i] = new UncategorizedProduct(productInfo);
            } else if (productInfo.productCode.startsWith("CL-")) {
                products[i] = new CleaningProduct(productInfo);
            } else if (productInfo.productCode.startsWith("TL-")) {
                products[i] = new ToolProduct(productInfo);
            } else {
                products[i] = new UncategorizedProduct(productInfo);
            }
        }

        return products;
    }

    private static void displayInfo(ProductDisplaySummary productDisplaySummary) {
        Summary summary = new Summary();
        summary.distributorName = productDisplaySummary.store.getDistributorName();

        System.out.printf(
                "Distributor: %s\n\n",
                summary.distributorName);

        for (int i = 0; i < productDisplaySummary.products.length; i++) {
            System.out.println(productDisplaySummary.products[i] + "\n");
            if (!(productDisplaySummary.products[i] instanceof UncategorizedProduct)) {
                double tempValue = productDisplaySummary.products[i].getStockValue();
                summary.totalValue += tempValue;
                summary.validStock++;
                if (tempValue > summary.mostValuablePrice) {
                    summary.mostValuablePrice = tempValue;
                    summary.mostValuableName = productDisplaySummary.products[i].getName();
                }

                if (productDisplaySummary.products[i] instanceof CleaningProduct) {
                    summary.cleaningProductTotalValue += tempValue;
                } else {
                    summary.toolProductTotalValue += tempValue;
                }
            } else {
                summary.invalidStock++;
            }
        }

        System.out.printf(
                "Valid products: %d\nInvalid products: %d\n\n",
                summary.validStock,
                summary.invalidStock);
        System.out.printf(
                "Total stock value: Rp %.1f\n\n",
                summary.totalValue);
        System.out.printf(
                "Most valuable product:\n%s Rp %.1f\n\n",
                summary.mostValuableName,
                summary.mostValuablePrice);
        System.out.printf(
                "Total stock by category:\nCleaning: Rp %.1f\nTool: Rp %.1f\n",
                summary.cleaningProductTotalValue,
                summary.toolProductTotalValue);
    }

    public static void main(String[] args) {
        ProductDisplaySummary productDisplaySummary = new ProductDisplaySummary();

        productDisplaySummary.store = initializeDistributor();
        productDisplaySummary.products = inputProduct(Integer.parseUnsignedInt(scanner.nextLine()));
        scanner.close();

        displayInfo(productDisplaySummary);
    }
}
