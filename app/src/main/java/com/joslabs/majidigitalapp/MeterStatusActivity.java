package com.joslabs.majidigitalapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MeterStatusActivity extends AppCompatActivity {
RecyclerView rcv_status;
List<UserModel>userModelList;
MeterStatusAdapter meterStatusAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_status);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rcv_status=findViewById(R.id.rcv_status);
        userModelList=new ArrayList<>();
        rcv_status.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcv_status.setHasFixedSize(true);
        rcv_status.setAdapter(null);
        getData();

    }

    private void getData() {
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching...", "Please wait...", false, false);
        Call<UserCallback> responseCallbackCall=RestClient.getApiService().user_list();
        responseCallbackCall.enqueue(new Callback<UserCallback>() {
            @Override
            public void onResponse(Call<UserCallback> call, Response<UserCallback> response) {
                loading.dismiss();
                meterStatusAdapter=new MeterStatusAdapter(getApplicationContext(),response.body().getUsers());
                rcv_status.setAdapter(meterStatusAdapter);
                Log.e("listuser",response.body().getUsers()+"emp");

            }

            @Override
            public void onFailure(Call<UserCallback> call, Throwable t) {
                loading.dismiss();
                Log.e("error",t.getMessage());
            }
        });
    }

}
