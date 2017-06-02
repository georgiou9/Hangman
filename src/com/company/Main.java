package com.company;

public class Main {

    public static void main(String[] args) {
//
//        if (args.length == 0) {
//            System.out.println("Usage : java Hangman <answer>");
//            System.out.println("answer is required");
//            System.exit(1);
//        }

        Game game = new Game();
        Prompter prompter = new Prompter(game);
        prompter.displayProgress();
        boolean isHit = prompter.promptForGuess();
        while (game.getRemainingTries() > 0 && !game.isWon()) {
            prompter.displayProgress();
            prompter.promptForGuess();
        }
        prompter.displayOutcome();

    }
}
