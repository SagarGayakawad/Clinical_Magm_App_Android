package com.example.mahe.lab6;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 DatabaseHelper db;
    Button b1;
    TextView b2;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHelper(this);
        b1=(Button)findViewById(R.id.login);
        b2=(TextView) findViewById(R.id.textView5);
        e1=(EditText)findViewById(R.id.editText8);
        e2=(EditText)findViewById(R.id.pwd);
        signupApp();
        loginApp();
    }
    public void loginApp()
    {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db.verUser(e2.getText().toString());
                String str1=e1.getText().toString();
                String str2=e2.getText().toString();
                if(res.getCount()!=0) {
                    while (res.moveToNext()) {
                        if (res.getString(0).equals(str1) && res.getString(1).equals(str2)) {
                            Toast.makeText(getApplicationContext(), "Login succesful.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                            String message=null;
                            intent.putExtra("message",e1.getText().toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Login Unsuccesful.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Login Unsuccesful.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public  void signupApp()
    {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
