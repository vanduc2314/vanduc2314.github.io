package com.example.todolate.viewModel.AdapterModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.example.todolate.R;
import com.example.todolate.model.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> implements Filterable {


    ArrayList<Task> listData;
    ArrayList<Task> listDataOld;
    private ItemClickListener itemClickListener;
    public void setData(ArrayList<Task> listData, ItemClickListener itemClickListener) {
        this.listData = listData;
        this.itemClickListener = itemClickListener;
        this.listDataOld=listData;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view, itemClickListener);
    }

    @Override
    public int getItemCount() {
        if(listData!=null){
            return listData.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = listData.get(position);
        holder.task_title.setText(task.getName());
        if (task.getStatus()) {
            holder.checkBox.setChecked(true);
        }
        else{
            holder.checkBox.setChecked(false);
        }
        if (holder.checkBox.isChecked()) {
            holder.task_title.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            holder.task_title.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
        }
        if(task.getDueDate()!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM YYYY");
            String ddate = sdf.format(task.getDueDate());
            holder.duedatetxt.setText(ddate);
            holder.duedatetxt.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search_key=charSequence.toString();
                if(search_key.isEmpty()){
                    listData=listDataOld;
                }
                else{
                    ArrayList<Task> list =new ArrayList<>();
                    for(Task t: listDataOld){
                        if(t.getName().toLowerCase().contains(search_key.toLowerCase())){
                            list.add(t);
                        }
                    }
                    listData=list;
                }
                FilterResults fr= new FilterResults();
                fr.values=listData;
                return  fr;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listData= (ArrayList<Task>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class TaskViewHolder extends RecyclerView.ViewHolder{

        private CheckBox checkBox;
        private TextView task_title, task_delete,duedatetxt;
        private SwipeLayout layout;
        private ItemClickListener itemClickListener;



        public TaskViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkboxtask);
            layout = itemView.findViewById(R.id.TaskItemLayout);
            task_title = itemView.findViewById(R.id.task_title);
            duedatetxt= itemView.findViewById(R.id.due_date_txt);
            task_delete = itemView.findViewById(R.id.delete_task);
            this.itemClickListener = itemClickListener;
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemClickListener.onTaskClick(getAdapterPosition());
                    return true;
                }
            });
            task_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Alert");
                    alert.setMessage("Are you sure to delete this Task");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            itemClickListener.onDeleteTaskClick(getAdapterPosition());
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
                }
            });
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onStatusClick(getAdapterPosition());

                }
            });
        }


    }

    public interface ItemClickListener {
        void onTaskClick(int position);

        void onDeleteTaskClick(int position);

        void onStatusClick(int position);
    }


}
