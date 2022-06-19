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

/**
 * The type Update income activity.
 * This class is used to update the income. The app will ask the user to enter the income description, amount and date. The user will be able to update the income.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class UpdateIncomeActivity extends AppCompatActivity {

    private EditText updateincomedesc;
    private EditText updateincomeamount;
    private EditText updateincomedate;
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
        setContentView(R.layout.activity_update_income);
        updateincomedesc = (EditText) findViewById(R.id.updateincomedesc);
        updateincomeamount = (EditText) findViewById(R.id.updateincomeamount);
        updateincomedate = (EditText) findViewById(R.id.updateincomedate);
        Button updateincomebutton = (Button) findViewById(R.id.incomeupdatebutton);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        updateincomebutton.setEnabled(false);

        /*
        * text watcher for the update income button to enable it when the user has entered all the required information
         */
        updateincomedesc.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateincomebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        * text watcher for the update income button to enable it when the user has entered all the required information
         */
        updateincomeamount.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                updateincomebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        * text watcher for the update income button to enable it when the user has entered all the required information
         */
        updateincomedate.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                updateincomebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        * on click listener for the update income button to update the income information to the database by calling the updateIncome method
         */
        updateincomebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Please wait...", true, false);
                updateIncome();
            }
        });
    }

    /*
    * method to update the income information to the database. The app will ask the user to enter the income description, amount and date. The user will be able to update the income.
     */
    private void updateIncome() {
        if (updateincomedesc.getText().toString().isEmpty() || updateincomeamount.getText().toString().isEmpty() || updateincomedate.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Please fill all field", Toast.LENGTH_SHORT).show();
            loading.dismiss();
        } else {
            mApiService.updateIncome(IncomeActivity.selectedincomeList.getPendapatan_Id(), updateincomedesc.getText().toString(), BigInteger.valueOf(Long.parseLong(updateincomeamount.getText().toString())), updateincomedate.getText().toString(), IncomeUpdateCatChooseActivity.selectedincomecatList.getKatPend_Id())
                    .enqueue(new retrofit2.Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                loading.dismiss();
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getString("message").equals("Income Updated")) {
                                        Toast.makeText(mContext, "Update Income Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(mContext, IncomeActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(mContext, "Update Income Failed", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                loading.dismiss();
                                Toast.makeText(mContext, "Update Income Failed", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(mContext, "Update Income Failed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            loading.dismiss();
                            Toast.makeText(mContext, "Update Income Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}