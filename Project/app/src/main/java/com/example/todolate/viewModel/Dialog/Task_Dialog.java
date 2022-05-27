package com.example.todolate.viewModel.Dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todolate.R;
import com.example.todolate.model.Task;
import com.example.todolate.viewModel.Screen.ListScreen;
import com.example.todolate.viewModel.Screen.SignInScreen;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task_Dialog extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    private Task t;
    private LinearLayout taskbtnlayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.add_task_dialog,container,false);
        EditText e =v.findViewById(R.id.txtTaskName);
        EditText rdate=v.findViewById(R.id.txt_remind);
        Button cancel= v.findViewById(R.id.btnCancel);
        ImageButton reminderDate= v.findViewById(R.id.remind_date);
        Button add= v.findViewById(R.id.btnAddTask);
        e.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        t= new Task();
        reminderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(rdate);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setName(e.getText().toString());
                t.setStatus(false);
                t.setCreatedDate(new Date());
                mListener.onButtonClicked(t);
                e.setText("");
                rdate.setText("");
                rdate.setVisibility(View.GONE);
                dismiss();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return v;
    }


    private void showDateTimeDialog(EditText rdate) {
        final Calendar calendar= Calendar.getInstance();
        DatePickerDialog.OnDateSetListener DateSetListener= new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR,i);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.DAY_OF_MONTH,i2);
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(Calendar.HOUR_OF_DAY,i);
                        calendar.set(Calendar.MINUTE,i1);
                        System.out.println(calendar.getTime()+"");
                        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm dd:MM:yyyy");
                        rdate.setVisibility(View.VISIBLE);
                        rdate.setText("Reminder: "+sdf.format(calendar.getTime()));
                        //set due
                        t.setDueDate(calendar.getTime());
                    }
                };
                new TimePickerDialog(Task_Dialog.this.getContext(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(this.getContext(),DateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public interface BottomSheetListener {
        void onButtonClicked(Task t);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}
