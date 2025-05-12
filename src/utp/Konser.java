import java.util.Scanner;

class InputStruct {
    String userID;
    String seatCode;
    String date;
    String type;
}

interface Printables {
    public String toString();
}

final class Handler {
    public static boolean checkIfExist(Ticket[] tickets, InputStruct inputStruct) {
        for (int i = 0; i < tickets.length; i++) {
            try {
                if (tickets[i].getSeatCode().equals(inputStruct.seatCode)) {
                    return false;
                }
            } catch (Exception e) {
            }
        }

        return true;
    }

    public static boolean checkUser(Ticket[] tickets, InputStruct inputStruct) {
        for (int i = 0; i < tickets.length; i++) {
            try {
                if (tickets[i].getUserID().equals(inputStruct.userID)) {
                    return false;
                }
            } catch (Exception e) {
            }
        }

        return true;
    }

    public static boolean isEarly(String date) {
        String[] ticket = date.split("-");

        int day = Integer.parseInt(ticket[0]);
        int month = Integer.parseInt(ticket[1]);
        int year = Integer.parseInt(ticket[2]);

        if (year != 2025) {
            return false;
        }
        if (month == 5 && day >= 18) {
            return true;
        }
        if (month == 6 && day <= 19) {
            return true;
        }

        // if (month == 6 && day <= 19 && year == 2025) {
        // return true;
        // } else if (month == 5 && day >= 18 && year == 2025) {
        // return true;
        // }

        return false;
    }

    public static boolean isPreSale(String date) {
        String[] ticket = date.split("-");

        int day = Integer.parseInt(ticket[0]);
        int month = Integer.parseInt(ticket[1]);
        int year = Integer.parseInt(ticket[2]);

        if (year != 2025) {
            return false;
        }
        if (month == 6 && day >= 20) {
            return true;
        }
        if (month == 7 || month == 8) {
            return true;
        }
        if (month == 9 && day <= 26) {
            return true;
        }

        return false;
    }

    public static boolean ticketValid(String date) {
        String[] ticket = date.split("-");

        int day = Integer.parseInt(ticket[0]);
        int month = Integer.parseInt(ticket[1]);
        int year = Integer.parseInt(ticket[2]);

        if (day == 27 && month == 9 && year > 2025) {
            return false;
        }

        return true;
    }
}

abstract class Ticket {
    private static int count;
    private String userID;
    private String seatCode;
    private String date;

    public Ticket(String userID, String seatCode, String date) {
        setUserID(userID);
        setSeatCode(seatCode);
        setDate(date);
        ++count;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static int getCount() {
        return count;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getSeatCode() {
        return this.seatCode;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s (%s) -> Rp%.2f",
                getUserID(),
                getSeatCode(),
                getType(),
                getPrice());
    }

    public abstract String getType();

    public abstract double getPrice();
}

class AlreadyPurchased extends Ticket implements Printables {
    private String type;
    private String message;

    public AlreadyPurchased(String userID, String seatCode, String date, String type) {
        super(userID, seatCode, date);
        setType(type);
    }

    public AlreadyPurchased(InputStruct inputStruct, String message) {
        super(
                inputStruct.userID,
                inputStruct.seatCode,
                inputStruct.date);
        setType(inputStruct.type);
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public double getPrice() {
        return 0d;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s",
                getUserID(),
                getMessage());
    }
}

class AlreadyExist extends Ticket implements Printables {
    private String type;
    private String message;

    public AlreadyExist(String userID, String seatCode, String date, String type) {
        super(userID, seatCode, date);
        setType(type);
    }

    public AlreadyExist(InputStruct inputStruct, String message) {
        super(
                inputStruct.userID,
                inputStruct.seatCode,
                inputStruct.date);
        setType(inputStruct.type);
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public double getPrice() {
        return 0d;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s (%s) -> %s",
                getUserID(),
                getSeatCode(),
                getType(),
                getMessage());
    }
}

class Uncategorized extends Ticket implements Printables {
    private String type;

    public Uncategorized(String userID, String seatCode, String date, String type) {
        super(userID, seatCode, date);
        setType(type);
    }

    public Uncategorized(InputStruct inputStruct) {
        super(
                inputStruct.userID,
                inputStruct.seatCode,
                inputStruct.date);
        setType(inputStruct.type);
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public double getPrice() {
        return 0d;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s -> INVALID SEAT",
                getUserID(),
                getSeatCode());
    }
}

class VIP extends Ticket implements Printables {
    private final double PRICE = 1500000d;

    private String type;

    public VIP(String userID, String seatCode, String date, String type) {
        super(userID, seatCode, date);
        setType(type);
    }

    public VIP(InputStruct inputStruct) {
        super(
                inputStruct.userID,
                inputStruct.seatCode,
                inputStruct.date);
        setType(inputStruct.type);
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public double getPrice() {
        double result = this.PRICE;

        if (Handler.isEarly(getDate())) {
            return this.PRICE * 0.76;
        }

        return result;
    }
}

class Regular extends Ticket implements Printables {
    private final double PRICE = 750000d;

    private String type;

    public Regular(String userID, String seatCode, String date, String type) {
        super(userID, seatCode, date);
        setType(type);
    }

    public Regular(InputStruct inputStruct) {
        super(
                inputStruct.userID,
                inputStruct.seatCode,
                inputStruct.date);
        setType(inputStruct.type);
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public double getPrice() {
        double result = this.PRICE;

        if (Handler.isEarly(getDate())) {
            return this.PRICE * 0.76;
        }

        return result;
    }
}

public class Konser {
    private static Scanner scanner = new Scanner(System.in);

    private static Ticket[] newTicket(int count) {
        Ticket[] tickets = new Ticket[count];

        for (int i = 0; i < count; i++) {
            InputStruct inputStruct = new InputStruct();

            inputStruct.userID = scanner.next();
            inputStruct.seatCode = scanner.next();
            inputStruct.date = scanner.next();

            String seatCode = inputStruct.seatCode.split("")[0];

            if (!Handler.ticketValid(inputStruct.date)) {
                inputStruct.type = "Uncategorized";
                tickets[i] = new Uncategorized(inputStruct);
            }

            int seatNumber = 0;

            try {
                seatNumber = Integer.parseInt(inputStruct.seatCode.substring(1, inputStruct.seatCode.length()));
            } catch (NumberFormatException e) {
                inputStruct.type = "Uncategorized";
                tickets[i] = new Uncategorized(inputStruct);
            }

            if (count - i != 1) {
                scanner.nextLine();
            }

            if (Handler.checkIfExist(tickets, inputStruct) &&
                    Handler.checkUser(tickets, inputStruct)) {
                switch (seatCode) {
                    case "A":
                    case "B":
                        if (seatNumber > 10 || seatNumber <= 0) {
                            inputStruct.type = "VIP";
                            tickets[i] = new Uncategorized(inputStruct);
                            break;
                        }
                        inputStruct.type = "VIP";
                        tickets[i] = new VIP(inputStruct);
                        break;
                    case "C":
                    case "D":
                    case "E":
                    case "F":
                    case "G":
                        if (seatNumber > 20 || seatNumber <= 0) {
                            inputStruct.type = "REG";
                            tickets[i] = new Uncategorized(inputStruct);
                            break;
                        }
                        inputStruct.type = "REG";
                        tickets[i] = new Regular(inputStruct);
                        break;
                    default:
                        inputStruct.type = "Uncategorized";
                        tickets[i] = new Uncategorized(inputStruct);
                        break;
                }
            } else if (Handler.checkUser(tickets, inputStruct)) {
                switch (seatCode) {
                    case "A":
                    case "B":
                        inputStruct.type = "VIP";
                        tickets[i] = new AlreadyExist(inputStruct, "ALREADY SOLD");
                        break;
                    case "C":
                    case "D":
                    case "E":
                    case "F":
                    case "G":
                        inputStruct.type = "REG";
                        tickets[i] = new AlreadyExist(inputStruct, "ALREADY SOLD");
                        break;
                }
            } else {
                switch (seatCode) {
                    case "A":
                    case "B":
                        inputStruct.type = "VIP";
                        tickets[i] = new AlreadyPurchased(inputStruct, "ALREADY PURCHASED");
                        break;
                    case "C":
                    case "D":
                    case "E":
                    case "F":
                    case "G":
                        inputStruct.type = "REG";
                        tickets[i] = new AlreadyPurchased(inputStruct, "ALREADY PURCHASED");
                        break;
                }
            }
        }

        return tickets;
    }

    private static void displayTicket(Ticket... tickets) {
        System.out.println("-------- PRINT TRANSAKSI --------");
        for (int i = 0; i < tickets.length; i++) {
            System.out.println(tickets[i]);
        }
    }

    public static void main(String[] args) {
        displayTicket(newTicket(Integer.parseUnsignedInt(scanner.nextLine())));
    }
}
