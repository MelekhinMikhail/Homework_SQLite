package com.mirea.kt.android2023.sql_lite_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextModel, editTextNumber, editTextYear;
    private Button buttonAdd, buttonShowList;
    private TextView textViewErrors;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextModel = findViewById(R.id.editTextModel);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextYear = findViewById(R.id.editTextYear);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonShowList = findViewById(R.id.buttonShowList);
        textViewErrors = findViewById(R.id.textViewErrors);

        dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db", null, 1));

        buttonAdd.setOnClickListener(x -> {

            String model = editTextModel.getText().toString();
            String number = editTextNumber.getText().toString();
            String yearStr = editTextYear.getText().toString();

            if (model.isEmpty() || number.isEmpty() || yearStr.isEmpty()) {
                textViewErrors.setText("Все поля должны быть заполнены!");
                return;
            }

            int year = Integer.parseInt(yearStr);
            if (year > 2023) {
                textViewErrors.setText(year + " год еще не наступил :)");
                return;
            } else if (year < 1900) {
                textViewErrors.setText("Сомневаюсь, что в " + year + " году были машины :)");
                return;
            }

            if (!dbManager.save(new Car(model, number, year))) {
                textViewErrors.setText("Ошибка при сохранении машины!");
                return;
            }

            Toast.makeText(this, "Машина успешно сохранена!", Toast.LENGTH_LONG).show();

            editTextModel.setText("");
            editTextNumber.setText("");
            editTextYear.setText("");
            textViewErrors.setText("");
        });

        buttonShowList.setOnClickListener(x -> {
            startActivity(new Intent(MainActivity.this, CarListActivity.class));
        });
    }
}