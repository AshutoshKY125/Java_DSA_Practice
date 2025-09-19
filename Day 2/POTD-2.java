import java.util.*;

class Spreadsheet {

    // Map to store each cell with its value
    // Example: "A1" -> 5, "B2" -> 10
    Map<String, Integer> m = new HashMap<>();

    /**
     * Constructor to create a spreadsheet with given rows.
     * Each row will have 26 columns (A to Z).
     *
     * @param rows number of rows in the spreadsheet
     */
    public Spreadsheet(int rows) {

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // 26 columns

        // Create cells row by row
        for (int i = 1; i <= rows; i++) {
            String r = "" + i; // row number as string
            for (int ind = 0; ind < 26; ind++) {
                // Example: A1, B1, ..., Z1, A2, B2, ...
                m.put(letters.charAt(ind) + r, 0); // initialize with 0
            }
        }
    }

    /**
     * Set a cell's value.
     *
     * @param cell  cell name (e.g., "A1")
     * @param value integer value to set
     */
    public void setCell(String cell, int value) {
        m.put(cell, value);
    }

    /**
     * Reset a cell to default value (0).
     *
     * @param cell cell name (e.g., "B2")
     */
    public void resetCell(String cell) {
        m.put(cell, 0);
    }

    /**
     * Evaluate a simple formula of type "=X+Y".
     * X and Y can be either:
     * - A cell name (e.g., "A1")
     * - A direct integer (e.g., "5")
     *
     * @param formula string like "=A1+5" or "=B2+C3"
     * @return result of evaluation
     */
    public int getValue(String formula) {

        // Extract parts of the formula
        // Example: "=A1+5" -> c1 = "A1", c2 = "5"
        String c1 = formula.substring(1, formula.indexOf("+"));
        String c2 = formula.substring(formula.indexOf("+") + 1, formula.length());

        int x = 0;
        int y = 0;

        // Check if c1 is a cell reference or a number
        if (m.containsKey(c1)) {
            x = m.get(c1);
        } else {
            x = Integer.parseInt(c1);
        }

        // Check if c2 is a cell reference or a number
        if (m.containsKey(c2)) {
            y = m.get(c2);
        } else {
            y = Integer.parseInt(c2);
        }

        // Return the sum
        return x + y;
    }
}
