package com.example.crud_mysql;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Eliminar_Producto extends Fragment {

    private View v;
    public static int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_eliminar__producto, container, false);

        return v;
    }
    @Override
    public void onStart(){
        super.onStart();
        Spinner sp = v.findViewById(R.id.spinner);
        ArrayList<String> p = new ArrayList<String>();
        p.add("-Seleccionar el Producto-");
        for(int i=0; i< Login.producto.getProductos().size(); i++){
            p.add(Login.producto.getProductos().get(i).getNombre());
        }

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.context,android.R.layout.simple_list_item_1,p);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 LinearLayout detalles = v.findViewById(R.id.Detalles_Producto);
                 TextView nom = v.findViewById(R.id.nom_p);
                TextView precio = v.findViewById(R.id.precio_p);
                TextView cat = v.findViewById(R.id.cat_p);
                 if( i > 0){
                    nom.setText(Login.producto.getProductos().get(i-1).getNombre());
                     precio.setText(String.valueOf(Login.producto.getProductos().get(i-1).getPrecio()));
                     cat.setText(String.valueOf(Login.producto.getProductos().get(i-1).getCategoria()));
                     id = Login.producto.getProductos().get(i-1).getId();
                     detalles.setVisibility(View.VISIBLE);
                 }else {
                     detalles.setVisibility(View.INVISIBLE);
                 }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
