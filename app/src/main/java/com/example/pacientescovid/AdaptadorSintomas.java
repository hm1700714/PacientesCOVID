package com.example.pacientescovid;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorSintomas extends RecyclerView.Adapter<AdaptadorSintomas.ViewHolderSintomas> {

    private final Context context;
    private Cursor cursor = null;

    public void setCursor(Cursor cursor) {
        if (cursor != this.cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    public AdaptadorSintomas(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolderSintomas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemSintoma = LayoutInflater.from(context).inflate(R.layout.item_sintoma, parent, false);

        return new ViewHolderSintomas(itemSintoma);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSintomas holder, int position) {
        cursor.moveToPosition(position);
        Sintomas sintomas = Converte.cursorToSintomas(cursor);
        holder.setSintoma(sintomas);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public Sintomas getSintomaSelecionado(){
        if (viewHolderSintomaSelecionado == null) return null;

        return viewHolderSintomaSelecionado.sintomas;
    }

    private ViewHolderSintomas viewHolderSintomaSelecionado = null;

    public class ViewHolderSintomas extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Sintomas sintomas = null;

        private final TextView textViewSintoma;
        private final TextView textViewDSintoma;

        public ViewHolderSintomas(@NonNull View itemView) {
            super(itemView);

            textViewSintoma = (TextView) itemView.findViewById(R.id.textViewSintoma);
            textViewDSintoma = (TextView) itemView.findViewById(R.id.textViewDescricaoSintoma);

            itemView.setOnClickListener(this);
        }

        public void setSintoma(Sintomas sintoma) {

            this.sintomas = sintoma;

            textViewSintoma.setText(sintoma.getSintoma());
            textViewDSintoma.setText(sintoma.getDescricaoSintoma());
        }

        public void onClick(View v){
            if (viewHolderSintomaSelecionado != null) {
                viewHolderSintomaSelecionado.desSeleciona();
            }

            viewHolderSintomaSelecionado = this;

            ((ActivityMostraSintomas) context).atualizaOpcoesMenu();
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
