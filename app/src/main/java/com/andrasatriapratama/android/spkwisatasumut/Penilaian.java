package com.andrasatriapratama.android.spkwisatasumut;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Penilaian extends AppCompatActivity {

    String[] daftar;
    ListView listView;
    DatabaseHelper databaseHelper;
    public static Penilaian mPenilaianActivity;
    Button mLihatDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLihatDataButton = (Button) findViewById(R.id.btnLihatData);
        mLihatDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Penilaian.this, PenilaianData.class);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(this);
        mPenilaianActivity = this;
        refreshList();
    }

    public void refreshList() {
        Cursor cursor = databaseHelper.getDataWisata();
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(1).toString();
        }

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                Intent intent = new Intent(getApplicationContext(), PenilaianProses.class);
                intent.putExtra("nama_wisata", selection);
                startActivity(intent);
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }

}
