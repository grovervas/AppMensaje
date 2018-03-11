package com.gydsoluciones.whatsapp.mensajes.Actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gydsoluciones.whatsapp.mensajes.Config.ConfigFirebase;
import com.gydsoluciones.whatsapp.mensajes.Fragmentos.ContactosFragment;
import com.gydsoluciones.whatsapp.mensajes.Fragmentos.ConversacionesFragment;
import com.gydsoluciones.whatsapp.mensajes.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private Button btnSalir;

    private FirebaseAuth autenticacion;
    //private Firebase firebase
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Mensajes");
        setSupportActionBar(toolbar);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Conversaciones", ConversacionesFragment.class)
                .add("Contactos", ContactosFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);


        //cuando probamos el cierre de sesion
        /*btnSalir = (Button)findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autenticacion = ConfigFirebase.getAutenticacionFirebase();
                autenticacion.signOut();
                regresarLogin();
            }
        });*/
    }


    //para probar el cierre de sesion
    private void regresarLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.mnu_salir:
                autenticacion = ConfigFirebase.getAutenticacionFirebase();
                autenticacion.signOut();
                regresarLogin();
                return true;
            case R.id.mnu_config:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
