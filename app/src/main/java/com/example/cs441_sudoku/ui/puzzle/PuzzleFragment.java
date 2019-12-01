package com.example.cs441_sudoku.ui.puzzle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cs441_sudoku.R;
import com.example.cs441_sudoku.Sudoku;

import org.json.JSONObject;
import org.w3c.dom.Text;

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
        Button checkButton = root.findViewById(R.id.checkSolveButton);
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CHECK ONCLICK
                final int result = Sudoku.isSolved();
                System.out.println(result);
                if(result > 0) {
                    // done!
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Congratulations! You solved the puzzle!");

                    final EditText input = new EditText(getContext());
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    input.setText("Enter your name!");
                    builder.setView(input);
                    builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String addr = "http://cs.binghamton.edu/~pmadden/courses/441score/postscore.php?player=" + input.getText().toString() + "&game=kwallac_sudoku_" + Sudoku.getDifficulty() + "&score=" + result;
                            addr.replace(" ", "_");
                            System.out.println(addr);
                            JsonObjectRequest solveRequest = new JsonObjectRequest(Request.Method.GET, addr, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //TODO: Handle error
                                }
                            });
                            requestQueue.add(solveRequest);
                        }
                    });
                    builder.show();

                } else {
                    // not done
                }
            }
        });

        Button resetButton = root.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sudoku.reset();
            }
        });

        Sudoku.init();
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
        Sudoku.setCellGroups(groups);
    }

    private void initButtonGrid(final View root) {
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
                        Sudoku.updateCell(pos[0], pos[1], ((rowInd * 3) + colInd)+1);
                        TextView moveCount = root.findViewById(R.id.moveCount);
                        int count = Integer.parseInt(moveCount.getText().toString()) + 1;
                        moveCount.setText(Integer.toString(count));
                    }
                });
                newRow.addView(button);
            }
            buttonsTable.addView(newRow);
        }

    }

    private int[] getCurCellPos() {
        return curCellPos;
    }
}