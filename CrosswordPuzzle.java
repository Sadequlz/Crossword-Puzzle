import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrosswordPuzzle {
    // Size of the crossword grid
    private static final int size = 15;
    // 2D array representing the crossword grid
    private char[][] grid;
    // List to store the words for the crossword
    private List<String> words;

    // Constructor to initialize the grid and the words list
    public CrosswordPuzzle() {
        grid = new char[size][size];
        words = new ArrayList<>();
        initializeGrid();
    }

    // Method to FILL the grid with empty spaces
    public void initializeGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' '; 
            }
        }
    }

    // Method to take inputs words from the user
    public void inputWords() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter five words, each at least 3 characters long:");
        while (words.size() < 5) {
            System.out.print("Enter word " + (words.size() + 1) + ": ");
            String word = scanner.nextLine().trim().toUpperCase();
            if (isValidWord(word)) {
                words.add(word);
            } 
            else {
              System.out.println("Invalid word. Please enter words that are at least 3 characters long and contain only alphabetic characters.");
            }
        }
    }

    // Method to validate a word
    private boolean isValidWord(String word) {
        return word.length() >= 3 && word.matches("[A-Z]+");
    }

    // Method to create the crossword puzzle
    public boolean createCrossword() {
      // Sorting words by length in descending order
      words.sort((a, b) -> b.length() - a.length());
      // Attempt to place each word on the grid
      for (int i = 0; i < words.size(); i++) {
        if (!placeWord(words.get(i), i == 0)) {
          // If a word can't be placed, return false
          return false;
        }
      }
      // If all words are placed, return true
      return true;
    }

    // Method to place a word on the grid
    public boolean placeWord(String word, boolean isFirstWord) {
        // Place the first word horizontally in the middle of the grid
      if (isFirstWord) {
        int startCol = (size - word.length()) / 2;
        for (int i = 0; i < word.length(); i++) {
          grid[size / 2][startCol + i] = word.charAt(i);
        }
          return true;
        }
        // For the following words, find a valid intersecting position
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col] != ' ' && word.indexOf(grid[row][col]) != -1) {
                  // Check for horizontal and vertical placements
                  int intersectIndex = word.indexOf(grid[row][col]);
                  if (canPlaceWordAt(word, row, col - intersectIndex, true)) {
                    placeWordAt(word, row, col - intersectIndex, true);
                    return true;
                  } 
                  else if (canPlaceWordAt(word, row - intersectIndex, col, false)) {
                    placeWordAt(word, row - intersectIndex, col, false);
                    return true;
                  }
                }
            }
        }
        // If no intersecting position found, return false
      return false;
    }

    // Method to check if a word can be placed at a specific position
    public boolean canPlaceWordAt(String word, int row, int col, boolean horizontal) {
      // Check if the word goes out of bounds
      if (horizontal && (col < 0 || col + word.length() > size)) {
        return false;
      }
      if (!horizontal && (row < 0 || row + word.length() > size)) {
        return false;
      }
      // Check if the word fits without conflicting with existing words
      for (int i = 0; i < word.length(); i++) {
        int currentRow;
        int currentCol;

        if (horizontal) {
            currentRow = row;
            currentCol = col + i;
        } else {
            currentRow = row + i;
            currentCol = col;
        }
        // Out of bounds check
        if (currentRow < 0 || currentRow >= size || currentCol < 0 || currentCol >= size) {
            return false; 
        }
        // Conflict check
        if (grid[currentRow][currentCol] != ' ' && grid[currentRow][currentCol] != word.charAt(i)) {
            return false; 
        }
      }

      return true;
    }
    //Method to place a word at a specific position
  public void placeWordAt(String word, int row, int col, boolean horizontal) {
      for (int i = 0; i < word.length(); i++) {
          int currentRow, currentCol;

          if (horizontal) {
              currentRow = row;
              currentCol = col + i;
          } else {
              currentRow = row + i;
              currentCol = col;
          }

          grid[currentRow][currentCol] = word.charAt(i);
      }
  }
    // Method to display the crossword grid
    public void displayCrossword() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == ' ') {
                    System.out.print(". ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
