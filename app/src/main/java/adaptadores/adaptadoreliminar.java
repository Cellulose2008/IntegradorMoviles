package adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a21300626_6_proyecto.R;

public class adaptadoreliminar extends RecyclerView.Adapter<adaptadoreliminar.activity> {

    public Context context;

    @NonNull
    @Override
    public activity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull activity holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static void eliminarSeleccionados() {

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
