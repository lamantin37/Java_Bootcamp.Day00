package day00.ex00;


import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int sumOfDigits = 0;

        String numAsString = String.valueOf(num);
        for (char digit : numAsString.toCharArray()) {
            sumOfDigits += Character.getNumericValue(digit);
        }

        System.out.println(sumOfDigits);
        scanner.close();
    }
}

