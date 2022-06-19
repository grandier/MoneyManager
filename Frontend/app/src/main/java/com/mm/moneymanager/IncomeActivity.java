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

/**
 * The type Income activity.
 * This class is to show the list of income and the user can click on the income to edit the income and delete the income.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class IncomeActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<IncomeList> itemsAdapter;
    private ArrayList<IncomeList> showIncome = new ArrayList<>();
    private ListView lvItems;
    /**
     * The constant selectedincomeList.
     */
    public static IncomeList selectedincomeList = null;
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
        setContentView(R.layout.activity_income);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        Button gotoincomecat = (Button) findViewById(R.id.gotoincomecat);
        Button gotoexpense = (Button) findViewById(R.id.gotoexpense);
        Button addincome = (Button) findViewById(R.id.incomeadd);
        ImageView profile = (ImageView) findViewById(R.id.profileincome);
        lvItems = (ListView) findViewById(R.id.listIncome);
        getListRequest();

        /*
        * Listener for back to expense activity
        * if user click back button, it will go to expense activity
         */
        gotoexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ExpenseActivity.class));
            }
        });

        /*
        * Add Listener to Button (Go to Income Category Activity)
        * if user click this button, it will go to income category activity
         */
        gotoincomecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, IncomeCategoryActivity.class));
            }
        });

        /*
        * Add Listener to Button (Add Income)
        * if user click this button, it will go to add income activity
         */
        addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, IncomeAddCatChooseActivity.class));
            }
        });

        /*
        * Add Listener to ImageView (Profile)
        * if user click this image view, it will go to profile activity
         */
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ProfileActivity.class));
            }
        });

        /*
        * Add Listener to ListView (Income List)
        * if user click this list view, it will go to income detail activity and show income detail information based on selected income
         */
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedincomeList = showIncome.get(position);
                Intent intent = new Intent(mContext, IncomeDetailActivity.class);
                //intent.putExtra("income", showIncome.get(position));
                startActivity(intent);
            }
        });
    }

    /**
     * Gets list request.
     * get income list from database and show it in list view
     */
    void getListRequest() {
        Gson gson = new Gson();
        mApiService.showincomeRequest()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray jsonArray = jsonRESULTS.getJSONArray("showIncome");
                                if (jsonRESULTS.getString("message").equals("Income Found")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    showIncome = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<IncomeList>>() {}.getType());
                                    itemsAdapter = new ArrayAdapter<IncomeList>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, showIncome);
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
