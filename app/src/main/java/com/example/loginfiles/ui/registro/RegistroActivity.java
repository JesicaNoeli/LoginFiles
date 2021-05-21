package com.example.loginfiles.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginfiles.R;
import com.example.loginfiles.model.Usuario;

import java.io.File;

public class RegistroActivity extends AppCompatActivity {
    private EditText dni, nombre, apellido, email, password;
    private TextView msjerror;
    private ViewModelRegistro vmr;
    private Button btGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        vmr = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(ViewModelRegistro.class);
        inicializarVistas();
        vmr.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                dni.setText(usuario.getDni() + "");
                nombre.setText(usuario.getNombre());
                apellido.setText(usuario.getApellido());
                email.setText(usuario.getEmail());
                password.setText(usuario.getPassword());

            }
        });

       /* if(getIntent().getExtras().getInt("")==1) {
            File directorio=getFilesDir();
            vmr.mostrar(directorio);
        }*/

        Bundle datos = this.getIntent().getExtras();
        if(datos!=null) {
            File directorio=getFilesDir();
            vmr.mostrar(directorio);
        }

    }

    public void inicializarVistas() {
        dni = findViewById(R.id.etDni);
        nombre = findViewById(R.id.etNombre);
        apellido = findViewById(R.id.etApellido);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        msjerror = findViewById(R.id.msjreg);
        btGuardar = findViewById(R.id.btGuardar);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File directorio=getFilesDir();
                Usuario us = new Usuario();
                if (!dni.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    us.setDni(Long.parseLong(dni.getText().toString()));
                    us.setNombre(nombre.getText().toString());
                    us.setApellido(apellido.getText().toString());
                    us.setEmail(email.getText().toString());
                    us.setPassword(password.getText().toString());
                    vmr.guardar(us, directorio);

                } else {
                    msjerror.setText("Todos los campos deben estar completos");
                }
            }

        });


    }
}