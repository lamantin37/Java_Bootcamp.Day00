package day00.ex03;

import java.util.Scanner;

public class Program {
    private static int parseGrades(String gradeStr) {
        String[] gradesArray = gradeStr.split(" ");
        if (gradesArray.length != 5) {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }

        int minGrade = 10;
        for (String grade : gradesArray) {
            int currentGrade = Integer.parseInt(grade);
            if (currentGrade > 9 || currentGrade < 1) {
                System.err.println("Illegal Argument!");
                System.exit(-1);
            }
            if (currentGrade < minGrade) {
                minGrade = currentGrade;
            }
        }

        return minGrade;
    }

    private static void drowGrades(int[] gradesArray){
        int week_counter = 1;
        for (int grade : gradesArray) {
            if (grade != 0) {
                System.out.print("Week " + week_counter + " ");
                for (int i = 0; i < grade; ++i) {
                    System.out.print("=");
                }
                System.out.println(">");
                ++week_counter;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int week = 1;
        String weekName = scanner.nextLine();
        int[] gradeArray = new int[18];
        while (week <= 18) {

            if (weekName.equals("42")) {
                break;
            }
            if (!weekName.equals("Week " + week)) {
                System.err.println("Illegal Argument!");
                System.exit(-1);
            }

            gradeArray[week - 1] = parseGrades(scanner.nextLine());
            weekName = scanner.nextLine();
            ++week;
        }
        drowGrades(gradeArray);

        scanner.close();
    }
}
