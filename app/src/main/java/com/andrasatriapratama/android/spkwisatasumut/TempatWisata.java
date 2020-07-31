package com.andrasatriapratama.android.spkwisatasumut;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TempatWisata extends AppCompatActivity {
    String[] daftar;
    ListView listView;
    Spinner spinner;
    TextView textView;
    DatabaseHelper databaseHelper;
    public static TempatWisata tempatWisataActivity;
    String[] tempatWisata = {"Pilih Tempat Wisata","Air Terjun Sipiso-piso","Air Terjun Dua Warna","Air Terjun Sikulikap","Air Terjun Sampuran Putih","Air Terjun Sampuran Efrata","Air Terjun Aek Sijornih","Air Terjun Bukit Gibeon","Air Terjun Simonang-monang","Air Terjun Ponot Asahan","Air Terjun Unong Sisapa Asahan","Air Terjun Alam Tani Asahan","Air Terjun Linggahara",
            "Danau Toba","Danau Sidihoni","Danau Aek Natonang","Danau Lau Kawar","Danau Linting","Danau Sicike-cike","Danau Marsabut","Danau Siais","Danau Megoto","Danau Laut Tidor","Danau Siombak","Danau Marambe","Danau Tasik",
            "Pantai Sorake","Pantai Tureloto","Pantai Bebas","Pantai Lumban Bulbul","Pantai Lubuk","Pantai Muara Indah","Pantai Romance Bay","Pantai Sorake","Pantai Bali Lestari","Pantai Cermin Theme Park","Pantai Pondok Indah Permai","Pantai Sialang Buah","Pantai Lagundri","Pantai Gawu Soyo","Pantai Perjuangan/Pentai Jono","Pantai Natal Mandailinng Natal","Pantai Bunga","Pantai Cermin",
			"Pantai Pasir Putih Parbaba","Bukit Lawang","Bukit Gundaling",
			"Pulau Siroktabe","Pulau Berhala","Pulau Samosir","Pulau Tolping","Pulau Sibandang","Pulau Tao Samosir","Pulau Mursala","Pulau Hinako","Pulau Tulas Samosir","Pulau Salah Nama","Pulau Pandang","Pulau Unggeh","Pulau Pearung","Pulau Sikantan",
			"Sungai Sembahe","Sungai Dua Rasa","Sungai Sari Laba Biru",
			"Gua Liang Lahar","Hutan Sibolangit","Air Soda Tarutung"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_wisata);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = (TextView) findViewById(R.id.tv_pesanKosong);

        databaseHelper = new DatabaseHelper(this);
        tempatWisataActivity = this;

        refreshList();

    }

    public void refreshList() {
        Cursor cursorListData = databaseHelper.getDataWisata();

        if (cursorListData.getCount() > 0 && cursorListData != null) {
            textView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }

        daftar = new String[cursorListData.getCount()];
        cursorListData.moveToFirst();
        for (int i = 0; i < cursorListData.getCount(); i++) {
            cursorListData.moveToPosition(i);
            daftar[i] = cursorListData.getString(1).toString();
        }

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Hapus Tempat Wisata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(TempatWisata.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                AlertDialog.Builder a_builder = new AlertDialog.Builder(TempatWisata.this);
                                a_builder.setMessage("Yakin dihapus?")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Cursor cursorDataWisata = databaseHelper.getOneNameWisata(selection);
                                                cursorDataWisata.moveToFirst();
                                                databaseHelper.deleteDataWisata(selection);
                                                databaseHelper.deleteDataKriteria(cursorDataWisata.getString(0));
                                                Toast.makeText(TempatWisata.this, selection + " berhasil dihapus", Toast.LENGTH_SHORT).show();
                                                refreshList();
                                            }
                                        })
                                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert = a_builder.create();
                                alert.setTitle("Perhatian !");
                                alert.show();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tempatWisata);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String item = parent.getItemAtPosition(pos).toString();
                if (item == "Pilih Tempat Wisata") {
                    return;
                }

                Cursor cursorCekData = databaseHelper.getOneNameWisata(item);
                if (cursorCekData.getCount() > 0) {
                    Toast.makeText(getApplicationContext(), item + " sudah ada", Toast.LENGTH_SHORT).show();
                    TempatWisata.tempatWisataActivity.refreshList();
                } else {
                    Boolean result = databaseHelper.insertDataWisata(item);
                    if (result) {
                        Toast.makeText(getApplicationContext(), item + " dipilih", Toast.LENGTH_LONG).show();
                    }
                    TempatWisata.tempatWisataActivity.refreshList();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
    }
}
