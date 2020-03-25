package com.mcgill.bluband;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private Button loginButton;
    private Button registerButton;
    private TextView forgotPassword;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.emailID); //Set email as input of email input in frontend
        password = (EditText) findViewById(R.id.passwordID);//Set password as input of password input in frontend
        loginButton = (Button)findViewById(R.id.loginID);//Connnect login button to button on login page
        registerButton = (Button)findViewById(R.id.registerID);
        forgotPassword = (TextView)findViewById(R.id.forgotPasswordText);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mFirebaseAuth.getCurrentUser();

                if (user != null) {
                    Toast.makeText(MainActivity.this,"Successfully Logged In",Toast.LENGTH_SHORT);
                    openHomeActivity();
                }
                else{
                    Toast.makeText(MainActivity.this,"Please Log In",Toast.LENGTH_SHORT);
                }
            }
        };

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgotPasswordActivity();
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gets input email and password
                String inputEmail = email.getText().toString();
                String inputPassword = password.getText().toString();

                if(inputEmail.isEmpty()) {
                    email.setError("Please enter email");
                    email.requestFocus(); //Pops a red error message prompting email input
                }

                else if(inputPassword.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus(); //Pops a red error message prompting email input
                }

                else if (inputEmail.isEmpty() && inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,"Both Fields are Empty!",Toast.LENGTH_SHORT).show();
                }

                //Verify is email/password combination is correct
                else if(!(inputEmail.isEmpty() && inputPassword.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(inputEmail,inputPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Could not sign in
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Successful Login",Toast.LENGTH_SHORT).show();
                                openHomeActivity();
                            }
                        }
                    });
                }

                else {
                    Toast.makeText(MainActivity.this,"Unexpected Error, Try Again Later",Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationActivity();
            }
        });
    }

    public void openRegistrationActivity(){
        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }
    public void openHomeActivity(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    public void openForgotPasswordActivity(){
        Intent intent = new Intent(this,ForgotPasswordActivity.class);
        startActivity(intent);
    }




}
