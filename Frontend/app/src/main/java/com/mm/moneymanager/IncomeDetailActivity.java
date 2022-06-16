package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class IncomeDetailActivity extends AppCompatActivity {

    TextView incomedeskripsi, incomejumlah, incometanggal, incomejenis;
    Button incomeedit, incomedelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail);
        incomedeskripsi = (TextView) findViewById(R.id.incomedeskripsi);
        incomejumlah = (TextView) findViewById(R.id.incomejumlah);
        incometanggal = (TextView) findViewById(R.id.incometanggal);
        incomejenis = (TextView) findViewById(R.id.incomejenis);
        incomeedit = (Button) findViewById(R.id.editincome);


        incomedeskripsi.setText(IncomeActivity.selectedincomeList.getDeskripsi());
        incomejumlah.setText(String.valueOf(IncomeActivity.selectedincomeList.getJumlah()));
        incometanggal.setText(IncomeActivity.selectedincomeList.getTanggal());
        incomejenis.setText(IncomeActivity.selectedincomeList.getJenis());
    }
}