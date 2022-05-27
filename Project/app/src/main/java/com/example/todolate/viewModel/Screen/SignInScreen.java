package com.example.todolate.viewModel.Screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolate.R;
import com.example.todolate.data.remote.Retrofit;
import com.example.todolate.data.remote.userService;
import com.example.todolate.model.User;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInScreen extends AppCompatActivity {
    private Button btnLogin, btnSignup;
    private TextView txtu_email, txtpassword, txtforgotP;
    private SignInButton btnGoogle;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private GoogleSignInClient gsc;
    private GoogleSignInOptions gso;
    private userService sus;
    private FirebaseAuth auth;
    private CoordinatorLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);
        loading= findViewById(R.id.loading);
        btnLogin = findViewById(R.id.btSignIn);
        btnSignup = findViewById(R.id.btSignUp);
        txtu_email = findViewById(R.id.txt_username_email);
        txtpassword = findViewById(R.id.txt_pass_login);
        txtforgotP = findViewById(R.id.txtForgetpassword);
        btnGoogle = findViewById(R.id.google_sign_in);
        auth = FirebaseAuth.getInstance();
        createRequest();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_email = txtu_email.getText().toString();
                String password = txtpassword.getText().toString();
                if(user_email!=null && password!=null){
                    InputMethodManager imm = (InputMethodManager) getSystemService(SignInScreen.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    loading.setVisibility(View.VISIBLE);
                    SignIn_Email(user_email, password);
                }

            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(SignInScreen.this,
                        SignUpScreen.class);
                startActivity(i);
                finish();
            }
        });

        txtforgotP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });


        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                google_sign_in();
            }
        });


    }

    private void createRequest() {
        //Configure
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("943347740477-k8v4opmh4vnh5ohb987kcr852muritpe.apps.googleusercontent.com")
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId("943347740477-k8v4opmh4vnh5ohb987kcr852muritpe.apps.googleusercontent.com")
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loading.setVisibility(View.VISIBLE);
                            FirebaseUser user = auth.getCurrentUser();
                            connectService("https://todolate-7b318-default-rtdb.firebaseio.com/");
                            Call<Map<String,User>> getUserList = sus.getUser("\"email\"",'"'+user.getEmail()+'"');
                            getUserList.enqueue(new Callback<Map<String,User>>() {
                                @Override
                                public void onResponse(Call<Map<String,User>> call, Response<Map<String,User>> response) {
                                    User u = new User();
                                    if(response.body().size()>0){
                                        for (Map.Entry<String, User> e : response.body().entrySet()) {
                                            u.setId(e.getKey());
                                            u.setEmail(e.getValue().getEmail());
                                            u.setFullname(e.getValue().getFullname());
                                            u.setUsername(e.getValue().getUsername());
                                            Gson gson = new Gson();
                                            String userString = gson.toJson(u);
                                            System.out.println("user"+userString);
                                            SharedPreferences mPrefs = SignInScreen.this.getSharedPreferences("user", MODE_PRIVATE);
                                            SharedPreferences.Editor prefsEditor = mPrefs.edit();
                                            prefsEditor.putString("user", userString);
                                            prefsEditor.commit();
                                            loading.setVisibility(View.GONE);
                                            Intent intent = new Intent(SignInScreen.this, Home.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                    else{
                                        u.setEmail(user.getEmail());
                                        u.setFullname(user.getDisplayName());
                                        u.setUsername(user.getDisplayName());
                                        connectService("https://todolate-7b318-default-rtdb.firebaseio.com/");
                                        Call<JsonObject> createNewUser= sus.sendUser(u);
                                        createNewUser.enqueue(new Callback<JsonObject>() {
                                            @Override
                                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                               u.setId(response.body().get("name")+"");
                                                Gson gson = new Gson();
                                                String userString = gson.toJson(u);
                                                System.out.println("user"+userString);
                                                SharedPreferences mPrefs = SignInScreen.this.getSharedPreferences("user", MODE_PRIVATE);
                                                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                                                prefsEditor.putString("user", userString);
                                                prefsEditor.commit();
                                                loading.setVisibility(View.GONE);
                                                Intent intent = new Intent(SignInScreen.this, Home.class);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onFailure(Call<Map<String,User>> call, Throwable t) {
                                }
                            });
                        }
                    }
                });
            } catch (ApiException e) {
                System.out.println("api"+e.getMessage());
            }
        }

    }


    private void google_sign_in() {
        Intent i = gsc.getSignInIntent();
        startActivityForResult(i, 1000);
    }

    private void signOut() {
        gsc.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    private void SignIn_Email(String email, String password) {
        connectService("https://identitytoolkit.googleapis.com/v1/");
        Map<String, String> user = new LinkedHashMap<>();
        user.put("email", email);
        user.put("password", password);
        Call<JsonObject> signIn = sus.signInWithEmail(user);
        signIn.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    connectService("https://todolate-7b318-default-rtdb.firebaseio.com/");
                    Call<Map<String,User>> getUserList = sus.getUser("\"email\"",'"'+email+'"');
                    getUserList.enqueue(new Callback<Map<String,User>>() {
                        @Override
                        public void onResponse(Call<Map<String,User>> call, Response<Map<String,User>> response) {
                            User u= new User();
                            for(Map.Entry<String,User> e: response.body().entrySet()){
                                u.setId(e.getKey());
                                u.setEmail(e.getValue().getEmail());
                                u.setFullname(e.getValue().getFullname());
                                u.setUsername(e.getValue().getUsername());
                                u.setImage(e.getValue().getImage());
                            }
                            Gson gson = new Gson();
                            String userString = gson.toJson(u);
                            SharedPreferences mPrefs = SignInScreen.this.getSharedPreferences("user", MODE_PRIVATE);
                            SharedPreferences.Editor prefsEditor = mPrefs.edit();
                            prefsEditor.putString("user",userString);
                            prefsEditor.commit();
                            loading.setVisibility(View.GONE);
                            Intent intent = new Intent(SignInScreen.this, Home.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Map<String,User>> call, Throwable t) {
                        }
                    });
                } else {
                    Toast.makeText(SignInScreen.this,response.body()+"",Toast.LENGTH_SHORT).show();
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