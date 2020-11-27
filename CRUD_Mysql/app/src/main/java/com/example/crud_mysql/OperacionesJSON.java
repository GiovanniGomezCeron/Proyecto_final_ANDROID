package com.example.crud_mysql;
import android.content.Context;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class OperacionesJSON {
    private Categoria cat;
    private ArrayList<Categoria> categorias ;
    public static String Param ="";

    public void EstablecerConexion(final Context context,final String file){
        StringRequest string=new StringRequest(Request.Method.POST, "http://192.168.56.1/ConexionMySQL/"+file
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String param) {
                try{
                    JSONObject json=new JSONObject(param);
                }catch (Exception e){
                    Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show(); }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Toast.makeText(context,volleyError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        }

    public void RecibirProducto(){
        final ArrayList<Producto> prod = new ArrayList<Producto>();
        StringRequest string=new StringRequest(Request.Method.POST, "http://192.168.56.1/ConexionMySQL/index3.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String param) {
                try{
                    int j = 0;
                    JSONObject json=new JSONObject(param);
                    int c = json.length() / 7;
                    for (int i=1; i <= c; i++){
                        Producto p = new Producto();
                        p.setId(json.getInt("p"+(j)));
                        p.setNombre(json.getString("p"+(j+1)));
                        p.setPrecio(json.getDouble("p"+(j + 2)));
                        p.setCategoria(json.getString("p"+(j + 3)));
                        p.setEstado(json.getInt("p"+(j + 4)));
                        p.setDescripcion(json.getString("p"+(j+5)));
                        p.setStock(json.getInt("p"+(j+6)));
                        j += 7;
                        prod.add(p);
                    }
                     Login.producto.setProductos(prod);
                }catch (Exception e){
                    Toast.makeText(MainActivity.context,e.getMessage(),Toast.LENGTH_LONG).show(); }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Toast.makeText(MainActivity.context,volleyError.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams()throws AuthFailureError {
                Map<String,String>map=new HashMap<String, String>();
                map.put("cant","0");
                return map;
            }
        };
        MySingleton.getInstance(MainActivity.context).addToRequestQueue(string);
    }

    public void RegistrarProducto(final Producto p){
        StringRequest string=new StringRequest(Request.Method.POST, "http://192.168.56.1/ConexionMySQL/index3.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String param) {
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Toast.makeText(MainActivity.context,volleyError.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams()throws AuthFailureError {
                Map<String,String>map=new HashMap<String, String>();
                String cant ="6";
                if(p.getId()!=0){
                    map.put("p6",String.valueOf(p.getId()));
                    cant ="7";
                }
                map.put("cant",cant);
                map.put("p0",String.valueOf(p.getNombre()));
                map.put("p1",String.valueOf(p.getStock()));
                map.put("p2",String.valueOf(p.getPrecio()));
                map.put("p3",String.valueOf(p.getEstado()));
                map.put("p4",p.getCategoria());
                map.put("p5",p.getDescripcion());

                return map;
            }
        };
        MySingleton.getInstance(MainActivity.context).addToRequestQueue(string);
    }
    public void EliminarProducto(final int id){
        StringRequest string=new StringRequest(Request.Method.POST, "http://192.168.56.1/ConexionMySQL/index3.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String param) {
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Toast.makeText(MainActivity.context,volleyError.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams()throws AuthFailureError {
                Map<String,String>map=new HashMap<String, String>();
                map.put("cant","1");
                map.put("p0",String.valueOf(id));
                return map;
            }
        };
        MySingleton.getInstance(MainActivity.context).addToRequestQueue(string);
    }
    public void EliminarCat(final int id){
        StringRequest string=new StringRequest(Request.Method.POST, "http://192.168.56.1/ConexionMySQL/index2.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String param) {
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Toast.makeText(MainActivity.context,volleyError.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams()throws AuthFailureError {
                Map<String,String>map=new HashMap<String, String>();
                map.put("cant","1");
                map.put("p0",String.valueOf(id));
                return map;
            }
        };
        MySingleton.getInstance(MainActivity.context).addToRequestQueue(string);
    }

    public void RegistrarCategoria(final Categoria c){
        StringRequest string=new StringRequest(Request.Method.POST, "http://192.168.56.1/ConexionMySQL/index2.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String param) {
                try{
                }catch (Exception e){
                    Toast.makeText(MainActivity.context,e.getMessage(),Toast.LENGTH_LONG).show(); }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Toast.makeText(MainActivity.context,volleyError.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams()throws AuthFailureError {
                Map<String,String>map=new HashMap<String, String>();
                String cant ="2";
                if(Editar_Categoria.id!=0){
                    map.put("p2",String.valueOf(Editar_Categoria.id));
                    cant = "3";
                }
                map.put("cant",cant);
                map.put("p0",c.getNom_categoria());
                map.put("p1",String.valueOf(c.getEstado()));
                return map;
            }
        };
        MySingleton.getInstance(MainActivity.context).addToRequestQueue(string);
    }
    public void RecibirCategoria(){
        categorias = new ArrayList<Categoria>();
        StringRequest string=new StringRequest(Request.Method.POST, "http://192.168.56.1/ConexionMySQL/index2.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String param) {
                try{
                    int j = 0;
                    JSONObject json=new JSONObject(param);
                    int ca = json.length() / 3;
                    for(int i=1; i <= ca ; i++){
                        Categoria c = new Categoria();
                        c.setNom_categoria(json.getString("p"+j));
                        c.setId(Integer.parseInt(json.getString("p"+(j+1))));
                        c.setEstado(Integer.parseInt(json.getString("p"+(j+2))));
                        categorias.add(c);
                        j+=3;
                    }
                    Login.categoria.setCategorias(categorias);
                }catch (Exception e){
                    Toast.makeText(MainActivity.context,"Error: "+e.getMessage(),Toast.LENGTH_LONG).show(); }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Toast.makeText(MainActivity.context,volleyError.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String,String>();
                map.put("cant","0");
                return map;
            }
        };
        MySingleton.getInstance(MainActivity.context).addToRequestQueue(string);
    }
    public void RecibirUser(){
        StringRequest request = new StringRequest(Request.Method.POST,"http://192.168.56.1/ConexionMySQL/index.php",new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{
                JSONObject json = new JSONObject(response);
                MainActivity.user.add(json.getString("p0"));
                MainActivity.user.add(json.getString("p1"));
                MainActivity.user.add(json.getString("p2"));
                MainActivity.user.add(json.getString("p3"));
                MainActivity.user.add(json.getString("p4"));
                }catch(Exception ex){
                    Toast.makeText(MainActivity.context, ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){

            }
        }){
            public Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> map = new HashMap<String,String>();
                map.put("cant","0");
                return map;
            }

        };
      MySingleton.getInstance(MainActivity.context).addToRequestQueue(request);
    }

    public void ModificarUser(final Usuario u){
        StringRequest request = new StringRequest(Request.Method.POST,"http://192.168.56.1/ConexionMySQL/index.php",new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){

            }
        }){
            public Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> map = new HashMap<String,String>();
                map.put("cant","6");
                map.put("p0",u.getNombre());
                map.put("p1",u.getApellido());
                map.put("p2",u.getUsuario());
                map.put("p3",u.getContraseña());
                map.put("p4",u.getCorreo());
                map.put("p5",MainActivity.user.get(4));
                return map;
            }

        };
        MySingleton.getInstance(MainActivity.context).addToRequestQueue(request);
    }


}



