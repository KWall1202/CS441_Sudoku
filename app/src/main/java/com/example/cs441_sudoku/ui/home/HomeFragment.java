package com.example.cs441_sudoku.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs441_sudoku.R;
import com.example.cs441_sudoku.Sudoku;
import com.example.cs441_sudoku.ui.puzzle.PuzzleFragment;

import org.json.JSONArray;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final Spinner difficultySpinner = root.findViewById(R.id.difficultySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.puzzle_difficulties, android.R.layout.simple_dropdown_item_1line);
        difficultySpinner.setAdapter(adapter);
        Button generatorButton = root.findViewById(R.id.generateButton);
        generatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://sugoku.herokuapp.com/board?difficulty=" + difficultySpinner.getSelectedItem().toString().toLowerCase();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int puzz[][] = new int[9][9];
                        try {
                            JSONArray values = response.getJSONArray("board");
                            for(int i=0; i < values.length(); i++) {
                                for(int j=0; j < values.getJSONArray(i).length(); j++) {
                                    puzz[i][j] = values.getJSONArray(i).getInt(j);
                                }
                            }
                        } catch(org.json.JSONException e) {
                            System.err.println(e.toString());
                        }
                        Sudoku.Puzzle.setPuzzle(puzz);
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //TODO: Handle error
                            }
                        });
                //TODO: handle request
            }
        });
        return root;
    }

}