package com.example.todolate.viewModel.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolate.R;
import com.example.todolate.data.remote.Retrofit;
import com.example.todolate.data.remote.userService;
import com.example.todolate.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpScreen extends AppCompatActivity {
    private Button btSignIn;
    private Button btSignUp;
    private TextView txtEmail;
    private TextView txtpass;
    private TextView txtName;
    private TextView txtUsername;
    private TextView txtCpass;
    private userService sus;
    private CoordinatorLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);
        btSignIn = findViewById(R.id.btn_sign_in);
        btSignUp = findViewById(R.id.btn_sign_up);
        txtEmail = findViewById(R.id.txt_email);
        txtpass = findViewById(R.id.txt_pass);
        txtName = findViewById(R.id.txt_full_name);
        txtCpass = findViewById(R.id.txt_confirmed_pass);
        txtUsername = findViewById(R.id.txt_username);
        loading= findViewById(R.id.loading);
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(SignUpScreen.this,
                        SignInScreen.class);
                startActivity(i);
                finish();
            }
        });
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String pass = txtpass.getText().toString();
                String fullname = txtName.getText().toString();
                String username = txtUsername.getText().toString();
                String cpass = txtCpass.getText().toString();
                if (fullname != null & username != null & cpass.equals(pass)) {
                    User u = new User(fullname, email, username, pass);
                    InputMethodManager imm = (InputMethodManager) getSystemService(SignUpScreen.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    signUpUser(u);
                }

            }
        });

    }

    private void signUpUser(User newUser) {
        loading.setVisibility(View.VISIBLE);
        Map<String, String> m = new LinkedHashMap<>();
        m.put("email", newUser.getEmail());
        m.put("password", newUser.getPassword());

        connectService("https://identitytoolkit.googleapis.com/v1/");
        Call<JsonObject> call = sus.signUpWithEmail(m);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    connectService("https://todolate-7b318-default-rtdb.firebaseio.com/");
                    Call<JsonObject> call1 = sus.sendUser(newUser);
                    call1.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            loading.setVisibility(View.GONE);
                            newUser.setId(response.body().get("name")+"");
                            Gson gson = new Gson();
                            String userString = gson.toJson(newUser);
                            SharedPreferences mPrefs = SignUpScreen.this.getSharedPreferences("user", MODE_PRIVATE);
                            SharedPreferences.Editor prefsEditor = mPrefs.edit();
                            prefsEditor.putString("user", userString);
                            prefsEditor.commit();
                            Intent intent = new Intent(SignUpScreen.this, Home.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                }
                else{
                    loading.setVisibility(View.GONE);
                    Toast.makeText(SignUpScreen.this,"Password length >8 and Email match the format abc@mailserver ",Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });


    }

    private void connectService(String base_url) {
        sus = Retrofit.getClient(base_url).create(userService.class);
    }

}
