package com.example.cs441_sudoku;

import java.util.ArrayList;

public class Sudoku {
    public class Puzzle {
        private Cell[][] cells;

        public Puzzle() {
            cells = new Cell[3][3];

        }

        public boolean isSolved() {
            // check 3x3 boxes
            for(int i=0; i < cells.length; i++) {
                for( int j=0; j < cells[i].length; j++) {
                    // if any 3x3 is unsolved then the puzzle is unsolved
                    if(!cells[i][j].isSolved()) return false;
                }
            }

            // check rows
            for(int i=0; i < 9; i++) {
                // check if rows/columns are solved
                for(int val = 1; val < 10; val++) {
                    //check row
                    boolean foundRow = false;
                    for (int j = 0; j < cells[i / 3].length; j++) {
                        int[] temp = cells[i][j].getRow(i % 3);
                        for (int a : temp) {
                            // found value
                            if (a == val) foundRow = true;
                        }
                    }
                    // if a row is unsolved then the puzzle is unsolved
                    if(!foundRow) return false;

                    boolean foundColumn = false;
                    for(int j=0; j < cells.length; j++) {
                        int[] temp = cells[j][i % 3].getColumn(i / 3);
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

    }

    public class Cell {
        private int[][] values;

        public Cell() {
            values = new int[3][3];
            for(int i = 0; i < values.length; i++)
                for(int j =0; j < values.length; j++)
                    values[i][j] = -1;
        }


        private int[] getRow(int i) {
            return values[i];
        }

        private int[] getColumn(int i) {
            int[] retVal = new int[3];
            for(int j = 0; j < values.length; j++) {
                retVal[i] = values[j][i];
            }
            return retVal;
        }

        private boolean isSolved() {
            for(int i=1; i < 10; i++) {
                boolean found = false;
                for(int j=0; j < values.length; j++){
                    for(int k=0; k < values[j].length; k++) {
                        if(values[j][k] == i) found = true;
                    }
                }
                // if a number is missing return false
                if(!found) return found;
            }
            // all numbers were found
            return true;
        }
    }
}
