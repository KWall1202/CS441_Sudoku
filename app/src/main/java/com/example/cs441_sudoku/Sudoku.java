package com.example.cs441_sudoku;

public class Sudoku {
    private static CellGroupView[][] cellGroups;
    private static int values[][] = new int[9][9];
    private static int puzzle[][] = new int[9][9];
    private static int answers[][] = new int[9][9];
    private static int moveCount = 0;
    private static String difficulty = "";

    public static void setCellGroups(CellGroupView[][] cellGroups) {
        Sudoku.cellGroups = cellGroups;
    }

    public static void updateCell(int row, int column, int value) {
        values[row][column] = value;
        cellGroups[row / 3][column / 3].updateCell(row % 3, column % 3, value);
    }

    public static void setPuzzle(int[][] puzz) {
        values = puzz;
        puzzle = puzz;
    }

    public static void setDifficulty(String diff) {
        difficulty = diff;
    }

    public static String getDifficulty() { return  difficulty;}

    public static void setSolution(int[][] sol) {
        answers = sol;
    }

    public static void reset() {
        setPuzzle(puzzle);
        init();
    }

    public static void init() {
        for(int i=0; i < values.length; i++) {
            for(int j=0; j < values[i].length; j++) {
                updateCell(i, j, values[i][j]);
            }
        }
        moveCount = 0;
    }


    public static boolean isSolved2() {
        // check 3x3 boxes
        for(int i = 0; i < cellGroups.length; i++) {
            for(int j = 0; j < cellGroups[i].length; j++) {
                // if any 3x3 is unsolved then the puzzle is unsolved
                if(!cellGroups[i][j].isSolved()) return false;
            }
        }

        // check rows
        for(int i=0; i < 9; i++) {
            // check if rows/columns are solved
            for(int val = 1; val < 10; val++) {
                //check row
                boolean foundRow = false;
                for (int j = 0; j < cellGroups[i / 3].length; j++) {
                    int[] temp = cellGroups[i][j].getRow(i % 3);
                    for (int a : temp) {
                        // found value
                        if (a == val) foundRow = true;
                    }
                }
                // if a row is unsolved then the puzzle is unsolved
                if(!foundRow) return false;

                boolean foundColumn = false;
                for(int j = 0; j < cellGroups.length; j++) {
                    int[] temp = cellGroups[j][i % 3].getColumn(i / 3);
                    for (int a : temp) {
                        // found value
                        if(a == val) foundColumn = true;
                    }
                }
                // if a column is unsolved then the puzzle is unsolved
                if(!foundColumn) return false;
            }
        }

        return true;

    }

    public static int isSolved() {
        if(isSolved2()) return moveCount;
        else return -1;
        /*boolean solved = true;
        for(int i=0; i < values.length; i++) {
            for(int j=0; j < values.length; j++) {
                if (values[i][j] != answers[i][j]) {
                    solved = false;
                }
            }
        }
        return solved ? moveCount : -1;*/
    }
}
