package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mm.moneymanager.request.BaseApiService;
import com.mm.moneymanager.request.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Add expense category activity.
 * This class is used to add the expense category. The app will ask the user to enter the expense category description. The user will be able to add the expense category.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class AddExpenseCategoryActivity extends AppCompatActivity {

    private EditText expensecatname;
    private Button addexpensecat;

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
        setContentView(R.layout.activity_add_expense_category);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        expensecatname = (EditText) findViewById(R.id.addcategoryexpense);
        addexpensecat = (Button) findViewById(R.id.categoryaddbutton);

        addexpensecat.setEnabled(false);

        /*
        * This is the code for the text watcher for the expense category name
         */
        expensecatname.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                addexpensecat.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        /*
        * Listview item click listener
         */
        addexpensecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpenseCategory();
            }
        });
    }

    /**
     *
     * Add expense category to database from the user input. The user will be redirected to the Expense Category List Activity after the expense category is added to the database.
     *
     */
    private void addExpenseCategory() {
        mApiService.insertCategoryExpense(expensecatname.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("message").equals("Category for Expense Created")) {
                            Toast.makeText(mContext, "Expense Category Added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext, ExpenseCategoryActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(mContext, "Failed to add category", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(mContext, "Failed to add category", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Failed to add category", Toast.LENGTH_SHORT).show();
            }
        });
    }
}