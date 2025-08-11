package com.example.dailymedreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int ADD_MEDICINE_REQUEST_CODE = 1;

    private RecyclerView recyclerView;
    private MedicineAdapter adapter;
    private ArrayList<Medicine> medicineList;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextView emptyListTextView;
    private AlarmScheduler alarmScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        emptyListTextView = findViewById(R.id.empty_list_text_view);
        fab = findViewById(R.id.add_fab);

        // Initialize AlarmScheduler
        alarmScheduler = new AlarmScheduler(this);

        // Dummy data for initial display
        medicineList = new ArrayList<>();
        medicineList.add(new Medicine(1, "Aspirin", "10mg", "8:00 AM", 15, 5));
        medicineList.add(new Medicine(2, "Vitamins", "1 capsule", "12:00 PM", 3, 5));
        medicineList.add(new Medicine(3, "Insulin", "2 units", "6:00 PM", 30, 10));

        // Set alarms for the initial dummy data
        for (Medicine medicine : medicineList) {
            alarmScheduler.scheduleAlarm(medicine);
        }

        adapter = new MedicineAdapter(medicineList);
        recyclerView.setAdapter(adapter);

        updateUI();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddMedicineActivity.class);
            startActivityForResult(intent, ADD_MEDICINE_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MEDICINE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Medicine newMedicine = (Medicine) data.getSerializableExtra("new_medicine");
            if (newMedicine != null) {
                medicineList.add(newMedicine);
                adapter.notifyItemInserted(medicineList.size() - 1);
                updateUI();
                Toast.makeText(this, "Medicine added!", Toast.LENGTH_SHORT).show();
                // Schedule an alarm for the newly added medicine
                alarmScheduler.scheduleAlarm(newMedicine);
            }
        }
    }

    private void updateUI() {
        if (medicineList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyListTextView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_help) {
            Toast.makeText(this, "Help selected", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawers();
        return true;
    }
}
