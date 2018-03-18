package com.example.hnf.qoryummuhanifah_1202154357_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CariGambarActivity extends AppCompatActivity {

    //mendeklarasikan komponen yang dipakai

    //EditText untuk memasukkan URL Image
    EditText mURLImage;

    //Button untuk memproses pencarian gambar
    Button mLoadImage;

    //ImageView sebagai wadah untuk menampilkan gambar
    ImageView mImage;

    //Popup progress ketika proses berjalan
    ProgressDialog mDialogLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);
        setTitle("Cari Gambar");

        //Referencing variable id pada layout
        mURLImage = findViewById(R.id.edtURLImage);
        mLoadImage = findViewById(R.id.btnFind);
        mImage = findViewById(R.id.imgGambar);

        //mengaktifkan klik tombol untuk mencari gambar
        mLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
            }
        });
    }

    //method untuk menginisiasi gambar menggunakan AsyncTask
    private void load() {
        String URLGambar = mURLImage.getText().toString();
        new LoadImage().execute(URLGambar);
    }

    //class AsyncTask
    private class LoadImage extends AsyncTask<String, Integer, Bitmap> {

        /* method yang dilakukan sebelum eksekusi
         * berfungsi untuk menampilkan progress bar ketika meload gambar
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialogLoad = new ProgressDialog(CariGambarActivity.this);
            mDialogLoad.setMessage("Loading ...");
            mDialogLoad.setMax(100);
            mDialogLoad.incrementProgressBy(1);
            mDialogLoad.show();
        }

        /* method pada saat eksekusi
         * berfungsi untuk mencari gambar berdasarkan url yang dimasukkan
         */
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                // me-Load URL image
                URL url = new URL(params[0]);

                // menjadikan input dari url ke bentuk bitmap(gambar)
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            } catch (IOException e) {
                Log.e("doInBackground() - ", e.getMessage());
            }

            //memberikan hasil gambar
            return bitmap;
        }

        /* method yang dilakukan setelah eksekusi
         * berfungsi untuk memasukkan gambar bitmap ke dalam ImageView pada layout
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mImage.setImageBitmap(bitmap);
            mDialogLoad.dismiss();
        }
    }
}