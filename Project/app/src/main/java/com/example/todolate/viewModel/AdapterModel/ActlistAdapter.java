package com.example.todolate.viewModel.AdapterModel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolate.R;
import com.example.todolate.model.ActList;
import com.example.todolate.viewModel.Screen.Home;
import com.example.todolate.viewModel.Screen.ListScreen;

import java.util.ArrayList;

public class ActlistAdapter extends RecyclerView.Adapter<ActlistAdapter.ActlistViewHolder> {

        private ArrayList<ActList> listData;
        private AppCompatActivity activity;
        private ListApdaterClick listApdaterClick;


    public void setData(ArrayList<ActList> listData,ListApdaterClick listApdaterClick) {
        this.listData = listData;
        this.listApdaterClick= listApdaterClick;
        notifyDataSetChanged();
    }

    public void setContext(AppCompatActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ActlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actlist_item, parent, false);
        return new ActlistViewHolder(view,listApdaterClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ActlistViewHolder holder, int position) {
        ActList al = listData.get(position);
        holder.txtListName.setText(al.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ListScreen.class);
                intent.putExtra("actlist",al);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listData != null) {
            return listData.size();
        }
        return 0;
    }

    public class ActlistViewHolder extends RecyclerView.ViewHolder{

            private TextView txtListName;
            private CardView cardView;
            public ActlistViewHolder(@NonNull View itemView, ListApdaterClick listApdaterClick) {
                super(itemView);
                txtListName = itemView.findViewById(R.id.txtListItemName);
                cardView = itemView.findViewById(R.id.listItemCardView);
                cardView.setBackgroundResource(android.R.color.transparent);

                cardView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(
                                activity);
                        alert.setTitle("Alert");
                        alert.setMessage("Are you sure to delete this List");
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listApdaterClick.deleteList(getAdapterPosition());
                                dialog.dismiss();

                            }
                        });
                        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();
                            }
                        });

                        alert.show();
                        return false;
                    }
                });

            }
        }
        public interface ListApdaterClick{
            void deleteList(int position);
        }

}



