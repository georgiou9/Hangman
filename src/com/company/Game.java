package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static final int MAX_MISSES = 7;

    private String answer;
    private String hits;
    private String misses;

    public Game() {
        try {
            this.answer = getRandomWord();
        } catch (IOException e) {
            e.printStackTrace();
        }
        hits = "";
        misses = "";
    }

    public String getAnswer() {
        return answer;
    }

    private char normalizeGuess(char letter) {
         if (! Character.isLetter(letter)) {
             throw new IllegalArgumentException("A letter is required");
         }
         letter = Character.toLowerCase(letter);
        if ((misses.indexOf(letter) != -1) || (hits.indexOf(letter)) != -1) {
            throw new IllegalArgumentException(letter + " has already been guesed");
        }
        return letter;
    }

    public boolean applyGuess(String letters) {
        if (letters.length() == 0) {
            throw new IllegalArgumentException("No letter found");
        }
        return applyGuess(letters.charAt(0));
    }

    public boolean applyGuess(char letter) {
        letter = normalizeGuess(letter);
        boolean isHit = answer.indexOf(letter) != -1;
        if (isHit) hits += letter;
        else misses += letter;
        return isHit;
    }

    public int getRemainingTries() {
        return MAX_MISSES - misses.length();
    }

    public String getCurrentProgress() {
        String progress = "";
        for (char letter : answer.toCharArray()) {
            char display = '-';
            if (hits.indexOf(letter) != -1) {
                display = letter;
            }
            progress += display;
        }
        return progress;
    }

//    Let the game know if it's won or not
    public boolean isWon() {
        return getCurrentProgress().indexOf('-') == -1;
    }

    // Return a random word from the specified txt file
    public static String getRandomWord() throws IOException {
        final String FILE_NAME = "resources/dictionary.txt";
        List<String> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(FILE_NAME));
            while(sc.hasNext()) {
                list.add(sc.next());
            }
        } catch (IOException e) {
            System.out.print("An Error Ocured \n");
            e.printStackTrace();
        }

        int size = list.size();
        int random = (int) (size * Math.random());
        return list.get(random);
    }
}
