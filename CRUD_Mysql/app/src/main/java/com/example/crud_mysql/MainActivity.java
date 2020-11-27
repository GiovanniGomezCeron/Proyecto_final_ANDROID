package com.example.crud_mysql;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.crud_mysql.ui.categorias.categorias;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static OperacionesJSON json;
    public static Context context;
    public static ArrayList<String> prod ;
    public static ArrayList<String> cat ;
    public static ArrayList<String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        json=new OperacionesJSON();
        context=getApplicationContext();
        prod = new ArrayList<String>();
        cat =  new ArrayList<String>();
        user =  new ArrayList<String>();

        /*prod.add("-Seleccione el Producto-");

        for(int i=0; i < Login.producto.getProductos().size(); i++){
            prod.add(Login.producto.getProductos().get(i).getNombre());
        }*/

        json.RecibirUser();
        try{
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.categoria,R.id.Productos,R.id.AggProducto).setDrawerLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        } catch(Exception e){
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }




    public void registrar(View view){
        String nombre=((TextInputEditText)findViewById(R.id.nombre)).getText().toString();
        if(!nombre.isEmpty()){
            int estado =1;
            if(((RadioButton)findViewById(R.id.cat_inact)).isChecked()){
                estado =0;
            }

            Categoria c = new Categoria();
            c.setNom_categoria(nombre);
            c.setEstado(estado);
            json.RegistrarCategoria(c);
            startActivity(new Intent(this,MainActivity.class));

        }else{
            Toast.makeText(this,"Rellene campo",Toast.LENGTH_LONG).show();
            ((TextInputLayout)findViewById(R.id.layout_cat)).requestFocus();
        }
    }


       public void eliminar(View view){

       }

       //////////////////////////////      METODOS   PARA FRAGMENT HOME /////////////////////////////////////////////
       public void RedCategoria(View view){
           startActivity(new Intent(getApplicationContext(), categorias.class));
       }

    public void Producto(View view){
        startActivity(new Intent(getApplicationContext(), Editar_Productos.class));
    }

    //////////////////////////////      METODOS   PARA EDITAR_PRODUCTOS E INGRESAR PRODUCTO/////////////////////////////////////////////
    public boolean InsertarProducto(View view){
        try{
        TextInputEditText[] e =new TextInputEditText[4];
        e[0]=(TextInputEditText)findViewById(R.id.nombre_p_i);
        e[1]=(TextInputEditText)findViewById(R.id.stock_p_i);
        e[2]=(TextInputEditText)findViewById(R.id.precio_p_i);
        e[3]=(TextInputEditText)findViewById(R.id.des_p_i);

        for(int i=0; i< e.length; i++){
            String v = e[i].getText().toString();
            if(v.isEmpty()){
                e[i].requestFocus();
                e[i].setError("Rellene campo");
                return false;
            }
        }


        //ARREGLO QUE GUARDARA LOS DATOS DE LOS EDITTEX
        Producto p = new Producto();
        p.setNombre(e[0].getText().toString());
        p.setStock(Integer.parseInt(e[1].getText().toString()));
        p.setPrecio(Double.parseDouble(e[2].getText().toString()));
        p.setDescripcion(e[3].getText().toString());


        //RECUPERANDO LA CATEGORIA SELECCIONADA
        int pos = ((Spinner)findViewById(R.id.categoria_p)).getSelectedItemPosition();
        int categoria = Login.categoria.getCategorias().get(pos).getId();

        ///OBTENIENDO RADIOBUTTON SELECCIONADO
       RadioButton activo = (RadioButton)findViewById(R.id.act_prod);


        int estado = 0;

         if(activo.isChecked()){
             estado = 1;
         }
         p.setEstado(estado);
         p.setCategoria(String.valueOf(categoria));

         if(((TextView)findViewById(R.id.descripcion_prod)).getText().toString().equals("Editar Producto")){
             p.setId(Editar_Producto.idProd);
         }

         json.RegistrarProducto(p);
         json.RecibirProducto();
            startActivity(new Intent(this,MainActivity.class));

        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    //////////////////////////////      METODOS   PARA ELIMINAR_PRODUCTOS /////////////////////////////////////////////
    public void EliminarP(View view){
        json.EliminarProducto(Eliminar_Producto.id);
        json.RecibirProducto();
        startActivity(new Intent(this,MainActivity.class));

    }

    //////////////////////////////      METODOS   PARA ELIMINAR_CATEGORIAS /////////////////////////////////////////////
    public void EliminarCat(View view){
        if(ComprobarCat()){
        json.EliminarCat(Eliminar_Categoria.id);
            startActivity(new Intent(this,MainActivity.class));
        }else{
            Toast.makeText(this,"La categoria tiene relación con Producto",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean ComprobarCat(){
        for(int i=0; i< Login.producto.getProductos().size(); i++){
            if(Login.producto.getProductos().get(i).getCategoria().equals(Eliminar_Categoria.c)){
                return false;
            }
        }
        return true;
    }

    public boolean ModifyUser(View view){
        TextInputEditText[] t = new TextInputEditText[5];
        t[0] = findViewById(R.id.nombre_u);
        t[1] = findViewById(R.id.apellido_u);
        t[2] = findViewById(R.id.user_u);
        t[3] = findViewById(R.id.con_u);
        t[4] = findViewById(R.id.correo_u);

        for(int i=0; i< t.length; i++){
           if(t[i].getText().toString().isEmpty()){
                return false;
            }
        }
        Usuario u = new Usuario();
        u.setNombre(t[0].getText().toString());
        u.setApellido(t[1].getText().toString());
        u.setUsuario(t[2].getText().toString());
        u.setContraseña(t[3].getText().toString());
        u.setCorreo(t[4].getText().toString());
        json.ModificarUser(u);
        startActivity(new Intent(this,MainActivity.class));
        return true;
    }

}
