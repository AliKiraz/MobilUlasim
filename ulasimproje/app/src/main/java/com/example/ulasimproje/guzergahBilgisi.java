package com.example.ulasimproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class guzergahBilgisi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guzergah_bilgisi);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragGuzergah, new GuzergahCay()).commit();

        if (Otobusler.buton1==true) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragGuzergah, new GuzergahCay()).commit();
        }
        else if (Otobusler.buton2==true) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragGuzergah, new GuzergahSoku()).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toptop_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent gerii = new Intent(getApplicationContext(), otobusSaatleri.class);
        startActivity(gerii);
        return super.onOptionsItemSelected(item);
    }
}