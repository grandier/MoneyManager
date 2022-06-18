package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mm.moneymanager.request.BaseApiService;
import com.mm.moneymanager.request.UtilsApi;

public class ExpenseCategoryDetailActivity extends AppCompatActivity {

    private TextView expensecatname;
    private Button editexpensecat;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_category_detail);
        expensecatname = (TextView) findViewById(R.id.expensecatname);
        editexpensecat= (Button) findViewById(R.id.editexpensecat);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        expensecatname.setText(ExpenseCategoryActivity.selectedexpensecatList.getJenis());

        editexpensecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateExpenseCategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}