package com.example.cs441_sudoku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CellGroupFragment extends Fragment {
    private int[][] values;
    private View view;

    public CellGroupFragment() {
        values = new int[3][3];
        for(int i = 0; i < values.length; i++)
            for(int j =0; j < values.length; j++)
                values[i][j] = -1;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cellgroup, container, false);

        TableLayout cellGroup = view.findViewById(R.id.cellGroupTable);
        for(int i=0; i < cellGroup.getChildCount(); i++) {
            TableRow curRow = (TableRow)cellGroup.getChildAt(i);
            for(int j=0; i < curRow.getChildCount(); j++) {
                TextView cell = (TextView)curRow.getChildAt(j);
                cell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        }

        return view;
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
