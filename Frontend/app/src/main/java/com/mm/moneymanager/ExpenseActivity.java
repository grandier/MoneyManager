package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mm.moneymanager.model.ExpenseList;
import com.mm.moneymanager.model.IncomeList;
import com.mm.moneymanager.request.BaseApiService;
import com.mm.moneymanager.request.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<ExpenseList> itemsAdapter;
    private ArrayList<ExpenseList> showExpense = new ArrayList<>();
    private ListView lvItems;
    public static ExpenseList selectedexpenseList = null;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        Button gotoexpensecat = (Button) findViewById(R.id.gotoexpensecat);
        Button gotoincome = (Button) findViewById(R.id.gotoincome);
        Button expenseadd = (Button) findViewById(R.id.expenseadd);
        ImageView profile = (ImageView) findViewById(R.id.profileexpense);
        lvItems = (ListView) findViewById(R.id.listExpense);
        getListRequest();

        gotoincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, IncomeActivity.class));
            }
        });

        gotoexpensecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ExpenseCategoryActivity.class));
            }
        });

        expenseadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ExpenseAddCatChooseActivity.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ProfileActivity.class));
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedexpenseList = showExpense.get(position);
                Intent intent = new Intent(mContext, ExpenseDetailActivity.class);
                //intent.putExtra("income", showIncome.get(position));
                startActivity(intent);
            }
        });
    }

    void getListRequest() {
        Gson gson = new Gson();
        mApiService.showexpenseRequest()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray jsonArray = jsonRESULTS.getJSONArray("showExpense");
                                if (jsonRESULTS.getString("message").equals("Expense Found")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    showExpense = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<ExpenseList>>() {}.getType());
                                    itemsAdapter = new ArrayAdapter<ExpenseList>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, showExpense);
                                    lvItems.setAdapter(itemsAdapter);
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("User not logged in");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "Sudah Login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }
}