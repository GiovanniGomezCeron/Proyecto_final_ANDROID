package com.example.crud_mysql;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterProducto extends BaseAdapter {
   private ArrayList<Producto> productos;
    public AdapterProducto() {
    this.productos = Login.producto.getProductos();
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int i) {
        return productos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String estado = "Activo",cat="";
        int k=0;
        View v=view;
        LayoutInflater l =LayoutInflater.from(MainActivity.context);
        v = l.inflate(R.layout.layout_producto,null);
        (((TextView)v.findViewById(R.id.id_producto_l))).setText(String.valueOf(productos.get(i).getId()));
       ((TextView)v.findViewById(R.id.nombre_producto_l)).setText(productos.get(i).getNombre());
        ((TextView)v.findViewById(R.id.precio_l)).setText(String.valueOf(productos.get(i).getPrecio()));
         if(productos.get(i).getEstado() < 1){
             estado = "Inactivo";
          }
        ((TextView)v.findViewById(R.id.produ_estado)).setText(estado);

       ((TextView)v.findViewById(R.id.prod_Categoria)).setText(productos.get(i).getCategoria());
        return v;
    }
}
