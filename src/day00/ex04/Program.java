package day00.ex04;


import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (str.length() >= 999) {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }
        printValues(sortFrequencyMap(parseCharacters(str)));
        scanner.close();
    }

    private static TreeMap<Character, Integer> parseCharacters(String str) {
        TreeMap<Character, Integer> frequencyMap = new TreeMap<>();

        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                frequencyMap.merge(Character.toUpperCase(ch), 1, Integer::sum);
            }
        }

        return frequencyMap;
    }

    private static TreeMap<Character, Integer> sortFrequencyMap(TreeMap<Character, Integer> frequencyMap) {
        while (frequencyMap.size() > 10) {
            frequencyMap.remove(findMinValue(frequencyMap));
        }
        return frequencyMap;
    }

    private static char findMinValue(TreeMap<Character, Integer> frequencyMap) {
        int minFrequency = Collections.min(frequencyMap.values());
        return frequencyMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == minFrequency)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No element found with the minimum value"));
    }

    private static void preprocessOutputValues(List<Map.Entry<Character, Integer>> entryList) {
        int scalar = entryList.stream().mapToInt(Map.Entry::getValue).max().orElse(0);
        if (scalar > 10) {
            entryList.forEach(entry -> entry.setValue((int) Math.floor(entry.getValue() / ((double) scalar / 10))));
        }
    }

    private static void printValues(TreeMap<Character, Integer> frequencyMap) {
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>();

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            entryList.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
        }

        entryList.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
        preprocessOutputValues(entryList);

        int maxFrequency = entryList.get(0).getValue();
        entryList.forEach(entry -> entry.setValue(entry.getValue() + 1));

        for (int i = maxFrequency + 1; i > 0; i--) {
            for (Map.Entry<Character, Integer> entry : entryList) {
                if (entry.getValue() >= i) {
                    if (entry.getValue() == i) {
                        System.out.printf("%3d ", frequencyMap.get(entry.getKey()));
                    } else {
                        System.out.print("  # ");
                    }
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        entryList.forEach(entry -> System.out.printf("%3s ", entry.getKey()));
        System.out.println();
    }
}
