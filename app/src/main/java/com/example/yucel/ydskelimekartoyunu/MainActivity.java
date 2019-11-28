package com.example.yucel.ydskelimekartoyunu;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button listele,vtKaydet,kayitSilButton,oyunuBaslatButton;
    ListView listView;
    EditText ingilizce,turkce,kayitSilText;
    TextView kullaniciText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listele= (Button) findViewById(R.id.listele);
        vtKaydet= (Button) findViewById(R.id.vtKaydet);
        final ListView listView = (ListView) findViewById(R.id.listView);
        ingilizce= (EditText) findViewById(R.id.ingilizce);
        turkce= (EditText) findViewById(R.id.turkce);
        kullaniciText= (TextView) findViewById(R.id.kullaniciText);
        kayitSilButton= (Button) findViewById(R.id.kayitSilButton);
        kayitSilText= (EditText) findViewById(R.id.textSil);
        oyunuBaslatButton= (Button) findViewById(R.id.oyunuBaslatButton);

        Bundle gelenKullanici=getIntent().getExtras();
        String gelenKullaniciAdi=gelenKullanici.getString("ycl");
        kullaniciText.setText(gelenKullaniciAdi.toString());

        vtKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veriTabani VeriTabani=new veriTabani(MainActivity.this);
                VeriTabani.veriEkle(ingilizce.getText().toString(),turkce.getText().toString());
            }
        });



        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veriTabani veritabani=new veriTabani(MainActivity.this);
                List<String> vVeriler=veritabani.veriListele();
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1,vVeriler);
                listView.setAdapter(adapter);
            }
        });


        kayitSilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veriTabani VeriTabani=new veriTabani(MainActivity.this);
                SQLiteDatabase db=VeriTabani.getWritableDatabase();
                db.delete("kelimeler","id=?",new String[]{kayitSilText.getText().toString()});

            }
        });

        oyunuBaslatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, oyunActivity.class);
                intent.putExtra("ycl2",kullaniciText.getText().toString());
                startActivity(intent);
            }
        });



    }
}
