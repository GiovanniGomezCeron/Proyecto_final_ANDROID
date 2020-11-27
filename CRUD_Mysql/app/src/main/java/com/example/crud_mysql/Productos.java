package com.example.crud_mysql;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class Productos extends Fragment {
    private View view=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_productos, container, false);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
       ListView lv=  view.findViewById(R.id.List_Producto);
        AdapterProducto adapter = new AdapterProducto();
        lv.setAdapter(adapter);
    }

}
