package com.example.notlaruygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class DetayActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextDersAdi, editTextNot1, editTextNot2;
    private VeriTabani veriTabani;
    private Notlar not;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        toolbar = findViewById(R.id.toolbar);
        editTextDersAdi = findViewById(R.id.editTextDersAdi);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);


        veriTabani = new VeriTabani(this);

        toolbar.setTitle("Not Detayları");

        setSupportActionBar(toolbar);

        not = (Notlar) getIntent().getSerializableExtra("notlar");

        editTextDersAdi.setText(not.getDers_adi());
        editTextNot1.setText(String.valueOf(not.getNot1()));
        editTextNot2.setText(String.valueOf(not.getNot2()));

        toolbar.setSubtitle("Ders ortalaması : "+(not.getNot1()+not.getNot2())/2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.not_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       switch(item.getItemId()){
           case R.id.action_sil:


               AlertDialog.Builder alert = new AlertDialog.Builder(DetayActivity.this);
               alert.setTitle("Uyarı");
               alert.setMessage("Silinsin mi?");
               alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       new NotlarDao().notSil(veriTabani,not.getNot_id());

                       startActivity(new Intent(DetayActivity.this,MainActivity.class));
                       finish();
                   }
               });
               alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });
               alert.create().show();
             /*  Snackbar.make(toolbar,"Silinsin mi?", Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {


                   }
               }).show();*/

               return true;

           case R.id.action_duzenle:

                 String ders = editTextDersAdi.getText().toString().trim();
                 String not1 = editTextNot1.getText().toString().trim();
                 String not2 = editTextNot2.getText().toString().trim();

               if (TextUtils.isEmpty(ders)){
                   Snackbar.make(toolbar,"Ders adı giriniz",Snackbar.LENGTH_LONG).show();
                   return false;
               }
               if (TextUtils.isEmpty(not1)){
                   Snackbar.make(toolbar,"1. Notu giriniz",Snackbar.LENGTH_LONG).show();
                   return false;
               }
               if (TextUtils.isEmpty(not2)){
                   Snackbar.make(toolbar,"2. Notu giriniz",Snackbar.LENGTH_LONG).show();
                   return false;
               }


               new NotlarDao().notGuncelle(veriTabani,not.getNot_id(),ders,Integer.parseInt(not1),Integer.parseInt(not2));

               startActivity(new Intent(DetayActivity.this,MainActivity.class));
               finish();
               return true;
           default:
               return false;
       }
    }
}