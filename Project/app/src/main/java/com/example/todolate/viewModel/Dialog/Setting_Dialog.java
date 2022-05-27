package com.example.todolate.viewModel.Dialog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todolate.R;
import com.example.todolate.viewModel.Screen.ChangeInfoScreen;
import com.example.todolate.viewModel.Screen.SignInScreen;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Setting_Dialog  extends BottomSheetDialogFragment{
    private SetButtonClick setButtonClick;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.setting_dialog,container,false);
        Button signOutBtn=v.findViewById(R.id.btnSignOut);
        Button editBtn = v.findViewById(R.id.btnEdit);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonClick.LoginClick(view);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangeInfoScreen.class);
                startActivity(intent);
                dismiss();
            }
        });
                return v;
    }


    public interface SetButtonClick{
        void LoginClick(View view);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            setButtonClick = (SetButtonClick) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement inteface");
        }
    }
}

