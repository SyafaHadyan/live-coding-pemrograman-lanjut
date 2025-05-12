import java.util.*;

abstract class Printables {
    abstract String formatString(String s, int i);
}

class Solution extends Printables {
    public void printSortDesc(HashMap<String, Integer> studentGrade) {
        int size = studentGrade.size();
        String result[] = new String[size];
        String name[] = new String[size];
        int grade[] = new int[size];
        int index = 0;
        for (String i : studentGrade.keySet()) {
            name[index] = i;
            grade[index] = studentGrade.get(i);
            index++;
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (grade[i] < grade[j]) {
                    int tempGradeSwap = grade[j];
                    grade[j] = grade[i];
                    grade[i] = tempGradeSwap;
                    String tempNameSwap = name[j];
                    name[j] = name[i];
                    name[i] = tempNameSwap;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            result[i] = formatString(name[i], grade[i]);
        }

        System.out.println("Urutan berdasarkan score (highest to lowest): " + Arrays.toString(result));
    }

    public void printSortAsc(HashMap<String, Integer> studentGrade) {
        int size = studentGrade.size();
        String result[] = new String[size];
        String name[] = new String[size];
        int grade[] = new int[size];
        int index = 0;
        for (String i : studentGrade.keySet()) {
            name[index] = i;
            grade[index] = studentGrade.get(i);
            index++;
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (grade[i] > grade[j]) {
                    int tempGradeSwap = grade[j];
                    grade[j] = grade[i];
                    grade[i] = tempGradeSwap;
                    String tempNameSwap = name[j];
                    name[j] = name[i];
                    name[i] = tempNameSwap;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            result[i] = formatString(name[i], grade[i]);
        }

        System.out.println("Reversed list berdasarkan nilai (lowest to highest): " + Arrays.toString(result));
    }

    public void printLowest(HashMap<String, Integer> studentGrade) {
        int lowest = Integer.MAX_VALUE;
        String name = "";
        for (String i : studentGrade.keySet()) {
            int tempGet = studentGrade.get(i);
            if (tempGet < lowest) {
                lowest = tempGet;
                name = i;
            }
        }

        System.out.println("Mahasiswa dengan nilai terendah: " + formatString(name, lowest));
    }

    public void printHighest(HashMap<String, Integer> studentGrade) {
        int highest = Integer.MIN_VALUE;
        String name = "";
        for (String i : studentGrade.keySet()) {
            int tempGet = studentGrade.get(i);
            if (tempGet > highest) {
                highest = tempGet;
                name = i;
            }
        }

        System.out.println("Mahasiswa dengan nilai tertinggi: " + formatString(name, highest));
    }

    @Override
    public String formatString(String name, int grade) {
        return String.format("Student{name='" + name + "', score=" + grade + "}");
    }
}

public class KolektorNilai {
    static Scanner scanner = new Scanner(System.in);

    private static HashMap<String, Integer> handleInput(int count) {
        HashMap<String, Integer> result = new HashMap<>();
        for (int i = 0; i < count; i++) {
            result.put(scanner.nextLine(), Integer.parseUnsignedInt(scanner.nextLine()));
        }

        return result;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> studentGrade = handleInput(Integer.parseUnsignedInt(scanner.nextLine()));
        scanner.close();
        Solution solution = new Solution();
        solution.printSortDesc(studentGrade);
        System.out.println();
        solution.printLowest(studentGrade);
        System.out.println();
        solution.printHighest(studentGrade);
        System.out.println();
        solution.printSortAsc(studentGrade);
    }
}
