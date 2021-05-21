package com.example.loginfiles.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginfiles.model.Usuario;
import com.example.loginfiles.request.ApiClient;
import com.example.loginfiles.ui.login.MainActivity;

import java.io.File;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ViewModelRegistro extends AndroidViewModel {
    private MutableLiveData<Usuario> mutableUs;
    Context context;

    public ViewModelRegistro(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuario(){
        if(mutableUs==null){
            mutableUs= new MutableLiveData<>();

        }
        return mutableUs;
    }


    public void guardar(Usuario us, File directorio){
        ApiClient.guardar(context,us, directorio);
        Inicio();

    }

    public void mostrar(File directorio){
        Usuario us= ApiClient.leer(context, directorio);
        mutableUs.setValue(us);
    }

    public void Inicio(){
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }





}
