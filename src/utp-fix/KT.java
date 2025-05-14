import java.util.NoSuchElementException;
import java.util.Scanner;

class InputStruct {
    String id;
    String seat;
    String date;
}

class TicketHandler {
    public static Ticket handleInvalid(InputStruct inputStruct) {
        /*
         * Create from ticket instead of REG or VIP
         */
        Ticket ticket = new Ticket(
                inputStruct.id,
                inputStruct.seat,
                inputStruct.date);

        ticket = new Ticket(
                inputStruct.id,
                inputStruct.seat,
                inputStruct.date);

        return ticket;
    }

    public static int handleDuplicate(InputStruct inputStruct, Ticket... tickets) {
        Ticket ticket = new Ticket(
                inputStruct.id,
                inputStruct.seat,
                inputStruct.date);

        return handleDuplicate(ticket, tickets);
    }

    public static int handleDuplicate(Ticket ticket, Ticket... tickets) {
        /*
         * 0 = No duplicate
         * 1 = User already purchased another ticket
         * 2 = Ticket already sold to another user
         */

        for (int i = 0; i < tickets.length; i++) {
            try {
                if (tickets[i].getId().equals(ticket.getId()) && tickets[i].getValid()) {
                    return 1;
                }
                if (tickets[i].getSeat().equals(ticket.getSeat()) && tickets[i].getValid()) {
                    return 2;
                }
            } catch (NullPointerException e) {
            }
        }

        return 0;
    }

    public static int ticketCategory(String seat) {
        /*
         * 0 = Invalid
         * 1 = Regular
         * 2 = VIP
         */

        String[] seatArray = seat.split("");

        try {
            Integer.parseUnsignedInt(seatArray[0]);
            return 0;
        } catch (NumberFormatException e) {
            for (int i = 1; i < seatArray.length; i++) {
                try {
                    Integer.parseUnsignedInt(seatArray[i]);
                } catch (NumberFormatException f) {
                    return 0;
                }
            }

            String seatNumberString = new String();

            for (int i = 1; i < seatArray.length; i++) {
                seatNumberString = seatNumberString.concat(seatArray[i]);
            }

            int seatNumber = Integer.parseUnsignedInt(seatNumberString);

            switch (seatArray[0]) {
                case "A":
                case "B":
                    if (seatNumber > 10) {
                        return 0;
                    }
                    return 2;
                case "C":
                case "D":
                case "E":
                case "F":
                case "G":
                    if (seatNumber > 20) {
                        return 0;
                    }
                    return 1;
                default:
                    return 0;
            }
        }
    }

    public static boolean isDateValid(String date) {
        String[] dateArray = date.split("\\s+");

        int day = Integer.parseUnsignedInt(dateArray[0]);
        int month = Integer.parseUnsignedInt(dateArray[1]);
        int year = Integer.parseUnsignedInt(dateArray[2]);

        if (year != 2025) {
            return false;
        }

        if (month < 5 || month > 9) {
            return false;
        }

        if (month == 5 && day < 18 ||
                month == 9 && day > 26) {
            return false;
        }
        return true;
    }

    public static double getPrice(double price, String date) {
        String[] dateArray = date.split("-");

        int day = Integer.parseUnsignedInt(dateArray[0]);
        int month = Integer.parseUnsignedInt(dateArray[1]);

        if (month == 5 && day >= 18 ||
                month == 6 && day <= 19) {
            return 0.76 * price;
        }

        return price;
    }

    public static boolean isSeatValid(String seat) {
        String start = new String(seat.split("")[0]).toUpperCase();

        switch (start) {
            case "A":
            case "B":
            case "C":
            case "D":
            case "E":
            case "F":
            case "G":
                return true;
            default:
                return false;
        }
    }

    public static boolean isSeatNumberValid(String seat) {
        String[] seatArray = seat.split("");

        for (int i = 1; i < seatArray.length; i++) {
            try {
                Integer.parseUnsignedInt(seatArray[i]);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }
}

class Ticket {
    private String id;
    private String seat;
    private String date;
    private String message;
    private boolean valid = true;

    public Ticket(String id, String seat, String date) {
        setId(id);
        setSeat(seat);
        setDate(date);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getId() {
        return this.id;
    }

    public String getSeat() {
        return this.seat;
    }

    public String getDate() {
        return this.date;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean getValid() {
        return this.valid;
    }

    public double getPrice() {
        return 0;
    }

    public String toString() {
        return String.format(
                "%s -> %s (%s) -> Rp%.2f",
                getId(),
                getSeat(),
                this.getClass().getSimpleName(),
                getPrice());
    }
}

class VIP extends Ticket {
    private double price = 1500000d;

    public VIP(String id, String seat, String date) {
        super(id, seat, date);
        setPrice();
    }

    public VIP(InputStruct inputStruct) {
        super(
                inputStruct.id,
                inputStruct.seat,
                inputStruct.date);
        setPrice();
    }

    public void setPrice() {
        this.price = TicketHandler.getPrice(price, getDate());
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}

class REG extends Ticket {
    private double price = 750000d;

    public REG(String id, String seat, String date) {
        super(id, seat, date);
        setPrice();
    }

    public REG(InputStruct inputStruct) {
        super(
                inputStruct.id,
                inputStruct.seat,
                inputStruct.date);
        setPrice();
    }

    public void setPrice() {
        this.price = TicketHandler.getPrice(price, getDate());
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}

public class KT {
    private static Scanner scanner = new Scanner(System.in);

    private static Ticket[] input() {
        return input(Integer.parseUnsignedInt(scanner.nextLine()));
    }

    private static Ticket[] input(int count) {
        Ticket[] tickets = new Ticket[count];

        for (int i = 0; i < count; i++) {
            InputStruct inputStruct = new InputStruct();

            inputStruct.id = scanner.next();
            inputStruct.seat = scanner.next();
            inputStruct.date = scanner.next();

            int result = TicketHandler.handleDuplicate(inputStruct, tickets);

            switch (result) {
                case 0:
                    if (TicketHandler.isSeatValid(inputStruct.seat) &&
                            TicketHandler.isSeatNumberValid(inputStruct.seat)) {
                        int seatCategory = TicketHandler.ticketCategory(inputStruct.seat);

                        switch (seatCategory) {
                            case 1:
                                tickets[i] = new REG(inputStruct);
                                break;
                            case 2:
                                tickets[i] = new VIP(inputStruct);
                                break;
                            default:
                                tickets[i] = TicketHandler.handleInvalid(inputStruct);
                                tickets[i].setValid(false);
                                tickets[i].setMessage("INVALID SEAT");
                                break;
                        }
                    } else {
                        tickets[i] = TicketHandler.handleInvalid(inputStruct);
                        tickets[i].setValid(false);
                        tickets[i].setMessage("INVALID SEAT");
                    }
                    break;
                case 1:
                    tickets[i] = TicketHandler.handleInvalid(inputStruct);
                    tickets[i].setValid(false);
                    tickets[i].setMessage("ALREADY PURCHASED");
                    break;
                case 2:
                    int seatCategory = TicketHandler.ticketCategory(inputStruct.seat);
                    switch (seatCategory) {
                        case 1:
                            tickets[i] = new REG(inputStruct);
                            tickets[i].setMessage("ALREADY SOLD");
                            tickets[i].setValid(false);
                            break;
                        case 2:
                            tickets[i] = new VIP(inputStruct);
                            tickets[i].setMessage("ALREADY SOLD");
                            tickets[i].setValid(false);
                            break;
                        default:
                            tickets[i] = TicketHandler.handleInvalid(inputStruct);
                            tickets[i].setValid(false);
                            tickets[i].setMessage("INVALID SEAT");
                            break;
                    }
                    break;
            }

            try {
                scanner.nextLine();
            } catch (NoSuchElementException e) {
                // Fix scanner next line
                // DO NOT REMOVE
            }
        }

        return tickets;
    }

    private static void display(Ticket... tickets) {
        System.out.println("-------- PRINT TRANSAKSI --------");

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i].getValid()) {
                System.out.println(tickets[i]);
            } else {
                if (tickets[i].getMessage().equals("INVALID SEAT")) {
                    System.out.println(
                            String.format(
                                    "%s -> %s -> %s",
                                    tickets[i].getId(),
                                    tickets[i].getSeat(),
                                    tickets[i].getMessage()));
                } else if (tickets[i].getMessage().equals("ALREADY PURCHASED")) {
                    System.out.println(
                            String.format(
                                    "%s -> %s",
                                    tickets[i].getId(),
                                    tickets[i].getMessage()));
                } else if (tickets[i].getMessage().equals("ALREADY SOLD")) {
                    System.out.println(
                            String.format(
                                    "%s -> %s (%s) -> %s",
                                    tickets[i].getId(),
                                    tickets[i].getSeat(),
                                    tickets[i].getClass().getSimpleName(),
                                    tickets[i].getMessage()));
                }
            }
        }
    }

    public static void main(String[] args) {
        display(input());
    }
}
