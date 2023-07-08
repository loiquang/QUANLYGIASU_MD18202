package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.Models.MonHoc_User_Models;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_In_Models;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;

public class MonHoc_User extends RecyclerView.Adapter<MonHoc_User.ViewHolder> implements Filterable {
    private ArrayList<MonHoc_User_Models> list;
    private ArrayList<MonHoc_User_Models> listOld;
    private ArrayList<Teacher_In_Models> list2;
    private Teacher_In teacherAdapter;
    int flag = 1;

    public MonHoc_User(ArrayList<MonHoc_User_Models> list) {
        this.list = list;
        this.listOld = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgTeacher.setImageResource(list.get(position).getImage());
        holder.txtName.setText(list.get(position).getName());
        holder.txtMon.setText(list.get(position).getJob());

        holder.imgDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    holder.recyclerView.setVisibility(View.VISIBLE);
                    list2 = new ArrayList<>();
                    list2.add(new Teacher_In_Models(R.drawable.inta, "Đỗ Quang Lơi", "Bốc Vác"));
                    list2.add(new Teacher_In_Models(R.drawable.faceb, "Đỗ Quang Lơi22", "Bốc Vác"));
                    list2.add(new Teacher_In_Models(R.drawable.gg, "Đỗ Quang Lơi33", "Bốc Vác"));
                    list2.add(new Teacher_In_Models(R.drawable.inta, "Đỗ Quang Lơi44", "Bốc Vác"));
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
                    teacherAdapter = new Teacher_In(list2);
                    holder.recyclerView.setLayoutManager(linearLayoutManager);
                    holder.recyclerView.setAdapter(teacherAdapter);
                    Toast.makeText(v.getContext(), "chưa có gì", Toast.LENGTH_SHORT).show();
                    flag++;
                } else {
                    holder.recyclerView.setVisibility(View.GONE);
                    flag--;
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()) {
                    list = listOld;
                } else {
                    ArrayList<MonHoc_User_Models> listNew = new ArrayList<>();
                    for (MonHoc_User_Models item : listOld) {
                        if (item.getName().toLowerCase().contains(search.toLowerCase())) {
                            listNew.add(item);
                        }
                    }
                    list = listNew;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<MonHoc_User_Models>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtMon;
        ImageView imgTeacher, imgDown;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtMon = itemView.findViewById(R.id.txtMon);
            imgTeacher = itemView.findViewById(R.id.imgTeacher);
            imgDown = itemView.findViewById(R.id.imgDown);
            recyclerView = itemView.findViewById(R.id.recycleView);
        }
    }
}
