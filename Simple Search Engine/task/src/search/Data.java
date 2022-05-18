package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Data {

    private Strategy strategy = Strategy.ALL;
    final private List<String> list = new ArrayList<>();
    final private Map<String, List<Integer>> map = new HashMap<>();

    public void readFile(String text) {
        File file = new File(text);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }

        generateInvertedIndex();
    }

    public void generateInvertedIndex() {
        for (int i = 0; i < list.size(); i++) {
            String[] arr = list.get(i).split(" ");
            for (int j = 0; j < arr.length; j++) {
                String key = arr[j].toLowerCase();
                if (map.containsKey(key)) {
                    map.get(key).add(i);
                } else {
                    map.put(key, new ArrayList<>());
                    map.get(key).add(i);
                }
            }
        }
    }

    public void search(String word) {
        switch (strategy) {
            case ALL:
                searchAll(word);
                break;
            case ANY:
                searchAny(word);
                break;
            case NONE:
                searchNone(word);
                break;
        }
    }

    public void print() {
        System.out.println("\n=== List of people ===");

        for (String s : list) {
            System.out.println(s);
        }
    }

    public void searchAll(String word) {
        for (String s : list) {
            if (s.toLowerCase().contains(word)) {
                System.out.println(s);
            }
        }
    }

    public void searchAny(String word) {
        String[] arr = word.split(" ");
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                set.addAll(map.get(arr[i]));
            }
        }
        if (set.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            for (Integer num : set) {
                System.out.println(list.get(num));
            }
        }
    }

    public void searchNone(String word) {
        String[] arr = word.split(" ");
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                set.addAll(map.get(arr[i]));
            }
        }

        String[] newList = new String[list.size()];

        for (int i = 0; i < newList.length; i++) {
            newList[i] = list.get(i);
        }

        for (Integer num : set) {
            newList[num] = null;
        }

        for (int i = 0; i < newList.length; i++) {
            if (newList[i] != null) {
                System.out.println(newList[i]);
            }
        }
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
