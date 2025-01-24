package com.example.a21300626_6_proyecto;

import android.content.Intent;
import android.os.Bundle;
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
        recordatorio unrecordatorio = new recordatorio(ET_titulo.getText().toString(),
                ET_cuerpo.getText().toString(), ET_fecha.getText().toString());

        unrecordatorio.setCuerpo(ET_cuerpo.getText().toString());
        unrecordatorio.setTitulo(ET_titulo.getText().toString());
        unrecordatorio.setFecha(ET_fecha.getText().toString());

        lista.listaIn.set(posicion, unrecordatorio);
        Toast.makeText(this, "Actualizado.", Toast.LENGTH_SHORT).show();
    }

    private void siguiente() {
        Integer tamaño = lista.listaIn.size();

        posicion = (posicion +1) % tamaño;
        llenardatos(posicion);
    }

    private void anterior() {
        Integer tamaño = lista.listaIn.size();

        posicion = (posicion -1) % tamaño;
        llenardatos(posicion);
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

        return super.onOptionsItemSelected(item);
    }
}