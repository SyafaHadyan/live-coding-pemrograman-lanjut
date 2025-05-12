import java.util.*;

class Movie {
    private String title;
    private int duration;
    private double ticketPrice;

    public Movie(String title, int duration, double ticketPrice) {
        this.title = title;
        this.duration = duration;
        this.ticketPrice = ticketPrice;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTitle() {
        return this.title;
    }

    public int getDuration() {
        return this.duration;
    }

    public double getTicketPrice() {
        return this.ticketPrice;
    }
}

class BookingDetails extends Movie {
    private String seat;

    public BookingDetails(String title, int duration, double ticketPrice, String seat) {
        super(title, duration, ticketPrice);
        this.seat = seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getSeat() {
        return this.seat;
    }

}

public class FilkomBioskop {
    static Scanner scanner = new Scanner(System.in);

    private static BookingDetails[] handleInput() {
        ArrayList<BookingDetails> bookingDetails = new ArrayList<>();
        String title = scanner.nextLine();
        int duration = Integer.parseUnsignedInt(scanner.nextLine());
        double ticketPrice = Double.parseDouble(scanner.nextLine());
        do {
            bookingDetails.add(new BookingDetails(title, duration, ticketPrice, scanner.nextLine()));
        } while (scanner.nextLine().equalsIgnoreCase("ya"));

        BookingDetails[] result = new BookingDetails[bookingDetails.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bookingDetails.get(i);
        }

        return result;
    }

    private static void printResult(BookingDetails[] bookingDetails) {
        System.out.println("Total tiket terjual: " + bookingDetails.length);
        System.out.println("Detail tiket yang terjual:");
        for (int i = 0; i < bookingDetails.length; i++) {
            System.out.print("- ");
            System.out.print("Film: " + bookingDetails[i].getTitle() + ", ");
            System.out.print("Durasi: " + bookingDetails[i].getDuration() + " menit, ");
            System.out.printf("Harga: $%.1f, ", bookingDetails[i].getTicketPrice());
            System.out.print("Kursi: " + bookingDetails[i].getSeat() + "\n");
        }
    }

    public static void main(String[] args) {
        BookingDetails[] bookingDetails = handleInput();
        printResult(bookingDetails);
    }
}
