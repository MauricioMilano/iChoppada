package com.example.mauricio.olamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {
    TextView cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cadastro = (TextView) findViewById(R.id.cadastro);
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreTelaCadastro();
            }
        });

    }
    private void abreTelaCadastro(){
        Intent cadastro = new Intent (this,cadastro.class);
        startActivity(cadastro);
    }

}
