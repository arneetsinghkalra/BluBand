package com.mcgill.bluband;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private TextView email, password, alreadyHaveAnAccount;
    private Button registerButton;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = (TextView)findViewById(R.id.emailID); //Set email as input of email input in frontend
        password = (TextView)findViewById(R.id.passwordID);//Set password as input of password input in frontend
        registerButton = (Button)findViewById(R.id.registerID);

        alreadyHaveAnAccount = (TextView)findViewById(R.id.haveAnAccountText);

        registerButton.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(RegistrationActivity.this,"Both Fields are Empty!",Toast.LENGTH_SHORT).show();
                }

                //Email and password is provided
                else if (!(inputEmail.isEmpty() && inputPassword.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(inputEmail,inputPassword).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //If not succesful
                            if (!task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this,task.getException().getMessage() ,Toast.LENGTH_SHORT).show();
                            }
                            else{
                                openLoginActivity(); //Successful so go to login page
                            }
                        }
                    });
                }

                else{
                    Toast.makeText(RegistrationActivity.this,"Unexpected Error: Please try again later.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Go back to login page if button clicked
        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }

    //Method to go back to login page
    public void openLoginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
