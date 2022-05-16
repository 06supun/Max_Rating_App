package com.example.max_rating_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    ImageView img_back;
    private EditText input_reg_email;
    private EditText input_reg_password;
    Button btn_register;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        input_reg_email = findViewById(R.id.input_log_email);
        input_reg_password = findViewById(R.id.input_log_password);
        btn_register = findViewById(R.id.btn_login);
        img_back = findViewById(R.id.img_back);
        mAuth = FirebaseAuth.getInstance();
// move to login page
        img_back.setOnClickListener(view -> {
            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
        });

        // register user
        btn_register.setOnClickListener(view -> {
            user_register();
        });
    }

    private void user_register() {
        String s_email = input_reg_email.getText().toString();
        String s_password = input_reg_password.getText().toString();

        if (TextUtils.isEmpty(s_email)) {
            input_reg_email.setError("Field Cannot be Empty");
        } else if (TextUtils.isEmpty(s_password)) {
            input_reg_password.setError("Field Cannot be Empty");
        } else {
            mAuth.createUserWithEmailAndPassword(s_email, s_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Registration is Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    } else if (!task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Registration failed" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}