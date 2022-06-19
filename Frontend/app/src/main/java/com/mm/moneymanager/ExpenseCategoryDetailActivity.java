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
 * The type Expense category detail activity.
 * This class is used to show the detail of the expense category. The user can click on the expense category to edit the expense category.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class ExpenseCategoryDetailActivity extends AppCompatActivity {

    private TextView expensecatname;
    private Button editexpensecat;

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
        setContentView(R.layout.activity_expense_category_detail);
        expensecatname = (TextView) findViewById(R.id.expensecatname);
        editexpensecat= (Button) findViewById(R.id.editexpensecat);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        expensecatname.setText(ExpenseCategoryActivity.selectedexpensecatList.getJenis());

        /*
            * Edit Expense Category button
            * if the user click on the edit expense category button, the app will open the AddExpenseCategoryActivity class
         */
        editexpensecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateExpenseCategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}