package com.example.loginfiles.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginfiles.R;
import com.example.loginfiles.model.Usuario;
import com.example.loginfiles.ui.registro.RegistroActivity;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btRegistrar, btLogin;
    private EditText mail,password;
    private TextView msjerror;
    private ViewModelMain vm;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelMain.class);
        inicializar();

        vm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Intent intent = new Intent(context.getApplicationContext(), RegistroActivity.class);
                intent.putExtra("login","verUs");
                startActivity(intent);
            }
        });

        vm.getMsj().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Advertencia")
                        .setMessage(mensaje)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });



        /*File carpetaApp=getFilesDir();//obtiene la carpeta donde esta instalada la app
        File info= new File(carpetaApp, "datos.dat");
        if(!info.exists()){//se asegura que no existe
            info.mkdir();//crea una carpeta en la carpeta de la app
        }
        File datos=new File(info, "datos.dat");
        if(!datos.exists()){
            try {
                datos.createNewFile();//crea un archivo en la carpeta info
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(info.isDirectory()){
            File lista[] = info.listFiles();
            for (File it: lista){ //itera y devuelve los nombres de los elementos que estan guardados en la carpeta info
                Log.d("salida", it.getName());
            }
        }else {
            Log.d("salida", "no es un directorio");
        }*/
    }

    public void inicializar(){
        btLogin= findViewById(R.id.btLogin);
        btRegistrar = findViewById(R.id.btRegistrar);
        mail=findViewById(R.id.email);
        password=findViewById(R.id.password);
        msjerror= findViewById(R.id.msjact);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File directorio = getFilesDir();
                vm.Ingresar(mail.getText().toString(), password.getText().toString(),directorio);


            }
        });
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.Registrarse();
            }
        });

    }


}