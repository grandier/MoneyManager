package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
 * The type Add income category activity.
 * This class is used to add the income category. The app will ask the user to enter the name of the income category. The user will be able to add the income category.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class AddIncomeCategoryActivity extends AppCompatActivity {

    private EditText incomecatname;
    private Button addincomecat;
    /**
     * The Loading.
     */
    ProgressDialog loading;

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
        setContentView(R.layout.activity_add_income_category);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        incomecatname = (EditText) findViewById(R.id.addcategory);
        addincomecat = (Button) findViewById(R.id.categoryaddbutton);

        addincomecat.setEnabled(false);

        /*
        * Add TextWatcher to EditText (Income Category Name)
        */
        incomecatname.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                addincomecat.setEnabled(s.toString().trim().length() != 0);
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
        * add income category button click event
         */
        addincomecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIncomeCategory();
            }
        });
    }

    /*
     * Add Income Category to database based on the user input in the EditText (Income Category Name) and the user will be redirected to the Income Category List Activity after the income category is added to the database.
     */

    private void addIncomeCategory() {
        mApiService.insertCategoryIncome(incomecatname.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("message").equals("Category for Income Created")) {
                            Toast.makeText(mContext, "Income Category Added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext, IncomeCategoryActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(mContext, "Failed to add Income Category", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(mContext, "Failed to add Income Category", Toast.LENGTH_SHORT).show();
                }
            }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Failed to add Income Category", Toast.LENGTH_SHORT).show();
                    }
                }
                );
    }
}
