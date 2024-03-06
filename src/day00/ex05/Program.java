package day00.ex05;

import java.util.HashMap;
import java.util.Scanner;

public class Program {
    private static final int MAX_STUDENTS = 10;
    private static final int MAX_DAYS = 30;
    private static final int MAX_CLASSES_PER_DAY = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] studentNames = new String[MAX_STUDENTS];
        int[] classTimesPerDay = new int[8];
        String[] daysOfWeek = new String[8];
        int maxNameLength = 0;

        int index = 0;
        for (String name = scanner.next(); !".".equals(name); name = scanner.next()) {
            studentNames[index] = name;
            maxNameLength = Math.max(maxNameLength, name.length());
            index++;
        }

        HashMap<String, Integer> dayIndexMap = new HashMap<>();
        dayIndexMap.put("MO", 7);
        dayIndexMap.put("TU", 1);
        dayIndexMap.put("WE", 2);
        dayIndexMap.put("TH", 3);
        dayIndexMap.put("FR", 4);
        dayIndexMap.put("SA", 5);
        dayIndexMap.put("SU", 6);

        while (scanner.hasNextInt()) {
            int classTime = scanner.nextInt();
            String day = scanner.next();
            updateClassSchedule(classTimesPerDay, daysOfWeek, classTime, dayIndexMap.get(day));
        }

        int[][][][] attendance = new int[MAX_STUDENTS][MAX_DAYS + 1][MAX_CLASSES_PER_DAY + 1][1];
        scanner.next();
        for (String name = scanner.next(); !".".equals(name); name = scanner.next()) {
            int studentIndex = getStudentIndex(studentNames, name);
            int classTime = scanner.nextInt();
            int date = scanner.nextInt();
            String status = scanner.next();
            int attendanceValue = getAttendanceValue(status);
            attendance[studentIndex][date][classTime][0] = attendanceValue;
        }

        printHeader(maxNameLength, classTimesPerDay, daysOfWeek);

        for (int j = 0; j < MAX_STUDENTS; ++j) {
            if (studentNames[j] != null) {
                printStudentAttendance(studentNames[j], maxNameLength, classTimesPerDay, attendance, j);
            }
        }
    }

    private static void updateClassSchedule(int[] classTimesPerDay, String[] daysOfWeek, int classTime, int dayIndex) {
        if (classTimesPerDay[dayIndex] == 0) {
            classTimesPerDay[dayIndex] = classTime;
            daysOfWeek[dayIndex] = getDayAbbreviation(dayIndex);
        } else {
            classTimesPerDay[dayIndex] += classTime;
        }
    }

    private static String getDayAbbreviation(int dayIndex) {
        String[] abbreviations = {"", "TU", "WE", "TH", "FR", "SA", "SU", "MO"};
        return abbreviations[dayIndex];
    }

    private static void printHeader(int maxNameLength, int[] classTimesPerDay, String[] daysOfWeek) {
        for (int l = 0; l < maxNameLength; ++l) {
            System.out.print(" ");
        }

        for (int count = 0; count <= 28; count += 7) {
            for (int k = 1; k < 8; ++k) {
                if (classTimesPerDay[k] != 0 && k + count < 31) {
                    if (k + count >= 10) {
                        System.out.printf("%d:00 %s %d|", classTimesPerDay[k], daysOfWeek[k], k + count);
                    } else {
                        System.out.printf(" %d:00 %s %d|", classTimesPerDay[k], daysOfWeek[k], k + count);
                    }
                }
            }
        }
        System.out.println();
    }

    private static void printStudentAttendance(String name, int maxNameLength, int[] classTimesPerDay, int[][][][] attendance, int studentIndex) {
        System.out.printf("%-" + maxNameLength + "s", name);

        for (int count = 0; count <= 28; count += 7) {
            for (int k = 1; k < 8; ++k) {
                if (classTimesPerDay[k] != 0 && k + count < 31) {
                    int attendanceValue = attendance[studentIndex][k + count][classTimesPerDay[k]][0];
                    System.out.printf("%10d|", attendanceValue);
                }
            }
        }
        System.out.println();
    }

    private static int getStudentIndex(String[] studentNames, String name) {
        for (int i = 0; i < MAX_STUDENTS; ++i) {
            if (studentNames[i] != null && studentNames[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private static int getAttendanceValue(String status) {
        if ("HERE".equals(status)) {
            return 1;
        } else if ("NOT_HERE".equals(status)) {
            return -1;
        } else {
            return 0;
        }
    }
}