import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class A6_Test {

    private static final String TEST_INPUT_FILE = "src/TestText.txt";

    public static void main(String[] args) throws IOException {

        List<Integer> listSortedRandoms = A6Utilities.generateRandoms(40, 101, 50);

        System.out.printf("  Random sorted grades list: %s%n", listSortedRandoms
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));

        System.out.printf("Corresponding letter grades: %s%n", A6Utilities.letterToGrades(listSortedRandoms)
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining("  ")));

        System.out.println();

        testSumBetween(listSortedRandoms, 1, 2);
        testSumBetween(listSortedRandoms, 49, 50);
        testSumBetween(listSortedRandoms, 5, 10);
        testSumBetween(listSortedRandoms, 22, 33);
        testSumBetween(listSortedRandoms, 1, 50);
        testSumBetween(listSortedRandoms, 15, 1);
        testSumBetween(listSortedRandoms, -1, 1);
        testSumBetween(listSortedRandoms, 0, 50);

        System.out.println();

        System.out.printf("Character counts in %s:%n", TEST_INPUT_FILE);

        A6Utilities.countCharacters(TEST_INPUT_FILE)
                .forEach((letter, count) -> System.out.printf("%3C: %d%n", letter, count));
    }

    private static void testSumBetween(List<Integer> listSortedRandoms, int sumFrom, int sumTo) {

        try {

            System.out.printf("Sum of numbers between %-2d and %-2d: %,d%n", sumFrom, sumTo, A6Utilities.sumBetween(listSortedRandoms, sumFrom, sumTo));
        } catch (Exception ex) {
            System.out.printf("Exception thrown by A6Utilities.sumBetween %-2d and %-2d: %s%n", sumFrom, sumTo, ex);
        }
    }
}