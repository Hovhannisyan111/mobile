package com.example.petros_sofi;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper db;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ArrayList<Integer> ids;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        btnBack = findViewById(R.id.btnBack);
        db = new DatabaseHelper(this);

        // Go Back button listener
        btnBack.setOnClickListener(v -> finish());

        loadData();
    }

    private void loadData() {
        list = new ArrayList<>();
        ids = new ArrayList<>();

        Cursor cursor = db.getAllStudents();

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);

            list.add(name + " (" + age + ")");
            ids.add(id);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> showOptionsDialog(position));
    }

    private void showOptionsDialog(int position){
        int studentId = ids.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Action");

        builder.setPositiveButton("Edit", (dialog, which) -> showEditDialog(studentId));

        builder.setNegativeButton("Delete", (dialog, which) -> {
            db.deleteStudent(studentId);
            loadData();
        });

        builder.show();
    }

    private void showEditDialog(int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);

        EditText name = view.findViewById(R.id.editName);
        EditText age = view.findViewById(R.id.editAge);

        builder.setView(view);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String newName = name.getText().toString();
            int newAge = Integer.parseInt(age.getText().toString());

            db.updateStudent(id, newName, newAge);
            loadData();
        });

        builder.setNegativeButton("Cancel", null);

        builder.show();
    }
}