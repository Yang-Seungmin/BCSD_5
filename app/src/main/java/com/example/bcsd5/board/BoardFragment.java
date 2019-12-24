package com.example.bcsd5.board;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bcsd5.R;

public class BoardFragment extends Fragment {

    private RecyclerView recyclerView;
    private BoardController boardController;

    public BoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.board_recycler_view);
        boardController = new BoardController(getActivity(), recyclerView);
    }

    public BoardController getBoardController() {
        return boardController;
    }
}