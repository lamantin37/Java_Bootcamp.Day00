package day00.ex02;


import java.util.Scanner;

public class Program {
    private static int sumOfDigids(int num) {
        int sumOfDigits = 0;

        String numAsString = String.valueOf(num);
        for (char digit : numAsString.toCharArray()) {
            sumOfDigits += Character.getNumericValue(digit);
        }

        return sumOfDigits;
    }

    private static int checkPrime(int number) {
        boolean isPrime = number > 1 && (number == 2 || number % 2 != 0) && (number == 3 || number % 3 != 0);
        if (isPrime) {
            for (int i = 6; i * i <= number; i += 6) {
                if (number % (i - 1) == 0 || number % (i + 1) == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime ? 1: 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        int coffee_requests = 0;
        
        while ((num = scanner.nextInt()) != 42) {
            coffee_requests += checkPrime(sumOfDigids(num));
        }

        System.out.println("Count of coffee-request – " + coffee_requests);
        scanner.close();
    }
}
