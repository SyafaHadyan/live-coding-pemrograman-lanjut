import java.util.Scanner;

class Guest {
    private String name;
    private String paymentMethod;
    private String guestType;
    private int nightCount;
    private int point;
    private double costPerNight = 750000d;
    private double extras;
    private double finalCostBeforeDiscount;
    private double finalCostAfterDiscount;
    private double finalCost;

    public void displayInfo() {
        System.out.println("Nama: " + this.name);
        System.out.println("Jumlah Malam: " + this.nightCount);
        System.out.println("Tipe Tamu: " + this.guestType);
        System.out.println("Metode Pembayaran: " + this.paymentMethod);
        System.out.printf("%s%.0f\n", "Total Harga Sebelum Diskon: Rp ", this.finalCostBeforeDiscount);
        System.out.printf("%s%.0f\n", "Total Harga Setelah Diskon: Rp ", this.finalCostAfterDiscount);
        System.out.println("Poin yang Didapatkan: " + this.point * this.nightCount);
        System.out.printf("%s%.0f\n", "Pajak/Diskon Tambahan: Rp ", this.extras);
        System.out.printf("%s%.0f\n", "Total Akhir yang Dibayarkan: Rp ", this.finalCost);
        System.out.println("---");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        switch (paymentMethod) {
            case "Cash":
                this.extras = this.finalCostAfterDiscount * 0.03;
                break;
            case "Credit Card":
                this.extras = this.finalCostAfterDiscount * 0.05;
                break;
            case "E-Wallet":
                this.extras = this.finalCostAfterDiscount * 0.02 * -1;
                break;
        }

        this.finalCost = this.finalCostAfterDiscount + this.extras;
    }

    public void setGuestType(String guestType) {
        this.guestType = guestType;
    }

    public void setNightCount(int nightCount) {
        this.nightCount = nightCount;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setExtras(double extras) {
        this.extras = extras;
    }

    public void setFinalCostBeforeDiscount(double finalCostBeforeDiscount) {
        this.finalCostBeforeDiscount = finalCostBeforeDiscount;
    }

    public void setFinalCostAfterDiscount(double finalCostAfterDiscount) {
        this.finalCostAfterDiscount = finalCostAfterDiscount;
    }

    public double getCostPerNight() {
        return this.costPerNight;
    }
}

class Regular extends Guest {
    private final int POINT_PER_NIGHT = 0;
    private final double DISCOUNT_PERCENTAGE = 0;
    private double finalCostBeforeDiscount;
    private double finalCostAfterDiscount;

    public void setInfo(String name, String guestType, String paymentMethod, int nightCount) {
        setName(name);
        setGuestType(guestType);
        setNightCount(nightCount);
        finalCostBeforeDiscount = nightCount * getCostPerNight();
        finalCostAfterDiscount = finalCostBeforeDiscount - (this.DISCOUNT_PERCENTAGE / 100d * finalCostBeforeDiscount);
        setPoint(this.POINT_PER_NIGHT);
        setFinalCostBeforeDiscount(this.finalCostBeforeDiscount);
        setFinalCostAfterDiscount(finalCostAfterDiscount);
        setPaymentMethod(paymentMethod);
    }
}

class Member extends Guest {
    private final int POINT_PER_NIGHT = 0;
    private final double DISCOUNT_PERCENTAGE = 15;
    private double finalCostBeforeDiscount;
    private double finalCostAfterDiscount;

    public void setInfo(String name, String guestType, String paymentMethod, int nightCount) {
        setName(name);
        setGuestType(guestType);
        setNightCount(nightCount);
        finalCostBeforeDiscount = nightCount * getCostPerNight();
        finalCostAfterDiscount = finalCostBeforeDiscount - (this.DISCOUNT_PERCENTAGE / 100d * finalCostBeforeDiscount);
        setPoint(this.POINT_PER_NIGHT);
        setFinalCostBeforeDiscount(this.finalCostBeforeDiscount);
        setFinalCostAfterDiscount(finalCostAfterDiscount);
        setPaymentMethod(paymentMethod);
    }
}

class VIP extends Guest {
    private final int POINT_PER_NIGHT = 10;
    private final double DISCOUNT_PERCENTAGE = 25;
    private double finalCostBeforeDiscount;
    private double finalCostAfterDiscount;

    public void setInfo(String name, String guestType, String paymentMethod, int nightCount) {
        setName(name);
        setGuestType(guestType);
        setNightCount(nightCount);
        finalCostBeforeDiscount = nightCount * getCostPerNight();
        finalCostAfterDiscount = finalCostBeforeDiscount - (this.DISCOUNT_PERCENTAGE / 100d * finalCostBeforeDiscount);
        setPoint(this.POINT_PER_NIGHT);
        setFinalCostBeforeDiscount(this.finalCostBeforeDiscount);
        setFinalCostAfterDiscount(finalCostAfterDiscount);
        setPaymentMethod(paymentMethod);
    }
}

public class Hotel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int guestCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < guestCount; i++) {
            String name = scanner.nextLine();
            int nightCount = Integer.parseInt(scanner.nextLine());
            String guestType = scanner.nextLine();
            String paymentMethod = scanner.nextLine();
            if (guestType.equals("Regular")) {
                Regular guest = new Regular();
                guest.setInfo(name, guestType, paymentMethod, nightCount);
                guest.displayInfo();
            } else if (guestType.equals("Member")) {
                Member guest = new Member();
                guest.setInfo(name, guestType, paymentMethod, nightCount);
                guest.displayInfo();
            } else if (guestType.equals("VIP")) {
                VIP guest = new VIP();
                guest.setInfo(name, guestType, paymentMethod, nightCount);
                guest.displayInfo();
            } else {
                Regular guest = new Regular();
                guest.setInfo(name, guestType, paymentMethod, nightCount);
                guest.displayInfo();
            }
        }
        scanner.close();
    }
}
