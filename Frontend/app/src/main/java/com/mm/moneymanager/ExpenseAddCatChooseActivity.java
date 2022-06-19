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
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mm.moneymanager.model.ExpenseCategoryList;
import com.mm.moneymanager.model.IncomeCategoryList;
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

/**
 * The type Expense add cat choose activity.
 * This class is used to add the expense category. The app will ask the user to enter the expense category name. The user will be able to add the expense category.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class ExpenseAddCatChooseActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<ExpenseCategoryList> itemsAdapter;
    private ArrayList<ExpenseCategoryList> categoryexpense = new ArrayList<>();
    private ListView lvItems;
    /**
     * The constant selectedexpensecatList.
     */
    public static ExpenseCategoryList selectedexpensecatList = null;
    private Button backtoexpense;
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
        setContentView(R.layout.activity_expense_add_cat_choose);
        backtoexpense = (Button) findViewById(R.id.backtoexpensechoose);
        lvItems = (ListView) findViewById(R.id.listExpenseCatChoose);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        getListRequest();

        /*
        * Back to expense activity
         */
        backtoexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenseAddCatChooseActivity.this, ExpenseActivity.class);
                startActivity(intent);
            }
        });

        /*
        * Add Listener to Button (Go to Expense Add Activity)
         */
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedexpensecatList = categoryexpense.get(position);
                Intent intent = new Intent(ExpenseAddCatChooseActivity.this, ExpenseAddActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Gets list request.
     * Get list of expense category from database and show it in listview (ExpenseAddCatChooseActivity)
     * @return the list request of expense category
     */
    void getListRequest() {
        Gson gson = new Gson();
        mApiService.showcategoryexpenseRequest()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray jsonArray = jsonRESULTS.getJSONArray("categoryexpense");
                                if (jsonRESULTS.getString("message").equals("Category Found")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    categoryexpense = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<ExpenseCategoryList>>() {}.getType());
                                    itemsAdapter = new ArrayAdapter<ExpenseCategoryList>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, categoryexpense);
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