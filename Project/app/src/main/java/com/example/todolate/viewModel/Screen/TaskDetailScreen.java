package com.example.todolate.viewModel.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.todolate.R;
import com.example.todolate.data.remote.Retrofit;
import com.example.todolate.data.remote.listService;
import com.example.todolate.data.remote.notificationService;
import com.example.todolate.model.Task;
import com.example.todolate.viewModel.Dialog.Task_Dialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDetailScreen extends AppCompatActivity {
    private Task t;
    private EditText task_title;
    private CheckBox status;
    private LinearLayout duedate;
    private LinearLayout remindme;
    private EditText note;
    private ImageButton delete;
    private TextView create;
    private TextView due_text;
    private listService ls;
    private String listID, token;
    private notificationService ns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_screen);
        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initUI();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        token = task.getResult();

                    }

                });

    }

    private void initUI() {
        t = (Task) getIntent().getExtras().getSerializable("task");
        listID = getIntent().getExtras().getString("list_id");
        task_title = findViewById(R.id.task_title);
        status = findViewById(R.id.checkboxtask);
        duedate = findViewById(R.id.add_due_date);
        due_text = findViewById(R.id.due_text);
        note = findViewById(R.id.note);
        delete = findViewById(R.id.delete_task);
        create = findViewById(R.id.create_date);
        updateStatusTask();
        deleteTaskSetUp();
        editTaskTitle();
        setDuedate();
        updateNote();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM YYYY");
        String cdate = sdf.format(t.getCreatedDate());
        create.setText("Created " + cdate);

    }

    private void setDuedate() {
        if (t.getDueDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm EEE, dd MMM YYYY");
            String date = sdf.format(t.getDueDate());
            due_text.setText("Due \n" + date);
        }
        duedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(due_text);
            }
        });
    }

    private void editTaskTitle() {
        task_title.setText(t.getName());
        task_title.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == keyEvent.ACTION_DOWN) && i == keyEvent.KEYCODE_ENTER) {
                    connectList();
                    Map<String, String> taskupdate = new LinkedHashMap<>();
                    taskupdate.put("name", task_title.getText().toString());
                    Call<JsonObject> updateTask = ls.updateTaskTitle(listID, t.getId().replace("\"", ""), taskupdate);
                    updateTask.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            task_title.clearFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(TaskDetailScreen.this.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                    return true;
                }
                return false;
            }
        });
    }

    private void updateStatusTask() {
        if (t.getStatus()) {
            status.setChecked(true);
        } else {
            status.setChecked(false);
        }
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectList();
                Map<String, Boolean> taskupdate = new LinkedHashMap<>();
                taskupdate.put("status", status.isChecked());
                Call<JsonObject> updateTask = ls.updateStatusTask(listID, t.getId().replace("\"", ""), taskupdate);
                updateTask.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void updateNote() {
        note.setText(t.getNote());
        note.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == keyEvent.ACTION_DOWN) && i == keyEvent.KEYCODE_ENTER) {
                    connectList();
                    Map<String, String> taskupdate = new LinkedHashMap<>();
                    taskupdate.put("note", note.getText().toString());
                    Call<JsonObject> updateTask = ls.updateTaskNote(listID, t.getId().replace("\"", ""), taskupdate);
                    updateTask.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            note.clearFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(TaskDetailScreen.this.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                    return true;
                }
                return false;
            }
        });

    }

    private void deleteTaskSetUp() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_dialog();
            }
        });
    }

    private void connectList() {
        ls = Retrofit.getClient("https://todolate-7b318-default-rtdb.firebaseio.com/").create(listService.class);
    }

    private void showDateTimeDialog(TextView rdate) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener DateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(Calendar.HOUR_OF_DAY, i);
                        calendar.set(Calendar.MINUTE, i1);
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm EEE, dd MMM YYYY");
                        rdate.setVisibility(View.VISIBLE);
                        rdate.setText("Due " + sdf.format(calendar.getTime()));
                        //set due
                        t.setDueDate(calendar.getTime());
                        connectList();
                        Map<String, Date> taskupdate = new LinkedHashMap<>();
                        taskupdate.put("dueDate", t.getDueDate());
                        Call<JsonObject> updateTask = ls.updateDueDate(listID, t.getId(), taskupdate);
                        updateTask.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {

                            }
                        });

                        String noti = "{" +
                                "    \"to\":\"" + token + "\"," +
                                " \"priority\": \"high\"," +
                                " \"data\" : {" +
                                " \"id\":" + (new Date().getSeconds()+ new Random().nextInt()) + "," +
                                "  \"title\" : \"You have a task on due\"," +
                                "  \"message\" :\"" + t.getName() + "\"," +
                                "  \"isScheduled\" : \"true\"," +
                                "  \"scheduledTime\" :" + t.getDueDate().getTime() + "}" +
                                "}";
                        System.out.println(noti);
                        JsonObject convertnoti= new Gson().fromJson(noti,JsonObject.class);
                        connectNoti();
                        Call<JsonObject> setschedule= ns.setNotificationDueDate(convertnoti);
                        setschedule.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                System.out.println(response.body());
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                System.out.println(t.toString());
                            }
                        });



                    }
                };
                new TimePickerDialog(TaskDetailScreen.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };
        new DatePickerDialog(TaskDetailScreen.this, DateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void connectNoti() {
        ns = Retrofit.getClient("https://fcm.googleapis.com/").create(notificationService.class);

    }

    private void delete_dialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(TaskDetailScreen.this);
        alert.setTitle("Alert");
        alert.setMessage("Are you sure to delete this List");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                connectList();
                Call<JsonObject> deletetask = ls.deleteTaskatList(listID, t.getId());
                deletetask.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
                dialog.dismiss();
                //xử lý lại việc back màn trước
                TaskDetailScreen.this.finish();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        alert.show();
    }
}