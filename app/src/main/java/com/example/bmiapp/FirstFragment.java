package com.example.bmiapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.bmiapp.databinding.FragmentFirstBinding;

import java.text.DecimalFormat;

public class FirstFragment extends Fragment {
    private TextView txt_result_bmi;
    private TextView txt_result_cat;
    private AutoCompleteTextView txt_height;
    private AutoCompleteTextView txt_weight;
    private Button calculate_bt;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        txt_result_bmi = view.findViewById(R.id.txt_result_bmi);
        txt_result_cat = view.findViewById(R.id.txt_result_cat);
        txt_height = view.findViewById(R.id.txt_height);
        txt_weight = view.findViewById(R.id.txt_weight);
        calculate_bt = view.findViewById(R.id.btn_calculate);
        calculate_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecimalFormat df = new DecimalFormat("#.#");
                double height_in_meter = Double.parseDouble(String.valueOf(txt_height.getText()));
                double weight_in_kg = Double.parseDouble(String.valueOf(txt_weight.getText()));
                if (height_in_meter > 0 && weight_in_kg > 0) {
                    try {
                        double bmi = weight_in_kg/(Math.pow(height_in_meter,2));
                        bmi = Double.parseDouble(df.format(bmi));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}