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

public class UpdateIncomeCategoryActivity extends AppCompatActivity {

    private EditText updatecategoryincome;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_income_category);
        updatecategoryincome = (EditText) findViewById(R.id.updatecategoryincome);
        Button updatecategoryincomebtn = (Button) findViewById(R.id.catincomeupdatebutton);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        updatecategoryincomebtn.setEnabled(false);

        updatecategoryincome.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                updatecategoryincomebtn.setEnabled(s.toString().trim().length() != 0);
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

        updatecategoryincomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateincomecategory();
            }
        });
    }

    private void updateincomecategory() {
        mApiService.updateCategoryIncome(IncomeCategoryActivity.selectedincomecatList.getKatPend_Id(), updatecategoryincome.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("message").equals("Income Updated")) {
                                    Toast.makeText(mContext, "Update Category Income Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mContext, IncomeCategoryActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(mContext, "Update Category Income Failed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "Update Category Income Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, "Update Income Category Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}