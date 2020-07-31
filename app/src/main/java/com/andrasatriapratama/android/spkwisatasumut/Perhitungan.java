package com.andrasatriapratama.android.spkwisatasumut;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class Perhitungan extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    TextView rekomendasi, hasilBobotAwal, hasilBobotBaru, hasilNormalisasi, hasilAkhir;
    double total;

    PieChart pieChart;
    ArrayList<Entry> entries;
    ArrayList<String> pieEntryLabels;
    PieDataSet pieDataSet;
    PieData pieData;

    ArrayList tempatWisataArray;

    LinearLayout linearLayout;
    Button tampilBtn, tutupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Kode program chart
        pieChart = (PieChart) findViewById(R.id.chart1);

        entries = new ArrayList<>();
        pieEntryLabels = new ArrayList<String>();
        tempatWisataArray = new ArrayList();

        // Kode program
        linearLayout = (LinearLayout) findViewById(R.id.lihatLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        rekomendasi = (TextView) findViewById(R.id.rekomendasi);
        hasilBobotAwal = (TextView) findViewById(R.id.hasilBobotAwal);
        hasilBobotBaru = (TextView) findViewById(R.id.hasilBobotBaru);
        hasilNormalisasi = (TextView) findViewById(R.id.hasilNormalisasi);
        hasilAkhir = (TextView) findViewById(R.id.hasilAkhir);
        tampilBtn = (Button) findViewById(R.id.lihat_perhitungan);
        tutupBtn = (Button) findViewById(R.id.tutup_perhitungan);
        tutupBtn.setVisibility(View.INVISIBLE);

        // array bobot awal kriteria
        double[] bobot = {5, 3, 4, 5, 4, 3};
        double b1 = bobot[0];
        double b2 = bobot[1];
        double b3 = bobot[2];
        double b4 = bobot[3];
        double b5 = bobot[4];
        double b6 = bobot[5];
        hasilBobotAwal.setText((int) b1 + " " + (int) b2 + " " + (int) b3 + " " + (int) b4 + " " + (int) b5 + " " + (int) b6);

        // array perbaikan bobot kriteria
        for (double val : bobot) {
            total += val;
        }

        double bb1 = b1 / total;
        double bb2 = b2 / total;
        double bb3 = b3 / total;
        double bb4 = b4 / total;
        double bb5 = b5 / total;
        double bb6 = b6 / total;
        hasilBobotBaru.setText(String.format("%.4f", bb1) + " " + String.format("%.4f", bb2) + " " + String.format("%.4f", bb3) + " " + String.format("%.4f", bb4) + " " + String.format("%.4f", bb5) + " " + String.format("%.4f", bb6));

        databaseHelper = new DatabaseHelper(this);
        Cursor cursorKriteria = databaseHelper.getDataKriteria();
        StringBuffer stringBuffer = new StringBuffer();
        if (cursorKriteria != null && cursorKriteria.getCount() > 0) {
            double sum = 0.0;
            while (cursorKriteria.moveToNext()) {
                Cursor cursorWisata = databaseHelper.getOneIdWisata(cursorKriteria.getString(1));
                cursorWisata.moveToFirst();

                double c1 = Double.valueOf(cursorKriteria.getString(2));
                double c2 = Double.valueOf(cursorKriteria.getString(3));
                double c3 = Double.valueOf(cursorKriteria.getString(4));
                double c4 = Double.valueOf(cursorKriteria.getString(5));
                double c5 = Double.valueOf(cursorKriteria.getString(6));
                double c6 = Double.valueOf(cursorKriteria.getString(7));
                double h = Math.pow(c1, -bb1)
                        * Math.pow(c2, bb2)
                        * Math.pow(c3, bb3)
                        * Math.pow(c4, bb4)
                        * Math.pow(c5, -bb5)
                        * Math.pow(c6, -bb6);
                sum = sum + h;
                stringBuffer.append(cursorWisata.getString(1).toString() + " : " + String.format("%.4f", h) + "\n");

            }
            hasilNormalisasi.setText(stringBuffer.toString());// STP

            Cursor cursorKriteria2 = databaseHelper.getDataKriteria();
            StringBuffer stringBuffer2 = new StringBuffer();

            int i = 0;
            while (cursorKriteria2.moveToNext()) {
                Cursor cursorWisata2 = databaseHelper.getOneIdWisata(cursorKriteria2.getString(1));
                cursorWisata2.moveToFirst();
                pieEntryLabels.add(cursorWisata2.getString(1).toString());

                double c1 = Double.valueOf(cursorKriteria2.getString(2));
                double c2 = Double.valueOf(cursorKriteria2.getString(3));
                double c3 = Double.valueOf(cursorKriteria2.getString(4));
                double c4 = Double.valueOf(cursorKriteria2.getString(5));
                double c5 = Double.valueOf(cursorKriteria2.getString(6));
                double c6 = Double.valueOf(cursorKriteria2.getString(7));
                double h = Math.pow(c1, -bb1)
                        * Math.pow(c2, bb2)
                        * Math.pow(c3, bb3)
                        * Math.pow(c4, bb4)
                        * Math.pow(c5, -bb5)
                        * Math.pow(c6, -bb6);
                stringBuffer2.append(cursorWisata2.getString(1).toString() + " : " + String.format("%.4f", h / sum) + "\n");
                entries.add(new BarEntry((float) (h / sum), i));
                tempatWisataArray.add(String.format("%.4f", h / sum) + " " + cursorWisata2.getString(1).toString());
                i++;
            }

            Collections.sort(tempatWisataArray);
            String rek = String.valueOf(tempatWisataArray.get(tempatWisataArray.size()-1));

            rekomendasi.setText(rek.substring(7, rek.length()));
            hasilAkhir.setText(stringBuffer2.toString());

            pieDataSet = new PieDataSet(entries, "");
            pieData = new PieData(pieEntryLabels, pieDataSet);

            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

            //Disable Hole in the Pie Chart
            pieChart.setDrawHoleEnabled(false);
            pieChart.setData(pieData);
            //pieChart.setUsePercentValues(true);
            pieChart.animateY(3000);
        } else {
            hasilNormalisasi.setText("Belum ada data");
        }
    }

    public void lihatPerhitungan(View view) {
        linearLayout.setVisibility(View.VISIBLE);
        tampilBtn.setVisibility(View.INVISIBLE);
        tutupBtn.setVisibility(View.VISIBLE);
    }

    public void tutupPerhitungan(View view) {
        linearLayout.setVisibility(View.INVISIBLE);
        tutupBtn.setVisibility(View.INVISIBLE);
        tampilBtn.setVisibility(View.VISIBLE);
    }
}
