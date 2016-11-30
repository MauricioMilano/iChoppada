package com.example.julia.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
//Ju desculpa tive q mudar de MainActivity para TelaPrincipal por motivos de:
//1.TOC
//2.Ela é, de fato, a tela principal
//3.Fica mais intuitivo
public class TelaPrincipal extends AppCompatActivity {

    Button btnlogin,confirmar;
    Spinner spn;
    Sessao sessao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        telaMain();
        sessao = new Sessao(getApplicationContext());
        sessao.checkLogin();
    }
    public void telaMain(){
        setContentView(R.layout.activity_main);
        confirmar =(Button) findViewById(R.id.entrar);
        confirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){loginPage();
            }
        });


        btnlogin = (Button) findViewById(R.id.newuser);
        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cadastro();
            }

        });
    }

    public void cadastro(){

        Intent telaLogin = new Intent(this,Cadastro.class);
        startActivity(telaLogin);
    }
    public void loginPage(){
        Intent feed = new Intent(this, Login.class);
        startActivity(feed);
    }

}