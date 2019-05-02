package com.joslabs.majidigitalapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CarouselView carouselView;
    ImageView regimg,qcimg,testimg,lotassignimg,disconnectimg,detailimg,noticeimg,fudimg,chatsimg;
    int[] sampleImages = {R.drawable.truck, R.drawable.meterb,R.drawable.cash};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        regimg=findViewById(R.id.regimg);
        detailimg=findViewById(R.id.detailimg);
        fudimg=findViewById(R.id.fudimg);
        disconnectimg=findViewById(R.id.disconnectimg);
        qcimg=findViewById(R.id.qcimg);
        testimg=findViewById(R.id.testimg);

        regimg.setOnClickListener(this);
        detailimg.setOnClickListener(this);
        fudimg.setOnClickListener(this);
        disconnectimg.setOnClickListener(this);
        qcimg.setOnClickListener(this);
        testimg.setOnClickListener(this);

        initCollapsingToolbar();
    }
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        //collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent ints;
        switch(v.getId()){
            case R.id.regimg:
                 ints=new Intent(getApplicationContext(),RegisterUserActivity.class);
                startActivity(ints);
                break;
            case R.id.detailimg:
                 ints=new Intent(getApplicationContext(),MeterReadingActivity.class);
                startActivity(ints);
                break;
            case R.id.fudimg:
                ints=new Intent(getApplicationContext(),MeterStatusActivity.class);
                startActivity(ints);
                break;
            case R.id.disconnectimg:
                ints=new Intent(getApplicationContext(),DisconnectionActivity.class);
                startActivity(ints);
                break;
            case R.id.qcimg:
                ints=new Intent(getApplicationContext(),ZoneMapActivity.class);
                startActivity(ints);
                break;
            case R.id.testimg:
                ints=new Intent(getApplicationContext(),MpesaPayment.class);
                startActivity(ints);
                break;
        }

    }
}
