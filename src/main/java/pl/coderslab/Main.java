package pl.coderslab;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        String[][] initialData;
        try {
            initialData = uploadData();
        } catch (IOException e) {
            System.err.println("Error while running uploadData methode: " +
                    e.getMessage());
        }

        optionSelect();

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

        String[][] dataArr = new String[0][3];
        try (Scanner file = new Scanner(csvPath)) {
            while (file.hasNextLine()) {
                String newLine = file.nextLine();
                dataArr = Arrays.copyOf(dataArr, dataArr.length + 1);
                dataArr[dataArr.length - 1] = newLine.split(", ");

            }
        } catch (IOException e) {
            System.err.println(csvPath.toAbsolutePath() + " does not exists.");
        }

        for (String[] data : dataArr){
            System.out.println(Arrays.toString(data));
        }

        return dataArr;

    }

    public static void optionSelect() {
        String input = "";
//        switch (input) {
//            case "add":
//                System.out.println("sd");
//                break;
//                case
//            default:
//                System.out.println("Please select a correct option.");
//        }

    }

}