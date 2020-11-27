package com.example.crud_mysql;

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
import android.widget.Toast;

import java.util.ArrayList;

public class Editar_Producto extends Fragment {
    private View v;
    private Spinner pro;
    private ArrayList<String> cat = new ArrayList<String>();
    public static int idProd=-1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.fragment_insertar_delete_producto, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        ((TextView)v.findViewById(R.id.descripcion_prod)).setText("Editar Producto");


        pro= v.findViewById(R.id.lista_pro);
        ArrayList<String> p = new ArrayList<String>();
        p.add("-Seleccionar el Producto-");
        for(int i=0; i< Login.producto.getProductos().size(); i++){
            p.add(Login.producto.getProductos().get(i).getNombre());
        }

        ArrayAdapter adapter3 = new ArrayAdapter(MainActivity.context,android.R.layout.simple_list_item_1, p);
        pro.setAdapter(adapter3);


        for(int i=0; i< Login.categoria.getCategorias().size(); i++){
            cat.add(Login.categoria.getCategorias().get(i).getNom_categoria());
        }

        final Spinner categoria =v.findViewById(R.id.categoria_p);
        ArrayAdapter adapter1= new ArrayAdapter(MainActivity.context,android.R.layout.simple_list_item_1, cat);
        categoria.setAdapter(adapter1);

        ((LinearLayout)v.findViewById(R.id.list_pro)).setVisibility(View.VISIBLE);
        ((LinearLayout)v.findViewById(R.id.mostrar)).setVisibility(View.INVISIBLE);


        pro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if( i > 0){
                   int pos = i - 1 ;
                 ((TextView)v.findViewById(R.id.nombre_p_i)).setText(Login.producto.getProductos().get(pos).getNombre());
                 ((TextView)v.findViewById(R.id.stock_p_i)).setText(String.valueOf(Login.producto.getProductos().get(pos).getStock()));
                 ((TextView)v.findViewById(R.id.precio_p_i)).setText(String.valueOf(Login.producto.getProductos().get(pos).getPrecio()));
                 ((TextView)v.findViewById(R.id.des_p_i)).setText(Login.producto.getProductos().get(pos).getDescripcion());
                  if(Login.producto.getProductos().get(pos).getEstado() < 1){
                       ((RadioButton)v.findViewById(R.id.inact_prod)).setChecked(true);
                   }
                  idProd = Login.producto.getProductos().get(pos).getId();

                 int j=0;
                 while(cat.size() > j ){
                     if(Login.producto.getProductos().get(pos).getCategoria().equals(cat.get(j))){
                         break;
                     }
                     j++;
                 }
                     categoria.setSelection(j);
                   ((LinearLayout)v.findViewById(R.id.mostrar)).setVisibility(View.VISIBLE);
               }else{
                   ((LinearLayout)v.findViewById(R.id.mostrar)).setVisibility(View.INVISIBLE);
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
