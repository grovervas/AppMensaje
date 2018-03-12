package com.gydsoluciones.whatsapp.mensajes.Clases;

/**
 * Created by Tux on 11/03/2018.
 */

public class Contacto {

    private String key;
    private String nombre;
    private String email;

    public Contacto(){}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
}
