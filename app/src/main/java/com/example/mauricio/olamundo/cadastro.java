package com.example.mauricio.olamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cadastro extends AppCompatActivity {
    TextView email,senha, senhaRep;
    Button confirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        email = (TextView) findViewById(R.id.email_cadastro);
        senha = (TextView) findViewById(R.id.senha_cadastro);
        senhaRep = (TextView) findViewById(R.id.senha_rep_cadastro);
        confirmar = (Button) findViewById(R.id.confirmar_cadastro);
        confirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                String senhaTxt = senha.getText().toString();
                String senhaRepTxt = senhaRep.getText().toString();
                View focusView = null;
                boolean cancel = false;
                if(senhaTxt!=senhaRepTxt){
                    senha.setError("As senhas não coincidem"+senhaTxt);
                    senhaRep.setError("As senhas não coincidem"+senhaRepTxt);
                }else{
                    senha.setError("foi");
                }
            }
        });

    }
    private void envia(){

    }
}
