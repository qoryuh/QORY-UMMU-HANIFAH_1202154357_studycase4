package com.example.hnf.qoryummuhanifah_1202154357_modul4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListNamaMahasiswaActivity extends AppCompatActivity {

    private ListView mList;
    private Button mLoadButton;
    private ProgressDialog mDialogLoad;
    private ArrayAdapter mNameAdapter;
    private Parcelable mListViewScroll = null;
    private int mDataLoaded = 0;
    private String mNameList[] = {"Karin","Ica","Muti","Putpus","Adilla","Aliya","Widi",
            "Arya","Hisyam","Hakim","Kiki","Hans","Rangga","Krisna","Sanny"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama_mahasiswa);

        mList = findViewById(R.id.lsvListName);
        mLoadButton = findViewById(R.id.btnLoadName);
        mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        if(mListViewScroll != null){
            mList.onRestoreInstanceState(mListViewScroll);
        }

        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadNameTask().execute();
            }
        });
    }

    private class LoadNameTask extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            mNameAdapter = (ArrayAdapter<String>)mList.getAdapter();

            mDialogLoad = new ProgressDialog(ListNamaMahasiswaActivity.this);
            mDialogLoad.setTitle("Loading ...");
            mDialogLoad.setMax(15);
            mDialogLoad.setProgress(0);
            mDialogLoad.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDialogLoad.show();
            mDataLoaded = 0;
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (String mNama : mNameList) {
                publishProgress(mNama);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Data has been loaded";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mNameAdapter.add(values[0]);
            mDataLoaded++;
            mDialogLoad.setProgress(mDataLoaded);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mDialogLoad.dismiss();
        }
    }
}