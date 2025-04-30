import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Date;

class UserDataStruct {
    String currentDirectory;
    String userInput;
    String message;
    File file;
}

public class FileManager {
    private static Pager pager = new Pager();
    private static UserDataStruct userDataStruct = new UserDataStruct();

    private static void init() {
        update(System.getProperty("user.dir"));
    }

    private static void update(String directory) {
        userDataStruct.currentDirectory = directory;
        userDataStruct.file = new File(userDataStruct.currentDirectory);
    }

    private static void writeFile(String fileName) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(pager.customInput("File content", true));
            fileWriter.close();

            pager.message(
                    String.format(
                            "wrote %s to %s",
                            getFileSize(),
                            userDataStruct.file.getName()));
        } catch (IOException e) {
            pager.message("Failed to write to file");
        }
    }

    private static String getFileSize() {
        long size = userDataStruct.file.length();
        String sizeString = "KB";
        if (size <= 1024 * 1000) {
            size /= 1000;
        } else {
            sizeString = "MB";
            size /= 1000 * 1000;
        }

        return String.format("%d %s", size, sizeString);
    }

    private static void ls() {
        try {
            pager.message("Directory list:");
            for (File directory : userDataStruct.file.listFiles()) {
                if (directory.isDirectory()) {
                    pager.message(directory.getName(), 4);
                }
            }
            pager.spacer();

            pager.message("File list:");
            for (File file : userDataStruct.file.listFiles()) {
                if (file.isFile()) {
                    pager.message(file.getName(), 4);
                }
            }
            pager.spacer();
        } catch (NullPointerException e) {
            pager.message("ls: invalid directory");
        }
    }

    private static void inf() {
        String[] input = userDataStruct.userInput.split("\\s+");
        String temp = userDataStruct.file.getAbsolutePath();

        if (input.length != 2) {
            pager.message("Usage: inf <file>");
            return;
        }

        String currentPath = userDataStruct.file.getAbsolutePath().concat("/" + input[1]);

        try {
            userDataStruct.file = new File(currentPath);

            if (userDataStruct.file.exists()) {
                String size = getFileSize();

                pager.message(String.format("Name: %s", userDataStruct.file.getName()));
                pager.message(String.format("Size: %s", size));
                pager.message(String.format("Absolute Path: %s", userDataStruct.file.getAbsolutePath()));
                pager.message(String.format("Last Modified: %s", new Date(userDataStruct.file.lastModified())));
                pager.message(String.format("Write: %b", userDataStruct.file.canWrite()));
                pager.message(String.format("Execute: %b", userDataStruct.file.canExecute()));
                pager.message(String.format("Hidden: %b", userDataStruct.file.isHidden()));
            } else {
                pager.message(
                        String.format(
                                "inf: %s: No such file or directory",
                                input[1]));
            }
        } catch (NullPointerException e) {
            pager.message("inf: path name is null");
        } finally {
            userDataStruct.file = new File(temp);
            update(System.getProperty("user.dir"));
        }
    }

    private static void cat() {
        String[] input = userDataStruct.userInput.split("\\s+");
        if (input.length != 2) {
            pager.message("Usage: cat <file>");
            return;
        }

        BufferedReader bufferedReader = null;
        userDataStruct.file = new File(input[1]);

        if (!userDataStruct.file.canRead()) {
            pager.message(String.format(
                    "Can't read %s",
                    input[1]));
        }

        try (FileReader fileReader = new FileReader(input[1])) {
            bufferedReader = new BufferedReader(fileReader);
            String content;

            while ((content = bufferedReader.readLine()) != null) {
                pager.message(content);
            }

            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            pager.message(
                    String.format(
                            "cat: %s: No such file or directory",
                            input[1]));
        }

        update(System.getProperty("user.dir"));
    }

    private static void touch() {
        String[] input = userDataStruct.userInput.split("\\s+");
        if (input.length != 2) {
            pager.message("Usage: touch <file>");
            return;
        }

        userDataStruct.file = new File(input[1]);

        if (userDataStruct.file.exists()) {
            while (true) {
                String deleteCreate = pager.customInput((String.format(
                        "File %s already exist, delete and create a new one? [Y/n]",
                        input[1])));

                if (deleteCreate.equalsIgnoreCase("Y")) {
                    userDataStruct.file.delete();
                    writeFile(input[1]);
                    break;
                } else if (deleteCreate.equalsIgnoreCase("n")) {
                    break;
                }
            }
        } else {
            pager.message(String.format("Created file: %s", userDataStruct.file.getName()));
            writeFile(input[1]);
        }

        userDataStruct.userInput = String.format("cat %s", input[1]);

        pager.spacer();
        pager.message("File content:");
        pager.spacer();

        cat();

        update(System.getProperty("user.dir"));
    }

    private static void mkdir() {
        String[] input = userDataStruct.userInput.split("\\s+");
        if (input.length != 2) {
            pager.message("Usage: mkdir <directory>");
            return;
        }

        userDataStruct.file = new File(input[1]);

        if (userDataStruct.file.exists()) {
            pager.message(String.format(
                    "Directory %s already exist",
                    input[1]));
        } else {
            userDataStruct.file.mkdir();
        }

        update(System.getProperty("user.dir"));
    }

    private static void commandNotFound() {
        userDataStruct.message = String.format("%s: command not found", userDataStruct.userInput);
    }

    private static void exit() {
        pager.footer();

        System.exit(0);
    }

    public static void main(String[] args) {
        init();
        boolean init = false;

        while (true) {
            pager.header("Java File Manager");

            pager.message(String.format("Current directory: %s", userDataStruct.currentDirectory));
            pager.spacer();

            if (!init) {
                ls();
                init = true;
            }

            if (userDataStruct.message != null) {
                pager.message(userDataStruct.message);
                pager.spacer();

                userDataStruct.message = null;
            }

            pager.message("ls: list directory contents");
            pager.message("inf <file>: get file info");
            pager.message("cat <file>: show file contents");
            pager.message("touch <file>: create file");
            pager.message("mkdir <directory>: create directory");
            pager.message("exit: exit file manager");
            pager.spacer();

            userDataStruct.userInput = pager.input();

            switch (userDataStruct.userInput.split("\\s+")[0]) {
                case "ls":
                    pager.spacer();
                    ls();

                    pager.spacer();
                    pager.message("press enter to continue");
                    pager.customInput();
                    break;
                case "inf":
                    pager.spacer();
                    inf();

                    pager.spacer();
                    pager.message("press enter to continue");
                    pager.customInput();
                    break;
                case "cat":
                    pager.spacer();
                    cat();

                    pager.spacer();
                    pager.message("press enter to continue");
                    pager.customInput();
                    break;
                case "touch":
                    pager.spacer();
                    touch();

                    pager.spacer();
                    pager.message("press enter to continue");
                    pager.customInput();
                    break;
                case "mkdir":
                    pager.spacer();
                    mkdir();

                    pager.spacer();
                    pager.message("press enter to continue");
                    pager.customInput();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    commandNotFound();
            }

            pager.footer();
        }
    }
}
