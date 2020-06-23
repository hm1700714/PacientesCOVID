package com.example.pacientescovid;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorDoentes extends RecyclerView.Adapter<AdaptadorDoentes.ViewHolderDoentes> {

    private final Context context;
    private Cursor cursor = null;

    public void setCursor(Cursor cursor) {
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
    public ViewHolderDoentes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemDoente = LayoutInflater.from(context).inflate(R.layout.item_doente, parent, false);

        return new ViewHolderDoentes(itemDoente);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDoentes holder, int position) {
        cursor.moveToPosition(position);
        Doentes doentes = Converte.cursorToDoentes(cursor);
        holder.setDoente(doentes);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public Doentes getDoenteSelecionado(){
        if (viewHolderDoenteSelecionado == null) return null;

        return viewHolderDoenteSelecionado.doentes;
    }

    private ViewHolderDoentes viewHolderDoenteSelecionado = null;

    public class ViewHolderDoentes extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Doentes doentes = null;

        private final TextView textViewNome;
        private final TextView textViewMorada;
        private final TextView textViewContacto;
        private final TextView textViewDNascimento;

        public ViewHolderDoentes(@NonNull View itemView) {
            super(itemView);

            textViewNome = (TextView) itemView.findViewById(R.id.textViewNome);
            textViewMorada = (TextView) itemView.findViewById(R.id.textViewMorada);
            textViewContacto = (TextView) itemView.findViewById(R.id.textViewContacto);
            textViewDNascimento = (TextView) itemView.findViewById(R.id.textViewDataNascimento);

            itemView.setOnClickListener(this);
        }

        public void setDoente(Doentes doente) {

            this.doentes = doente;

            textViewNome.setText(doente.getNomeUtente());
            textViewMorada.setText(doente.getMoradaUtente());
            textViewContacto.setText(doente.getContactoUtente());
            textViewDNascimento.setText(doente.getDataNascimentoUtente());
        }

        @Override
        public void onClick(View v) {
            if (viewHolderDoenteSelecionado != null) {
                viewHolderDoenteSelecionado.desSeleciona();
            }

            viewHolderDoenteSelecionado = this;

            ((ActivityMostraDoentes) context).atualizaOpcoesMenu();
            seleciona();
        }

        private void seleciona() {
            itemView.setBackgroundResource(R.color.colorPrimary);
        }

        private void desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white);
        }
    }
}

