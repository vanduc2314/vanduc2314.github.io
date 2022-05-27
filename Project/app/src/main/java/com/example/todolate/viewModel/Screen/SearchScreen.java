package com.example.todolate.viewModel.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;

import com.example.todolate.R;
import com.example.todolate.data.remote.Retrofit;
import com.example.todolate.data.remote.listService;
import com.example.todolate.model.Task;
import com.example.todolate.viewModel.AdapterModel.ActlistAdapter;
import com.example.todolate.viewModel.AdapterModel.TaskAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchScreen extends AppCompatActivity implements TaskAdapter.ItemClickListener {
    private ArrayList<Task> taskList;
    private TaskAdapter taskAdapter;
    private RecyclerView taskListView;
    private listService ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
        taskListView = findViewById(R.id.listTaskView);
        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        SearchView searchView= (SearchView) toolbar.getMenu().findItem(R.id.search).getActionView();
        taskAdapter = new TaskAdapter();
        taskList=(ArrayList<Task>) getIntent().getSerializableExtra("task_list");
        setTaskAdapter();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                taskAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                taskAdapter.getFilter().filter(s);
                return false;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setTaskAdapter() {
        LinearLayoutManager lnm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        taskListView.setLayoutManager(lnm);
        taskAdapter.setData(taskList, this);
        taskListView.setAdapter(taskAdapter);
    }
    private void connectService() {
        ls = Retrofit.getClient("https://todolate-7b318-default-rtdb.firebaseio.com/").create(listService.class);
    }

    @Override
    public void onTaskClick(int position) {
        taskList.get(position);
        Intent i = new Intent(SearchScreen.this, TaskDetailScreen.class);
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
}
