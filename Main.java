public class Main {
    public static void main(String[] args) {
        boolean success = false;

        while (!success) {
            // Create a new instance of CrosswordPuzzle for each attempt
            CrosswordPuzzle puzzle = new CrosswordPuzzle();

            // Input words from the user
            puzzle.inputWords();

            // Try to create the crossword puzzle
            success = puzzle.createCrossword();

            if (success) {
                // Display the crossword if successful
                puzzle.displayCrossword();
            } 
            else {
                // Inform the user of failure and ask for new words
                System.out.println("Failed to create a crossword puzzle with the given words. Please try again with a different set of words.");
            }
        }
    }
}

