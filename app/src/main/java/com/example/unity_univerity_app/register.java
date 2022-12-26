package com.example.unity_univerity_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText efname,elname,eemail,epassword,edepartment,esection;
    RadioButton emale,efemale;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        efname = findViewById(R.id.efname);
        elname = findViewById(R.id.elname);
        eemail = findViewById(R.id.eemail);
        epassword = findViewById(R.id.epassword);
        emale = findViewById(R.id.emale);
        efemale = findViewById(R.id.efemale);
        edepartment = findViewById(R.id.edepartment);
        esection = findViewById(R.id.esection);
        btn1 = findViewById(R.id.btn1);


        ImageView ethiopia = findViewById(R.id.back);

        ethiopia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), home.class);
                startActivity(i);
            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();

            }
        });





    }


    public void insert() {

        try {
            String fname = efname.getText().toString();
            String lname = elname.getText().toString();
            String email = eemail.getText().toString();
            String password = epassword.getText().toString();
            String male = emale.getText().toString();
            String female = efemale.getText().toString();
            String department = edepartment.getText().toString();
            String section = esection.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("registration", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS student(email VARCHAR PRIMARY KEY, fname VARCHAR, lname VARCHAR, password VARCHAR, male VARCHAR, female VARCHAR, department VARCHAR, section VARCHAR)");

            String sql = "insert into student(email,fname,lname,password,male,female,department,section)values(?,?,?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,email);
            statement.bindString(2,fname);
            statement.bindString(3,lname);
            statement.bindString(4,password);
            statement.bindString(5,male);
            statement.bindString(6,female);
            statement.bindString(7,department);
            statement.bindString(8,section);
            statement.execute();

            Toast.makeText(this, "Record Added", Toast.LENGTH_SHORT).show();

            eemail.setText("");
            efname.setText("");
            elname.setText("");
            epassword.setText("");
            emale.setText("");
            efemale.setText("");
            edepartment.setText("");
            esection.setText("");
            efname.requestFocus();




        }
        catch (Exception ex) {

        }



    }
}