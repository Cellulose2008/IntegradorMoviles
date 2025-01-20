package com.example.a21300626_6_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import lista.lista;
import recordatorio.recordatorio;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton irVer, irCrear, irEditar, irBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        irVer = findViewById(R.id.irVer);
        irCrear = findViewById(R.id.irCrear);
        irEditar = findViewById(R.id.irEditar);
        irBorrar = findViewById(R.id.irBorrar);

        setSupportActionBar(toolbar);

        irVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewteams = new Intent(MainActivity.this, verl.class);
                startActivity(viewteams);
            }
        });

        irCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewteams = new Intent(MainActivity.this, insertarl.class);
                startActivity(viewteams);
            }
        });

        irEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewteams = new Intent(MainActivity.this, Actualizar.class);
                startActivity(viewteams);
            }
        });

        irBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewteams = new Intent(MainActivity.this, Eliminar.class);
                startActivity(viewteams);
            }
        });
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
    private void empezar() {
        String url = "http://192.168.100.100/ver.php"; // cambia la IP por la tuya (ipconfig en cmd)
        Response.Listener<JSONArray> respuesta = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject recordatorio = response.getJSONObject(i);
                        String titulo = recordatorio.getString("titulo");
                        String desarrollo = recordatorio.getString("desarrollo");
                        String fecha = recordatorio.getString("fecha");
                        lista.listaIn.add(new recordatorio(titulo, desarrollo, fecha));
                    }
                } catch (Exception e) {
                    Log.d("3", "aqui estoy");
                    throw new RuntimeException(e);
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, respuesta, errorListener);
        RequestQueue fila = Volley.newRequestQueue(MainActivity.this);
        fila.add(jsonArrayRequest);
    }
}