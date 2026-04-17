package com.example.trigapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    RadioButton radioSin, radioCos;
    TextView result;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextNumber);
        radioSin = findViewById(R.id.radioSin);
        radioCos = findViewById(R.id.radioCos);
        result = findViewById(R.id.textResult);
        button = findViewById(R.id.buttonCalculate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = editText.getText().toString();

                if(value.isEmpty()){
                    result.setText("Մուտքագրեք թիվ");
                    return;
                }

                double x = Double.parseDouble(value);

                if(radioSin.isChecked()){
                    double sin = Math.sin(x);
                    result.setText("sin(x) = " + sin);
                }

                else if(radioCos.isChecked()){
                    double cos = Math.cos(x);
                    result.setText("cos(x) = " + cos);
                }

                else{
                    result.setText("Ընտրեք գործողությունը");
                }
            }
        });
    }
}
