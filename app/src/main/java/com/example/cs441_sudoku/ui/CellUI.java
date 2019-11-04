package com.example.cs441_sudoku.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CellUI {
    private int color;
    private TextView topText;
    private TextView botText;
    private TextView val;
    private Boolean notes;

    public CellUI() {
        color = Color.WHITE;
        notes = true;
    }


    public LinearLayout getView(Context context) {
        LinearLayout retView = new LinearLayout(context);
        retView.setBackgroundColor(color);
        LinearLayout.LayoutParams retParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        retView.setLayoutParams(retParams);
        if(notes) {
            LinearLayout.LayoutParams topParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
        }


        return retView;
    }
}
