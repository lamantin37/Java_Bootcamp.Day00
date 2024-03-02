package day00.ex04;


import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        if (str.length() >= 999) {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }

        printValues(SortFrequencyMap(parseCharacters(str)));
        scanner.close();
    }

    private static TreeMap<Character, Integer> parseCharacters(String str) {
        TreeMap<Character, Integer> frequencyMap = new TreeMap<>();

        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                frequencyMap.put(Character.toUpperCase(ch),
                        frequencyMap.getOrDefault(Character.toUpperCase(ch), 0) + 1);
            }
        }

        return frequencyMap;
    }

    private static TreeMap<Character, Integer> SortFrequencyMap(TreeMap<Character, Integer> frequencyMap) {

        while (frequencyMap.size() > 10) {
            frequencyMap.remove(findMinValue(frequencyMap));
        }

        return frequencyMap;
    }

    private static char findMinValue(TreeMap<Character, Integer> frequencyMap) {
        int minFrequency = 1000;
        char keyValue = ' ';

        for (Map.Entry<Character, Integer> enter : frequencyMap.descendingMap().entrySet()) {
            if (enter.getValue() < minFrequency) {
                minFrequency = enter.getValue();
                keyValue = enter.getKey();
            }
        }

        return keyValue;
    }

    private static List<Map.Entry<Character, Integer>> preprocessOutputValues(
            List<Map.Entry<Character, Integer>> entryList) {
        int scalar = 0;
        for (Map.Entry<Character, Integer> entry : entryList) {
            scalar = Math.max(scalar, entry.getValue());
        }

        if (scalar > 10) {
            final int finalScalar = scalar;
            entryList.forEach(entry -> entry.setValue((int) Math.floor(entry.getValue()
                    / ((double) finalScalar / 10))));
        }


        return entryList;
    }

    private static void printValues(TreeMap<Character, Integer> frequencyMap) {
        Map<Character, Integer> finalFrequency = new TreeMap<>(frequencyMap);
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        preprocessOutputValues(entryList);

        int maxFrequency = entryList.get(0).getValue(); // Максимальная частота
        entryList.forEach((entry) -> entry.setValue(entry.getValue() + 1));

        for (int i = maxFrequency + 1; i > 0; i--) {
            for (Map.Entry<Character, Integer> entry : entryList) {
                if (entry.getValue() >= i) {
                    if (entry.getValue() == i) {
                        System.out.printf("%3d ", finalFrequency.get(entry.getKey()));
                    } else {
                        System.out.printf("  # ", entry.getValue());
                    }
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        for (Map.Entry<Character, Integer> entry : entryList) {
            System.out.printf("%3s ", entry.getKey());
        }
        System.out.println();
    }
}
