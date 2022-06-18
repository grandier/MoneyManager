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

public class IncomeCategoryDetailActivity extends AppCompatActivity {

    private TextView incomecatname;
    private Button editincomecat;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_category_detail);
        incomecatname = (TextView) findViewById(R.id.incomecatname);
        editincomecat= (Button) findViewById(R.id.editincomecat);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        incomecatname.setText(IncomeCategoryActivity.selectedincomecatList.getJenis());

        editincomecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateIncomeCategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}