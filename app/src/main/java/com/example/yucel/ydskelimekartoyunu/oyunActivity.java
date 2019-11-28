package com.example.yucel.ydskelimekartoyunu;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class oyunActivity extends AppCompatActivity {
    private Button cevaplaButton,kelimeButon,digerSoruButton;
    private TextView textView2,textView3,textView4,puan,kullaniciAdiText,toplamSoruSayisi;
    private EditText editText;
    private int sayac=0,i=0,puanSayac=0;
    String turkceKelime="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun);

        cevaplaButton= (Button) findViewById(R.id.cevaplaButton);
        textView2=(TextView) findViewById(R.id.textView2);
      //  textView3=(TextView)findViewById(R.id.textView3);
      //  textView4=(TextView)findViewById(R.id.textView4);
        editText= (EditText) findViewById(R.id.editText2);
        kelimeButon= (Button) findViewById(R.id.kelimeButton);
        digerSoruButton= (Button) findViewById(R.id.digerSoruButton);
        puan= (TextView) findViewById(R.id.puan);
        kullaniciAdiText= (TextView) findViewById(R.id.kulllaniciAdiText);
        toplamSoruSayisi= (TextView) findViewById(R.id.toplamSoruSayisi);

        Bundle gelenKullanici=getIntent().getExtras();
        String gelenKullaniciAdi=gelenKullanici.getString("ycl2");
        kullaniciAdiText.setText(gelenKullaniciAdi);


        veriTabani vt2=new veriTabani(oyunActivity.this);
        SQLiteDatabase veri2=vt2.getReadableDatabase();
        Cursor cursor = veri2.rawQuery("SELECT * FROM kelimeler", null);

        final String array[] = new String[cursor.getCount()];
        final int kelimeSayisi=cursor.getCount();
        i = 0;

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            array[i] = cursor.getString(0);
            i++;
            cursor.moveToNext();
        }

        digerSoruButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                digerSoruButton.setText("DIGER SORU");
                veriTabani vt=new veriTabani(oyunActivity.this);
                SQLiteDatabase veri=vt.getReadableDatabase();
                Cursor cursor = veri.rawQuery("SELECT * FROM kelimeler WHERE id ='"+array[sayac]+"'", null);
                cevaplaButton.setVisibility(View.VISIBLE);
                if(cursor != null)
                {
                    if (cursor.moveToFirst()) {
                        kelimeButon.setText(cursor.getString(1));
                       // textView4.setText(cursor.getString(2));
                        turkceKelime=cursor.getString(2).toString();
                    }
                }
                cursor.close();
                veri.close();


                if(sayac<kelimeSayisi-1){
                    sayac++;
                }
                toplamSoruSayisi.setText(String.valueOf(sayac)+" / "+String.valueOf(kelimeSayisi));

            }
        });



        cevaplaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((editText.getText().toString()).equals(turkceKelime)){
                    textView2.setTextColor(Color.parseColor("#00F800"));
                    textView2.setText("Tebrikler Cevabınız Dogru");
                    cevaplaButton.setVisibility(View.INVISIBLE);
                    puanSayac+=10;

                }else{
                    textView2.setTextColor(Color.parseColor("#FF0000"));
                    textView2.setText("yanlis");
                    cevaplaButton.setVisibility(View.INVISIBLE);
                }
                puan.setText(String.valueOf(puanSayac)+"  ");
            }
        });




    }
}
