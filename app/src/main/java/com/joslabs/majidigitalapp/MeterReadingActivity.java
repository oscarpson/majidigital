package com.joslabs.majidigitalapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MeterReadingActivity extends AppCompatActivity {
    Button btnreg;
    EditText cid,creading,consumed,unitp,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_reading);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        cid=findViewById(R.id.edt_customer_id);
        creading=findViewById(R.id.edt_current_reading);
        consumed=findViewById(R.id.edt_consumed_unit);
        unitp=findViewById(R.id.edt_unit_price);

        btnreg=findViewById(R.id.btnsave);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=cid.getText().toString();
                String reading=creading.getText().toString();
                String consm=consumed.getText().toString();
                String unitprice=unitp.getText().toString();

                final ProgressDialog loading = ProgressDialog.show(v.getContext(), "Fetching...", "Please wait...", false, false);
                Call<ResponseCallback> response=RestClient.getApiService().add_reading(id,reading,consm,unitprice,reading,"20");
                response.enqueue(new Callback<ResponseCallback>() {
                    @Override
                    public void onResponse(Call<ResponseCallback> call, Response<ResponseCallback> responseb) {
                        loading.dismiss();
                        Log.e("msg",responseb.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<ResponseCallback> call, Throwable t) {
loading.dismiss();
                        Log.e("errorretro",t.getMessage());
                    }
                });
            }
        });
    }

}
