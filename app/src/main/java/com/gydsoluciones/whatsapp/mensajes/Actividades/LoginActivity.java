package com.gydsoluciones.whatsapp.mensajes.Actividades;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.gydsoluciones.whatsapp.mensajes.Clases.Permisos;
import com.gydsoluciones.whatsapp.mensajes.Config.ConfigFirebase;
import com.gydsoluciones.whatsapp.mensajes.R;

public class LoginActivity extends AppCompatActivity {


    private Button btnRegistro;
    private EditText etEmailLogin;
    private EditText etClave;
    private TextView tvCrearUsuario;
    private String[] permisos = new String[]{android.Manifest.permission.INTERNET, android.Manifest.permission.SEND_SMS};

    private DatabaseReference referenciaFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Colocar en la clase de permisos
        Permisos.validarPermisos(1,this, permisos);

        etEmailLogin = (EditText)findViewById(R.id.etEmailLogin);
        etClave = (EditText)findViewById(R.id.etClave);
        tvCrearUsuario = (TextView)findViewById(R.id.tvCrearCuenta);
        btnRegistro = (Button)findViewById(R.id.btnRegistro);

        //referenciaFirebase = ConfigFirebase.getFirebase();
        //referenciaFirebase.child("puntos").setValue("900");

        /*

        /////Codigo vbalidacion de SMS/////

        SimpleMaskFormatter simpleMaskTelefono = new SimpleMaskFormatter("+NN NNN-NNNNNN");
        MaskTextWatcher maskTelefono = new MaskTextWatcher(tvTelefono,simpleMaskTelefono);

        tvTelefono.addTextChangedListener( maskTelefono );

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreUsuario = tvNombre.getText().toString();
                String telefonoCompleto = tvTelefono.getText().toString();

                String telefonoFormatoCorrecto = telefonoCompleto.replace("+","");
                telefonoFormatoCorrecto = telefonoFormatoCorrecto.replace(" ","");
                telefonoFormatoCorrecto = telefonoFormatoCorrecto.replace("-","");

                //Generando token
                //Random random = new Random();
                //int numeroRandom = random.nextInt(9999-1000) + 1000;
                //String token = String.valueOf(numeroRandom);
                //Log.i("Mensajes",telefonoFormatoCorrecto + " " + token);

                Preferencias preferencias = new Preferencias(LoginActivity.this);
                preferencias.SalvarUsuarioPreferencias(nombreUsuario,telefonoFormatoCorrecto);

            }
        });

        */
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult){
        super.onRequestPermissionsResult(requestCode,permissions,grantResult);

        for(int resultado : grantResult){
            if(resultado == PackageManager.PERMISSION_DENIED){
                alertaPermiso();
            }
        }

    }

    private void alertaPermiso() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permisos negados");
        builder.setMessage("Para que la aplicación funcione es necesario los permisos.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void abrirRegistroUsuario(View view){
        Intent intent = new Intent(this,ValidadorActivity.class);
        startActivity(intent);
    }


}
