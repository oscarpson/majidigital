package com.joslabs.majidigitalapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MpesaPayment extends AppCompatActivity {
MpesaAdapter mpesaAdapter;
List<MpesaModel> mpesaModelList;
RecyclerView rcv_mpesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpesa_payment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rcv_mpesa=findViewById(R.id.rcv_mpesa);
        mpesaModelList=new ArrayList<>();
        rcv_mpesa.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcv_mpesa.setHasFixedSize(true);
        rcv_mpesa.setAdapter(null);
        getData();

    }

    private void getData() {
        Gson gson= new Gson();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://95.216.48.57/waterbilling/index.php/master/add_payments/all_payments/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService apiService=retrofit.create(ApiService.class);
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching...", "Please wait...", false, false);
        Call<MpesaCallback> responseCallbackCall=apiService.mpesa_list();
        responseCallbackCall.enqueue(new Callback<MpesaCallback>() {
            @Override
            public void onResponse(Call<MpesaCallback> call, Response<MpesaCallback> response) {
                loading.dismiss();
                mpesaAdapter=new MpesaAdapter(getApplicationContext(),response.body().getMpesaModelList());
                rcv_mpesa.setAdapter(mpesaAdapter);
                Log.e("listuser",response.body().getMpesaModelList()+"emp");
            }

            @Override
            public void onFailure(Call<MpesaCallback> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }

}
