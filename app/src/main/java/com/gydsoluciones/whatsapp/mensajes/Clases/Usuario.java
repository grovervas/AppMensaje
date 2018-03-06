package com.gydsoluciones.whatsapp.mensajes.Clases;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.gydsoluciones.whatsapp.mensajes.Config.ConfigFirebase;

public class Usuario {

    private String nombre;
    private String email;
    private String clave;
    private String id;

    public Usuario(){
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Exclude
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void guardar_usuario(){
        DatabaseReference reference = ConfigFirebase.getFirebase();
        reference.child("usuarios").child(getId()).setValue(this);
    }

}
