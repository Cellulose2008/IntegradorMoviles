package adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a21300626_6_proyecto.R;

import lista.lista;

public class adaptadorver extends RecyclerView.Adapter<adaptadorver.activity> {

    public Context context;

    @NonNull
    @Override
    public adaptadorver.activity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.viewholderver, null);
        activity obj = new activity(v);

        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull adaptadorver.activity holder, int position) {
        final int pos = position;
        holder.titulo.setText(lista.listaIn.get(position).getTitulo());

    }

    @Override
    public int getItemCount() {
        return lista.listaIn.size();
    }

    public class activity extends RecyclerView.ViewHolder {
        TextView titulo, fecha, cuerpo;

        public activity(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.tituloviewID);
            fecha = itemView.findViewById(R.id.fechaviewID);
            cuerpo = itemView.findViewById(R.id.cuerpoviewID);
        }
    }
}
