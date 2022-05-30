package com.example.ulasimproje;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class otobusSaatleri extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tv,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
    TextView gv,gv1,gv2,gv3,gv4,gv5,gv6,gv7,gv8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otobus_saatleri);
        tv=findViewById(R.id.textView20);
        tv1=findViewById(R.id.textView21);
        tv2=findViewById(R.id.textView22);
        tv3=findViewById(R.id.textView23);
        tv4=findViewById(R.id.textView24);
        tv5=findViewById(R.id.textView25);
        tv6=findViewById(R.id.textView26);
        tv7=findViewById(R.id.textView27);
        tv8=findViewById(R.id.textView28);
        gv=findViewById(R.id.textView30);
        gv1=findViewById(R.id.textView31);
        gv2=findViewById(R.id.textView32);
        gv3=findViewById(R.id.textView33);
        gv4=findViewById(R.id.textView34);
        gv5=findViewById(R.id.textView35);
        gv6=findViewById(R.id.textView36);
        gv7=findViewById(R.id.textView37);
        gv8=findViewById(R.id.textView38);

        if(Otobusler.buton1==true){

            db.collection("Busses")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    //  Log.d(TAG, document.getId() + " => " + document.getData());
                                    tv.setText(""+document.getData().get("gidis1"));
                                    tv1.setText(""+document.getData().get("gidis2"));
                                    tv2.setText(""+document.getData().get("gidis3"));
                                    tv3.setText(""+document.getData().get("gidis4"));
                                    tv4.setText(""+document.getData().get("gidis5"));
                                    tv5.setText(""+document.getData().get("gidis6"));
                                    tv6.setText(""+document.getData().get("gidis7"));
                                    tv7.setText(""+document.getData().get("gidis8"));
                                    tv8.setText(""+document.getData().get("gidis9"));
                                    gv.setText(""+document.getData().get("gelis1"));
                                    gv1.setText(""+document.getData().get("gelis2"));
                                    gv2.setText(""+document.getData().get("gelis3"));
                                    gv3.setText(""+document.getData().get("gelis4"));
                                    gv4.setText(""+document.getData().get("gelis5"));
                                    gv5.setText(""+document.getData().get("gelis6"));
                                    gv6.setText(""+document.getData().get("gelis7"));
                                    gv7.setText(""+document.getData().get("gelis8"));
                                    gv8.setText(""+document.getData().get("gelis9"));
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        if(Otobusler.buton2=true){

            db.collection("Busses2")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    //  Log.d(TAG, document.getId() + " => " + document.getData());
                                    tv.setText(""+document.getData().get("gidis1"));
                                    tv1.setText(""+document.getData().get("gidis2"));
                                    tv2.setText(""+document.getData().get("gidis3"));
                                    tv3.setText(""+document.getData().get("gidis4"));
                                    tv4.setText(""+document.getData().get("gidis5"));
                                    tv5.setText(""+document.getData().get("gidis6"));
                                    tv6.setText(""+document.getData().get("gidis7"));
                                    tv7.setText(""+document.getData().get("gidis8"));
                                    tv8.setText(""+document.getData().get("gidis9"));
                                    gv.setText(""+document.getData().get("gelis1"));
                                    gv1.setText(""+document.getData().get("gelis2"));
                                    gv2.setText(""+document.getData().get("gelis3"));
                                    gv3.setText(""+document.getData().get("gelis4"));
                                    gv4.setText(""+document.getData().get("gelis5"));
                                    gv5.setText(""+document.getData().get("gelis6"));
                                    gv6.setText(""+document.getData().get("gelis7"));
                                    gv7.setText(""+document.getData().get("gelis8"));
                                    gv8.setText(""+document.getData().get("gelis9"));
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.guzergahbilgisi:
                Intent guzergahBilgisi = new Intent(getApplicationContext(), guzergahBilgisi.class);
                startActivity(guzergahBilgisi);
                break;
            case R.id.geri:
                Intent geri = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(geri);
                Otobusler.buton1=false;
                Otobusler.buton2=false;
                break;
        }

        return super.onOptionsItemSelected(item);

    }

}