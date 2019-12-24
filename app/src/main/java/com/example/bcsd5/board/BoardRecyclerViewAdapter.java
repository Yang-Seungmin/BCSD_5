package com.example.bcsd5.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bcsd5.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.Inflater;

public class BoardRecyclerViewAdapter extends RecyclerView.Adapter<BoardRecyclerViewAdapter.ViewHolder> {

    private ArrayList<BoardItemData> boardItemDataArrayList;

    public BoardRecyclerViewAdapter(ArrayList<BoardItemData> boardItemDataArrayList) {
        this.boardItemDataArrayList = boardItemDataArrayList;
    }

    @NonNull
    @Override
    public BoardRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardRecyclerViewAdapter.ViewHolder holder, int position) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        BoardItemData boardItemData = boardItemDataArrayList.get(position);
        holder.title.setText(boardItemData.getTitle());
        holder.desc.setText(boardItemData.getAuthor() +
                        " | " +
                        simpleDateFormat.format(new Date(boardItemData.getCreatedTime())));
    }

    @Override
    public int getItemCount() {
        return boardItemDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_text_view_title);
            desc = itemView.findViewById(R.id.item_text_view_desc);
        }
    }
}
