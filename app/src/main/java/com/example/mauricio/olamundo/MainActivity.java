package com.example.mauricio.olamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.*;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Button btnlogin,confirmar;
    Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        telaMain();
    }

    public void telaMain(){
        setContentView(R.layout.activity_principal);
        spn = (Spinner) findViewById(R.id.fac);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.faculdades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);

        confirmar =(Button) findViewById(R.id.entrar);
        confirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                abreFeed();
            }
        });


        btnlogin = (Button) findViewById(R.id.btnlog);
        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                abreTelaLogin();
            }

        });
    }
    public void abreTelaLogin(){

        Intent telaLogin = new Intent(this,login.class);
        startActivity(telaLogin);
    }
    public void abreFeed(){
        Intent feed = new Intent(this, feed.class);
        startActivity(feed);
    }

}


