package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import java.math.BigInteger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ExpenseAddActivity extends AppCompatActivity {

    private EditText addexpensedesc;
    private EditText addexpenseamount;
    private EditText addexpensedate;
    private Button addexpensebutton;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_add);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        addexpensedesc = (EditText) findViewById(R.id.addexpensedesc);
        addexpenseamount = (EditText) findViewById(R.id.addexpenseamount);
        addexpensedate = (EditText) findViewById(R.id.addexpensedate);
        addexpensebutton = (Button) findViewById(R.id.expenseaddbutton);

        addexpensebutton.setEnabled(false);

        addexpensedesc.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addexpensebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addexpenseamount.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                addexpensebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addexpensedate.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                addexpensebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addexpensebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Please wait...", true, false);
                addExpense();
            }
        });
    }

    private void addExpense() {
        if(addexpensedesc.getText().toString().isEmpty() || addexpenseamount.getText().toString().isEmpty() || addexpensedate.getText().toString().isEmpty()){
            Toast.makeText(mContext, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            loading.dismiss();
        }else{
            mApiService.insertExpense(addexpensedesc.getText().toString(), BigInteger.valueOf(Long.parseLong(addexpenseamount.getText().toString())), addexpensedate.getText().toString(), ExpenseAddCatChooseActivity.selectedexpensecatList.getKatPeng_Id())
                    .enqueue(new retrofit2.Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                loading.dismiss();
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getString("message").equals("Expense Created")) {
                                        Toast.makeText(mContext, "Income Added", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(mContext, ExpenseActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(mContext, "Failed to add Expense", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(mContext, "Failed to add Expense", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(mContext, "Failed to add Expense", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(mContext, "Failed to add Income", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}