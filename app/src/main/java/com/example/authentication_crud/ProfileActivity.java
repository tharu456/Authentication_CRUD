package com.example.authentication_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.MessageFormat;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        TextView textView=findViewById(R.id.tv);
        textView.setText(MessageFormat.format("Hello {0} 😁", Objects.requireNonNull(user).getDisplayName()));
    }
}