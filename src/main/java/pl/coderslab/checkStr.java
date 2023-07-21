package pl.coderslab;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.regex.Pattern;

public class checkStr {
    public static void main(String[] args) {
        boolean checkStr = isNumeric("1o");
        System.out.println(checkStr);
        System.out.println(NumberUtils.isCreatable("1.2"));

    }


    public static boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

}
