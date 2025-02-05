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

import lista.lista;
import recordatorio.recordatorio;

public class Actualizar extends AppCompatActivity {

    EditText ET_titulo,ET_fecha,ET_cuerpo;
    Button Anterior, GuardarCambios, Siguiente;
    Toolbar toolbar;
    int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actualizar);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        Anterior = findViewById(R.id.anterior);
        GuardarCambios = findViewById(R.id.guardarCambios);
        Siguiente = findViewById(R.id.siguiente);

        ET_cuerpo = findViewById(R.id.cuerpoID);
        ET_fecha = findViewById(R.id.fechaID);
        ET_titulo = findViewById(R.id.tituloID);

        posicion = 0;
        if(!lista.listaIn.isEmpty()) {
            llenardatos(posicion);
        }else{
            Toast.makeText(this, "No hay recordatorios que modificar", Toast.LENGTH_SHORT).show();
            Intent viewteams = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(viewteams);
        }

        Anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anterior();
            }
        });

        GuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });

        Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguiente();
            }
        });

    }

    private void actualizar() {
        if (ET_titulo.getText().toString().isEmpty() || ET_cuerpo.getText().toString().isEmpty() || ET_fecha.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            JSONObject nuevos = new JSONObject();
            try {
                nuevos.put("titulo", ET_titulo.getText().toString());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            try {
                nuevos.put("desarrollo", ET_cuerpo.getText().toString());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            try {
                nuevos.put("fecha", ET_fecha.getText().toString());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            try {
                nuevos.put("id", posicion + 1);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            String url = "http://192.168.100.100/actualizar.php"; //cambia la IP por la tuya (ipconfig en cmd)
            JsonObjectRequest pet = new JsonObjectRequest(Request.Method.POST, url, nuevos, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getInt("usr") != -1) {
                            recordatorio unrecordatorio = new recordatorio(ET_titulo.getText().toString(),
                                    ET_cuerpo.getText().toString(), ET_fecha.getText().toString());
                            unrecordatorio.setCuerpo(ET_cuerpo.getText().toString());
                            unrecordatorio.setTitulo(ET_titulo.getText().toString());
                            unrecordatorio.setFecha(ET_fecha.getText().toString());
                            lista.listaIn.set(posicion, unrecordatorio);
                            Toast.makeText(getApplicationContext(), "Se ha actualizado el recordatorio", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al actualizar el recordatorio", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al actualizar el recordatorio", Toast.LENGTH_SHORT).show();
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
            RequestQueue fila = Volley.newRequestQueue(Actualizar.this);
            fila.add(pet);
        }
    }


    private void siguiente() {
        Integer tamaño = lista.listaIn.size();

        posicion = (posicion +1) % tamaño;
        llenardatos(posicion);
    }

    private void anterior() {
        Integer tamaño = lista.listaIn.size();
        if (posicion != 0) {
            posicion = (posicion - 1) % tamaño;
            llenardatos(posicion);
        }else{
            posicion = tamaño - 1;
            llenardatos(posicion);
        }
    }

    private void llenardatos(int posicion) {
            ET_titulo.setText(lista.listaIn.get(posicion).getTitulo());
            ET_fecha.setText(lista.listaIn.get(posicion).getFecha());
            ET_cuerpo.setText(lista.listaIn.get(posicion).getCuerpo());
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

        if (item.getItemId() == R.id.salir) {
            Intent viewteams = new Intent(this, inicio.class);
            startActivity(viewteams);
        }
        return super.onOptionsItemSelected(item);
    }
}