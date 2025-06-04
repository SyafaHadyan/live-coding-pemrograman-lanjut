import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Manajer {
    static final int MAX_CLASS = 3;

    static Scanner scanner;
    static ArrayList<String> studentList;
    static HashMap<String, ArrayList<String>> registrationList;
    static ArrayList<String> classList;
    static HashSet<String> consultationList;
    static String[] commandInput;

    private static void init() {
        scanner = new Scanner(System.in);
        studentList = new ArrayList<>();
        registrationList = new HashMap<>();
        classList = new ArrayList<>();
        consultationList = new HashSet<>();
    }

    private static void processInput() {
        int count = Integer.parseUnsignedInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {

            String command = scanner.next();

            switch (command) {
                case "ADD_STUDENT":
                    studentList.add(scanner.next());
                    break;

                case "ADD_CLASS":
                    classList.add(scanner.next());
                    break;

                case "REGISTER_CLASS":
                    String name = scanner.next();
                    String className = scanner.next();

                    if (registrationList.containsKey(name)) {
                        ArrayList<String> currrentStudentClassList = registrationList.get(name);

                        if (currrentStudentClassList.size() < MAX_CLASS) {
                            currrentStudentClassList.add(className);
                        }
                    } else {
                        registrationList.put(
                                name,
                                new ArrayList<>(Arrays.asList(className)));
                    }

                    break;

                case "CONSULTATION":
                    consultationList.add(scanner.next());
                    break;

                default:
                    System.err.println("INVALID COMMAND");
            }

            // DO NOT REMOVE: FIX HACKERRANK ERROR
            try {
                scanner.nextLine();
            } catch (NoSuchElementException e) {
            }
        }
    }

    private static void sortAlphabet() {
        Collections.sort(classList);
    }

    private static void displayOutput() {
        System.out.printf("Daftar Pelajar: %s\n", studentList);
        System.out.printf("Daftar Kelas: %s\n", classList);
        System.out.printf("Antrean Konsultasi: %s\n", consultationList);
        System.out.println("Kelas yang Diikuti:");

        for (String i : registrationList.keySet()) {
            System.out.printf("%s: %s\n", i, registrationList.get(i));
        }
    }

    public static void main(String[] args) {
        init();
        processInput();
        sortAlphabet();
        displayOutput();
    }
}
