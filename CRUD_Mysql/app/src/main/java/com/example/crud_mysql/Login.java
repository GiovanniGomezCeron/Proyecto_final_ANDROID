package com.example.crud_mysql;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    public static OperacionesJSON json;
    public static Context context;
    public static String USER,ANSWER,QUESTION;
    public static Producto producto;
    public static Categoria categoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        json=new OperacionesJSON();
        context=getApplicationContext();
        producto = new Producto();
        categoria = new Categoria();
}
    public void ValidarUser(View view) {
        try{
        TextInputEditText user = findViewById(R.id.nameuser);
        TextInputEditText psw = findViewById(R.id.password);
        final String un = user.getText().toString();
        final String pw = psw.getText().toString();
        if (un.isEmpty()) {
            user.setError("Rellene campo");
            user.requestFocus();
        }
        else if (pw.isEmpty()) {
            psw.setError("Rellene campo");
            psw.requestFocus();
        } else {
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.56.1/ConexionMySQL/index.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try{
                        JSONObject json = new JSONObject(s);
                       QUESTION = json.getString("p0");
                       ANSWER = json.getString("p1");
                    }catch (JSONException e){
                        Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError volleyError){
                    Toast.makeText(getApplicationContext(),volleyError.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String,String>();
                    map.put("cant","2");
                    map.put("p0",un);
                    map.put("p1",pw);
                    return map;
                }
            };
            MySingleton.getInstance(this).addToRequestQueue(request);

                if (Login.QUESTION!=null) {
                    USER = un;
                 startActivity(new Intent(this, Main2Activity.class));

            } else {

            }
        }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onPause(){ finish();
    super.onPause();

    }
}