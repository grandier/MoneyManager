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
 * The type Update expense activity.
 * This class is used to update the expense. The app will ask the user to enter the expense description, amount and date. The user will be able to update the expense.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class UpdateExpenseActivity extends AppCompatActivity {

    private EditText updateexpensedesc;
    private EditText updateexpenseamount;
    private EditText updateexpensedate;
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
        setContentView(R.layout.activity_update_expense);
        updateexpensedesc = (EditText) findViewById(R.id.updateexpensedesc);
        updateexpenseamount = (EditText) findViewById(R.id.updateexpenseamount);
        updateexpensedate = (EditText) findViewById(R.id.updateexpensedate);
        Button updateexpensebutton = (Button) findViewById(R.id.expenseupdatebutton);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        updateexpensebutton.setEnabled(false);

        /*
        * add text watcher to the edit text to check if the user has entered the required data or not. If the user has entered the required data, the button will be enabled.
         */
        updateexpensedesc.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateexpensebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        * add text watcher to the edit text to check if the user has entered the required data or not. If the user has entered the required data, the button will be enabled.
         */
        updateexpenseamount.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                updateexpensebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        * add text watcher to the edit text to check if the user has entered the required data or not. If the user has entered the required data, the button will be enabled.
         */
        updateexpensedate.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                updateexpensebutton.setEnabled(s.toString().trim().length() != 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
        * add click listener to the button to update the expense. The app will ask the user to enter the expense description, amount and date. The user will be able to update the expense.
        * by calling the update expense method to update the expense.
         */
        updateexpensebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Please wait...", true, false);
                updateExpense();
            }
        });
    }

    /*
    * This method is used to update the expense. The app will ask the user to enter the expense description, amount and date. The user will be able to update the expense.
    * if the user has entered the required data, the app will call the update expense method to update the expense. If the user has not entered the required data, the app will show a toast message.
     */
    private void updateExpense() {
        if (updateexpensedesc.getText().toString().isEmpty() || updateexpenseamount.getText().toString().isEmpty() || updateexpensedate.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Please fill all field", Toast.LENGTH_SHORT).show();
            loading.dismiss();
        } else {
            mApiService.updateExpense(ExpenseActivity.selectedexpenseList.getPengeluaran_Id(), updateexpensedesc.getText().toString(), BigInteger.valueOf(Long.parseLong(updateexpenseamount.getText().toString())), updateexpensedate.getText().toString(), ExpenseUpdateCatChooseActivity.selectedexpensecatList.getKatPeng_Id())
                    .enqueue(new retrofit2.Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                loading.dismiss();
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getString("message").equals("Expense Updated")) {
                                        Toast.makeText(mContext, "Update Expense Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(mContext, ExpenseActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(mContext, "Update Expense Failed", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                loading.dismiss();
                                Toast.makeText(mContext, "Update Expense Failed", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(mContext, "Update Expense Failed", Toast.LENGTH_SHORT).show();
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