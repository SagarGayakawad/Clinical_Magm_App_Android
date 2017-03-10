package com.example.mahe.lab6;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
DatabaseHelper db;
    Spinner s1,s2,s3;
    EditText e1;
    Button b1,b2,b3;
    int year_x,month_x,day_x;
    static  final int DIALOG_ID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        db=new DatabaseHelper(this);
        final Calendar cal=Calendar.getInstance();
        year_x= cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        s1=(Spinner)findViewById(R.id.spinner2);
        s2=(Spinner)findViewById(R.id.spinner3);
        s3=(Spinner)findViewById(R.id.spinner4);
        b1=(Button)findViewById(R.id.datebut);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.logout);
        e1=(EditText)findViewById(R.id.date);
        loadspec();
        showDatePick();
        getApp();
        logoutApp();
    }

    public  void logoutApp()
    {
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main4Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void showDatePick()
    {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id)
    {
     if(id==DIALOG_ID)
         return new DatePickerDialog(this,dpickerListner,year_x,month_x,day_x);
     else
         return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year_x=i;
            month_x=i1+1;
            day_x=i2;
            e1.setText(year_x+"-"+month_x+"-"+day_x);
            loadTime();
        }
    };


    public  void loadspec()
    {
        List<String> spec=db.getAllSpec();
        ArrayAdapter<String> label=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spec);
        label.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(label);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadDoct();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public  void loadTime()
    {
        List<String> atime=db.getAllTime(e1.getText().toString(),s2.getSelectedItem().toString());
        boolean s1=atime.isEmpty();
        if(s1==true) {
        Toast.makeText(getApplicationContext(),"Doctor is not Free,select Other Date.",Toast.LENGTH_SHORT).show();
            e1.setText(" ");
        }
        else {
            ArrayAdapter<String> label = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atime);
            label.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s3.setAdapter(label);
        }
    }
    public  void loadDoct()
    {

               List<String> doctors=db.getAllSpecDoc(s1.getSelectedItem().toString());
               ArrayAdapter<String> label=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,doctors);
               label.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               s2.setAdapter(label);
    }

    public  void getApp()
    {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                        int month=cal.get(Calendar.MONTH);
                                int day=cal.get(Calendar.DAY_OF_MONTH);
                if(year_x>=year&&month_x>=month&&day_x>=day)
                {
                     Bundle bundle=getIntent().getExtras();
                     String Message=bundle.getString("message");
                    if(e1.getText().toString().equals(" "))
                    {

                    }
                    else
                    {
                        boolean res=db.inserApp(Message,s2.getSelectedItem().toString(),e1.getText().toString(),s3.getSelectedItem().toString());
                        if(res==false)
                            Toast.makeText(getApplicationContext(),"Appointment is not Confirmed.",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(),"Apointment is Confirmed.",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Selected Date is Should be present or future date.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
