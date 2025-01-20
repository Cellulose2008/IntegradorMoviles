package adaptadores;

import static lista.lista.listaIn;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a21300626_6_proyecto.R;

import java.util.ArrayList;
import java.util.List;

import recordatorio.recordatorio;

public class adaptadoreliminar extends RecyclerView.Adapter<adaptadoreliminar.activity> {

    public Context context;

    @NonNull
    @Override
    public activity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context,R.layout.viewholdereliminar, null);
        activity obj = new activity(v);

        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull activity holder, int position) {
        final int pos = position;

        holder.nombre.setText(listaIn.get(position).getTitulo());
        holder.check.setChecked(listaIn.get(position).isChecked());
        holder.check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            listaIn.get(pos).setChecked(isChecked);
        });

    }

    @Override
    public int getItemCount() {
        return listaIn.size();
    }

    public static void eliminarSeleccionados() {
        List<recordatorio> itemsParaEliminar = new ArrayList<>();
        for (recordatorio item : listaIn) {
            if (item.isChecked()) {
                itemsParaEliminar.add(item);
            }
        }
        listaIn.removeAll(itemsParaEliminar);
    }

    public class activity extends RecyclerView.ViewHolder {

        TextView nombre;
        CheckBox check;

        public activity(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.theview);
            check = itemView.findViewById(R.id.thecheckbox);
        }
    }
}
