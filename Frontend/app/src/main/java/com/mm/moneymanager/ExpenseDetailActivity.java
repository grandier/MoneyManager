package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ExpenseDetailActivity extends AppCompatActivity {

    TextView expensedeskripsi, expensejumlah, expensetanggal, expensejenis;
    Button expenseedit, expensedelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);
        expensedeskripsi = (TextView) findViewById(R.id.expensedeskripsi);
        expensejumlah = (TextView) findViewById(R.id.expensejumlah);
        expensetanggal = (TextView) findViewById(R.id.expensetanggal);
        expensejenis = (TextView) findViewById(R.id.expensejenis);
        expenseedit = (Button) findViewById(R.id.editexpense);

        expensedeskripsi.setText(ExpenseActivity.selectedexpenseList.getDeskripsi());
        expensejumlah.setText(String.valueOf(ExpenseActivity.selectedexpenseList.getJumlah()));
        expensetanggal.setText(ExpenseActivity.selectedexpenseList.getTanggal());
        expensejenis.setText(ExpenseActivity.selectedexpenseList.getJenis());
    }
}