package com.example.cs441_sudoku;

public class Sudoku {
    public static class Puzzle {
        private CellGroupView[][] cellGroups;

        public Puzzle() {
            cellGroups = new CellGroupView[3][3];
        }

        public Puzzle(CellGroupView groups[][] ) {
            cellGroups = groups;
        }

        public boolean isSolved() {
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

    }

}
