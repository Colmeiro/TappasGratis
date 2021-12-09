package com.example.tappasgratis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editTextBarraBusqueda;
    Button botonBuscar, botonAltaLocal, botonZonaBuscar,botonBuscaPorCiudad, botonRecycler;
    static int contadorTortilla =0;
    static int contadorEnsaladilla =0;
    static int contadorEmpanada =0;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextBarraBusqueda=(EditText) findViewById(R.id.editTextBarraBusqueda);
        botonBuscar=(Button) findViewById(R.id.botonBuscar);
        botonAltaLocal=(Button) findViewById(R.id.botonAltaLocal);
        botonBuscaPorCiudad=(Button) findViewById(R.id.botonBuscaPorCiudad);
        //botonRecycler=(Button) findViewById(R.id.botonRecycler);

        //botonZonaBuscar=(Button) findViewById(R.id.botonZonaBuscar);

        botonAltaLocal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroLocalesActivity.class);
                startActivity(intent);
            }
        });

        botonBuscaPorCiudad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BusquedaPorCiudadActivity.class);
                startActivity(intent);
            }
        });

        botonRecycler.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });
    }

    public void mensajeTortilla(View v){
        if(contadorTortilla==0){
            Toast.makeText(getApplicationContext(), "Qué rica la tortilla eh?", Toast.LENGTH_SHORT).show();
            contadorTortilla++;
        }else if(contadorTortilla==1){
            Toast.makeText(getApplicationContext(), "Me comía yo un cacho con gusto.", Toast.LENGTH_SHORT).show();
            contadorTortilla++;
        }else if(contadorTortilla==2){
            Toast.makeText(getApplicationContext(), "¡QUEREMOS TORTILLA YA!.", Toast.LENGTH_SHORT).show();
            contadorTortilla++;
        } else if (contadorTortilla >= 3) {
            Toast.makeText(getApplicationContext(), "Tortillaaa, tortillaaa...", Toast.LENGTH_SHORT).show();
        }
    }

    public void mensajeEnsaladilla(View v){
        if(contadorEnsaladilla==0){
            Toast.makeText(getApplicationContext(), "Qué fresca parece eh??", Toast.LENGTH_SHORT).show();
            contadorEnsaladilla++;
        }else if(contadorEnsaladilla==1){
            Toast.makeText(getApplicationContext(), "Ensaladilla todo el año!.", Toast.LENGTH_SHORT).show();
            contadorEnsaladilla++;
        } else if (contadorEnsaladilla >= 2) {
            Toast.makeText(getApplicationContext(), "En...sa...la...di...lla", Toast.LENGTH_SHORT).show();
        }
    }

    public void mensajeEmpanada(View v){
        if(contadorEmpanada==0){
            Toast.makeText(getApplicationContext(), "Rica empanada", Toast.LENGTH_SHORT).show();
            contadorEmpanada++;
        }else if(contadorEmpanada==1){
            Toast.makeText(getApplicationContext(), "De qué será?", Toast.LENGTH_SHORT).show();
            contadorEmpanada++;
        }else if(contadorEmpanada==2){
            Toast.makeText(getApplicationContext(), "Empanada Patrimonio Universal", Toast.LENGTH_SHORT).show();
            contadorEmpanada++;
        } else if (contadorEmpanada >= 3) {
            Toast.makeText(getApplicationContext(), "Festa da empanada, non che digo nada", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void ejecutarServicio(String URL) {

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
                parametros.put("local_id", editTextId.getText().toString());
                parametros.put("local_nombre", editTextNombre.getText().toString());
                parametros.put("local_ciudad", editTextCiudad.getText().toString());
                parametros.put("local_telefono", editTextTelefono.getText().toString());
                parametros.put("local_valoracion", editTextValoracion.getText().toString());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }*/


    /*private void buscarLocalPorId(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                Toast.makeText(getApplicationContext(), response.length(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        editTextId.setText(jsonObject.getString("local_id"));
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
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }*/

}























