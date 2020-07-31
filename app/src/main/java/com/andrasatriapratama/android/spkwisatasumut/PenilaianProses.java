package com.andrasatriapratama.android.spkwisatasumut;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PenilaianProses extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    TextView text;
    Button btnSimpan;
    RadioGroup rgBiaya, rgFasilitas, rgTransportasi, rgKeindahan, rgWaktu, rgJarak;
    RadioButton rbBiaya, rbFasilitas, rbTransportasi, rbKeindahan, rbWaktu, rbJarak;
    String id_wisata, nama_wisata, biaya, fasilitas, transportasi, keindahan, waktu, jarak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian_proses);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper = new DatabaseHelper(this);

        final Cursor cursorDataWisata = databaseHelper.getOneNameWisata(getIntent().getStringExtra("nama_wisata"));
        cursorDataWisata.moveToFirst();
        id_wisata = cursorDataWisata.getString(0).toString();
        nama_wisata = cursorDataWisata.getString(1).toString();
        text = (TextView) findViewById(R.id.text);
        text.setText(nama_wisata);

        rgBiaya = (RadioGroup) findViewById(R.id.rgBiaya);
        rgFasilitas = (RadioGroup) findViewById(R.id.rgFasilitas);
        rgTransportasi = (RadioGroup) findViewById(R.id.rgTransportasi);
        rgKeindahan = (RadioGroup) findViewById(R.id.rgKeindahan);
        rgWaktu = (RadioGroup) findViewById(R.id.rgWaktu);
        rgJarak = (RadioGroup) findViewById(R.id.rgJarak);

        Cursor cursorCek = databaseHelper.getOneDataKriteria(id_wisata);
        if (cursorCek.getCount() > 0 && cursorCek != null) {
            cursorCek.moveToFirst();

            // Biaya
            switch (cursorCek.getString(2).toString()) {
                case "5":
                    //rgBiaya.check(R.id.rbBiaya5);
                    rbBiaya = (RadioButton) findViewById(R.id.rbBiaya5);
                    rbBiaya.setChecked(true);
                    break;
                case "4":
                    rbBiaya = (RadioButton) findViewById(R.id.rbBiaya4);
                    rbBiaya.setChecked(true);
                    break;
                case "3":
                    rbBiaya = (RadioButton) findViewById(R.id.rbBiaya3);
                    rbBiaya.setChecked(true);
                    break;
                case "2":
                    rbBiaya = (RadioButton) findViewById(R.id.rbBiaya2);
                    rbBiaya.setChecked(true);
                    break;
                case "1":
                    rbBiaya = (RadioButton) findViewById(R.id.rbBiaya1);
                    rbBiaya.setChecked(true);
                    break;
            }

            // Fasilitas
            switch (cursorCek.getString(3).toString()) {
                case "5":
                    rbFasilitas = (RadioButton) findViewById(R.id.rbFasilitas5);
                    rbFasilitas.setChecked(true);
                    break;
                case "4":
                    rbFasilitas = (RadioButton) findViewById(R.id.rbFasilitas4);
                    rbFasilitas.setChecked(true);
                    break;
                case "3":
                    rbFasilitas = (RadioButton) findViewById(R.id.rbFasilitas3);
                    rbFasilitas.setChecked(true);
                    break;
                case "2":
                    rbFasilitas = (RadioButton) findViewById(R.id.rbFasilitas2);
                    rbFasilitas.setChecked(true);
                    break;
                case "1":
                    rbFasilitas = (RadioButton) findViewById(R.id.rbFasilitas1);
                    rbFasilitas.setChecked(true);
                    break;
            }

            // Transportasi
            switch (cursorCek.getString(4).toString()) {
                case "5":
                    rbTransportasi = (RadioButton) findViewById(R.id.rbTransportasi5);
                    rbTransportasi.setChecked(true);
                    break;
                case "4":
                    rbTransportasi = (RadioButton) findViewById(R.id.rbTransportasi4);
                    rbTransportasi.setChecked(true);
                    break;
                case "3":
                    rbTransportasi = (RadioButton) findViewById(R.id.rbTransportasi3);
                    rbTransportasi.setChecked(true);
                    break;
                case "2":
                    rbTransportasi = (RadioButton) findViewById(R.id.rbTransportasi2);
                    rbTransportasi.setChecked(true);
                    break;
                case "1":
                    rbTransportasi = (RadioButton) findViewById(R.id.rbTransportasi1);
                    rbTransportasi.setChecked(true);
                    break;
            }

            // Keindahan
            switch (cursorCek.getString(5).toString()) {
                case "5":
                    rbKeindahan = (RadioButton) findViewById(R.id.rbKeindahan5);
                    rbKeindahan.setChecked(true);
                    break;
                case "4":
                    rbKeindahan = (RadioButton) findViewById(R.id.rbKeindahan4);
                    rbKeindahan.setChecked(true);
                    break;
                case "3":
                    rbKeindahan = (RadioButton) findViewById(R.id.rbKeindahan3);
                    rbKeindahan.setChecked(true);
                    break;
                case "2":
                    rbKeindahan = (RadioButton) findViewById(R.id.rbKeindahan2);
                    rbKeindahan.setChecked(true);
                    break;
                case "1":
                    rbKeindahan = (RadioButton) findViewById(R.id.rbKeindahan1);
                    rbKeindahan.setChecked(true);
                    break;
            }

            // Waktu
            switch (cursorCek.getString(6).toString()) {
                case "5":
                    rbWaktu = (RadioButton) findViewById(R.id.rbWaktu5);
                    rbWaktu.setChecked(true);
                    break;
                case "4":
                    rbWaktu = (RadioButton) findViewById(R.id.rbWaktu4);
                    rbWaktu.setChecked(true);
                    break;
                case "3":
                    rbWaktu = (RadioButton) findViewById(R.id.rbWaktu3);
                    rbWaktu.setChecked(true);
                    break;
                case "2":
                    rbWaktu = (RadioButton) findViewById(R.id.rbWaktu2);
                    rbWaktu.setChecked(true);
                    break;
                case "1":
                    rbWaktu = (RadioButton) findViewById(R.id.rbWaktu1);
                    rbWaktu.setChecked(true);
                    break;
            }

            // Jarak
            switch (cursorCek.getString(7).toString()) {
                case "5":
                    rbJarak = (RadioButton) findViewById(R.id.rbJarak5);
                    rbJarak.setChecked(true);
                    break;
                case "4":
                    rbJarak = (RadioButton) findViewById(R.id.rbJarak4);
                    rbJarak.setChecked(true);
                    break;
                case "3":
                    rbJarak = (RadioButton) findViewById(R.id.rbJarak3);
                    rbJarak.setChecked(true);
                    break;
                case "2":
                    rbJarak = (RadioButton) findViewById(R.id.rbJarak2);
                    rbJarak.setChecked(true);
                    break;
                case "1":
                    rbJarak = (RadioButton) findViewById(R.id.rbJarak1);
                    rbJarak.setChecked(true);
                    break;
            }
        }

        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rbBiaya = (RadioButton) findViewById(rgBiaya.getCheckedRadioButtonId());
                switch (rbBiaya.getText().toString()) {
                    case "Biaya Sangat Banyak":
                        biaya = "5";
                        break;
                    case "Biaya Lumayan Banyak":
                        biaya = "4";
                        break;
                    case "Biaya Sedang":
                        biaya = "3";
                        break;
                    case "Biaya Sedikit":
                        biaya = "2";
                        break;
                    case "Biaya Sangat Sedikit":
                        biaya = "1";
                        break;
                }

                rbFasilitas = (RadioButton) findViewById(rgFasilitas.getCheckedRadioButtonId());
                switch (rbFasilitas.getText().toString()) {
                    case "Sangat Baik Sekali":
                        fasilitas = "5";
                        break;
                    case "Sangat Baik":
                        fasilitas = "4";
                        break;
                    case "Baik":
                        fasilitas = "3";
                        break;
                    case "Cukup Baik":
                        fasilitas = "2";
                        break;
                    case "Kurang Baik":
                        fasilitas = "1";
                        break;
                }

                rbTransportasi = (RadioButton) findViewById(rgTransportasi.getCheckedRadioButtonId());
                switch (rbTransportasi.getText().toString()) {
                    case "Semua Jenis Transportasi":
                        transportasi = "5";
                        break;
                    case "Hampir Semua Transportasi":
                        transportasi = "4";
                        break;
                    case "Sebagian Jenis Transportasi":
                        transportasi = "3";
                        break;
                    case "Sedikit Jenis Transportasi":
                        transportasi = "2";
                        break;
                    case "Hanya Satu Jenis Transportasi":
                        transportasi = "1";
                        break;
                }

                rbKeindahan = (RadioButton) findViewById(rgKeindahan.getCheckedRadioButtonId());
                switch (rbKeindahan.getText().toString()) {
                    case "Sangat Indah Sekali":
                        keindahan = "5";
                        break;
                    case "Sangat Indah":
                        keindahan = "4";
                        break;
                    case "Indah":
                        keindahan = "3";
                        break;
                    case "Cukup Indah":
                        keindahan = "2";
                        break;
                    case "Kurang Indah":
                        keindahan = "1";
                        break;
                }

                rbWaktu = (RadioButton) findViewById(rgWaktu.getCheckedRadioButtonId());
                switch (rbWaktu.getText().toString()) {
                    case "Sangat Lama Sekali":
                        waktu = "5";
                        break;
                    case "Sangat Lama":
                        waktu = "4";
                        break;
                    case "Lama":
                        waktu = "3";
                        break;
                    case "Cukup Lama":
                        waktu = "2";
                        break;
                    case "Tidak Lama":
                        waktu = "1";
                        break;
                }

                rbJarak = (RadioButton) findViewById(rgJarak.getCheckedRadioButtonId());
                switch (rbJarak.getText().toString()) {
                    case "Sangat Jauh Sekali":
                        jarak = "5";
                        break;
                    case "Sangat Jauh":
                        jarak = "4";
                        break;
                    case "Jauh":
                        jarak = "3";
                        break;
                    case "Cukup Jauh":
                        jarak = "2";
                        break;
                    case "Tidak Jauh":
                        jarak = "1";
                        break;
                }

                Cursor cursorKriteria = databaseHelper.getOneDataKriteria(id_wisata);
                if (cursorKriteria.getCount() > 0) {
                    databaseHelper.updateDataKriteria(id_wisata, biaya, fasilitas, transportasi, keindahan, waktu, jarak);
                    Toast.makeText(getApplicationContext(), "Penilaian " + nama_wisata + " disimpan", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    databaseHelper.insertDataKriteria(id_wisata, biaya, fasilitas, transportasi, keindahan, waktu, jarak);
                    Toast.makeText(getApplicationContext(), "Penilaian " + nama_wisata + " disimpan", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });


    }
}
