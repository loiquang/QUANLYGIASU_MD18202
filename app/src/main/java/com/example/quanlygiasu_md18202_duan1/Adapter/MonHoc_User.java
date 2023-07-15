package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.content.Context;
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

import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.MonHoc_User_Models;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;

public class MonHoc_User extends RecyclerView.Adapter<MonHoc_User.ViewHolder> implements Filterable {
    private ArrayList<MonHoc_User_Models> list;
    private ArrayList<MonHoc_User_Models> listOld;
    private ArrayList<Teacher_MD> list2;
    private Teacher_In teacherAdapter;

    private Interface_list interface_list;
    private GetListFireBase getListFireBase;
    private Context context;
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
        getListFireBase = new GetListFireBase();
        holder.txtName.setText(list.get(position).getName());

        ArrayList<Teacher_MD> list1 = new ArrayList<>();
        getListFireBase.readDatabase(new Interface_list() {
            @Override
            public void onListReceived(ArrayList<Teacher_MD> list) {
                for (Teacher_MD teacher_md : list) {
                    if (teacher_md.getSubject().equals(holder.txtName.getText()))
                        list1.add(teacher_md);
                }
                holder.txtMon.setText("Số Lượng: " + list1.size());
            }
        });

        holder.imgDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list2 = new ArrayList<>();
                getListFireBase.readDatabase(new Interface_list() {
                    @Override
                    public void onListReceived(ArrayList<Teacher_MD> list) {
                        for (Teacher_MD teacher_md : list) {
                            if (teacher_md.getSubject().equals(holder.txtName.getText()))
                                list2.add(teacher_md);
                        }
                        teacherAdapter = new Teacher_In(list2);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
                        holder.recyclerView.setLayoutManager(linearLayoutManager);
                        holder.recyclerView.setAdapter(teacherAdapter);

                    }
                });


                    holder.recyclerView.setVisibility(View.VISIBLE);

                    holder.imgDown.setVisibility(View.GONE);
                    holder.imgUp.setVisibility(View.VISIBLE);



            }
        });
holder.imgUp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        holder.recyclerView.setVisibility(View.GONE);
        flag--;
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
        TextView txtName, txtMon;
        ImageView imgTeacher, imgDown, imgUp;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtMon = itemView.findViewById(R.id.txtMon);
            imgTeacher = itemView.findViewById(R.id.imgTeacher);
            imgDown = itemView.findViewById(R.id.imgDown);
            imgUp= itemView.findViewById(R.id.imgUp);
            recyclerView = itemView.findViewById(R.id.recycleView);
        }
    }
}
