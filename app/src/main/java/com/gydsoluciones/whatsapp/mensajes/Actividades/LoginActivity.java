package com.gydsoluciones.whatsapp.mensajes.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.gydsoluciones.whatsapp.mensajes.R;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText tvTelefono;
    private Button btnRegistro;
    private EditText tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvNombre = (EditText)findViewById(R.id.tvNombre);
        tvTelefono = (EditText)findViewById(R.id.tvTelefono);
        btnRegistro = (Button)findViewById(R.id.btnRegistro);

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
                Random random = new Random();
                int numeroRandom = random.nextInt(9999-1000) + 1000;

                String token = String.valueOf(numeroRandom);
                Log.i("Mensajes",telefonoFormatoCorrecto + " " + token);

            }
        });
    }
}
