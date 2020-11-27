package com.example.crud_mysql;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class insertar_delete_producto extends Fragment {
    View view = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_insertar_delete_producto, container, false);
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();

        Spinner categorias = view.findViewById(R.id.categoria_p);

        

        ArrayList<String> cat =new ArrayList<String>();

        for(int i=0; i< Login.categoria.getCategorias().size(); i++){
            cat.add(Login.categoria.getCategorias().get(i).getNom_categoria());
        }
         ArrayAdapter adapter1 = new ArrayAdapter(MainActivity.context,android.R.layout.simple_spinner_item,cat);
        categorias.setAdapter(adapter1);
    }

}
//unidad_m_p
//categoria_p