package com.gydsoluciones.whatsapp.mensajes.Actividades;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.gydsoluciones.whatsapp.mensajes.Clases.Usuario;
import com.gydsoluciones.whatsapp.mensajes.Config.ConfigFirebase;
import com.gydsoluciones.whatsapp.mensajes.R;

public class ValidadorActivity extends AppCompatActivity {

    private EditText etNombre, etEmailRegistro, etClaveRegistro;
    private Button btnCrearUsuairo;
    private Usuario usuario;

    private FirebaseAuth autenticacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        etNombre = (EditText)findViewById(R.id.etNombre);
        etEmailRegistro = (EditText)findViewById(R.id.etEmailRegistro);
        etClaveRegistro = (EditText)findViewById(R.id.etClaveRegsitro);
        btnCrearUsuairo = (Button)findViewById(R.id.btnCrearUsuario);

        autenticacion = FirebaseAuth.getInstance();

        btnCrearUsuairo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = new Usuario();
                usuario.setNombre(etNombre.getText().toString());
                usuario.setEmail(etEmailRegistro.getText().toString());
                usuario.setClave(etClaveRegistro.getText().toString());
                registrarUsuario();
            }
        });

    }

    private void registrarUsuario(){
        //autenticacion = ConfigFirebase.getAutenticacionFirebase();
        Log.i("Usuairo","Empresando a crear el usuario");
        autenticacion.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getClave()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ValidadorActivity.this,"Usuario creado con exito",Toast.LENGTH_SHORT).show();

                    usuario.setId( task.getResult().getUser().getUid());
                    usuario.guardar_usuario();

                    autenticacion.signOut();
                    finish();

                }else{
                    String error = "";
                    try{
                        throw task.getException();

                    }catch(FirebaseAuthWeakPasswordException e){
                        error = "Digite una constraseña correcta, con numeros y letras";
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        error = "El correo ingresado no es correcto";
                    }catch(FirebaseAuthUserCollisionException e){
                        error = "Este usuario ya esta registrado";
                    }catch(Exception e){
                        error = "Error al realizar el registro";
                        e.printStackTrace();
                    }
                    Toast.makeText(ValidadorActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
