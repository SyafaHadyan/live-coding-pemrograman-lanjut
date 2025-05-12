import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int publishedYear;

    public Book(String title, String author, int publishedYear) {
        setTitle(title);
        setAuthor(author);
        setPublishedYear(publishedYear);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublishedYear() {
        return this.publishedYear;
    }

    public String toString() {
        return String.format(
                "Judul: " + getTitle() + ", " +
                        "Penulis: " + getAuthor() + ", " +
                        "Tahun: " + getPublishedYear());
    }
}

public class BookCounter {
    static Scanner scanner = new Scanner(System.in);

    private static Book[] newBook(int count) {
        Book[] books = new Book[count];
        for (int i = 0; i < count; i++) {
            String title = scanner.next();
            String author = scanner.next();
            int publishedYear = scanner.nextInt();
            try {
                scanner.nextLine();
            } catch (Exception e) {
                // Fix input
            }
            books[i] = new Book(title, author, publishedYear);
        }

        return books;
    }

    private static void displayBookInfo(Book... books) {
        for (Book i : books) {
            System.out.println(i);
        }
        System.out.println("Total buku: " + books.length);
    }

    public static void main(String[] args) {
        displayBookInfo(newBook(Integer.parseUnsignedInt(scanner.nextLine())));
    }
}
