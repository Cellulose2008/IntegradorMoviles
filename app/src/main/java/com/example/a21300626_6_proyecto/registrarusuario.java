package com.example.a21300626_6_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

public class registrarusuario extends AppCompatActivity {
    EditText regusuario, regcontra;
    Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrarusuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        regusuario = findViewById(R.id.regusuarioID);
        regcontra = findViewById(R.id.regcontraID);
        aceptar = findViewById(R.id.aceptarID);
        aceptar.setOnClickListener(view -> {
            String usuario = regusuario.getText().toString();
            String contra = regcontra.getText().toString();
            if (usuario.isEmpty() || contra.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }else {
                JSONObject nuevo = new JSONObject();
                try {
                    nuevo.put("usuario", usuario);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                    Log.d("3", "aqui usuario");
                    throw new RuntimeException(e);
                }
                try {
                    nuevo.put("contra", contra);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                    Log.d("3", "aqui contra");
                    throw new RuntimeException(e);
                }
                String url = "http://192.168.137.217/registro.php"; //cambia la IP por la tuya (ipconfig en cmd)
                JsonObjectRequest pet = new JsonObjectRequest(Request.Method.POST, url, nuevo, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("usr") != -1) {
                                Toast.makeText(getApplicationContext(), "Se ha creado el usuario", Toast.LENGTH_SHORT).show();
                                Intent viewteams = new Intent(getApplicationContext(), inicio.class);
                                startActivity(viewteams);
                            } else {
                                Toast.makeText(getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show();
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
                RequestQueue fila = Volley.newRequestQueue(registrarusuario.this);
                fila.add(pet);
            }
        });
    }
}