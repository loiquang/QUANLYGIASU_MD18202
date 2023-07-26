package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.R;

public class TienTrinhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_trinh);

        TextView buoc1 = findViewById(R.id.buoc1);
        TextView buoc2 = findViewById(R.id.buoc2);
        TextView buoc3 = findViewById(R.id.buoc3);
        TextView buoc4 = findViewById(R.id.buoc4);
        EditText edtNum = findViewById(R.id.edtNumber);
        Button btnNum = findViewById(R.id.btnNumber);

        btnNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtNum = edtNum.getText().toString();
                switch (txtNum){
                    case "1":
                        buoc1.setTextColor(Color.GREEN);
                        break;
                    case "2":
                        buoc2.setTextColor(Color.GREEN);
                        break;
                    case "3":
                        buoc3.setTextColor(Color.GREEN);
                        break;
                    case "4":
                        buoc4.setTextColor(Color.GREEN);
                        break;
                }
            }
        });
    }
}