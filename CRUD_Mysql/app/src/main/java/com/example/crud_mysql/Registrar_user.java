package com.example.crud_mysql;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.*;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;

import com.google.android.material.textfield.TextInputEditText;

public class Registrar_user extends Fragment {
    private TextInputEditText editTexts[];
    private ArrayList<String>datos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_registrar_user, container, false);
    }
    public boolean Main(View view){
        editTexts=new TextInputEditText[7];
        datos=new ArrayList<String>();
        editTexts[0]=view.findViewById(R.id.nombre);
        editTexts[1]=view.findViewById(R.id.apellido);
        editTexts[2]=view.findViewById(R.id.correo);
        editTexts[3]=view.findViewById(R.id.usuario);
        editTexts[4]=view.findViewById(R.id.contraseña);
        editTexts[5]=view.findViewById(R.id.contraseña2);
        editTexts[6]=view.findViewById(R.id.respuesta);

        for(int i=0; i < 7; i++){
            TextInputEditText e = editTexts[i];
           if(e.getText().toString().isEmpty()){
               e.setError("Rellene campo");
               e.requestFocus();
               return true;
           }
           datos.add(e.getText().toString());
        }
        //1- guardar, 2-editar, 3- borrar
        if(editTexts[5].getText().toString().equals(editTexts[4].getText().toString())){
            OperacionesJSON json=new OperacionesJSON();
           // json.enviar(datos,"index.php");
        }else{
            Toast.makeText(getContext(),"Contraseñas no son iguales", Toast.LENGTH_LONG).show();
            editTexts[4].requestFocus();
        }
        return false;
    }
}
