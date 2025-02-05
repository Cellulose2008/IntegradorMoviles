package adaptadores;

import static androidx.core.content.ContextCompat.startActivity;
import static lista.lista.listaIn;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a21300626_6_proyecto.R;
import com.example.a21300626_6_proyecto.inicio;
import com.example.a21300626_6_proyecto.insertarl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import recordatorio.recordatorio;

public class adaptadoreliminar extends RecyclerView.Adapter<adaptadoreliminar.activity> {

    public static Context context;

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

                JSONObject nuevo = new JSONObject();
                try {
                    nuevo.put("titulo", item.getTitulo());
                } catch (JSONException e) {
                    //Toast.makeText(getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                    Log.d("3", "aqui usuario");
                    throw new RuntimeException(e);
                }
                try {
                    nuevo.put("desarrollo", item.getCuerpo());
                } catch (JSONException e) {
                    //Toast.makeText(getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                    Log.d("3", "aqui contra");
                    throw new RuntimeException(e);
                }
                try {
                    nuevo.put("fecha", item.getFecha());
                } catch (JSONException e) {
                    //Toast.makeText(getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                    Log.d("3", "aqui contra");
                    throw new RuntimeException(e);
                }
                String url = "http://192.168.100.100/eliminar.php"; //cambia la IP por la tuya (ipconfig en cmd)
                JsonObjectRequest pet = new JsonObjectRequest(Request.Method.POST, url, nuevo, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("usr") != -1) {
                                Toast.makeText(context.getApplicationContext(), "Se ha eliminado el recordatorio", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context.getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(context.getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = (error.getMessage() != null) ? error.getMessage() : "Error desconocido";
                        // Mostrar el mensaje de error en Log
                        Log.d("error", error.getMessage());
                    }
                });
                RequestQueue fila = Volley.newRequestQueue(context);
                fila.add(pet);

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
