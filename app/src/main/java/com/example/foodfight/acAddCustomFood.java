package com.example.foodfight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;

/* This activity is part of the stretch goal were custom
 * foods are added to the Food table
 *
 */
public class acAddCustomFood extends AppCompatActivity {
    private static final String COMMA_DELIMITER = ",";
    DatabaseHandler db;
    EditText editName, editCalories, editManufacturer;
    TextView name, calories, manufacturer;
    Button btnAddData;
    private File line;

    public acAddCustomFood() throws FileNotFoundException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_food);
        db = new DatabaseHandler(this);

        //cast our items on our page
        //https://www.youtube.com/watch?v=T0ClYrJukPA&ab_channel=ProgrammingKnowledge
        editName = (EditText) findViewById(R.id.editName);
        editCalories = (EditText) findViewById(R.id.editCalories);
        editManufacturer = (EditText) findViewById(R.id.editManufacturer);
        btnAddData = (Button) findViewById(R.id.btnAddData);
        addData();
    }

    public void addData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cals = editCalories.getText().toString();
                        int calConv = Integer.parseInt(cals);
                        db.insertData(100, editName.getText().toString(),
                                calConv,
                                editManufacturer.getText().toString());
                        Toast.makeText(acAddCustomFood.this,"Data Inserted", Toast.LENGTH_LONG).show();

                    }

                }

        );
        this.finish();
    }
}
