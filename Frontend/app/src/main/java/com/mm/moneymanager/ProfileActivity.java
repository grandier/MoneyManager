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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * The type Profile activity.
 * This activity is used show the user's profile based on the token that is generated when the user logs in.
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 * @since 2022-06-18
 */
public class ProfileActivity extends AppCompatActivity {

    private TextView username;
    private Button logout;
    private Button deleteprofile;
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
        setContentView(R.layout.activity_profile);
        username = (TextView) findViewById(R.id.profileusername);
        deleteprofile = (Button) findViewById(R.id.deleteprofile);
        logout = (Button) findViewById(R.id.logoutbtn);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        getuser();

        /*
        * This button is used to delete the user's profile. It will ask the user if they are sure they want to delete their profile. If they click yes, the user's profile will be deleted. If they click no, the user will be redirected back to the profile page.
         */
        deleteprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(true);
                builder.setTitle("Are You Sure?");
                builder.setMessage("You want to delete this account?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                loading = ProgressDialog.show(mContext, null, "Please wait...", true, false);
                                deleteuser();
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

        /*
        * This button is used to logout the user. If user click logout button, it will go to login activity.
         */
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Gets user.
     * This method is used to get the user's profile based on the token that is generated when the user logs in. It will get the user's username and display it on the profile page.
     */
    public void getuser() {
        mApiService.showuserRequest().enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        JSONArray result = jsonRESULTS.getJSONArray("user");
                        username.setText(result.getJSONObject(0).getString("username"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Deleteuser.
     * This method is used to delete the user's profile. It will delete the user's profile based on the token that is generated when the user logs in.
     * if the user's profile is successfully deleted, it will go to login activity. If the user's profile is not successfully deleted, it will show a toast message.
     */
    public void deleteuser() {
        mApiService.deleteUser().enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("message").equals("User deleted")) {
                            Toast.makeText(mContext, "User Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "User Deleted Failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(mContext, "Gagal Delete", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}