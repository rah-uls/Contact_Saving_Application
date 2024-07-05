package com.example.to_do_list;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.student.counselling.Utilities.Selectitemclick;
import java.util.ArrayList;
public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    RecycleAdapter adapter;
    FloatingActionButton floatingActionButton;
    ArrayList<Structure_List> arrayList=new ArrayList();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView =findViewById(R.id.recyle);
        floatingActionButton=findViewById(R.id.addbtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList.add(new Structure_List(R.drawable.a,"NAME-","NUMBER- "));
        adapter=new RecycleAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(MainActivity2.this);
                dialog.setContentView(R.layout.update_lay);
                Button button;
                EditText editText1,editText2;
                editText1=dialog.findViewById(R.id.ediname);
                editText2=dialog.findViewById(R.id.edinumber);
                button=dialog.findViewById(R.id.btn);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="";
                        String number="";
                        if (editText1.getText().toString().equals("")){
//                            Toast.makeText(MainActivity2.this, "Please Fill The Name- ", Toast.LENGTH_SHORT).show();
                            editText1.setError("please fill");
                            editText1.requestFocus();
                            return;
                        }
                        else{
                            name=editText1.getText().toString();

                        }
                        if (editText2.getText().toString().equals("")){
//                            Toast.makeText(MainActivity2.this, "Please Fill The Number- ", Toast.LENGTH_SHORT).show();
                            editText2.setError("please fill");
                            editText2.requestFocus();
                            return;
                        }
                        else {
                            number=editText2.getText().toString();

                        }
                        arrayList.add(new Structure_List(name,number));
                        adapter.notifyItemInserted(arrayList.size()-1);
                        recyclerView.scrollToPosition(arrayList.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        recyclerView.addOnItemTouchListener(new Selectitemclick(this, recyclerView, new Selectitemclick.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }
}