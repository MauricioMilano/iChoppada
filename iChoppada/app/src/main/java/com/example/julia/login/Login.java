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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//TODO a porra toda
//Aparentemente o Firebase fornece métodos de login(óbvio, tem pra criar conta
//pq n teria pra login) então, se Deus quiser, não vai ser tão difícil de se fazer.
//Se Deus quiser.
public class Login extends AppCompatActivity {
    Button enviar;
    EditText email, senha;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "EmailPassword";
    Sessao sessao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessao = new Sessao(getApplicationContext());
        enviar = (Button) findViewById(R.id.entrar);
        email = (EditText) findViewById(R.id.emailEntLogin);
        senha = (EditText) findViewById(R.id.senhaEntLogin);

        mAuth = FirebaseAuth.getInstance();
        if(sessao.isLoggedIn()){
            screenChoppadas();
        }else{
        mAuthListener = new FirebaseAuth.AuthStateListener() {
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
        enviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((email.getText().toString()).isEmpty() || (senha.getText().toString()).isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, digite o nome e a senha corretamente",
                            Toast.LENGTH_SHORT).show();
                } else {
                            fazerLogin(email, senha);


                }
            }
        });}


    }
    public void fazerLogin(final EditText email, final EditText senha ){
        sessao = new Sessao(getApplicationContext());
        mAuth.signInWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(Login.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();

                        }else {
                            sessao.createLoginSession(senha.getText().toString(),email.getText().toString());
                            screenChoppadas();
                            finish();

                        }

                        // ...
                    }
                });



    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void screenChoppadas(){
        Intent choppadas = new Intent(this, MostrarChoppadas.class);
        startActivity(choppadas);
    }

}



