package com.gydsoluciones.whatsapp.mensajes.Actividades;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.gydsoluciones.whatsapp.mensajes.Clases.Contacto;
import com.gydsoluciones.whatsapp.mensajes.Clases.Usuario;
import com.gydsoluciones.whatsapp.mensajes.Config.ConfigFirebase;
import com.gydsoluciones.whatsapp.mensajes.Fragmentos.ContactosFragment;
import com.gydsoluciones.whatsapp.mensajes.Fragmentos.ConversacionesFragment;
import com.gydsoluciones.whatsapp.mensajes.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button btnSalir;

    private FirebaseAuth autenticacion;
    //crear al momento de agregar usuario
    private DatabaseReference referenceFirebase;
    private FirebaseUser firebaseUser;
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
            case R.id.mnu_contacto:
                abrirRegistroContaco();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirRegistroContaco(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Agregar usuario");
        alertDialog.setMessage("Email de usuario:");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(this);
        alertDialog.setView(editText);
        alertDialog.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String contacto = editText.getText().toString();
                if(contacto.isEmpty()){
                    Toast.makeText(MainActivity.this,"Ingrese un email",Toast.LENGTH_SHORT).show();
                }else{
                    //verificar si existe el usuario.
                    final boolean existeUsuario = false;

                    referenceFirebase = ConfigFirebase.getFirebase().child("usuarios");
                    Query query = referenceFirebase.orderByChild("email").equalTo(contacto);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot != null){
                                autenticacion = ConfigFirebase.getAutenticacionFirebase();
                                String idUsuario = autenticacion.getUid();
                                for(DataSnapshot data : dataSnapshot.getChildren()){
                                    Log.i("Mensaje",data.getKey());
                                    DatabaseReference reference = ConfigFirebase.getFirebase();
                                    reference.child("contactos").child(idUsuario).child(data.getKey()).setValue(data.getValue());
                                }
                                Toast.makeText(MainActivity.this,"Usuario agregado",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this,"No existe el contacto",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertDialog.create();
        alertDialog.show();

    }
}
