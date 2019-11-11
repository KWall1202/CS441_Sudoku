package com.example.cs441_sudoku;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CellGroupView extends TableLayout {
    private int[][] values;
    private TextView[][] cells;
    private TableLayout table;

    public CellGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CellGroupView(Context context) {
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initLayout(getContext());
    }

    private void initLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.addView(inflater.inflate(R.layout.cellgroup_view, null));
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(int i = 0; i < getChildCount(); i++){
            getChildAt(i).layout(l, t, r, b);
        }
    }

    private void init() {
        values = new int[3][3];
        cells = new TextView[3][3];
        table = (TableLayout)getChildAt(0);
        for(int i = 0; i < values.length; i++) {
            TableRow curRow = (TableRow)table.getChildAt(i);
            for(int j = 0; j < values.length; j++) {
                cells[i][j] = (TextView)curRow.getChildAt(j);
                updateCell(i,j, -1);
            }
        }
        //update();
    }

    private void updateCell(int row, int column, int value) {
        values[row][column] = value;
        cells[row][column].setText(Integer.toString(value));
    }

    public void update() {
        for(int i = 0; i < values.length; i++) {
            for(int j = 0; j < values.length; j++) {
                if(values[i][j] == -1) {
                    cells[i][j].setText(" ");
                }
            }
        }
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
