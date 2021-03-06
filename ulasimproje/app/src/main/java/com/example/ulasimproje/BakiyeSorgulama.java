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
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BakiyeSorgulama#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BakiyeSorgulama extends Fragment {
    EditText kartno;
    Button butonum;
    TextView tv;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BakiyeSorgulama() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BakiyeSorgulama.
     */
    // TODO: Rename and change types and number of parameters
    public static BakiyeSorgulama newInstance(String param1, String param2) {
        BakiyeSorgulama fragment = new BakiyeSorgulama();
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
        View view = inflater.inflate(R.layout.fragment_bakiye_sorgulama, container, false);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        butonum=view.findViewById(R.id.sorgula);
        kartno= view.findViewById(R.id.KartNumarasi16);
        tv=view.findViewById(R.id.textView2);
        Editable numara = kartno.getText();
        butonum.setOnClickListener(new View.OnClickListener() {
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

                                        tv.setText(""+document.getData().get(numara.toString())+" TL");
                                        //  tv.setText(numara.toString());

                                    }
                                } else {
                                    Log.w(TAG, "Error getting documents.", task.getException());
                                }
                            }
                        });
            }
        });



        // Inflate the layout for this fragment
        return view; //inflater.inflate(R.layout.fragment_bakiye_sorgulama, container, false);
    }
}