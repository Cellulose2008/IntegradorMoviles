package com.example.a21300626_6_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lista.lista;
import recordatorio.recordatorio;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class insertarl extends AppCompatActivity {
    EditText ET_titulo,ET_fecha,ET_cuerpo;
    Toolbar toolbar;
    Button  crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insertarl);

        toolbar = findViewById(R.id.toolbar);
        ET_titulo = findViewById(R.id.ET_titulo);
        ET_fecha = findViewById(R.id.ET_fecha);
        ET_cuerpo = findViewById(R.id.ET_cuerpo);
        crear = findViewById(R.id.BT_crear);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar2();
            }
        });
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

   /* private void insertar() throws JSONException {
        String titulo, desarrollo, fecha;
        titulo = ET_titulo.getText().toString();
        desarrollo = ET_cuerpo.getText().toString();
        fecha = ET_fecha.getText().toString();
        if(!titulo.isEmpty() || !desarrollo.isEmpty() || !fecha.isEmpty()){
            recordatorio nuevo = new recordatorio(titulo, desarrollo, fecha);
            lista.listaIn.add(nuevo);
            JSONObject newRecordatorio = new JSONObject();
            newRecordatorio.put("titulo", titulo);
            newRecordatorio.put("desarrollo", desarrollo);
            newRecordatorio.put("fecha", fecha);
            String url = "http://192.168.100.100/insertar.php"; //cambia la IP por la tuya (ipconfig en cmd)
            JsonObjectRequest pet = new JsonObjectRequest(Request.Method.POST, url, newRecordatorio, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Toast.makeText(getApplicationContext(), "Se ha creado el recordatorioIn", Toast.LENGTH_SHORT).show();
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
    */

    private void insertar2(){
        String titulo, desarrollo, fecha;
        titulo = ET_titulo.getText().toString();
        desarrollo = ET_cuerpo.getText().toString();
        fecha = ET_fecha.getText().toString();
        if(!titulo.isEmpty() || !desarrollo.isEmpty() || !fecha.isEmpty()) {
            recordatorio nuevo = new recordatorio(titulo, desarrollo, fecha);
            lista.listaIn.add(nuevo);
            Toast.makeText(this, "Nota creada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}