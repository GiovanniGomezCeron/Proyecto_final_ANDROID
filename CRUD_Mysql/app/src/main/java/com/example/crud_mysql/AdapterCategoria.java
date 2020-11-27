package com.example.crud_mysql;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class AdapterCategoria extends BaseAdapter {
    private ArrayList<Categoria> categorias;
    public AdapterCategoria(){
        this.categorias = Login.categoria.getCategorias();
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int i) {
        return categorias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        String c = "Inactivo";
        try{
        LayoutInflater l=LayoutInflater.from(MainActivity.context);
        v =l.inflate(R.layout.layout_categorias,null);
        ((TextView)v.findViewById(R.id.id_categoria)).setText(String.valueOf(categorias.get(i).getId()));
        ((TextView)v.findViewById(R.id.nombre_cat)).setText(categorias.get(i).getNom_categoria());
        if(categorias.get(i).getEstado() > 0){
           c = "Activo";
        }
        ((TextView)v.findViewById(R.id.estado_cat)).setText(c);
        }
        catch(Exception e){
            Toast.makeText(MainActivity.context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return v;
    }
}
