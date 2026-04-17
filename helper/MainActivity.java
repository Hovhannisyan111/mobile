package com.example.petros_sofi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editName, editAge;
    Button btnSave, btnNext;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        btnSave = findViewById(R.id.btnSave);
        btnNext = findViewById(R.id.btnNext);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String ageStr = editAge.getText().toString();

            if(name.isEmpty() || ageStr.isEmpty()){
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);

            boolean inserted = db.insertStudent(name, age);

            if(inserted){
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                editName.setText("");
                editAge.setText("");
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        btnNext.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListActivity.class));
        });
    }
}