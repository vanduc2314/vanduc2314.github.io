package com.example.todolate.viewModel.Screen;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.todolate.R;
import com.example.todolate.data.FirebaseNotify;
import com.example.todolate.data.remote.Retrofit;
import com.example.todolate.data.remote.listService;
import com.example.todolate.data.remote.notificationService;
import com.example.todolate.data.remote.userService;
import com.example.todolate.model.ActList;
import com.example.todolate.model.Task;
import com.example.todolate.viewModel.AdapterModel.ActlistAdapter;
import com.example.todolate.model.User;
import com.example.todolate.viewModel.AdapterModel.TaskAdapter;
import com.example.todolate.viewModel.Dialog.Setting_Dialog;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends AppCompatActivity implements ActlistAdapter.ListApdaterClick, TaskAdapter.ItemClickListener, Setting_Dialog.SetButtonClick {


    private Button btnAdd;
    private MaterialToolbar tb;
    private RecyclerView activityListView;
    private listService ls;
    private userService us;
    private User u;
    private ActlistAdapter actlistAdapter;
    private ArrayList<ActList> actLists;
    private ArrayList<Task> taskList;
    private ArrayList<Task> allTask;
    private RecyclerView taskListView;
    private TaskAdapter taskAdapter;
    private CoordinatorLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        actlistAdapter = new ActlistAdapter();
        actLists = new ArrayList<>();
        tb = findViewById(R.id.toolbar);
        loading= findViewById(R.id.loading);
        activityListView = findViewById(R.id.activityListsView);
        taskListView = findViewById(R.id.listTaskView);
        btnAdd = findViewById(R.id.btnAddList);
        MenuItem search = tb.getMenu().findItem(R.id.search);
        taskAdapter = new TaskAdapter();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddListDialog(Gravity.CENTER);
            }
        });
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Setting_Dialog sd = new Setting_Dialog();
                sd.show(getSupportFragmentManager(), "setting_dialog");
            }
        });
        search.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent i = new Intent(Home.this, SearchScreen.class);
                i.putExtra("task_list", allTask);
                startActivity(i);
                return false;
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        setUpUser();
        if (u.getImage() != null) {
            Bitmap image = Bitmap.createScaledBitmap(u.getImageBitmap(), 120, 120, true);
            RoundedBitmapDrawable dr =
                    RoundedBitmapDrawableFactory.create(getResources(), image);
            dr.setCircular(true);
            tb.setNavigationIcon(dr);
        }
            getListFromDb();
    }

    private void setTaskAdapter() {
        LinearLayoutManager lnm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        taskListView.setLayoutManager(lnm);
        taskAdapter.setData(taskList, this);
        taskListView.setAdapter(taskAdapter);
    }

    private void setAdapterAddList() {
        LinearLayoutManager lnm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityListView.setLayoutManager(lnm);
        actlistAdapter.setData(actLists, this);
        actlistAdapter.setContext(this);
        activityListView.setAdapter(actlistAdapter);
    }

    private void setUpUser() {
        SharedPreferences mPrefs = getSharedPreferences("user", MODE_PRIVATE);
        u = new User();
        String json = mPrefs.getString("user", null);
        if (json == null) {
            Intent i = new Intent(Home.this, SignInScreen.class);
            startActivity(i);
            finish();
        }
        Gson gson = new Gson();
        u = gson.fromJson(json, User.class);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(u.getUsername());
    }

    private void openAddListDialog(int gravity) {
        Dialog dialog = new Dialog(Home.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_list_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtt = window.getAttributes();
        windowAtt.gravity = gravity;
        window.setAttributes(windowAtt);

        EditText txtListName = dialog.findViewById(R.id.txtListName);
        Button btnCreate = dialog.findViewById(R.id.Add_list);
        Button btnCancel = dialog.findViewById(R.id.cancel_action);
        dialog.show();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.setVisibility(View.VISIBLE);
                ActList al = new ActList();
                if(txtListName.getText().toString().equals("")){
                    al.setName("Untitle List");
                }
                else{
                    al.setName(txtListName.getText().toString());
                }
                addToDb(al);
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void addToDb(ActList al) {
        connectService();
        Call<JsonObject> create = ls.createList(al);
        create.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                connectUser();
                Map<String, String> list = new LinkedHashMap<>();
                al.setId(response.body().get("name").toString().replace("\"", ""));
                list.put(al.getId().replace("\"", ""), al.getName());
                Call<JsonObject> updatetouser = us.updateListtoUser(u.getId().replace("\"", ""), list);
                updatetouser.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        //chuyển hướng sang trang list, truyền cả list theo
                        actLists.add(al);
                        actlistAdapter.setData(actLists, Home.this);
                        Intent i = new Intent(Home.this,ListScreen.class);
                        i.putExtra("actlist",al);
                        loading.setVisibility(View.GONE);
                        startActivity(i);
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void getListFromDb() {
        connectUser();
        Call<JsonObject> list_of_User = us.getListofUser(u.getId().replace("\"", ""));
        list_of_User.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Map<String, String> map;
                Gson gs = new Gson();
                map = gs.fromJson(response.body(), Map.class);
                actLists = new ArrayList<>();
                for (Map.Entry<String, String> e : map.entrySet()) {
                    ActList al = new ActList();
                    al.setId(e.getKey());
                    al.setName(e.getValue());
                    mapTasktoList(al.getId());
                    actLists.add(al);
                }
                setAdapterAddList();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void connectService() {
        ls = Retrofit.getClient("https://todolate-7b318-default-rtdb.firebaseio.com/").create(listService.class);
    }

    private void connectUser() {
        us = Retrofit.getClient("https://todolate-7b318-default-rtdb.firebaseio.com/").create(userService.class);
    }

    private void mapTasktoList(String listid) {
        taskList = new ArrayList<>();
        allTask = new ArrayList<>();
        connectService();
        Call<Map<String, Task>> list_of_Task = ls.getTaskByListID(listid);
        list_of_Task.enqueue(new Callback<Map<String, Task>>() {
            @Override
            public void onResponse(Call<Map<String, Task>> call, Response<Map<String, Task>> response) {
                if (response.body() != null) {
                    for (Map.Entry<String, Task> e : response.body().entrySet()) {
                        Task t = new Task();
                        t.setId(e.getKey().replace("\"", ""));
                        t.setName(e.getValue().getName());
                        t.setStatus(e.getValue().getStatus());
                        t.setDueDate(e.getValue().getDueDate());
                        t.setCreatedDate(e.getValue().getCreatedDate());
                        t.setNote(e.getValue().getNote());
                        t.setListId(listid);
                        Date today = new Date();
                        if (t.getDueDate() != null && t.getDueDate().after(today)) {
                            taskList.add(t);
                        }
                        allTask.add(t);
                    }
                    setTaskAdapter();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Task>> call, Throwable t) {

            }
        });
    }

    @Override
    public void deleteList(int position) {
        connectService();
        Call<JsonObject> deleteList = ls.deleteList(actLists.get(position).getId());
        deleteList.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        connectUser();
        Call<JsonObject> deleteListAtUser = us.deleteListAtUser(u.getId(), actLists.get(position).getId());
        deleteListAtUser.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        actLists.remove(position);
        actlistAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onTaskClick(int position) {
        taskList.get(position);
        Intent i = new Intent(Home.this, TaskDetailScreen.class);
        System.out.println(taskList.get(position));
        i.putExtra("task", taskList.get(position));
        i.putExtra("list_id", taskList.get(position).getListId());
        startActivity(i);
    }

    @Override
    public void onDeleteTaskClick(int position) {
        String taskID = taskList.get(position).getId();
        connectService();
        Call<JsonObject> deletetaskatlist = ls.deleteTaskatList(taskList.get(position).getListId(), taskID);
        deletetaskatlist.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        taskList.remove(position);
        taskAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onStatusClick(int position) {
        Task uTask = taskList.get(position);
        if (uTask.getStatus()) {
            uTask.setStatus(false);
        } else {
            uTask.setStatus(true);
        }
        connectService();
        Map<String, Boolean> taskupdate = new LinkedHashMap<>();
        taskupdate.put("status", uTask.getStatus());
        Call<JsonObject> updateTask = ls.updateStatusTask(taskList.get(position).getListId(), uTask.getId().replace("\"", ""), taskupdate);
        updateTask.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                taskAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    @Override
    public void LoginClick(View view) {
        SharedPreferences settings = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= settings.edit();
        settings.edit().clear().commit();
        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                build();
        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(view.getContext(),gso);
        googleSignInClient.signOut();
        Intent intent = new Intent(view.getContext(),SignInScreen.class);
        startActivity(intent);
        finish();
    }
}