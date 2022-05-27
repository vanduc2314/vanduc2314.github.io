package com.example.todolate.viewModel.Screen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.todolate.R;
import com.example.todolate.data.remote.Retrofit;
import com.example.todolate.data.remote.userService;
import com.example.todolate.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.gun0912.*;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeInfoScreen extends AppCompatActivity {

    private static final int PICK_IMAGER_REQUEST = 1;

    private User u;
    private Toolbar toolbar;
    private EditText txtFullName;
    private EditText txtUserName;
    private Button btnUpdate;
    private Button btnCancel;
    private ImageView imageAvatar;
    private userService us;
    String oldimage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_info_screen);
        setUpUser();
        txtFullName = findViewById(R.id.txt_full_name);
        txtUserName = findViewById(R.id.txt_username);
        btnUpdate = findViewById(R.id.btn_change_info);
        toolbar = findViewById(R.id.ToolBar);
        imageAvatar = findViewById(R.id.imageAvatar);

        txtFullName.setText(u.getFullname());
        txtUserName.setText(u.getUsername());
        oldimage = u.getImage();

        if(u.getImage() == null || u.getImage().equals(""))
        {
            int res = getResources().getIdentifier("@drawable/avatar_img", "drawable", this.getPackageName());
            imageAvatar.setImageResource(res);
        }
        else
        {
            imageAvatar.setImageBitmap(u.getImageBitmap());
        }

        imageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verify()) {
                    updateUserInfo();
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void requestPermission()
    {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(ChangeInfoScreen.this,"Permission Denied",Toast.LENGTH_LONG);
            }
        };

        TedPermission.create()
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();


    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGER_REQUEST && resultCode == RESULT_OK
           && data != null && data.getData() != null)
        {
            Uri mImgUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),mImgUri);
                u.setImage(bitmap);
                imageAvatar.setImageBitmap(u.getImageBitmap());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void updateUserInfo()
    {
        String newFullname = txtFullName.getText().toString();
        String newUserName = txtFullName.getText().toString();
        //send to API to change email
        //...
        //
        u.setFullname(newFullname);
        u.setUsername(newUserName);

        updateUserToDb();
        sendUserToPref();
        Toast.makeText(ChangeInfoScreen.this, "Update Success", Toast.LENGTH_LONG).show();
    }

    private void updateUserToDb() {
        connectUser();
        Call<JsonObject> update = us.updateUser(u.getId(),u);
        update.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                System.out.println("API update OK");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println("API update Error");
            }
        });
    }


    private boolean verify()
    {
        String newFullname = txtFullName.getText().toString();
        String newUserName = txtFullName.getText().toString();
        if(newFullname.equals(u.getFullname())
                && newUserName.equals(u.getUsername())
                && ((oldimage == null && u.getImage() == null)
                || (oldimage != null && oldimage.equals(u.getImage()))))
        {
            Toast.makeText(ChangeInfoScreen.this, "Your info is unchanged", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(newFullname.trim().equals(""))
        {
            Toast.makeText(ChangeInfoScreen.this, "Your full name cannot be blank", Toast.LENGTH_LONG).show();
            txtFullName.requestFocus();
            return false;
        }

        else if (newUserName.trim().equals(""))
        {
            Toast.makeText(ChangeInfoScreen.this, "Your username cannot be blank", Toast.LENGTH_LONG).show();
            txtUserName.requestFocus();
            return false;
        }
        return true;
    }

    private void setUpUser() {
        SharedPreferences mPrefs = getSharedPreferences("user", MODE_PRIVATE);
        u = new User();
        String json = mPrefs.getString("user", null);
        Gson gson = new Gson();
        u = gson.fromJson(json, User.class);
        System.out.println(u.getFullname());
    }

    private void sendUserToPref()
    {
        Gson gson = new Gson();
        String userString = gson.toJson(u);
        System.out.println("user"+userString);
        SharedPreferences mPrefs = ChangeInfoScreen.this.getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("user", userString);
        prefsEditor.commit();
    }

    private void connectUser() {
        us = Retrofit.getClient("https://todolate-7b318-default-rtdb.firebaseio.com/").create(userService.class);
    }
}