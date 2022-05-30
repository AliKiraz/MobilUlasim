package com.example.ulasimproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;


public class Login extends AppCompatActivity {

    // Tanımlamalar

    // Firebase mAuth nesnemizi oluşturma
    private FirebaseAuth mAuth;

    private EditText email, sifre;
    private Button girisYap, uyeOl;

    private TextView sifremiUnuttum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // mAuth nesnemizi tanımlama
        mAuth = FirebaseAuth.getInstance();

        // Alanların bağlanması
        email = findViewById(R.id.email);
        sifre = findViewById(R.id.sifre);

        sifremiUnuttum = findViewById(R.id.sifremiUnuttum);

        // Butonların bağlanması
        girisYap = findViewById(R.id.girisYap);
        uyeOl = findViewById(R.id.uyeOl);

        sifremiUnuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty()) {

                    Toast.makeText(Login.this, "Bir Mail Adresi Giriniz!", Toast.LENGTH_SHORT).show();

                } else {

                    mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(Login.this, "Sıfırlama Maili Gönderildi!", Toast.LENGTH_SHORT).show();

                            } else {

                                Log.e("hata", task.getException().toString());

                            }

                        }
                    });

                }

            }
        });

        girisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mailKontrol(true, email.getText().toString(), sifre.getText().toString());

                // İlk parametremizde giriş işlemi olduğunu belirten bir kontrolcü kullanıyoruz
                // İkinci parametremiz mail adresi
                // Üçüncü parametremiz şifre

            }
        });

        uyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mailKontrol(false, email.getText().toString(), sifre.getText().toString());
                // İlk parametremizde üyelik işlemi olduğunu belirten bir kontrolcü kullanıyoruz
                // İkinci parametremiz mail adresi
                // Üçüncü parametremiz şifre

            }
        });

    }

    // E-mail adresi kontrolü
    private void mailKontrol(final boolean girisYap, final String mail, final String sifre) {

        mAuth.fetchSignInMethodsForEmail(mail).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                if (task.isSuccessful()) {

                    if (task.getResult() != null && task.getResult().getSignInMethods() != null) {

                        boolean email_var;

                        if (task.getResult().getSignInMethods().isEmpty()) {

                            //Email bulunamadi
                            Log.e("hata", "Email Bulunamadi");

                            email_var = false;

                        } else {

                            Log.e("hata", "Email Bulundu");

                            email_var = true;

                        }


                        // İşlemin türüne göre giriş veya üyelik yaptımak
                        if (girisYap) { // Giriş işlemleri için

                            // Email adresi varsa girişe devam eder
                            if (email_var) {

                                // Mail adresi üyeyse girişe gidilir
                                girisYaptir(mail, sifre);

                            } else {

                                Toast.makeText(Login.this, "Mail Adresi Bulunamadi!", Toast.LENGTH_SHORT).show();

                            }


                        } else { // Üyelik İşlemleri için

                            if (email_var) {

                                Toast.makeText(Login.this, "Bu Maile Kayıtlı Bir Üyelik Zaten Bulunmakta!", Toast.LENGTH_LONG).show();

                            } else {

                                // Email adresi yoksa üyeliğe devam eder
                                uyelikYaptir(mail, sifre);

                            }

                        }

                    }

                }

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                if (e.getMessage() != null && e.getMessage().equals("A network error (such as timeout, interrupted connection or unreachable host) has occurred.")) {

                    Log.e("hata", "Bağlantı sağlanamadı internet bağlantınızı kontrol ediniz!");

                }

            }

        });

    }

    private void girisYaptir(String mail, String sifre) {

        mAuth.signInWithEmailAndPassword(mail, sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() && task.getResult() != null) {

                    if (task.getResult().getUser() != null && task.getResult().getUser().isEmailVerified()) {

                        Toast.makeText(Login.this, "Giriş Başarılı!", Toast.LENGTH_LONG).show();

                        girisSonrasiIslemler();

                    } else {

                        // Aktivasyon yapılmadığı için linki tekrar yollama
                        if (task.getResult().getUser() != null)
                            task.getResult().getUser().sendEmailVerification();

                    }

                }

            }
        });

    }

    private void uyelikYaptir(String mail, String sifre) {

        mAuth.createUserWithEmailAndPassword(mail, sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() && task.getResult() != null) {

                    // Aktivasyon linki yollama
                    if (task.getResult().getUser() != null)
                        task.getResult().getUser().sendEmailVerification();

                    Toast.makeText(Login.this, "Üyelik Başarılı! Aktivasyon Linki Mailinize Gönderildi!", Toast.LENGTH_LONG).show();

                    // Buraya yoruma almamın sebebi aktivasyon sonra giriş işlemi yapılmasını isteyebileceğiniz içindir
                    //girisSonrasiIslemler();

                }

            }
        });

    }

    private void girisSonrasiIslemler() {

        // Giriş sonrası işlemleriniz burada yaptıracaksınız
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intent);

    }

}