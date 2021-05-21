package com.example.loginfiles.request;

import android.content.Context;
import android.widget.Toast;

import com.example.loginfiles.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApiClient {
    public  static  void  guardar(Context context, Usuario usuario, File directorio){
        File file=new File(directorio,"usuario.dat");
        try {
            FileOutputStream fo = new FileOutputStream((file));
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            DataOutputStream dos=new DataOutputStream((bo));

            dos.writeLong(usuario.getDni());
            dos.writeUTF(usuario.getNombre());
            dos.writeUTF(usuario.getApellido());
            dos.writeUTF(usuario.getEmail());
            dos.writeUTF(usuario.getPassword());

            bo.flush();
            fo.close();

        }catch (IOException e){
            e.printStackTrace();

        }


    }

    public static Usuario leer(Context context,File directorio){
        File file = new File(directorio,"usuario.dat");
        Usuario usuario=new Usuario();
        if(file.exists()){


            try {
                FileInputStream fi = new FileInputStream(file);
                BufferedInputStream bi =new BufferedInputStream(fi);
                DataInputStream dis= new DataInputStream(bi);


                usuario.setDni(dis.readLong());
                usuario.setNombre(dis.readUTF());
                usuario.setApellido(dis.readUTF());
                usuario.setEmail(dis.readUTF());
                usuario.setPassword(dis.readUTF());


                fi.close();
                return usuario;

            }catch (IOException e){
                Toast.makeText(context, "Datos no encontrados", Toast.LENGTH_SHORT).show();
            }
        }
        return  usuario;
    }

    public  static Usuario login(Context context, String mail, String password,File directorio) {
        File file = new File(directorio, "usuario.dat");
        Usuario usuario = null;
        if (file.exists()) {


            try {
                FileInputStream fi = new FileInputStream(file);
                BufferedInputStream bi = new BufferedInputStream(fi);
                DataInputStream dis = new DataInputStream(bi);

                long dni = dis.readLong();
                String nombre= dis.readUTF();
                String apellido= dis.readUTF();
                String email= dis.readUTF();
                String pass= dis.readUTF();



                fi.close();
                if(mail.equals(email)&&password.equals(pass)){
                    usuario=new Usuario(dni,apellido,nombre,email,pass);
                    return  usuario;
                }else {
                    return usuario;
                }


            } catch (IOException e) {
                Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }


        }
        return usuario;
    }
}
