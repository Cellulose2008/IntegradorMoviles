package com.example.a21300626_6_proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import recordatorio.recordatorio;

public class Eliminar extends AppCompatActivity {

    SharedPreferences archivo;
    TextView ET_titulo;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eliminar);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu) {
            Intent viewteams = new Intent(this, MainActivity.class);
            startActivity(viewteams);
        }

        if (item.getItemId() == R.id.crear) {
            Intent viewteams = new Intent(this, insertarl.class);
            startActivity(viewteams);
        }

        if (item.getItemId() == R.id.ver) {
            Intent viewteams = new Intent(this, verl.class);
            startActivity(viewteams);
        }

        if (item.getItemId() == R.id.editar) {
            Intent viewteams = new Intent(this, Actualizar.class);
            startActivity(viewteams);
        }

        if (item.getItemId() == R.id.borrar) {
            Intent viewteams = new Intent(this, Eliminar.class);
            startActivity(viewteams);
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertar() throws JSONException {
        String titulo;
        titulo = ET_titulo.getText().toString();
        if(!titulo.isEmpty()){
            JSONObject newRecordatorio = new JSONObject();
            newRecordatorio.put("titulo", titulo);
            String url = "http://192.168.100.100/eliminar.php?title=";
            url=url+titulo;
            //cambia la IP por la tuya (ipconfig en cmd)
            JsonObjectRequest pet = new JsonObjectRequest(Request.Method.POST, url, newRecordatorio, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //Toast.makeText(getApplicationContext(), "Se ha creado el recordatorio", Toast.LENGTH_SHORT).show();

                    } catch (Exception e){
                        Log.d("3", "aqui estoy");
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
            RequestQueue fila = Volley.newRequestQueue(this);
            fila.add(pet);
        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}