package search;

import java.util.Scanner;

public class User {
    final static Scanner scanner = new Scanner(System.in);
    final static Data data = new Data();

    public static void start(String input) {
        data.readFile(input);
        menu();
    }

    public static void menu() {
        System.out.println("\n=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");

        int select = scanner.nextInt();

        switch (select) {
            case 1:
                System.out.println("Select a matching strategy: ALL, ANY, NONE");
                String strategy = scanner.next();

                if (strategy.equals("All")) {
                    data.setStrategy(Strategy.ALL);
                } else if (strategy.equals("ANY")) {
                    data.setStrategy(Strategy.ANY);
                } else {
                    data.setStrategy(Strategy.NONE);
                }

                scanner.nextLine();
                System.out.println("\nEnter a name or email to search all suitable people.");
                data.search(scanner.nextLine().toLowerCase());
                menu();
                break;
            case 2:
                data.print();
                menu();
                break;
            case 0:
                System.out.println("Bye!");
                System.exit(0);
            default:
                System.out.println("Incorrect option! Try again.");
                menu();
        }
    }
}
