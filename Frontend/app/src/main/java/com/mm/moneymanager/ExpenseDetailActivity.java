package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

/**
 * The type Expense detail activity.
 * This class is used to show the detail of the expense. The user can click on the expense to edit the expense. The user can also delete the expense. The user can also click on the back button to go back to the previous page.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class ExpenseDetailActivity extends AppCompatActivity {

    /**
     * The Expensedeskripsi.
     */
    TextView expensedeskripsi, /**
     * The Expensejumlah.
     */
    expensejumlah, /**
     * The Expensetanggal.
     */
    expensetanggal, /**
     * The Expensejenis.
     */
    expensejenis;
    /**
     * The Expenseedit.
     */
    Button expenseedit, /**
     * The Expensedelete.
     */
    expensedelete;

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

    /**
     * The constant dateFormatInput.
     */
    public static final DateFormat dateFormatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    /**
     * The constant dateFormatOutput.
     */
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

        /*
         * Add Date format to Expense Date
         * Show all infromation in TextView from database
         */
        try {
            Date date = dateFormatInput.parse(ExpenseActivity.selectedexpenseList.getTanggal());

            expensedeskripsi.setText(ExpenseActivity.selectedexpenseList.getDeskripsi());
            expensejumlah.setText(String.valueOf(ExpenseActivity.selectedexpenseList.getJumlah()));
            expensetanggal.setText(dateFormatOutput.format(date));
            expensejenis.setText(ExpenseActivity.selectedexpenseList.getJenis());
        } catch (ParseException e) {
                e.printStackTrace();
            }

        /*
         * Edit Expense button
         * Open UpdateExpenseActivity
         * and send data to UpdateExpenseActivity
         */
        expenseedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ExpenseUpdateCatChooseActivity.class);
                startActivity(intent);
            }
        });

        /*
         * Delete Expense button
         * Show alert dialog to confirm delete
         * if user click yes, delete data from database
         * and back to ExpenseActivity
         * if user click no, back to ExpenseActivity
         * and show toast message
         */
        expensedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(true);
                builder.setTitle("Are You Sure?");
                builder.setMessage("You want to delete this expense?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            /*
                                * Delete Expense
                                * Show loading dialog
                             */
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                loading = ProgressDialog.show(mContext, null, "Please wait...", true, false);
                                deleteExpense();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        }

    /*
        * Delete Expense from database
        * Show toast message if delete success or failed
        * Close loading dialog
        * Back to ExpenseActivity
     */
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