package com.example.crud_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main2);

          TextInputLayout pregunta = findViewById(R.id.preg);
          pregunta.setHint(Login.QUESTION);

          new Hilos().start();

        }
        catch(Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void ValidarAnsw(View view){
       try{
           TextInputEditText respuesta = findViewById(R.id.validacion);

         if(respuesta.getText().toString().equals(Login.ANSWER)){
             startActivity(new Intent(this,MainActivity.class));
        }else{
            respuesta.setError("Respuesta Incorrecta");
            respuesta.requestFocus();
        }
        }catch(Exception e ){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
