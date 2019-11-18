package com.example.cs441_sudoku.ui.puzzle;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cs441_sudoku.R;
import com.example.cs441_sudoku.Sudoku;

public class PuzzleFragment extends Fragment {
    private TableLayout board;
    private TextView currentCell;
    private int curCellPos[] = {0, 0};

    private PuzzleViewModel puzzleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        puzzleViewModel =
                ViewModelProviders.of(this).get(PuzzleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_puzzle, container, false);
        initBoard(root);
        initButtonGrid(root);
        return root;
    }

    private void initBoard(View root) {
        board = root.findViewById(R.id.sudokuBoard);
        com.example.cs441_sudoku.CellGroupView groups[][] = new com.example.cs441_sudoku.CellGroupView[3][3];
        for(int i=0; i < board.getChildCount(); i++) {
            TableRow row = (TableRow)board.getChildAt(i);
            for(int j=0; j < row.getChildCount(); j++) {
                LinearLayout lay = (LinearLayout)row.getChildAt(j);
                com.example.cs441_sudoku.CellGroupView cellGroup = (com.example.cs441_sudoku.CellGroupView)lay.getChildAt(0);
                groups[i][j] = cellGroup;
                TextView cells[][] = cellGroup.getCells();
                for(int k=0; k < cells.length; k++) {
                    final int rowInd = 3*i + k;
                    for(int l=0; l < cells[k].length; l++) {
                        final int colInd = 3*j + l;
                        cells[k][l].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(currentCell != null) currentCell.setBackgroundColor(Color.WHITE);
                                currentCell = (TextView)view;
                                currentCell.setBackgroundColor(Color.YELLOW);
                                curCellPos[0] = rowInd;
                                curCellPos[1] = colInd;
                            }
                        });
                    }
                }
            }
        }
    }

    private void initButtonGrid(View root) {
        TableLayout buttonsTable = root.findViewById(R.id.inputButtonGrid);
        for(int i=0; i<3; i++) {
            TableRow newRow = new TableRow(getContext());
            final int rowInd = i;
            for(int j=0; j < 3; j++) {
                final int colInd = j;
                Button button = new Button(getContext());
                button.setText(Integer.toString(3 * i + j + 1));
                button.setBackgroundColor(Color.CYAN);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos[] = getCurCellPos();
                        Sudoku.Puzzle.updateCell(pos[0], pos[1], ((rowInd * 3) + colInd)+1);
                    }
                });
                newRow.addView(button);
            }
            buttonsTable.addView(newRow);
        }

    }

    public int[] getCurCellPos() {
        return curCellPos;
    }
}