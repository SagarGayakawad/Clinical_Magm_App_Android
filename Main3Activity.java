package com.example.mahe.lab6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
EditText e1,e2,e3;
    Button b1,b2;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText4);
        e3=(EditText)findViewById(R.id.editText5);
        b1=(Button)findViewById(R.id.add);
        b2=(Button)findViewById(R.id.add1);
        insrtDoctor();
        insrtTime();
    }

    public  void insrtDoctor()
    {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res=db.insertDoctor(e1.getText().toString(),e2.getText().toString());
                if(res==true)
                    Toast.makeText(getApplicationContext(),"Data is Inserted.",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Data is not Inserted.",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void insrtTime()
    {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res=db.insertTime(e3.getText().toString());
                if(res==true)
                    Toast.makeText(getApplicationContext(),"Data is Inserted.",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Data is not Inserted.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
