package com.gydsoluciones.whatsapp.mensajes.Config;

import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Tux on 3/03/2018.
 */

public final class ConfigFirebase {

    private static DatabaseReference referenciaFireBase;
    private static FirebaseAuth autenticacionFirebase;

    public static DatabaseReference getFirebase(){

        if(referenciaFireBase == null){
            referenciaFireBase = FirebaseDatabase.getInstance().getReference();
        }

        return referenciaFireBase;
    }

    public static FirebaseAuth getAutenticacionFirebase(){
        if(autenticacionFirebase == null){
            autenticacionFirebase = FirebaseAuth.getInstance();
        }
        return autenticacionFirebase;
    }

}
