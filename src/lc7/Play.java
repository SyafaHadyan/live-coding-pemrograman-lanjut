import java.util.Scanner;

abstract class Duet {
    public abstract void sing(String lyric);

    public abstract void playInstrument(String lyric);

    public abstract void hum(String lyric);
}

class Hardin extends Duet {
    public void sing(String lyric) {
        System.out.println(lyric);
    }

    public void playInstrument(String lyric) {
        System.out.println(lyric);
    }

    public void hum(String lyric) {
        System.out.println(lyric);
    }
}

class Guest extends Duet {
    public void sing(String lyric) {
        System.out.println(lyric);
    }

    public void playInstrument(String lyric) {
        System.out.println(lyric);
    }

    public void hum(String lyric) {
        System.out.println(lyric);
    }
}

public class Play {
    static final String[] LYRIC_INDEX = new String[] {
            "Di bawah langit yang penuh bintang, aku terdiam menunggu, berharap kau kembali.",
            "Dum... tss... dum dum...", "Menunggu hadirmu~",
            "Dalam hening malam, aku merindukan suara langkahmu yang tak kunjung datang.",
            "Ding... dong... plink... plonk...", "Dalam mimpiku~" };
    static final int[] TURN = new int[] { 0, 0, 0, 1, 1, 1 };
    static final int[] ACTION = new int[] { 0, 1, 2, 0, 1, 2 };

    private static void play(int row) {
        Hardin hardin = new Hardin();
        Guest guest = new Guest();
        for (int i = 0; i < row; i++) {
            int index = i % LYRIC_INDEX.length;
            int turn = TURN[index];
            int action = ACTION[index];
            if (turn == 0) {
                if (action == 0) {
                    hardin.sing(LYRIC_INDEX[index]);
                } else if (action == 1) {
                    hardin.playInstrument(LYRIC_INDEX[index]);
                } else {
                    hardin.hum(LYRIC_INDEX[index]);
                }
            } else {
                if (action == 0) {
                    guest.sing(LYRIC_INDEX[index]);
                } else if (action == 1) {
                    guest.playInstrument(LYRIC_INDEX[index]);
                } else {
                    guest.hum(LYRIC_INDEX[index]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        play(scanner.nextInt());
        scanner.close();
    }
}
