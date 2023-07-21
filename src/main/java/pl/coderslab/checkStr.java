package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class checkStr {

    public static void main(String[] args) {
        try {
            test2();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void test1() {
        boolean checkStr = isNumeric("1o");
        System.out.println(checkStr);
        System.out.println(NumberUtils.isCreatable("1.2"));

        String[] firstNames = {"Abigail", "Alexandra", "Alison", "Amanda"}; // tworzymy tablicę
        System.out.println(Arrays.toString(firstNames)); // wyświetlamy testowo jej elementy
        firstNames = ArrayUtils.remove(firstNames, 1); //
        System.out.println(Arrays.toString(firstNames));


    }


    public static boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public static void test2()
            throws Exception
    {

        String s = "Geeksforgeeks has Scanner Class Methods";

        // create a new scanner
        // with the specified String Object
        Scanner scanner = new Scanner(s);

        // print a line of the scanner
        System.out.println("Scanner String:\n"
                + scanner.nextLine());

        // change the locale of this scanner
        scanner.useLocale(Locale.US);

        // change the radix of this scanner
        scanner.useRadix(30);

        System.out.println("\nBefore Reset:\n");

        // print the values before reset
        System.out.println("Radix: " + scanner.radix());
        System.out.println("Locale: " + scanner.locale());

        // reset
        scanner.reset();


        System.out.println("\nAfter Reset:\n");

        System.out.println("Radix: " + scanner.radix());
        System.out.println("Locale: " + scanner.locale());

        // close the scanner
        scanner.close();
    }
}



    // Java program to illustrate the
// reset() method of Scanner class in Java
