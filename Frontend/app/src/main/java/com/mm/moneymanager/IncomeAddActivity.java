package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mm.moneymanager.request.BaseApiService;
import com.mm.moneymanager.request.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * The type Income add activity.
 * This class is used to add the income. The app will ask the user to enter the income description, amount and date. The user will be able to add the income.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class IncomeAddActivity extends AppCompatActivity {

    private EditText addincomedesc;
    private EditText addincomeamount;
    private EditText addincomedate;
    private Button addincomebutton;
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
        setContentView(R.layout.activity_income_add);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        addincomedesc = (EditText) findViewById(R.id.addincomedesc);
        addincomeamount = (EditText) findViewById(R.id.addincomeamount);
        addincomedate = (EditText) findViewById(R.id.addincomedate);
        addincomebutton = (Button) findViewById(R.id.incomeaddbutton);

        addincomebutton.setEnabled(false);

        /*
        * TextWatcher for addincomeamount EditText to enable/disable addincomebutton based on whether addincomeamount is empty or not
         */
        addincomedesc.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addincomebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        * TextWatcher for addincomeamount EditText to enable/disable addincomebutton (Expense Amount) based on whether addincomeamount is empty or not
         */
        addincomeamount.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                addincomebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        * TextWatcher for addincomeamount EditText to enable/disable addincomebutton (Expense Amount) based on whether addincomeamount is empty or not
         */
        addincomedate.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                addincomebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        * Income Add Button Click Listener to add Income to DB and redirect to Income List Activity after successful addition
         */
        addincomebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                addIncome();
            }
        });

    }

    /*
    * Method to add Income to DB and redirect to Income List Activity after successful addition of Income
    *
     */
    private void addIncome() {
        if(addincomedesc.getText().toString().isEmpty() || addincomeamount.getText().toString().isEmpty() || addincomedate.getText().toString().isEmpty()){
            Toast.makeText(mContext, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            loading.dismiss();
        }else{
            mApiService.insertIncome(addincomedesc.getText().toString(), BigInteger.valueOf(Long.parseLong(addincomeamount.getText().toString())), addincomedate.getText().toString(), IncomeAddCatChooseActivity.selectedincomecatList.getKatPend_Id())
                    .enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        loading.dismiss();
                        try {
                            JSONObject jsonRESULTS = new JSONObject(response.body().string());
                            if (jsonRESULTS.getString("message").equals("Income Created")) {
                                Toast.makeText(mContext, "Income Category", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, IncomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(mContext, "Failed to add Income", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(mContext, "Failed to add Income", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(mContext, "Failed to add Income", Toast.LENGTH_SHORT).show();
                }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(mContext, "Failed to add Income", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        }
}