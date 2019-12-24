package com.example.bcsd5.board;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BoardController {
    private Context context;

    private RecyclerView boardRecyclerView;
    private BoardRecyclerViewAdapter boardRecyclerViewAdapter;
    private ArrayList<BoardItemData> boardItemDataArrayList;

    public BoardController(Context context, RecyclerView boardRecyclerView) {
        this.context = context;
        this.boardRecyclerView = boardRecyclerView;
        this.boardItemDataArrayList = new ArrayList<>();
        this.boardRecyclerViewAdapter = new BoardRecyclerViewAdapter(boardItemDataArrayList);

        init();
    }

    private void init() {
        boardRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        boardRecyclerView.setAdapter(boardRecyclerViewAdapter);
    }

    public RecyclerView getBoardRecyclerView() {
        return boardRecyclerView;
    }

    public BoardRecyclerViewAdapter getBoardRecyclerViewAdapter() {
        return boardRecyclerViewAdapter;
    }

    public ArrayList<BoardItemData> getBoardItemDataArrayList() {
        return boardItemDataArrayList;
    }

    public void updateView() {
        boardRecyclerViewAdapter.updateData();
    }

    public void addBoardItem(BoardItemData boardItemData) {
        boardItemDataArrayList.add(boardItemData);
        updateView();
    }
}
