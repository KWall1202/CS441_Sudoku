package com.example.cs441_sudoku.ui.puzzle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private Sudoku.Puzzle puzzle;

    private PuzzleViewModel puzzleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        puzzleViewModel =
                ViewModelProviders.of(this).get(PuzzleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_puzzle, container, false);
        initBoard(root);
        return root;
    }

    private void initBoard(View root) {
        board = root.findViewById(R.id.sudokuBoard);
        com.example.cs441_sudoku.CellGroupView groups[][] = new com.example.cs441_sudoku.CellGroupView[3][3];
        for(int i=0; i < board.getChildCount(); i++) {
            TableRow row = (TableRow)board.getChildAt(i);
            for(int j=0; j < row.getChildCount(); j++) {
                LinearLayout lay = (LinearLayout)row.getChildAt(i);
                com.example.cs441_sudoku.CellGroupView cellGroup = (com.example.cs441_sudoku.CellGroupView)lay.getChildAt(0);
                groups[i][j] = cellGroup;
                TextView cells[][] = cellGroup.getCells();
                for(int k=0; k < cells.length; k++) {
                    for(int l=0; l < cells[k].length; l++) {
                        cells[k][l].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                currentCell = (TextView)view;
                            }
                        });
                    }
                }
            }
        }
        puzzle = new com.example.cs441_sudoku.Sudoku.Puzzle(groups);
    }

    private void initButtonGrid(View root) {


    }
}