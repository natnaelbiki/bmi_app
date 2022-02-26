package com.example.bmiapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bmiapp.databinding.ActivityAboutUsBinding;

public class AboutUsActivity extends AppCompatActivity {

    private ActivityAboutUsBinding binding;
    private TextView about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        about = findViewById(R.id.txt_about_us);
        about.setText("This Software is Designed and Distributed under licence");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return true;
    }
    public void onBackClick(MenuItem item) {
        Intent about = new Intent(AboutUsActivity.this,BmiActivity.class);
        startActivity(about);
        finish();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent about = new Intent(AboutUsActivity.this,BmiActivity.class);
        startActivity(about);
        finish();
    }
}