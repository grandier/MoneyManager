package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class AddIncomeCategoryActivity extends AppCompatActivity {

    private EditText incomecatname;
    private Button addincomecat;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income_category);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        incomecatname = (EditText) findViewById(R.id.addcategory);
        addincomecat = (Button) findViewById(R.id.categoryaddbutton);

        addincomecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIncomeCategory();
            }
        });
    }

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
