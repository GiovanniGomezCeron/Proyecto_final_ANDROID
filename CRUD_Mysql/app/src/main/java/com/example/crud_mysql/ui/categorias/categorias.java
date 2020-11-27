package com.example.crud_mysql.ui.categorias;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud_mysql.AdapterCategoria;
import com.example.crud_mysql.MainActivity;
import com.example.crud_mysql.OperacionesJSON;
import com.example.crud_mysql.R;

public class categorias extends Fragment {
    View v=null;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       v = inflater.inflate(R.layout.categorias_fragment, container, false);
        return v;
    }

    @Override
    public void onStart() {
        try{
        super.onStart();
             AdapterCategoria adapter=new AdapterCategoria() ;
             ListView l=((ListView)v.findViewById(R.id.listView));
             l.setAdapter(adapter);
             l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(MainActivity.context,String.valueOf(adapterView),Toast.LENGTH_LONG).show();
                }
            });
        }
        catch(Exception e){
            Toast.makeText(MainActivity.context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


}

