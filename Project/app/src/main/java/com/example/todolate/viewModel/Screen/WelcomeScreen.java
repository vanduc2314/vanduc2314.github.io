package com.example.todolate.viewModel.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.todolate.R;

public class WelcomeScreen extends AppCompatActivity {
    private SharedPreferences spf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        Button btCreateAccount = findViewById(R.id.btn_create);
        TextView btSignIn = findViewById(R.id.txt_sign_in);
        if (checkLogin()) {
            Intent i = new Intent(WelcomeScreen.this, Home.class);
            startActivity(i);
            finish();
        }
        ;
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(WelcomeScreen.this,
                        SignInScreen.class);
                startActivity(i);
            }
        });

        btCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(WelcomeScreen.this,
                        SignUpScreen.class);
                startActivity(i);
            }
        });
    }

    private boolean checkLogin() {
        spf = getSharedPreferences("user", MODE_PRIVATE);
        String user=spf.getString("user",null);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }
}