package com.gydsoluciones.whatsapp.mensajes.Actividades;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gydsoluciones.whatsapp.mensajes.Config.ConfigFirebase;
import com.gydsoluciones.whatsapp.mensajes.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
