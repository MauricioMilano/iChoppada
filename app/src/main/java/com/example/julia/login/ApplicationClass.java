package com.example.julia.login;


import android.app.Application;
import android.content.res.Configuration;
import com.firebase.client.Firebase;

/**
 * Created by João on 23/11/2016.
 */


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@!!!!!DANGER!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//@@@@@@@@@@@@@@@@@@@@@@@@@!!!!!!!!!!!!!!NÃO ALTERAR OU DELETAR!!!!!!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//A não ser que o Firebase não seja mais utilizado =D
//Se não, vai bugar o código e vai ficar 40 minutos se perguntando qual é o problema
//Não que eu tenha feito isso...
public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

}
