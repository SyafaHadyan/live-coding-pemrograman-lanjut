import java.util.Scanner;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class RehanWangsaff {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StringBuffer title = new StringBuffer(scanner.nextLine());
        int activityCount = Integer.parseInt(scanner.nextLine());
        StringBuilder activity = new StringBuilder();

        for (int i = 0; i < activityCount; i++) {
            activity.append(scanner.nextLine().replaceAll("\\s+", ""));
        }

        StringJoiner friendList = new StringJoiner(", ", "Teman yang ditemuhi hari ini: [", "]");

        int friendCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < friendCount; i++) {
            friendList.add(scanner.nextLine());
        }

        StringTokenizer quotes = new StringTokenizer(scanner.nextLine(), " ");

        System.out.println(title.reverse());
        System.out.println(activity);
        System.out.println(friendList);
        System.out.println("Jumlah kata dalam quotes: " + quotes.countTokens());

        scanner.close();
    }
}
