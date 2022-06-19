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

/**
 * The type Income category detail activity.
 * This activity is used to show the detail of income category and also to edit the income category based on the id of the income category that is passed from the previous activity (IncomeCategoryActivity) as an intent.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class IncomeCategoryDetailActivity extends AppCompatActivity {

    private TextView incomecatname;
    private Button editincomecat;

    /**
     * The M context.
     */
    Context mContext;
    /**
     * The M api service.
     */
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_category_detail);
        incomecatname = (TextView) findViewById(R.id.incomecatname);
        editincomecat= (Button) findViewById(R.id.editincomecat);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        /*
        * Get the intent that started this activity and extract the string
        * if it exists it will display the name of the income category
         */
        incomecatname.setText(IncomeCategoryActivity.selectedincomecatList.getJenis());

        /*
        * When the edit button is clicked it will open the IncomeCategoryEditActivity and pass the income category id to it so that it can be edited and then it will finish this activity
         */
        editincomecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateIncomeCategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}