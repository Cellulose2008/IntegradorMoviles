package com.example.a21300626_6_proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import adaptadores.adaptadoreliminar;
import recordatorio.recordatorio;

public class Eliminar extends AppCompatActivity {

    RecyclerView rv2;
    ImageButton eliminar;
    Toolbar toolbar;
    SharedPreferences archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eliminar);

        toolbar = findViewById(R.id.toolbar);
        eliminar = findViewById(R.id.eliminarID);

        setSupportActionBar(toolbar);

        rv2=findViewById(R.id.rv_eliminar);
        adaptadoreliminar av = new adaptadoreliminar();
        av.context=this;
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(llm);
        rv2.setAdapter(av);

        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
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

        if (item.getItemId() == R.id.edcu) {
            Intent viewteams = new Intent(this, editarusuario.class);
            startActivity(viewteams);
        }

        if (item.getItemId() == R.id.salir) {
            Intent viewteams = new Intent(this, inicio.class);
            startActivity(viewteams);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick_Eliminar(View view) {
        adaptadoreliminar.eliminarSeleccionados();
        Intent delete = new Intent(this, Eliminar.class);
        startActivity(delete);
    }
}