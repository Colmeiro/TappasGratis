package com.example.tappasgratis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroLocalesActivity extends AppCompatActivity {

    EditText editTextId, editTextNombre, editTextCiudad, editTextTelefono,editTextValoracion;

    Button botonAgregar, botonCancelar;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_locales);

        editTextId=(EditText) findViewById(R.id.editTextId);
        editTextNombre=(EditText) findViewById(R.id.editTextNombre);
        editTextCiudad=(EditText) findViewById(R.id.editTextCiudad);
        editTextTelefono=(EditText) findViewById(R.id.editTextTelefono);
        editTextValoracion=(EditText) findViewById(R.id.editTextValoracion);

        botonAgregar=(Button) findViewById(R.id.botonAgregar);
        botonCancelar=(Button) findViewById(R.id.botonCancelar);

        botonAgregar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://192.168.0.14:80/app/create.php");
                Toast.makeText(getApplicationContext(), "Local Registrado!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        botonCancelar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ejecutarServicio(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> Toast.makeText(getApplicationContext(), "OPERACIÓN EXITOSA", Toast.LENGTH_SHORT).show(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("local_id", "");
                parametros.put("local_nombre", editTextNombre.getText().toString());
                parametros.put("local_ciudad", editTextCiudad.getText().toString());
                parametros.put("local_telefono", editTextTelefono.getText().toString());
                parametros.put("local_valoracion", editTextValoracion.getText().toString());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}