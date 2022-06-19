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
 * The type Update expense category activity.
 * This class is used to update the expense category. The app will ask the user to enter the expense category description, amount and date. The user will be able to update the expense category.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class UpdateExpenseCategoryActivity extends AppCompatActivity {

    private EditText expensecatname;
    private Button updateexpensecat;

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
        setContentView(R.layout.activity_update_expense_category);
        expensecatname = (EditText) findViewById(R.id.updatecategoryexpense);
        updateexpensecat = (Button) findViewById(R.id.catexpenseupdatebutton);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        updateexpensecat.setEnabled(false);

        /*
        * TextWatcher for expensecatname EditText to enable or disable the update expense category button.
         */
        expensecatname.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                updateexpensecat.setEnabled(s.toString().trim().length() != 0);
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
        * OnClickListener for updateexpensecat button. This will update the expense category by calling the updateExpenseCategory method.
         */
        updateexpensecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateexpensecat();
            }
        });
    }

    /*
    * This method will update the expense category. The app will ask the user to enter the expense category description. The user will be able to update the expense category.
     */
    private void updateexpensecat() {
        mApiService.updateCategoryExpense(ExpenseCategoryActivity.selectedexpensecatList.getKatPeng_Id(), expensecatname.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("message").equals("Expense Updated")) {
                                    Toast.makeText(mContext, "Update Category Expense Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mContext, ExpenseCategoryActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(mContext, "Update Category Expense Failed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "Update Category Expense Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, "Update Income Category Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}