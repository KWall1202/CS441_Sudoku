package com.example.cs441_sudoku;

import androidx.fragment.app.Fragment;

public class CellGroupFragment extends Fragment {
    private int[][] values;

    public CellGroupFragment() {
        values = new int[3][3];
        for(int i = 0; i < values.length; i++)
            for(int j =0; j < values.length; j++)
                values[i][j] = -1;
    }


    public int[] getRow(int i) {
        return values[i];
    }

    public int[] getColumn(int i) {
        int[] retVal = new int[3];
        for(int j = 0; j < values.length; j++) {
            retVal[i] = values[j][i];
        }
        return retVal;
    }

    public boolean isSolved() {
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
