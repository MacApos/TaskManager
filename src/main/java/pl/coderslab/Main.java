package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Main {
    static String[][] tasks = new String[0][3];

    public static void main(String[] args) {
        String[][] initialData;
        try {
            initialData = uploadData();
        } catch (IOException e) {
            System.err.println("Error while running uploadData methode: " +
                    e.getMessage());
        }

        optionSelect();
//        addTask();
        printResults(tasks);
    }


    public static String[][] uploadData() throws IOException {
        Path csvPath = Paths.get("/home/maciej/Documents/Zasoby_do_projektu/" +
                "05_attachment_Zasoby do projektu.pl/tasks.csv");

        if (!Files.exists(csvPath)) {
            System.err.println(csvPath.toAbsolutePath() + " doesn't exists.");
        }

        System.out.print("Uploading initial file...");
//        int wait = 0;
//        while (wait != 3){
//            try {
//                Thread.sleep(500);
//                System.out.print(".");
//                wait ++;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }

        System.out.println();


        try (Scanner file = new Scanner(csvPath)) {
            while (file.hasNextLine()) {
                String newLine = file.nextLine();
                tasks = Arrays.copyOf(tasks, tasks.length + 1);
                tasks[tasks.length - 1] = newLine.split(", ");

            }
        } catch (IOException e) {
            System.err.println(csvPath.toAbsolutePath() + " does not exists.");
        }

        printResults(tasks);
        return tasks;

    }

    public static void optionSelect() {
        System.out.println("""
                Choose your option:
                1. add
                2. list
                3. remove
                4. exit
                """);
        Scanner optionScan = new Scanner(System.in);


//        while (option.isEmpty()) {
//            System.out.println("empty");
//            option = scan.nextLine();
//        }

//        while(scan.hasNextLine()){
//    }
        String option = optionScan.nextLine();
        switch (option.toLowerCase()) {
            case "add":
                addTask();
                optionSelect();

            case "list":
                listTask();
                break;

            case "remove":
                removeTask();
                optionSelect();

            case "exit":
                exitManager();
                break;

            default:
                System.out.println("Please select a correct option.");
                optionSelect();
        }
        optionScan.reset();
    }

    private static void listTask() {

    }

    public static void addTask() {
        Scanner addScan = new Scanner(System.in);
        System.out.println("Please add description.");
        StringBuilder sb = new StringBuilder();

        String description = addScan.nextLine();
        while (description.trim().isEmpty()) {
            System.out.println("No description.");
            description = addScan.nextLine();
        }
        sb.append(description).append("\n");

        System.out.println("Add date in dd-MM-yyyy format.");
        String date = addScan.nextLine();
        while (!validateDate(date)) {
            System.out.println("Please insert date in dd-MM-yyyy format.");
            date = addScan.nextLine();
        }
        sb.append(date).append("\n");

        System.out.println("Is this task important true/false.");
        while (!addScan.hasNextBoolean()) {
            System.out.println("Inset true or false");
            addScan.nextLine();
        }
        sb.append(addScan.nextLine()).append("\n");

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = sb.toString().split("\n");

        addScan.reset();
    }


    public static void removeTask() {
        Scanner removeScan = new Scanner(System.in);

        System.out.println("Select number of task to be remove.");
        while (!removeScan.hasNextInt()) {
            System.out.println("Insert number.");
            removeScan.nextLine();
        }
        int number = Integer.parseInt(removeScan.nextLine()) - 1;


        try {
            tasks = ArrayUtils.remove(tasks, number);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index is out of boundary. Insert number from: 0 to " + tasks.length);
            removeTask();
        }

        removeScan.reset();
    }


    public static void exitManager() {
        Scanner exitScan = new Scanner(System.in);
        System.out.println("Are you sure you want to exit? y/n");
        String confiramtion = "";
        while (!confiramtion.equals("y") &&
                !confiramtion.equals("n")) {
            confiramtion = exitScan.nextLine();
            System.out.println("Insert y or n.");
        }
        if (confiramtion.equals("n")) {
            optionSelect();
        }
    }

    public static boolean validateDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

        try {
            LocalDate date = LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static void printResults(String[][] results) {
        System.out.println();
        for (String[] task : results) {
            System.out.println(Arrays.toString(task));
        }
    }
}

