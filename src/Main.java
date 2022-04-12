import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        writeOutput(checkPairs(readInput()));
    }

    public static String[][] readInput() {
        try(Scanner scanner = new Scanner(new File("input.txt"))) {
            String[] firstBlock = new String[Integer.parseInt(scanner.nextLine())];
            for (int i = 0; i < firstBlock.length; i++) {
                firstBlock[i] = scanner.nextLine();
            }

            String[] secondBlock = new String[Integer.parseInt(scanner.nextLine())];
            for (int i = 0; i < secondBlock.length; i++) {
                secondBlock[i] = scanner.nextLine();
            }

            return new String[][]{firstBlock, secondBlock};
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void writeOutput(String[] strings) {
        try(FileWriter writer = new FileWriter(new File("output.txt"))) {
            for (int i = 0; i < strings.length; i++) {
                if(i != 0) writer.write('\n');
                writer.write(strings[i]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[] checkPairs(String[][] strings) {
        String[] result = new String[Math.max(strings[0].length, strings[1].length)];

        for (int i = 0; i < result.length; i++) {
            result[i] = strings[0][i] + ":?";
            int maxCoincideWords = 0;
            for (int j = 0; j < strings[1].length; j++) {
                if(wordsCoincide(strings[0][i], strings[1][j]) > maxCoincideWords) {
                    result[i] = strings[0][i] + ":" + strings[1][j];
                    maxCoincideWords = wordsCoincide(strings[0][i], strings[1][j]);
                }
            }
        }
        return result;
    }

    public static int wordsCoincide(String firstString, String secondString) {
        int counter = 0;

        String[] first = firstString.split(" ");
        String[] second = secondString.split(" ");

        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < second.length; j++) {
                if(first[i].equalsIgnoreCase(second[j])) counter++;
            }
        }

        return counter;
    }
}
