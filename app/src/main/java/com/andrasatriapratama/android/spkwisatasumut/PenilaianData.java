package com.andrasatriapratama.android.spkwisatasumut;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class PenilaianData extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    TextView hasil;
    String id_wisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hasil = (TextView) findViewById(R.id.hasil);

        databaseHelper = new DatabaseHelper(this);
        Cursor cursorKriteria = databaseHelper.getDataKriteria();
        StringBuffer stringBuffer = new StringBuffer();
        if (cursorKriteria != null && cursorKriteria.getCount() > 0) {
            while (cursorKriteria.moveToNext()) {
                id_wisata = cursorKriteria.getString(1);
                Cursor cursorWisata = databaseHelper.getOneIdWisata(id_wisata);
                cursorWisata.moveToFirst();
                stringBuffer.append("Nama Wisata : " + cursorWisata.getString(1) + "\n");
                stringBuffer.append("C1 (Biaya) : " + cursorKriteria.getString(2) + "\n");
                stringBuffer.append("C2 (Fasilitas) : " + cursorKriteria.getString(3) + "\n");
                stringBuffer.append("C3 (Transportasi) : " + cursorKriteria.getString(4) + "\n");
                stringBuffer.append("C4 (Keindahan) : " + cursorKriteria.getString(5) + "\n");
                stringBuffer.append("C5 (Waktu) " + cursorKriteria.getString(6) + "\n");
                stringBuffer.append("C6 (Jarak) " + cursorKriteria.getString(7) + "\n\n");
            }
            hasil.setText(stringBuffer.toString());
        } else {
            hasil.setText("Belum ada data");
        }


    }
}
