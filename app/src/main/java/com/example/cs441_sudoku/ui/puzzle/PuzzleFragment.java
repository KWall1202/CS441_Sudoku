package com.example.cs441_sudoku.ui.puzzle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cs441_sudoku.R;

public class PuzzleFragment extends Fragment {

    private PuzzleViewModel puzzleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        puzzleViewModel =
                ViewModelProviders.of(this).get(PuzzleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_puzzle, container, false);
        return root;
    }
}