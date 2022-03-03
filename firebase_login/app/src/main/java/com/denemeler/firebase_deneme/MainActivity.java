package com.denemeler.firebase_deneme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    private FirebaseAuth mAuth;
    EditText mail,password;
    Button kayit,giris;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail=findViewById(R.id.emailText);
        password= findViewById(R.id.parolaText);

        kayit=findViewById(R.id.passwordBtn);
        kayit.setOnClickListener(this);

        giris=findViewById(R.id.registerBtn);
        giris.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser=mAuth.getCurrentUser();

        if (currentUser != null){
            Intent intent= new Intent(MainActivity.this, ExamplePageActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void register(){

        String mail_=mail.getText().toString();
        String password_=password.getText().toString();

        mAuth.createUserWithEmailAndPassword(mail_, password_)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                    Toast toa=Toast.makeText(MainActivity.this,"Success . . .",Toast.LENGTH_LONG);
                    toa.setGravity(Gravity.CENTER,0,0);
                    toa.show();

                        }

                        else{
                            task.getException();
                            Toast toa=Toast.makeText(MainActivity.this,"Failed . . .",Toast.LENGTH_LONG);
                            toa.setGravity(Gravity.CENTER,0,0);
                            toa.show();
                            }
                    }
                });

    }


    public void login() {
        String mail_ = mail.getText().toString();
        String password_ = password.getText().toString();

        mAuth.signInWithEmailAndPassword(mail_,password_).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Intent intent= new Intent(MainActivity.this, ExamplePageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){

            case R.id.passwordBtn:
                register();
                break;

            case R.id.registerBtn:
                login();
                break;

        }


    }
}