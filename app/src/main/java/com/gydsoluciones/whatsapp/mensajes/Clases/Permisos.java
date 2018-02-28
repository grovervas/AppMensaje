package com.gydsoluciones.whatsapp.mensajes.Clases;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tux on 24/02/2018.
 */

public class Permisos {

    public static boolean validarPermisos(int requestCode, Activity activity, String[] permisos) {

        if (Build.VERSION.SDK_INT >= 23) {

            List<String> listaPermisos = new ArrayList<String>();

            for (String permiso : permisos) {
                Boolean validarPermiso = ContextCompat.checkSelfPermission(activity, permiso) == PackageManager.PERMISSION_GRANTED;
                if (!validarPermiso) {
                    listaPermisos.add(permiso);
                }
            }

            if (listaPermisos.isEmpty()) {
                return true;
            }

            String[] nuevosPermisos = new String[listaPermisos.size()];
            listaPermisos.toArray(nuevosPermisos);

            ActivityCompat.requestPermissions(activity, nuevosPermisos, requestCode);
        }
        return true;
    }
}



