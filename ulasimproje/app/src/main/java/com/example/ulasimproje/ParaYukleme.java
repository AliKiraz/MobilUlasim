package com.example.ulasimproje;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParaYukleme#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParaYukleme extends Fragment {
    Button btn;
    EditText oKart,kKart,tutar;
    Editable oKartE,kKartE,tutarE;
    int suankitutar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ParaYukleme() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ParaYukleme.
     */
    // TODO: Rename and change types and number of parameters
    public static ParaYukleme newInstance(String param1, String param2) {
        ParaYukleme fragment = new ParaYukleme();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vieww = inflater.inflate(R.layout.fragment_para_yukleme, container, false);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_para_yukleme, container, false);

        btn=vieww.findViewById(R.id.yuklebtn);
        oKart=vieww.findViewById(R.id.KartNumarasi16);
        kKart=vieww.findViewById(R.id.KrediKarti);
        tutar=vieww.findViewById(R.id.Miktar);
        oKartE=oKart.getText();
        kKartE=kKart.getText();
        tutarE=tutar.getText();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("kartbilgileri")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        //  Log.d(TAG, document.getId() + " => " + document.getData());
                                        //tv.setText(""+document.getData().get(numara.toString())+" TL");
                                        //  tv.setText(numara.toString());
                                        suankitutar=Integer.parseInt(document.getData().get(oKartE.toString()).toString());
                                        int eklenecektutar=Integer.parseInt(tutarE.toString());
                                        suankitutar=eklenecektutar+suankitutar;


                                    }
                                } else {
                                    Log.w(TAG, "Error getting documents.", task.getException());
                                }
                            }
                        });



                Map<String, Object> user = new HashMap<>();
                user.put(oKartE.toString(), suankitutar);

                // Add a new document with a generated ID
                db.collection("kartbilgileri")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });







            }
        });










        return vieww;
    }
}