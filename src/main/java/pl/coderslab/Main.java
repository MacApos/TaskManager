package pl.coderslab;

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

        for (String[] data : tasks) {
            System.out.println(Arrays.toString(data));
        }
        System.out.println();
        return tasks;

    }

    public static void optionSelect() {
        System.out.println("""
                Choose your option:
                1. add
                2. list
                3. remove
                4. exit""");
        Scanner scan = new Scanner(System.in);


//        while (option.isEmpty()) {
//            System.out.println("empty");
//            option = scan.nextLine();
//        }

//        while(scan.hasNextLine()){
//    }
        String option = scan.nextLine();
        switch (option.toLowerCase()) {
            case "add":
                addTask();
                break;

            case "list":
                System.out.println("list");
                break;

            case "remove":
                System.out.println("remove");
                break;

            case "exit":
                System.out.println("Are you sure you want to exit? y/n");
                String confiramtion = scan.nextLine();
                if (confiramtion.equals("y")) {
                    break;
                } else if (confiramtion.equals("n")) {
                    optionSelect();
                } else {
                    while (!confiramtion.equals("y") &&
                            !confiramtion.equals("n")) {
                        System.err.println("Insert y or n.");
                        confiramtion = scan.nextLine();
                    }
                }
                break;

            default:
                System.out.println("Please select a correct option.");

        }

    }

    public static void addTask() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please add description.");
        StringBuilder sb = new StringBuilder();

        String description = scan.nextLine();
        while (description.trim().isEmpty()) {
            System.out.println("No description.");
            description = scan.nextLine();
        }
        sb.append(description).append("\n");

        System.out.println("Add date in dd-MM-yyyy format.");
        String date = scan.nextLine();
        while (!validateDate(date)) {
            System.out.println("Please insert date in dd-MM-yyyy format.");
            date = scan.nextLine();
        }
        sb.append(date).append("\n");

        System.out.println("Is this task important true/false.");
        while (!scan.hasNextBoolean()) {
            System.out.println("Inset true or false");
            scan.nextLine();
        }
        sb.append(scan.nextLine()).append("\n");

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = sb.toString().split("\n");
        for (String[] task : tasks) {
            System.out.println(Arrays.toString(task));
        }
    }

    public static boolean validateInt(String str) {
        try {
            Double.parseDouble(str);
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
        return true;
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
}

