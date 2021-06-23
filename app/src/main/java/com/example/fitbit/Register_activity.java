package com.example.fitbit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register_activity extends AppCompatActivity implements View.OnClickListener{
    private TextView registerUser;
    private EditText editTextname,editTextemail,editTextpass,editTextdob;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        registerUser=(Button)findViewById(R.id.SignUpButton);
        editTextname= findViewById(R.id.UserName);
        editTextemail=findViewById(R.id.email);
        editTextpass=findViewById(R.id.passWord);
        editTextdob=findViewById(R.id.dateOfBirth);
        progressBar=findViewById(R.id.progressBar);

        registerUser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SignUpButton:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        final String uName=editTextname.getText().toString().trim();
        final String userEmail=editTextemail.getText().toString().trim();
        String userPassword=editTextpass.getText().toString().trim();
        final String userDOB=editTextdob.getText().toString().trim();
        if(uName.isEmpty() && userEmail.isEmpty() && userPassword.isEmpty() && userDOB.isEmpty())
        {
            editTextname.setError("Full Name is Required");
            editTextemail.setError("Email Address  is Required");
            editTextpass.setError("Password Cannot be empty");
            editTextdob.setError("Date Of Birth required");

            return;
        }

        if(uName.isEmpty() )
        {
            editTextname.setError("Full Name is Required");
            editTextname.requestFocus();
            return;
        }
        if(userEmail.isEmpty())
        {
            editTextemail.setError("Email Address  is Required");
            editTextemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
        {
            editTextemail.setError("Please provide Valid E-mail Address!!");
            editTextemail.requestFocus();
            return;
        }

        if(userPassword.isEmpty())
        {
            editTextpass.setError("Password Cannot be empty");
            editTextpass.requestFocus();
            return;
        }
        if(userDOB.isEmpty())
        {
            editTextdob.setError("Date Of Birth required");
            editTextdob.requestFocus();
            return;
        }
        if(userPassword.length()<6)
        {
            editTextpass.setError("Minimum Password Length is 6");
            editTextpass.requestFocus();
            return;

        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User user=new User(uName,userEmail,userDOB);
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(Register_activity.this,"Registration Successfull",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);


                                        //redirect here
                                        startActivity(new Intent(Register_activity.this,Home.class));

                                    }
                                    else
                                    {
                                        Toast.makeText(Register_activity.this,"Failed to register Try Again!!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(Register_activity.this,"Failed to register ",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
