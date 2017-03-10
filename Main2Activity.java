package com.example.mahe.lab6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
DatabaseHelper db;
    Button b2;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b2=(Button)findViewById(R.id.signup1);
        e1=(EditText)findViewById(R.id.seditText);
        e2=(EditText)findViewById(R.id.editText2);
        db=new DatabaseHelper(this);
        insertUser();
    }
    public  void insertUser()
    {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=e1.getText().toString();
                String pwd=e2.getText().toString();
                if(uname.equals(" ")||pwd.equals(" "))
                {
                    Toast.makeText(getApplicationContext(),"Username or Password can not be empty.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean res=db.inserusers(uname,pwd);
                    if(res==true) {
                        Toast.makeText(getApplicationContext(), "Signup Succesfull.", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Signup not Succesfull.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
