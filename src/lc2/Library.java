import java.util.Scanner;

class DigitalLibrary {
    String title;
    String author;
    int publishedDate;
    long price;
    String category;
}

public class Library {
    private static String toCurrency(long input) {
        String[] cost = String.valueOf(input).split("");
        String costDigit = "";
        String result = "Rp";
        int counter = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            costDigit = costDigit.concat(cost[i]);
            counter++;
            if (counter % 3 == 0 && i != 0) {
                costDigit = costDigit.concat(",");
            }
        }
        counter = costDigit.length() - 1;
        for (int i = 0; i < costDigit.length(); i++) {
            result = result.concat(String.valueOf(costDigit.charAt(counter--)));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DigitalLibrary book0 = new DigitalLibrary();
        DigitalLibrary book1 = new DigitalLibrary();
        book0.title = input.nextLine();
        book0.author = input.nextLine();
        book0.publishedDate = Integer.parseInt(input.nextLine());
        book0.price = Long.parseLong(input.nextLine());
        book0.category = input.nextLine();
        book1.title = input.nextLine();
        book1.author = input.nextLine();
        book1.publishedDate = Integer.parseInt(input.nextLine());
        book1.price = Long.parseLong(input.nextLine());
        book1.category = input.nextLine();
        input.close();
        System.out.println("========================================");
        System.out.println("         DATA BUKU PERPUSTAKAAN         ");
        System.out.println("========================================");
        System.out.println("Judul Buku      : " + book0.title);
        System.out.println("Pengarang       : " + book0.author);
        System.out.println("Tahun Terbit    : " + book0.publishedDate);
        System.out.println("Harga Buku      : " + toCurrency(book0.price));
        System.out.println("Kategori        : " + book0.category);
        System.out.println("----------------------------------------");
        System.out.println("Judul Buku      : " + book1.title);
        System.out.println("Pengarang       : " + book1.author);
        System.out.println("Tahun Terbit    : " + book1.publishedDate);
        System.out.println("Harga Buku      : " + toCurrency(book1.price));
        System.out.println("Kategori        : " + book1.category);
        System.out.println("----------------------------------------");
    }
}
