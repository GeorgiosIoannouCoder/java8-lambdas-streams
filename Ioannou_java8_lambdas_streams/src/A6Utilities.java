import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A6Utilities.java
 * A6Utilities class works with Java’s Lambdas and Streams as an alternative to traditional procedural programming.
 *
 * @author Georgios Ioannou
 * @version 1.0 08 Dec 2021
 */

public class A6Utilities {

    /**
     * Returns the corresponding letter grade. Don't modify this method, simply use it when converting single
     * grades to their equivalent letter grade
     *
     * @param grade the integer grade to convert
     * @return the letter grade equivalent to the integer grade
     */
    private static char letterGrade(final int grade) {

        if (grade < 0) return '?';
        else if (grade < 60) return 'F';
        else if (grade < 70) return 'D';
        else if (grade < 80) return 'C';
        else if (grade < 90) return 'B';
        else if (grade <= 100) return 'A';
        else return '?';
    }

    /**
     * The method uses Java Streams only. Don't use any looping structures, conditional statements are OK (IF, TERNARY, SWITCH)
     * <p>
     * Generates a list of secure random numbers using java.security.SecureRandom:
     * 1. Random numbers between min and max:
     * 2. The number of randoms is equal to poolSize
     * 3. The list is sorted
     *
     * @param min      the minimum random number range
     * @param max      the maximum random number range
     * @param poolSize the number of random numbers to generate
     * @return a sorted list of random numbers between min and max and size poolSize
     */
    public static List<Integer> generateRandoms(final int min, final int max, final int poolSize) {

        SecureRandom secureRandom = new SecureRandom();
        return secureRandom
                .ints(poolSize, min, max)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * The method uses Java Streams only. Don't use any looping structures, conditional statements are OK (IF, TERNARY, SWITCH)
     * <p>
     * Receives a list of grades and returns a list of corresponding letter grades. Use the private letterGrade method
     *
     * @param listGrades the list of grades to convert
     * @return a list of corresponding letter grades
     */
    public static List<Character> letterToGrades(final List<Integer> listGrades) {

        return listGrades
                .stream()
                .mapToInt(Integer::intValue)
                .mapToObj(A6Utilities::letterGrade)
                .toList();
    }

    /**
     * The method uses Java Streams only. Don't use any looping structures, conditional statements are OK (IF, TERNARY, SWITCH)
     * <p>
     * Returns the sum of values within the listInt parameter between sumFrom and sumTo (inclusive). The method throws an IllegalArgumentException
     * with message "Incorrect parameters" if sumFrom is larger than sumTo, or if sumFrom and sumTo are outside the list's ranges
     *
     * @param listInt a list of integer values
     * @param sumFrom the starting sum position
     * @param sumTo   the end sum position
     * @return the sum of values between the parameters sumFrom and sumTo
     */
    public static int sumBetween(final List<Integer> listInt, final int sumFrom, final int sumTo) throws IllegalArgumentException {

        if ((sumFrom > sumTo) || (sumFrom < 1) || (sumTo > listInt.size())) {
            throw new IllegalArgumentException("Incorrect parameters");
        }
        else {
            return listInt
                    .stream()
                    .mapToInt(Integer::intValue)
                    .skip(sumFrom - 1)
                    .limit(sumTo - sumFrom + 1)
                    .sum();
        }
    }

    /**
     * The method uses Java Streams only. Don't use any looping structures, conditional statements are OK (IF, TERNARY, SWITCH)
     * <p>
     * Returns a count of each character within the provided text file. This question is a simpler version of the example
     * in Section 17.13 (slide 177)
     *
     * @param fileName the file to count the characters for
     * @return each character and its count
     * @throws IOException thrown if the file read operation fails
     */
    public static TreeMap<Character, Long> countCharacters(final String fileName) throws IOException {

        Pattern pattern = Pattern.compile("");
        return Files
                .lines(Paths.get(fileName))
                .flatMap(pattern::splitAsStream)
                .map(x -> x.charAt(0))
                .collect(Collectors.groupingBy(Character::toUpperCase, TreeMap::new, Collectors.counting()));
    }
}