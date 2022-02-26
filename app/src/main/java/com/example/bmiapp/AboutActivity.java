package com.example.bmiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.bmiapp.databinding.ActivityBmiBinding;

import java.text.DecimalFormat;


public class AboutActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityBmiBinding binding;
Button bt;
TextView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        binding = ActivityBmiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);

        about = findViewById(R.id.txt_about_us);
        about.setText("This Software is Designed and Distributed under licence");
        bt = findViewById(R.id.about_back_bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bmi = new Intent(AboutActivity.this, BmiActivity.class);
                startActivity(bmi);
                finish();
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return true;
    }

}