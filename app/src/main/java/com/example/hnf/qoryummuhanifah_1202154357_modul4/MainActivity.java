package com.example.hnf.qoryummuhanifah_1202154357_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Mendeklarasi komponen yang akan dipakai
    Button mCariGambar, mListNamaMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCariGambar = findViewById(R.id.btnCariGambar);
        mListNamaMahasiswa = findViewById(R.id.btnListNamaMahasiswa);

        //mengaktifkan klik tombol untuk berpindah ke Activity CariGambar
        mCariGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityA = new Intent(MainActivity.this, CariGambarActivity.class);
                startActivity(activityA);
            }
        });

        //mengaktifkan klik tombol untuk berpindah ke Activity ListNamaMahasiswa
        mListNamaMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityB = new Intent(MainActivity.this, ListNamaMahasiswaActivity.class);
                startActivity(activityB);
            }
        });
    }
}
