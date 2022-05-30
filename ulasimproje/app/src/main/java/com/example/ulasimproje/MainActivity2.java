package com.example.ulasimproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView btm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentC, new Otobusler()).commit();

        btm = findViewById(R.id.bottom);
        btm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.Otobusler:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentC, new Otobusler()).commit();
                        break;

                    case R.id.BakiyeSorgulama:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentC, new BakiyeSorgulama()).commit();
                        break;


                }

                return true;
            }
        });

    }
}