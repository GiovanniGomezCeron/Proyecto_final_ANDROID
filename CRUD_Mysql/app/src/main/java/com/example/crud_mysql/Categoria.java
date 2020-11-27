package com.example.crud_mysql;
import java.util.ArrayList;

public class Categoria {
    private ArrayList<Categoria> categorias;
    private String nom_categoria;
    private int estado;
    private int id;

    public Categoria() {
        this.categorias = new ArrayList<Categoria>();
    }

    public String getNom_categoria() {
        return nom_categoria;
    }

    public void setNom_categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   public void eliminar(int position){

        categorias.remove(position);
   }
   public void actualizar(int position, ArrayList<String> nuevo){


   }

   public ArrayList<Categoria> getCategorias() {
        return this.categorias;
    }
    public void setCategorias( ArrayList<Categoria> cat){
        this.categorias = cat;
    }

}
