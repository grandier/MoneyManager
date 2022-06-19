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
 * The type Income add cat choose activity.
 * This class is used to choose the income category. The app will ask the user to enter the income category name. The user will be able to add the income category.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class IncomeAddCatChooseActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<IncomeCategoryList> itemsAdapter;
    private ArrayList<IncomeCategoryList> categoryincome = new ArrayList<>();
    private ListView lvItems;
    /**
     * The constant selectedincomecatList.
     */
    public static IncomeCategoryList selectedincomecatList = null;
    private Button backtoincome;
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
        setContentView(R.layout.activity_income_add_cat_choose);
        backtoincome = (Button) findViewById(R.id.backtoincomechoose);
        lvItems = (ListView) findViewById(R.id.listIncomeCatChoose);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        getListRequest();

        /*
        * back button to income activity
        * if user click back button, it will go to income activity
         */
        backtoincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IncomeAddCatChooseActivity.this, IncomeActivity.class);
                startActivity(intent);
            }
        });

        /*
        * Listview item click listener to get the selected item category and send to next activity
        * if user click on item category
         */
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedincomecatList = categoryincome.get(position);
                Intent intent = new Intent(IncomeAddCatChooseActivity.this, IncomeAddActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Gets list request
     * if user click on add category button, it will go to income add activity based on the selected category from listview.
     * @return the list request of income category
     *
     */
    void getListRequest() {
        Gson gson = new Gson();
        mApiService.showcategoryincomeRequest()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray jsonArray = jsonRESULTS.getJSONArray("categoryincome");
                                if (jsonRESULTS.getString("message").equals("Category Found")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    categoryincome = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<IncomeCategoryList>>() {}.getType());
                                    itemsAdapter = new ArrayAdapter<IncomeCategoryList>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, categoryincome);
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