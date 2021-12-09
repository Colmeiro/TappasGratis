package com.example.tappasgratis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BusquedaPorCiudadActivity extends AppCompatActivity {

    EditText editTextId, editTextNombre, editTextCiudad, editTextTelefono, editTextValoracion;
    Button botonBuscar, botonCancelar,botonLimpiarDatos;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_busqueda_por_ciudad);
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextCiudad = (EditText) findViewById(R.id.editTextCiudad);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        editTextValoracion = (EditText) findViewById(R.id.editTextValoracion);
        botonBuscar = (Button) findViewById(R.id.botonBuscar);
        botonCancelar = (Button) findViewById(R.id.botonCancelar);
        botonLimpiarDatos = (Button) findViewById(R.id.botonLimpiarDatos);


        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("http://192.168.0.14:80/app/buscarPorCiudad.php?local_ciudad="+editTextCiudad.getText()+"");
                buscarLocalPorCiudad("http://192.168.0.14:80/app/buscarPorCiudad.php?local_ciudad="+editTextCiudad.getText()+"");

            }
        });

        botonLimpiarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarDatos();
            }
        });
    }

    private void buscarLocalPorCiudad(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        editTextNombre.setText(jsonObject.getString("local_nombre"));
                        editTextCiudad.setText(jsonObject.getString("local_ciudad"));
                        editTextTelefono.setText(jsonObject.getString("local_telefono"));
                        editTextValoracion.setText(jsonObject.getString("local_valoracion"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }


    public void toMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        /*EditText editText = (EditText) findViewById(R.id.editText); // Buscamos el EditText
        String message = editText.getText().toString(); // Leemos el string que esté escrito dentro

        intent.putExtra("numero", 42); // Añadimos un valor de tipo int
        intent.putExtra(EXTRA_MESSAGE, message); // Añadimos un String usando una constante como clave*/

        startActivity(intent);
    }

    public void limpiarDatos(){
        editTextNombre.setText("");
        editTextCiudad.setText("");
        editTextTelefono.setText("");
        editTextValoracion.setText("");
    }
}