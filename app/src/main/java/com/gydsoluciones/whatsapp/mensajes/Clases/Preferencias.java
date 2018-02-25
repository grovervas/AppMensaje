package com.gydsoluciones.whatsapp.mensajes.Clases;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Tux on 24/02/2018.
 */

public class Preferencias {

    private Context contexto;
    private SharedPreferences preferences;
    private final String ARCHIVO = "MensajesPreferencias";
    private final int MODE = 0;
    private SharedPreferences.Editor editor;

    public Preferencias(Context parametros){

        contexto = parametros;
        preferences = contexto.getSharedPreferences(ARCHIVO,MODE);
        editor = preferences.edit();
    }

    public void SalvarUsuarioPreferencias(String nombre, String telefono){
        editor.putString("nombre",nombre);
        editor.putString("telefono",telefono);
        editor.commit();
    }

    public HashMap<String,String> getDatosUsurio(){
        HashMap<String, String> datos = new HashMap<>();
        datos.put("nombre", preferences.getString("nombre", null));
        datos.put("telefono", preferences.getString("telefono",null));
        return datos;
    }
}
