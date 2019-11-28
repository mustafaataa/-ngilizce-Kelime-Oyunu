package com.example.yucel.ydskelimekartoyunu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class kullaniciGiris extends AppCompatActivity {
    Button buton2;
    Button buton1;
    TextView yazi;
    EditText kullanici;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_giris);
         buton2 = (Button) findViewById(R.id.button2);
         kullanici= (EditText) findViewById(R.id.kullanici);
       // yazi= (TextView) findViewById(R.id.textView2);
        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(1);
            }
        });

 buton1 = (Button) findViewById(R.id.button);
        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("ycl",kullanici.getText().toString());
                Toast.makeText(getApplicationContext(),"Kullanici Bilgisi Kaydedildi...",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });


    }
}
