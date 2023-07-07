package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.Models.teacher;
import com.example.quanlygiasu_md18202_duan1.Models.teacher2;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<teacher> list;
    private ArrayList<teacher2> list2;
    private TeacherAdapter teacherAdapter;
    int flag = 1;

    public UserAdapter(ArrayList<teacher> list) {
        this.list = list;
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
                    list2.add(new teacher2(R.drawable.inta, "Đỗ Quang Lơi", "Bốc Vác"));
                    list2.add(new teacher2(R.drawable.faceb, "Đỗ Quang Lơi22", "Bốc Vác"));
                    list2.add(new teacher2(R.drawable.gg, "Đỗ Quang Lơi33", "Bốc Vác"));
                    list2.add(new teacher2(R.drawable.inta, "Đỗ Quang Lơi44", "Bốc Vác"));
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
                    teacherAdapter = new TeacherAdapter(list2);
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
        return list.size();
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
