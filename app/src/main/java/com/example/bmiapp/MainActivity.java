package com.example.bmiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    TextView txt_result_bmi;
    TextView txt_result_cat;
    AutoCompleteTextView txt_height;
    AutoCompleteTextView txt_weight;
    Button calculate_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_result_bmi = findViewById(R.id.txt_result_bmi);
        txt_result_cat = findViewById(R.id.txt_result_cat);
        txt_height = findViewById(R.id.txt_height);
        txt_weight = findViewById(R.id.txt_weight);
        calculate_bt = findViewById(R.id.btn_calculate);
        calculate_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double height_in_meter = Double.parseDouble(String.valueOf(txt_height.getText()));
                double weight_in_kg = Double.parseDouble(String.valueOf(txt_weight.getText()));
                if (height_in_meter > 0 && weight_in_kg > 0) {
                    try {
                        double bmi = weight_in_kg/(Math.pow(height_in_meter,2));
                        if (bmi<18.5) {
                            txt_result_cat.setText("Underweight: you will have to gain more weight until you reach healthy levels");
                            txt_result_bmi.setText("BMI: " + bmi);
                        }
                        else if (bmi>=18.5 && bmi<=24.9){
                            txt_result_cat.setText("Healthy: Work to maintain this weight as you will significantly reduce your risks of developing health complications");
                            txt_result_bmi.setText("BMI: " + bmi);
                        }
                        else if (bmi>=25 && bmi<=29.9){
                            txt_result_cat.setText("Overweight: you will need to lose weight or you will be more susceptible to severe health complications");
                            txt_result_bmi.setText("BMI: " + bmi);
                        }
                        else if (bmi>=30){
                            txt_result_cat.setText("Obese: you will need to lose weight or you will be more susceptible to severe health complications");
                            txt_result_bmi.setText("BMI: " + bmi);
                        }
                    }catch (Exception e)
                    {
                        txt_result_bmi.setText("error:"+(e.getMessage().toString()));
                    }

                }
            }
        });


    }

}