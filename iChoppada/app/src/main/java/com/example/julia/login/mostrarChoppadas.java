package com.example.julia.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;


import com.firebase.client.Firebase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by João on 23/11/2016.
 */

/**         Soneto de Ódio em versos brancos(a.k.a. falta do que fazer)
//
//          Essa classe linda e perfeita
//          É a pior classe que eu ja fiz
//          Desejo tudo de horrível para ela
//          Porque ela merece
//
//          Ela reclamava do snapshot de Choppada
//          Dizia dar um erro NullPointerException
//          Depois de 30 minutos dei CTRL-C CTRL-V
//          E resolvi o bug; mas que merda de classe?
//
//          Uma vez ela disse que não conseguia converter
//          De String pra Choppada
//          Mas a String ERA uma Choppada;mas que merda de classe?
//
//          E se achar que reclamo à toa
//          Te convido a experimentar
//          Ir se fuder
//          Verso extra pq beijos
*/

//Favor não alterar muito o código, caso queira.
//EM LAB WEB ISSO ERA 4X MAIS FÁCIL
public class mostrarChoppadas extends AppCompatActivity {

    private DatabaseReference refDB = FirebaseDatabase.getInstance().getReference();
    DatabaseReference choppRef = refDB;//Referencia o banco de dados
    List<Choppada> listaChopp = new ArrayList<Choppada>();
    ListView listViewChoppada;
    Button addChopp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choppada);

        //Organizei as coisas por ListView porque fica mais bonitinho
        //Mas é bem ruim de mexer em ListView
        //Considerando mudar para TextView e perder alguns décimos
        listViewChoppada = (ListView) findViewById(R.id.choppada);

        //Cria um Adapter para acessar e organizar os dados de listaChopp
        ChoppAdapter adapter = new ChoppAdapter(this,listaChopp);
        listViewChoppada.setAdapter(adapter);

        addChopp = (Button) findViewById(R.id.add_chopp);
        addChopp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                adicionarChopp();
            }

        });


        choppRef.addValueEventListener(new ValueEventListener() {
            //Pega todas as choppadas e passa para um ArrayList
            //para o Adapter(que só funciona com Arrays obrigado Google)(só funciona leia-se não quero escrever pra HashMap)
            //poder administrar os dados
            //Snapshot pega uma cópia dos dados do nó na referência(no caso refDB)
            //Aparentemente getValue filtra os resultados pelo parâmetro.

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot choppadaSnapshot : dataSnapshot.getChildren()) {
                        Choppada chopp =  choppadaSnapshot.getValue(Choppada.class);
                        listaChopp.add(choppadaSnapshot.getValue(Choppada.class));

                }
            }

            //Não sei. Não pergunte.
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    //Redireciona para a tela de adicionar choppadas.
    public void adicionarChopp(){
        Intent intent = new Intent(this,adicionarChoppada.class);
        startActivity(intent);
    }


}