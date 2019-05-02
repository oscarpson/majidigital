package com.joslabs.majidigitalapp;


import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;




public class SnackClass {

    public static void setSnackBar(View coordinatorLayout, String snackTitle) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, snackTitle, 4000);//TODO check snack duration
        ViewGroup viewGroup= (ViewGroup) snackbar.getView();
        viewGroup.setBackgroundResource(R.color.green);

        TextView txtv = (TextView) viewGroup.findViewById(com.google.android.material.R.id.snackbar_text);
        txtv.setGravity(Gravity.FILL_HORIZONTAL);

        snackbar.show();
    }

    public static  void setErrorSnackbar(View coordinatorLayout, String snackTitle) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, snackTitle, 5000);
        ViewGroup viewGroup= (ViewGroup) snackbar.getView();
        viewGroup.setBackgroundResource(R.color.red);

        TextView txtv = (TextView) viewGroup.findViewById(com.google.android.material.R.id.snackbar_text);
        txtv.setGravity(Gravity.FILL_HORIZONTAL);

        snackbar.show();
    }
}
