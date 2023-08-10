package com.example.quanlygiasu_md18202_duan1.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.MonHoc_User_Models;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MonHoc_User extends RecyclerView.Adapter<MonHoc_User.ViewHolder> implements Filterable {
    private ArrayList<MonHoc_User_Models> list;
    private ArrayList<MonHoc_User_Models> listOld;
    private ArrayList<Teacher_MD> list2;
    private Teacher_In teacherAdapter;

    private GetListFireBase getListFireBase;
    private Context context;
    int flag = 1;

    public MonHoc_User(ArrayList<MonHoc_User_Models> list, Context context) {
        this.list = list;
        this.listOld = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        getListFireBase = new GetListFireBase();
        holder.imgTeacher.setImageResource(list.get(position).getImage());
        holder.txtName.setText(list.get(position).getName());
        SharedPreferences sharedPreferences = context.getSharedPreferences("isRememberData", MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");
        ArrayList<Teacher_MD> list1 = new ArrayList<>();
        FirebaseDatabase auth = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1 = auth.getReference("teacher");
        getListFireBase.readDatabase(databaseReference1, new Interface_list() {
            @Override
            public void onListReceived(ArrayList<Teacher_MD> list) {

                for (Teacher_MD teacher_md : list) {
                    if (teacher_md.getTeacher_md().getSubject().equals(holder.txtName.getText()) && teacher_md.getTeacher_md().getStatus().equals("Hoạt động"))
                        list1.add(teacher_md);
                    else if (teacher_md.getTeacher_md().getSubject().equals(holder.txtName.getText()) && user.equals("admin")) {
                        list1.add(teacher_md);
                    }
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context.getApplicationContext());
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(holder.recyclerView.getContext(), linearLayoutManager.getOrientation());
                holder.recyclerView.addItemDecoration(dividerItemDecoration);
                teacherAdapter = new Teacher_In(list1, context);

                holder.recyclerView.setLayoutManager(linearLayoutManager);
                holder.recyclerView.setAdapter(teacherAdapter);
                holder.txtMon.setText("Số Lượng: " + list1.size());
            }

            @Override
            public void onListReceived1(ArrayList<User> list) {
            }

            @Override
            public void onListReceived2(ArrayList<ReQuestGS> list) {
            }

        });

        holder.imgDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.imgDown.setVisibility(View.GONE);
                holder.imgUp.setVisibility(View.VISIBLE);
            }
        });
        holder.imgUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.recyclerView.setVisibility(View.GONE);
                holder.imgDown.setVisibility(View.VISIBLE);
                holder.imgUp.setVisibility(View.GONE);
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
        TextView txtName, txtMon, txtNameUser;
        ImageView imgTeacher, imgDown, imgUp;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtMon = itemView.findViewById(R.id.txtMon);
            imgTeacher = itemView.findViewById(R.id.imgTeacher);
            imgDown = itemView.findViewById(R.id.imgDown);
            imgUp = itemView.findViewById(R.id.imgUp);
            recyclerView = itemView.findViewById(R.id.recycleView);


        }
    }
}
