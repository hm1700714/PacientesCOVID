package com.example.pacientescovid;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorEstado extends RecyclerView.Adapter<AdaptadorEstado.ViewHolderEstado> {

    private final Context context;
    private Cursor cursor = null;

    public void setCursor(Cursor cursor) {
        if (cursor != this.cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    public AdaptadorEstado(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolderEstado onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemEstado = LayoutInflater.from(context).inflate(R.layout.item_estado, parent, false);

        return new ViewHolderEstado(itemEstado);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEstado holder, int position) {
        cursor.moveToPosition(position);
        EstadoSaude estado = Converte.cursorToEstadoSaude(cursor);
        holder.setEstado(estado);
    }

    @Override
    public int getItemCount() {
        if(cursor == null) {
            return 0;
        }

        return cursor.getCount();
    }

    public EstadoSaude getEstadoSelecionado(){
        if (viewHolderEstadoSelecionado == null) return null;

        return viewHolderEstadoSelecionado.estado;
    }

    private ViewHolderEstado viewHolderEstadoSelecionado = null;

    public class ViewHolderEstado extends RecyclerView.ViewHolder implements View.OnClickListener {

        private EstadoSaude estado = null;

        private final TextView textViewNome;
        private final TextView textViewHora;
        private final TextView textViewDia;
        private final TextView textViewTemperatura;
        private final TextView textViewMedicamentos;

        public ViewHolderEstado(@NonNull View itemView) {
            super(itemView);

            textViewNome = (TextView) itemView.findViewById(R.id.textViewMostraNome);
            textViewHora = (TextView) itemView.findViewById(R.id.textViewMostraHora);
            textViewDia = (TextView) itemView.findViewById(R.id.textViewMostraDia);
            textViewTemperatura = (TextView) itemView.findViewById(R.id.textViewMostraTemperatura);
            textViewMedicamentos = (TextView) itemView.findViewById(R.id.textViewMostraMedicamentos);


            itemView.setOnClickListener(this);
        }

        public void setEstado(EstadoSaude estado) {
            this.estado = estado;

            textViewNome.setText(String.valueOf(estado.getIdDoente()));
            textViewHora.setText(estado.getHoraVisita());
            textViewDia.setText(estado.getDiaVisita());
            textViewTemperatura.setText(estado.getTemperatura());
            textViewMedicamentos.setText(estado.getMedicamentos());
        }


        @Override
        public void onClick(View v) {
            if (viewHolderEstadoSelecionado != null) {
                viewHolderEstadoSelecionado.desSeleciona();
            }

            viewHolderEstadoSelecionado = this;

            ((ActivityMostraEstado) context).atualizaOpcoesMenu();
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
