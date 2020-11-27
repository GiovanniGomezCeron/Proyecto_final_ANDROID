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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class Editar_Categoria extends Fragment {
    private View v;
    public static int id = 0;
    private LinearLayout l,c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.fragment_insert__delete__categoria, container, false);
    }
    @Override
    public void onStart(){
        super.onStart();
        ((TextView)v.findViewById(R.id.titulo)).setText("Editar Categoria");
        Spinner list = v.findViewById(R.id.list);

        ArrayList<String> cat = new ArrayList<String>();
        cat.add("--Seleccionar la Categoria-");
        for(int j=0; j < Login.categoria.getCategorias().size(); j++){
            cat.add(Login.categoria.getCategorias().get(j).getNom_categoria());
        }
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.context,android.R.layout.simple_list_item_1,cat);
        list.setAdapter(adapter);

         l = v.findViewById(R.id.list_cat);
        l.setVisibility(View.VISIBLE);

        c = v.findViewById(R.id.ver_in);
        c.setVisibility(View.INVISIBLE);

        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long k) {
                if (i > 0) {
                    int pos = i - 1;
                    c.setVisibility(View.VISIBLE);
                    ((TextView) v.findViewById(R.id.nombre)).setText(Login.categoria.getCategorias().get(pos).getNom_categoria());
                    if (Login.categoria.getCategorias().get(pos).getEstado() < 1) {
                        ((RadioButton) v.findViewById(R.id.cat_inact)).setChecked(true);
                    }
                    id = Login.categoria.getCategorias().get(pos).getId();
                }else{
                    c.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
