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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cs441_sudoku.R;
import com.example.cs441_sudoku.Sudoku;

import org.json.JSONArray;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RequestQueue requestQueue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        requestQueue = Volley.newRequestQueue(getContext());
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        initGenerator(root);

        return root;
    }

    private void initGenerator(View root) {
        final Spinner difficultySpinner = root.findViewById(R.id.difficultySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.puzzle_difficulties, android.R.layout.simple_dropdown_item_1line);
        difficultySpinner.setAdapter(adapter);
        Button generatorButton = root.findViewById(R.id.generateButton);
        generatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("sending generate request");
                String url = "https://sugoku.herokuapp.com/board?difficulty=" + difficultySpinner.getSelectedItem().toString().toLowerCase();
                JSONArray board = new JSONArray();
                JsonObjectRequest generateRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int[][] puzz = new int[9][9];
                        System.out.println("Got response");
                        try {
                            JSONArray values = response.getJSONArray("board");
                            System.out.println(response.toString());
                            for(int i=0; i < values.length(); i++) {
                                JSONArray row = values.getJSONArray(i);
                                for(int j=0; j < row.length(); j++) {
                                    puzz[i][j] = row.getInt(j);
                                    System.out.println(puzz[i][j]);
                                }
                            }
                        } catch(org.json.JSONException e) {
                            System.err.println(e.toString());
                        }
                        Sudoku.setPuzzle(puzz);
                        System.out.println("sending solve request");
                        JsonObjectRequest solveRequest = new JsonObjectRequest(Request.Method.POST, "https://sugoku.herokuapp.com/solve", response, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                int [][] sol = new int[9][9];
                                System.out.println("got solve response");
                                try {
                                    JSONArray values = response.getJSONArray("board");
                                    System.out.println(response.toString());
                                    for(int i=0; i < values.length(); i++) {
                                        JSONArray row = values.getJSONArray(i);
                                        for(int j=0; j < row.length(); j++) {
                                            sol[i][j] = row.getInt(j);
                                            System.out.println(sol[i][j]);
                                        }
                                    }
                                } catch(org.json.JSONException e) {
                                    System.err.println(e.toString());
                                }
                                Sudoku.setSolution(sol);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //TODO: Handle error
                            }
                        });
                        requestQueue.add(solveRequest);
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //TODO: Handle error
                            }
                        });
                requestQueue.add(generateRequest);

            }
        });
    }
}