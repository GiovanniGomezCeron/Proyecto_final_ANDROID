package com.example.crud_mysql;

import android.content.Context;
import android.net.Uri;
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

import java.util.ArrayList;


public class Eliminar_Categoria extends Fragment {
    private View v;
    public static int id;
    public static String c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.fragment_eliminar__categoria, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Spinner cat = v.findViewById(R.id.spinner_cat);
        ArrayList<String> ca = new ArrayList<String>();
        ca.add("--Seleccionar la Categoria-");

        for(int i=0; i< Login.categoria.getCategorias().size(); i++){
            ca.add(Login.categoria.getCategorias().get(i).getNom_categoria());
        }
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.context,android.R.layout.simple_list_item_1,ca);
        cat.setAdapter(adapter);

        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String c="Activo";
                if(i > 0){
                    int pos = i -1;
                    ((TextView)v.findViewById(R.id.nom_cat)).setText(Login.categoria.getCategorias().get(pos).getNom_categoria());
                    if(Login.categoria.getCategorias().get(pos).getEstado() < 1){
                        c ="Inactivo";
                    }
                    ((TextView)v.findViewById(R.id.estado_ca)).setText(c);
                    ((LinearLayout)v.findViewById(R.id.Detalles_cat)).setVisibility(View.VISIBLE);
                    id= Login.categoria.getCategorias().get(pos).getId();
                    c = Login.categoria.getCategorias().get(pos).getNom_categoria();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
