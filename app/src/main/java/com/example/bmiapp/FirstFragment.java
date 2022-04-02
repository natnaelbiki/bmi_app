package com.example.bmiapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                try {
                    if (txt_height.getText().toString().isEmpty()){
                        txt_height.setBackgroundColor(Color.red(0));
                        Toast.makeText(getActivity(), "Please fill height", Toast.LENGTH_SHORT).show();
                    }
                    else if(txt_weight.getText().toString().isEmpty()) {
                        txt_weight.setBackgroundColor(Color.red(0));
                        Toast.makeText(getActivity(), "Please fill weight", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                try {


                    double height_in_meter = Double.parseDouble(String.valueOf(txt_height.getText()));
                    double weight_in_kg = Double.parseDouble(String.valueOf(txt_weight.getText()));
                    if (height_in_meter > 0 && height_in_meter < 2.5) {
                        if (weight_in_kg > 0 && weight_in_kg < 200){
                            try {
                                double bmi = getBmi(weight_in_kg, height_in_meter);
                                bmi = Double.parseDouble(df.format(bmi));
                                txt_result_bmi.setText("BMI: "+bmi);
                                txt_result_cat.setText(getCategory(bmi));
                            }catch (Exception e)
                            {
                                Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(weight_in_kg<0 || weight_in_kg>200){
                            Toast.makeText(getActivity(), "Wrong Weight Input", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else if(height_in_meter < 0 || height_in_meter > 2.5){
                        Toast.makeText(getActivity(), "Wrong Height Input", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Ops..Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public double getBmi(double w, double h){
        return w/(Math.pow(h,2));
    }
    public String getCategory(double bmi){
        String s="";
        if (bmi<18.5) {
           s = ("Underweight: you will have to gain more weight until you reach healthy levels");
        }
        else if (bmi>=18.5 && bmi<=24.9){
            s = ("Healthy: Work to maintain this weight as you will significantly reduce your risks of developing health complications");
        }
        else if (bmi>=25 && bmi<=29.9){
            s = ("Overweight: you will need to lose weight or you will be more susceptible to severe health complications");
        }
        else if (bmi>=30){
            s = ("Obese: you will need to lose weight or you will be more susceptible to severe health complications");
        }
        else{
            s = "Ops..Something went wrong";
        }
        return s;
    }

}