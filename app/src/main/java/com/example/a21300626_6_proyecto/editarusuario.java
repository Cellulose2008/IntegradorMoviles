package com.example.a21300626_6_proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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

public class editarusuario extends AppCompatActivity {
    EditText editusuario, editcontra;
    Button guardar;
    SharedPreferences sesion;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editarusuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editcontra = findViewById(R.id.editcontraID);
        guardar = findViewById(R.id.guardarID);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sesion = getSharedPreferences("sesion", MODE_PRIVATE);
        guardar.setOnClickListener(view -> {
            String contra = editcontra.getText().toString();
            if (contra.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }else {
                JSONObject nuevo = new JSONObject();
                try {
                    nuevo.put("contra", contra);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                int id_usuario = sesion.getInt("id_usuario", -1);
                try {
                    nuevo.put("id_usuario", id_usuario);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String url = "http://192.168.100.100/modificar.php"; //cambia la IP por la tuya (ipconfig en cmd)
                JsonObjectRequest pet = new JsonObjectRequest(Request.Method.POST, url, nuevo, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getInt("usr")!=-1){
                                Toast.makeText(getApplicationContext(), "Se ha modificado el usuario", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Error al modificar el usuario", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error al modificar el usuario", Toast.LENGTH_SHORT).show();
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
                RequestQueue fila = Volley.newRequestQueue(editarusuario.this);
                fila.add(pet);
                Toast.makeText(this, "Contraseña modificada.", Toast.LENGTH_SHORT).show();
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

        if (item.getItemId() == R.id.salir) {
            Intent viewteams = new Intent(this, inicio.class);
            startActivity(viewteams);
        }

        return super.onOptionsItemSelected(item);
    }
}