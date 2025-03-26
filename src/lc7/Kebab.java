import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Kebab {
    static final int INPUT_COUNT = 3;
    static final long[] NORMAL_PRICE = new long[] { 20000, 30000, 50000 };
    static final long[] DISCOUNTED_PRICE = new long[] { 18000, 27000, 45000 };
    static final int[] DISCOUNT_CONSTRAINT = new int[] { 10, 10, 10 };
    static final Map<String, Integer> KEBAB_PRICE_INDEX = Map.ofEntries(
            Map.entry("Ayam", 0),
            Map.entry("Sapi", 1),
            Map.entry("Premium", 2));

    private static long countPrice(String type, int count) {
        int kebabType = KEBAB_PRICE_INDEX.get(type);
        if (count >= DISCOUNT_CONSTRAINT[kebabType]) {
            return DISCOUNTED_PRICE[kebabType] * count;
        } else {
            return NORMAL_PRICE[kebabType] * count;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> kebabList = new HashMap<>();
        long result = 0l;
        for (int i = 0; i < INPUT_COUNT; i++) {
            scanner.next();
            kebabList.merge(scanner.next(), scanner.nextInt(), Integer::sum);
        }
        scanner.close();
        for (String i : kebabList.keySet()) {
            result += countPrice(i, kebabList.get(i));
        }
        System.out.println("Rp" + result);
    }
}
