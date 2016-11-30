package com.example.julia.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by João on 23/11/2016.
 */
public class AdicionarChoppada extends AppCompatActivity {
    DatabaseReference refDB = FirebaseDatabase.getInstance().getReference().child("choppadas");
    EditText lerNome, lerLocal;
    Button adicionar;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chopp);

        //Prepara para ler de EditText
        lerNome = (EditText) findViewById(R.id.nome_da_choppada);
        lerLocal = (EditText) findViewById(R.id.local_da_choppada);
        adicionar = (Button) findViewById(R.id.adicionar_chopp);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lê os valores colocados pelo usuário
                String nome = lerNome.getText().toString();
                String local = lerLocal.getText().toString();


                //Adiciona novos valores ao banco de dados, sem sobreescrever.
                //PRECISO ACHAR UMA MANEIRA DE MOSTRAR ESSES VALORES
                //PQ ESSA PORRA NAO FUNCIONA DEUS????!!!!!
                /*
                  String key = refDB.child("choppadas").push().getKey();
                  Choppada chop = new Choppada(nome,local);
                  Map<String, Object> choppValues = chop.toMap();
                    Map<String, Object> updates = new HashMap<String, Object>();
                    updates.put("/choppadas/"+key,choppValues);
                    refDB.updateChildren(updates);
                */

                //Subscreve todos os dados de um nó do banco de dados com o valor passado em setValue.
                //Mas funciona hehehehehhehehehehehehehheheheheheheehh
                Choppada choppada = new Choppada(nome,local);
                choppada.setNome(nome);choppada.setLocal(local);//Não sei pq eu escrevi isso mas funcionou
                refDB.setValue(choppada);

                finish();// termina a activity e desempilha

            }
        });
    }


}

