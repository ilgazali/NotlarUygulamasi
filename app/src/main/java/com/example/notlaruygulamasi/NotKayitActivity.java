package com.example.notlaruygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class NotKayitActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextDersAdi, editTextNot1, editTextNot2;
    private Button buttonKaydet;

    private VeriTabani veriTabani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_kayit);

        toolbar = findViewById(R.id.toolbar);
        editTextDersAdi = findViewById(R.id.editTextDersAdi);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);
        buttonKaydet = findViewById(R.id.buttonKaydet);

        veriTabani = new VeriTabani(getApplicationContext());

        toolbar.setTitle("Not Kaydet");
        setSupportActionBar(toolbar);

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String ders = editTextDersAdi.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();


                if (TextUtils.isEmpty(ders)){
                    Snackbar.make(view,"Ders adı giriniz",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(view,"1. Notu giriniz",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(view,"2. Notu giriniz",Snackbar.LENGTH_LONG).show();
                    return;
                }



                new NotlarDao().notEkle(veriTabani,ders,Integer.parseInt(not1),Integer.parseInt(not2));


                startActivity(new Intent(NotKayitActivity.this,MainActivity.class));
                finish();
            }
        });

    }
}