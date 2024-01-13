package com.example.authentication_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        FirebaseAuth auth=FirebaseAuth.getInstance();

        MaterialButton createAccount=findViewById(R.id.createAccount);
        TextInputLayout mailLayout=findViewById(R.id.mailLayout);
        TextInputLayout passwordLayout=findViewById(R.id.passwordLayout);
        TextInputLayout nameLayout=findViewById(R.id.nameLayout);
        TextInputEditText nameET=findViewById(R.id.nameET);
        TextInputEditText mailET=findViewById(R.id.mailET);
        TextInputEditText passwordET=findViewById(R.id.passwordET);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.requireNonNull(nameET.getText()).toString().isEmpty()){
                    nameLayout.setError("This field is required");
                }else if(Objects.requireNonNull(mailET.getText()).toString().isEmpty()){
                    mailLayout.setError("This field is required");
                }else if(Objects.requireNonNull(passwordET.getText()).toString().isEmpty()){
                    passwordLayout.setError("This field is required");
            }else {
                    ProgressDialog progressDialog=new ProgressDialog(CreateAccountActivity.this);
                    progressDialog.setMessage("Creating Account...");
                    progressDialog.show();

                    auth.createUserWithEmailAndPassword(mailET.getText().toString(),passwordET.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            UserProfileChangeRequest request=new UserProfileChangeRequest.Builder()
                                    .setDisplayName(nameET.getText().toString()).build();
                            Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).updateProfile(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    progressDialog.dismiss();
                                    Toast.makeText(CreateAccountActivity.this, "Account created successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(CreateAccountActivity.this,ProfileActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(CreateAccountActivity.this, "There was an error while creating the account", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(CreateAccountActivity.this, "There was an error while creating the account", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}