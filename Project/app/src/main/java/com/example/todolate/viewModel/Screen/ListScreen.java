package com.example.todolate.viewModel.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.todolate.R;
import com.example.todolate.data.remote.Retrofit;
import com.example.todolate.data.remote.listService;
import com.example.todolate.data.remote.notificationService;
import com.example.todolate.model.ActList;
import com.example.todolate.model.Task;
import com.example.todolate.viewModel.AdapterModel.TaskAdapter;
import com.example.todolate.viewModel.Dialog.Task_Dialog;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListScreen extends AppCompatActivity implements Task_Dialog.BottomSheetListener, TaskAdapter.ItemClickListener {

    private ActList actList;
    private TextView txtListTitle, deleteTask;
    private ArrayList<Task> taskList;
    private Button btnAddTask;
    private RecyclerView taskListView;
    private TaskAdapter taskAdapter;
    private listService ls;
    private notificationService ts;
    private View taskItemView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_screen);
        Intent intent = this.getIntent();
        actList = (ActList) intent.getSerializableExtra("actlist");
        txtListTitle = findViewById(R.id.txtListTitle);
        btnAddTask = findViewById(R.id.btnAddATask);
        taskListView = findViewById(R.id.listTaskView);
        txtListTitle.setText(actList.getName());
        Toolbar toolbar= findViewById(R.id.listToolBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task_Dialog td = new Task_Dialog();
                td.show(getSupportFragmentManager(), "task_dialog");

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter();
        setTaskAdapter();
        getTaskFromDb();
    }

    private void setTaskAdapter() {
        LinearLayoutManager lnm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        taskListView.setLayoutManager(lnm);
        taskAdapter.setData(taskList, this);
        taskListView.setAdapter(taskAdapter);
    }

    private void AddTaskToDb(Task task) {
        connectList();
        Call<JsonObject> addTaskIdtoList = ls.addTaskToList(actList.getId().replace("\"", ""), task);
        addTaskIdtoList.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                taskList.add(0,task);
                taskAdapter.notifyItemInserted(0);
                taskListView.scrollToPosition(0);
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


    }

    private void getTaskFromDb() {
        connectList();
        Call<Map<String, Task>> list_of_Task = ls.getTaskByListID(actList.getId());
        list_of_Task.enqueue(new Callback<Map<String, Task>>() {
            @Override
            public void onResponse(Call<Map<String, Task>> call, Response<Map<String, Task>> response) {
                if(response.body()!=null){
                    for (Map.Entry<String, Task> e : response.body().entrySet()) {
                        Task t = new Task();
                        t.setId(e.getKey().replace("\"", ""));
                        t.setName(e.getValue().getName());
                        t.setStatus(e.getValue().getStatus());
                        t.setDueDate(e.getValue().getDueDate());
                        t.setCreatedDate(e.getValue().getCreatedDate());
                        t.setNote(e.getValue().getNote());
                        taskList.add(0, t);
                    }
                    setTaskAdapter();
                }


            }

            @Override
            public void onFailure(Call<Map<String, Task>> call, Throwable t) {

            }
        });
    }


    private void connectList() {
        ls = Retrofit.getClient("https://todolate-7b318-default-rtdb.firebaseio.com/").create(listService.class);
    }

    @Override
    public void onButtonClicked(Task t) {
        AddTaskToDb(t);
    }


    @Override
    public void onTaskClick(int position) {
        taskList.get(position);
        Intent i = new Intent(ListScreen.this, TaskDetailScreen.class);
        System.out.println(taskList.get(position));
        i.putExtra("task", taskList.get(position));
        i.putExtra("list_id", actList.getId());
        startActivity(i);
    }

    @Override
    public void onDeleteTaskClick(int position) {
        String taskID = taskList.get(position).getId();
        connectList();
        Call<JsonObject> deletetaskatlist = ls.deleteTaskatList(actList.getId(), taskID);
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
        connectList();
        Map<String, Boolean> taskupdate = new LinkedHashMap<>();
        taskupdate.put("status", uTask.getStatus());
        Call<JsonObject> updateTask = ls.updateStatusTask(actList.getId(), uTask.getId().replace("\"", ""), taskupdate);
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

}