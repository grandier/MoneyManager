package com.mm.moneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
 * The type Login activity.
 * This class is used to login the user and get the token for the user to access the rest of the app without being logged in. The user can also register for the app.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * The Loginusername.
     */
    EditText loginusername;
    /**
     * The Loginpassword.
     */
    EditText loginpassword;
    /**
     * The Loginbutton.
     */
    ImageView loginbutton;
    /**
     * The Loginsignup.
     */
    TextView loginsignup;
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
        setContentView(R.layout.activity_login);

        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        initComponents();

    }

    /**
     * Init components.
     */
    private void initComponents(){
        loginusername = (EditText) findViewById(R.id.loginusername);
        loginpassword = (EditText) findViewById(R.id.loginpassword);
        loginbutton = (ImageView) findViewById(R.id.loginbutton);
        loginsignup = (TextView) findViewById(R.id.loginsignup);

        /*
        * Login button click event to login user and validate the credentials and redirect to dashboard activity if valid credentials are given else display error message to user and keep the user in login activity
         */
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

        /*
        * Signup button click event to redirect to signup activity if user is not registered else display error message to user and keep the user in login activity
         */
        loginsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });
    }

    /*
    * Request login.
    * This method is used to request login to the server and validate the credentials and redirect to dashboard activity if valid credentials are given else display error message to user and keep the user in login activity and close the loading dialog
    * checked username and password is not empty and not null and then request login to the server
     */
    private void requestLogin(){
        mApiService.loginRequest(loginusername.getText().toString(), loginpassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("message").equals("success")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    Toast.makeText(mContext, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();
                                    // String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                    Intent intent = new Intent(mContext, IncomeActivity.class);
                                    // intent.putExtra("result_nama", nama);
                                    startActivity(intent);
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }
}