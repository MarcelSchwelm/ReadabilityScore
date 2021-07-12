package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static int numberOfWords;
    static int numberOfCharacters;
    static int numberOfSentences;
    static int numberOfSyllables;
    static int numberOfPolysyllables;

    public static void main(String[] args) {
        String input;
        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(args[0]));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.err.println("Error reading File: " + args[0]);
        }

        System.out.println("The text is:");
        if (lines != null) {
            for (String line : lines) {
                System.out.println(line);
            }
        }

        numberOfWords = countWords(lines);
        numberOfSentences = countSentences(lines);
        numberOfCharacters = countCharacters(lines);
        numberOfSyllables = countSyllables(lines);

        System.out.println("%nWords: " + numberOfWords);
        System.out.println("Sentences: " + numberOfSentences);
        System.out.println("Characters: " + numberOfCharacters);
        System.out.println("Syllables: " + numberOfSyllables);
        System.out.println("Polysyllables: " + numberOfPolysyllables);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");

        try (Scanner scanner = new Scanner(System.in)) {
            input = scanner.next();
        }
        switch (input.toLowerCase(Locale.ROOT)) {
            case "ari":
                calculateARI();
                break;
            case "fk":
                calculateFK();
                break;
            case "smog":
                calculateSMOG();
                break;
            case "cl":
                calculateCL();
                break;
            case "all":
                calculateARI();
                calculateFK();
                calculateSMOG();
                calculateCL();
                break;
            default:
                System.out.println("Couldn't match input!");
        }
    }

    private static void calculateARI() {
        double score;
        score = 4.71 * ((double) numberOfCharacters / (double) numberOfWords) + 0.5 * ((double) numberOfWords / (double) numberOfSentences) - 21.43;
        System.out.printf("Automated Readability Index: %.2f (about %s-year-olds).%n", score, getAges(score));
    }

    private static void calculateCL() {
        double score;
        double l;
        double s;
        l = calculateAverageNrOfCharsPer100Words();
        s = calculateAverageNrOfSentPer100Words();
        score = 0.0588 * l - 0.296 * s - 15.8;
        System.out.printf("Coleman–Liau index: %.2f (about %s-year-olds).%n", score, getAges(score));
    }

    private static double calculateAverageNrOfSentPer100Words() {
        return numberOfSentences / ((double) numberOfWords / 100);
    }

    private static double calculateAverageNrOfCharsPer100Words() {
        return numberOfCharacters / ((double) numberOfWords / 100);
    }

    private static void calculateSMOG() {
        double score;
        score = 1.043 * Math.sqrt((double) (numberOfPolysyllables * 30) / numberOfSentences) + 3.1291;
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).%n", score, getAges(score));
    }

    private static void calculateFK() {
        double score;
        score = 0.39 * numberOfWords / numberOfSentences + 11.8 * numberOfSyllables / numberOfWords - 15.59;
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s-year-olds).%n", score, getAges(score));
    }

    private static int countSyllables(List<String> lines) {
        int numberOfSyllables = 0;
        int numberOfVocals;
        char precedingChar;
        boolean precedingCharIsVocal;
        numberOfPolysyllables=0;

        for (String line : lines) {
            String[] words = line.split(" ");

            for (String word : words) {
                word = word.toLowerCase(Locale.ROOT);
                word = word.replaceAll("[.,!?]", "");

                numberOfVocals = 0;
                precedingChar = word.charAt(0);
                if (precedingChar == 'a' || precedingChar == 'e' || precedingChar == 'i' || precedingChar == 'o' || precedingChar == 'u' || precedingChar == 'y') {
                    numberOfVocals++;
                    precedingCharIsVocal = true;
                } else {
                    precedingCharIsVocal = false;
                }
                for (int i = 1; i < word.length(); i++) {
                    if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u' || word.charAt(i) == 'y') {
                        if (!precedingCharIsVocal) {
                            numberOfVocals++;
                        }
                        precedingCharIsVocal = true;
                    } else {
                        precedingCharIsVocal = false;
                    }
                    if (i == word.length() - 1 && word.charAt(i) == 'e') {
                        numberOfVocals--;
                    }
                }

                if (numberOfVocals == 0) {
                    numberOfSyllables++;
                } else {
                    numberOfSyllables += numberOfVocals;
                }
                if (numberOfVocals > 2) {
                    numberOfPolysyllables++;
                }
            }
        }
        return numberOfSyllables;
    }

    private static String getAges(double score) {

        int scoreInt = (int) Math.round(score);
        switch (scoreInt) {
            case 1:
                return "6";
            case 2:
                return "7";
            case 3:
                return "9";
            case 4:
                return "10";
            case 5:
                return "11";
            case 6:
                return "12";
            case 7:
                return "13";
            case 8:
                return "14";
            case 9:
                return "15";
            case 10:
                return "16";
            case 11:
                return "17";
            case 12:
                return "18";
            case 13:
                return "24";
            case 14:
                return "24+";
            default:
                return "Couldn't match input";
        }
    }

    private static int countCharacters(List<String> lines) {
        int numberOfCharacters = 0;
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                if ((line.charAt(i) != ' ') && (line.charAt(i) != '\n') && (line.charAt(i) != '\t')) {
                    numberOfCharacters++;
                }
            }
        }
        return numberOfCharacters;
    }

    private static int countWords(List<String> lines) {
        int numberOfWords = 0;
        for (String line : lines) {
            String[] words = line.split(" ");
            numberOfWords += words.length;
        }
        return numberOfWords;
    }

    private static int countSentences(List<String> lines) {
        int numberOfSentences = 0;
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                if (word.matches(".*\\b[.!?]")) {
                    numberOfSentences++;
                }
            }
        }
        if (!lines.get(lines.size() - 1).matches(".*\\b[.!?]")) {
            numberOfSentences++;
        }
        return numberOfSentences;
    }
}
