package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mm.moneymanager.request.BaseApiService;
import com.mm.moneymanager.request.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseDetailActivity extends AppCompatActivity {

    TextView expensedeskripsi, expensejumlah, expensetanggal, expensejenis;
    Button expenseedit, expensedelete;

    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    public static final DateFormat dateFormatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    public static final SimpleDateFormat dateFormatOutput = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);
        expensedeskripsi = (TextView) findViewById(R.id.expensedeskripsi);
        expensejumlah = (TextView) findViewById(R.id.expensejumlah);
        expensetanggal = (TextView) findViewById(R.id.expensetanggal);
        expensejenis = (TextView) findViewById(R.id.expensejenis);
        expenseedit = (Button) findViewById(R.id.editexpense);
        expensedelete = (Button) findViewById(R.id.deleteexpense);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        try {
            Date date = dateFormatInput.parse(ExpenseActivity.selectedexpenseList.getTanggal());

            expensedeskripsi.setText(ExpenseActivity.selectedexpenseList.getDeskripsi());
            expensejumlah.setText(String.valueOf(ExpenseActivity.selectedexpenseList.getJumlah()));
            expensetanggal.setText(dateFormatOutput.format(date));
            expensejenis.setText(ExpenseActivity.selectedexpenseList.getJenis());
        } catch (ParseException e) {
                e.printStackTrace();
            }

        expenseedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ExpenseUpdateCatChooseActivity.class);
                startActivity(intent);
            }
        });

        expensedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Please wait...", true, false);
                deleteExpense();
            }
        });
        }

    private void deleteExpense() {
        mApiService.deleteExpense(ExpenseActivity.selectedexpenseList.getPengeluaran_Id())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("message").equals("Expense Deleted")) {
                                    Toast.makeText(mContext, "Expense Deleted", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mContext, ExpenseActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(mContext, "Expense Deleted Failed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}