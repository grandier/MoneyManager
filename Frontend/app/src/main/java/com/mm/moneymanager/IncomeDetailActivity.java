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

public class IncomeDetailActivity extends AppCompatActivity {

    TextView incomedeskripsi, incomejumlah, incometanggal, incomejenis;
    Button incomeedit, incomedelete;

    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    public static final DateFormat dateFormatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    public static final SimpleDateFormat dateFormatOutput = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        incomedeskripsi = (TextView) findViewById(R.id.incomedeskripsi);
        incomejumlah = (TextView) findViewById(R.id.incomejumlah);
        incometanggal = (TextView) findViewById(R.id.incometanggal);
        incomejenis = (TextView) findViewById(R.id.incomejenis);
        incomeedit = (Button) findViewById(R.id.editincome);
        incomedelete = (Button) findViewById(R.id.deleteincome);

        try {
            Date date = dateFormatInput.parse(IncomeActivity.selectedincomeList.getTanggal());

            incomedeskripsi.setText(IncomeActivity.selectedincomeList.getDeskripsi());
            incomejumlah.setText(String.valueOf(IncomeActivity.selectedincomeList.getJumlah()));
            assert date != null;
            incometanggal.setText(dateFormatOutput.format(date));
            incomejenis.setText(IncomeActivity.selectedincomeList.getJenis());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        incomeedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, IncomeUpdateCatChooseActivity.class));
            }
        });

        incomedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Please wait...", true, false);
                incomedelete();
            }
        });
    }

    private void incomedelete() {
        mApiService.deleteIncome(IncomeActivity.selectedincomeList.getPendapatan_Id())
            .enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        loading.dismiss();
                        try {
                            JSONObject jsonRESULTS = new JSONObject(response.body().string());
                            if (jsonRESULTS.getString("message").equals("Income Deleted")) {
                                Toast.makeText(mContext, "Income Deleted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, IncomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(mContext, "Income Deleted Failed", Toast.LENGTH_SHORT).show();
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