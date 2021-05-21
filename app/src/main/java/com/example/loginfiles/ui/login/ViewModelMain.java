package com.example.loginfiles.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginfiles.model.Usuario;
import com.example.loginfiles.request.ApiClient;
import com.example.loginfiles.ui.registro.RegistroActivity;

import java.io.File;

public class ViewModelMain extends AndroidViewModel {
    private MutableLiveData<Usuario> usuario;
    private MutableLiveData<String>msj;
    Context context;

    public ViewModelMain(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuario(){
        if(usuario==null){
            usuario= new MutableLiveData<>();

        }
        return usuario;
    }
    public LiveData<String> getMsj(){
        if(msj==null){
            msj= new MutableLiveData<>();

        }
        return msj;
    }

    public void Ingresar(String mail, String password,File directorio){
        Usuario us = ApiClient.login(context, mail, password, directorio);
        if (us==null){
            msj.setValue("Usuario no registrado  o datos incorrectos");
        }else{
            usuario.setValue(us);
        }

    }

    public void Registrarse (){
        Intent i = new Intent(context, RegistroActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);





        }
}
