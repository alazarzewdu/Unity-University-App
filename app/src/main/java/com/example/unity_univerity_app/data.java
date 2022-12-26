package com.example.unity_univerity_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class data extends AppCompatActivity {
    
    TextView email;
    

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        
        email = findViewById(R.id.users);
        
        
        String users = getSharedPreferences("email",MODE_PRIVATE).getString("email", "");
        email.setText(users);
    }
}