package com.example.a21300626_6_proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class inicio extends AppCompatActivity {

    EditText usuario, contra;
    Button ingresar;
    SharedPreferences archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);

        usuario = findViewById(R.id.usuarioID);
        contra = findViewById(R.id.contraID);
        ingresar = findViewById(R.id.ingresarID);
        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
            }
        });

    }

    private void ingresar() {

        //cambia la IP por la tuya (ipconfig en cmd)
        String url="http://192.168.100.28/ingreso.php?usr=";
        url=url+usuario.getText().toString();
        url=url+"&pass=";
        url=url+contra.getText().toString();

        //obtiene datos
        JsonObjectRequest pet = new JsonObjectRequest(Request.Method.GET,url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getInt("usr") != -1) {
                        Intent i = new Intent(inicio.this, MainActivity.class);
                        SharedPreferences.Editor editor = archivo.edit(); //almacena y mueve datos clave-valor
                        editor.putInt("id_usuario", response.getInt("usr"));
                        editor.apply();
                        startActivity(i);
                        finish();
                    } else {
                        usuario.setText("");
                        contra.setText("");
                        Toast.makeText(inicio.this, "Credenciales incorrectas.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //Toast.makeText(inicio.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyerror) {
                Log.d("yo", volleyerror.getMessage());
            }

        });
        RequestQueue lanzarPeticion = Volley.newRequestQueue(this); //realiza la conexion http
        lanzarPeticion.add(pet);

        /*if (usuario.getText().toString().equals("GUSA")&&
                contra.getText().toString().equals("cisco")) {

            Intent in = new Intent(this, MainActivity.class);
            SharedPreferences.Editor editor = archivo.edit();
            editor.putString("usuario","GUSA");
            editor.putString("contra", "cisco");
            editor.putBoolean("valido", true);
            editor.apply();
            //editor.commit();
            startActivity(in);
            finish();
        }
        else {
            //usuario.setText();
            Toast.makeText(this, "Datos erroneos.", Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}