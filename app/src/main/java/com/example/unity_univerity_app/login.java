package com.example.unity_univerity_app;

import androidx.annotation.StyleableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText lemail,lpassword;
    Button l_login;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lemail = findViewById(R.id.lemail);
        lpassword = findViewById(R.id.lpassword);
        l_login = findViewById(R.id.l_login);


        l_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

//        Button button = findViewById(R.id.l_login);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LayoutInflater li = getLayoutInflater();
//                View layout = li.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.custom_toast_layout_id));
//                Toast toast = new Toast(getApplicationContext());
//                toast.setDuration(Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP,10,130);
//                toast.setView(layout);//setting the view of custom toast layout
//                toast.show();
//            }
//
//    });
}

    public void login () {

        String email = lemail.getText().toString();
        String pass = lpassword.getText().toString();

        if(email.equals("") || pass.equals("")) {
            Toast.makeText(this, "Email or Password Blank", Toast.LENGTH_SHORT).show();
        }
        else if (null!=checkUser(email,pass))
        {
            String userDb = checkUser(email,pass);

            Intent i = new Intent(login.this,data.class);
            i.putExtra("email", userDb);
            startActivity(i);

        }
        else {
            Toast.makeText(this, "error login", Toast.LENGTH_SHORT).show();

        }

}

        public String checkUser(String email, String pass) {

            SQLiteDatabase db = openOrCreateDatabase("registration", Context.MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("select email,password from student where email = ? and password = ? ", new String[]{email,pass});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                String email1 = cursor.getString(0);
                String password1 = cursor.getString(1);
                SharedPreferences.Editor sp = getSharedPreferences("email",MODE_PRIVATE).edit();
                sp.putString("email", email1);
                sp.apply();
                cursor.close();
                return email;
            }
            return null;
        }

}

