package com.example.julia.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

//SE FOR FAZER TESTES COM ISSO
//LEMBRE-SE: UMA CONTA DE EMAIL POR PARÂMETO SENHA DE NO MÍNIMO SEIS DÍGITOS
//ACHO QUE TAMBÉM NÃO PODE CARACTERES ESPECIAIS

//Parcialmente copiado dos docs do Firebase
//Não sei lidar muito bem com activities então se você fizer um cadastro, a página de cadastro às vezes não limpa os dados
//Também o botão de Entrar continua na tela principal mas isso é só colocar um Invisible no botão
public class cadastro extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    private DatabaseReference refDB = FirebaseDatabase.getInstance().getReference();

    Map<String, String> dadosUsuario = new HashMap<String,String>();


    private static final String TAG = "EmailPassword";
    EditText lerEmail, lerSenha, lerNome;
    Button entrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);



        //Pega instância da autenticação do Firebase
        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        //Campos de entrada de Email, Senha, Nome.
        lerEmail = (EditText) findViewById(R.id.emailEnt);
        lerSenha = (EditText) findViewById(R.id.senhaEnt);
        lerNome = (EditText) findViewById(R.id.nomeEnt);

        //Seta o listener para o botão de entrar, redirecionando para a tela de choppadas
        entrar = (Button) findViewById(R.id.entrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((lerEmail.getText().toString()).isEmpty() || (lerSenha.getText().toString()).isEmpty()){
                    Toast.makeText(getApplicationContext(),"Por favor, digite o nome e a senha corretamente",
                                   Toast.LENGTH_SHORT).show();
                }
                else{
                    criarConta(lerEmail.getText().toString(),lerSenha.getText().toString());

                }
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();

        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }


    //Cria conta usando e-mail e senha; e loga o usuário.
    private void criarConta(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(cadastro.this, "Conta criada com sucesso! Logando... ",
                                Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            Toast.makeText(cadastro.this, "Login Falhou: " + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            dadosUsuario.put("nome",lerNome.getText().toString());
                            String usuarioId = auth.getCurrentUser().getUid().toString();
                            refDB.child("users").push().setValue(dadosUsuario);
                            screenChoppadas();
                        }

                    }
                });

    }
    //Redireciona para a tela com todas as choppadas.
    public void screenChoppadas(){
        Intent choppadas = new Intent(this, mostrarChoppadas.class);
        startActivity(choppadas);
    }


}
