package com.example.pacientescovid;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorDoentes extends RecyclerView.Adapter<AdaptadorDoentes.ViewHolderDoentes> {
    private final Context context;
    private Cursor cursor = null;

    public void setCursor(){
        if (cursor != this.cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    public AdaptadorDoentes(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorDoentes.ViewHolderDoentes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemDoente = LayoutInflater.from(context).inflate(R.layout.item_doente, parent, false);

        return new ViewHolderLivro(itemDoente);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDoentes.ViewHolderDoentes holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private ViewHolderLivro viewHolderDoenteSelecionado = null;
}
