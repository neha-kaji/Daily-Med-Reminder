package com.example.dailymedreminder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medreminder.R;

public class AddMedicineActivity extends AppCompatActivity {

    private EditText nameEditText, dosageEditText, timeEditText, stockEditText, thresholdEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        nameEditText = findViewById(R.id.name_edit_text);
        dosageEditText = findViewById(R.id.dosage_edit_text);
        timeEditText = findViewById(R.id.time_edit_text);
        stockEditText = findViewById(R.id.stock_edit_text);
        thresholdEditText = findViewById(R.id.threshold_edit_text);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(v -> {
            if (validateInputs()) {
                String name = nameEditText.getText().toString();
                String dosage = dosageEditText.getText().toString();
                String time = timeEditText.getText().toString();
                int stock = Integer.parseInt(stockEditText.getText().toString());
                int threshold = Integer.parseInt(thresholdEditText.getText().toString());

                // Create a new medicine object
                Medicine newMedicine = new Medicine(
                        (int) (Math.random() * 1000), // Generate a random ID for demonstration
                        name, dosage, time, stock, threshold
                );

                // Pass the new medicine back to MainActivity using Intent
                Intent resultIntent = new Intent();
                resultIntent.putExtra("new_medicine", newMedicine);
                setResult(RESULT_OK, resultIntent);
                finish(); // Close this activity
            }
        });
    }

    private boolean validateInputs() {
        if (nameEditText.getText().toString().isEmpty() ||
                dosageEditText.getText().toString().isEmpty() ||
                timeEditText.getText().toString().isEmpty() ||
                stockEditText.getText().toString().isEmpty() ||
                thresholdEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
